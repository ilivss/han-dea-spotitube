package han.oose.dea.persistence;

import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;

import javax.sql.DataSource;

public interface IUserDAO {
    User authenticate(User user) throws PersistenceException;

    User getUser(String username);

    void setDataSource(DataSource dataSource);
}
