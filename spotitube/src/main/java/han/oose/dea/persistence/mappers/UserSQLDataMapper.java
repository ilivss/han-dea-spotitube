package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserSQLDataMapper implements IDataMapper<ResultSet, User> {
    @Override
    public User toDomainObject(ResultSet resultSet) {
        try {
            return new User(resultSet.getString("username"), resultSet.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
