package han.oose.dea.dao;

import han.oose.dea.domain.User;

import javax.enterprise.inject.Default;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    DataSource dataSource;

    public User getUser(String username, String password) {
      return new User(123, "john", "doe");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
