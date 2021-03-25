package han.oose.dea.persistence;

import han.oose.dea.persistence.mappers.UserSQLDataMapper;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;

import javax.enterprise.inject.Default;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserMySQLDAO implements IUserDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Inject
    private UserSQLDataMapper userSQLDataMapper;

    // Queries
    private static final String AUTH_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String GET_USER_QUERY = "SELECT * FROM user WHERE username = ?";

    @Override
    public User authenticate(User user) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return userSQLDataMapper.toDomainObject(resultSet);
            }
        } catch (SQLException e) {
            throw new PersistenceException("No database connection!");
        }

        return null;
    }

    @Override
    public User getUser(String username) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USER_QUERY);

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return userSQLDataMapper.toDomainObject(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
