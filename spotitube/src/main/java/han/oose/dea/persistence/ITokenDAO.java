package han.oose.dea.persistence;

import han.oose.dea.domain.Token;
import han.oose.dea.exceptions.PersistenceException;

import javax.sql.DataSource;

public interface ITokenDAO {
    Token generateAndSaveToken(String username) throws PersistenceException;

    Token getToken(String token);

    void setDataSource(DataSource dataSource);
}
