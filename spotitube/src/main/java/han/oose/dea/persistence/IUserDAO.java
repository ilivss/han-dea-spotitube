package han.oose.dea.persistence;

import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;

import javax.sql.DataSource;

public interface IUserDAO {
    User getUser(User user) throws PersistenceException;

    void setDataSource(DataSource dataSource);
}
