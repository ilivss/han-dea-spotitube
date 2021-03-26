package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TokenMySQLDataMapperTest {
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private TokenMySQLDataMapper tokenMySQLDataMapper;

    @BeforeEach
    void setUp() {
        tokenMySQLDataMapper = new TokenMySQLDataMapper();
        openMocks(this);
    }

    @Test
    void toDomainObjectSuccess() {
        try {
            // Arrange
            String expectedUsername = "username";
            String expectedToken = "token";
            Date expectedExpiryDate = new java.sql.Date(System.currentTimeMillis());

            // Instruct Mocks
            when(resultSet.getString("username")).thenReturn(expectedUsername);
            when(resultSet.getString("token")).thenReturn(expectedToken);
            when(resultSet.getDate("expiry_date")).thenReturn(expectedExpiryDate);

            // Act
            Token token = tokenMySQLDataMapper.toDomainObject(resultSet);

            // Assert
            assertEquals(expectedUsername, token.getUsername());
            assertEquals(expectedToken, token.getToken());
            assertEquals(expectedExpiryDate, token.getExpiryDate());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void toDomainObjectFail() throws SQLException {
        // Arrange

        // Instruct Mocks
        when(resultSet.getString(any())).thenThrow(new SQLException());
        when(resultSet.getDate(any())).thenThrow(new SQLException());

        // Act
        Token token = tokenMySQLDataMapper.toDomainObject(resultSet);

        // Assert
        assertNull(token);
    }
}