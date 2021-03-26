package han.oose.dea.persistence.mappers;

import han.oose.dea.domain.Playlist;
import han.oose.dea.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserSQLDataMapperTest {

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserSQLDataMapper userSQLDataMapper;

    @BeforeEach
    void setUp() {
        userSQLDataMapper = new UserSQLDataMapper();
        openMocks(this);
    }

    @Test
    void toDomainObjectSuccess() {
        try {
            // Arrange
            String expectedUsername = "username";
            String expectedPassword = "password";

            // Instruct Mocks
            when(resultSet.getString("username")).thenReturn(expectedUsername);
            when(resultSet.getString("password")).thenReturn(expectedPassword);

            // Act
            User user = userSQLDataMapper.toDomainObject(resultSet);

            // Assert
            assertEquals(expectedUsername, user.getUsername());
            assertEquals(expectedPassword, user.getPassword());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void toDomainObjectFailure() throws SQLException {
        // Arrange

        // Instruct Mocks
        when(resultSet.getInt(any())).thenThrow(new SQLException());
        when(resultSet.getString(any())).thenThrow(new SQLException());

        // Act
        User user = userSQLDataMapper.toDomainObject(resultSet);

        // Assert
        assertNull(user);
    }
}