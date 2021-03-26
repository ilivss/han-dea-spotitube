package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Token;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMySQLDataMapper implements IDataMapper <ResultSet, Token> {
    @Override
    public Token toDomainObject(ResultSet resultSet) {
        try {
            return new Token(
                    resultSet.getString("username"),
                    resultSet.getString("token"),
                    resultSet.getDate("expiry_date")
                    );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
