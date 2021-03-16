package han.oose.dea.dao;

import han.oose.dea.domain.User;

import javax.enterprise.inject.Default;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Default
public class UserDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    // Queries
    private static final String AUTH_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";

    public User authenticate(String username, String password) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
