package han.oose.dea.services;

import han.oose.dea.dao.ITokenDAO;
import han.oose.dea.dao.IUserDAO;
import han.oose.dea.domain.Token;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthServiceTest {
    private User user;

    @Mock
    private IUserDAO userDAO;

    @Mock
    private ITokenDAO tokenDAO;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setup() {
        this.authService = new AuthService();
        openMocks(this);

        user = new User();
    }

    @Test
    public void loginSuccess() throws PersistenceException {
        // Arrange
        Token expectedToken = new Token();
        when(userDAO.getUser(user)).thenReturn(user);
        when(tokenDAO.generateAndSaveToken(any())).thenReturn(expectedToken);

        // Act
        Token actualToken = authService.login(user);

        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void loginFailure() throws PersistenceException {
        // Arrange
        when(userDAO.getUser(user)).thenReturn(null);

        // Act
        Token actualToken = authService.login(user);

        // Assert
        assertNull(actualToken);
    }
}