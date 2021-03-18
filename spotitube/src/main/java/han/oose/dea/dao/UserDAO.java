package han.oose.dea.dao;

import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;

import javax.enterprise.inject.Default;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAO implements IUserDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    // Queries
    private static final String AUTH_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";

    @Override
    public User getUser(User user) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(resultSet.getString("username"),
                        resultSet.getString("password"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenceException("No database connection!");
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
