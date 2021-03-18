package han.oose.dea.dao;

import han.oose.dea.domain.Token;
import han.oose.dea.exceptions.PersistenceException;

import javax.sql.DataSource;

public interface ITokenDAO {
    Token generateAndSaveToken(String username) throws PersistenceException;

    void setDataSource(DataSource dataSource);
}
