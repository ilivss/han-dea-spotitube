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
    private static final String UPDATE_TOKEN_QUERY = "UPDATE user SET token = ? WHERE id = ?";

    public User getUser(String username, String password) {
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

    public void updateToken(User user, String token) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TOKEN_QUERY);

            statement.setString(1, token);
            statement.setInt(2, user.getId());
            statement.execute();
            user.setToken(token); // Set token of user object.
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
