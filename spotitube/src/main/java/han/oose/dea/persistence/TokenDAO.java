package han.oose.dea.persistence;

import han.oose.dea.domain.Token;
import han.oose.dea.exceptions.PersistenceException;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

@Default
public class TokenDAO implements ITokenDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    // Queries
    private static final String INSERT_TOKEN_QUERY = "INSERT INTO token (username, token, expiry_date) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE token = ?, expiry_date = ?;";

    @Override
    public Token generateAndSaveToken(String username) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_TOKEN_QUERY);

            // TODO: Date does not work!
            Token token = new Token(username, UUID.randomUUID().toString(), new Date(LocalDate.now().plusDays(30).toEpochDay()));

            statement.setString(1, token.getUsername());
            statement.setString(2, token.getToken());
            statement.setDate(3, token.getExpiry_date());
            statement.setString(4, token.getToken());
            statement.setDate(5, token.getExpiry_date());


            statement.executeUpdate();

            return token;

        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
