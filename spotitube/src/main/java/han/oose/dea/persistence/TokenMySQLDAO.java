package han.oose.dea.persistence;

import han.oose.dea.domain.Token;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.persistence.mappers.TokenMySQLDataMapper;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;

import java.util.UUID;

@Default
public class TokenMySQLDAO implements ITokenDAO {
    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Inject
    private TokenMySQLDataMapper tokenDataMapper;

    // Queries
    private static final String INSERT_TOKEN_QUERY = "INSERT INTO token (username, token, expiry_date) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE token = ?, expiry_date = ?;";
    private static final String GET_TOKEN_QUERY = "SELECT * FROM token WHERE token = ?";

    @Override
    public Token generateAndSaveToken(String username) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_TOKEN_QUERY);

            // TODO: Date does not work!
            Token token = new Token(username, UUID.randomUUID().toString(), new java.sql.Date(System.currentTimeMillis()));

            statement.setString(1, token.getUsername());
            statement.setString(2, token.getToken());
            statement.setString(3, token.getExpiryDate().toString());
            statement.setString(4, token.getToken());
            statement.setDate(5, token.getExpiryDate());


            statement.execute();

            return token;

        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    @Override
    public Token getToken(String token) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_TOKEN_QUERY);

            statement.setString(1, token);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return tokenDataMapper.toDomainObject(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  null;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
