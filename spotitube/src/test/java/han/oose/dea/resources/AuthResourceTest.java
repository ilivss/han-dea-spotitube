package han.oose.dea.resources;


import han.oose.dea.domain.Token;
import han.oose.dea.domain.User;
import han.oose.dea.dto.TokenDTO;
import han.oose.dea.dto.UserDTO;
import han.oose.dea.exceptions.AuthException;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.mappers.TokenMapper;
import han.oose.dea.mappers.UserMapper;
import han.oose.dea.services.AuthService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class AuthResourceTest {
    private User user;
    private UserDTO userDTO;
    private Token token;
    private TokenDTO tokenDTO;

    @Mock
    private AuthService authService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private TokenMapper tokenMapper;

    @InjectMocks
    private AuthResource authResource;

    @BeforeEach
    public void setup() {
        this.authResource = new AuthResource();
        openMocks(this);

        this.user = new User();
        this.userDTO = new UserDTO();
        this.token = new Token();
        this.tokenDTO = new TokenDTO();

        when(userMapper.fromDTO(userDTO)).thenReturn(user);
        when(tokenMapper.toDTO(token)).thenReturn(tokenDTO);
    }

    @Test
    public void loginSuccess() throws PersistenceException, AuthException {
        // Arrange
        when(authService.login(user)).thenReturn(token);

        // Act
        Response response = this.authResource.login(userDTO);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(tokenDTO, response.getEntity());
    }

    @Test
    public void loginFailure() throws PersistenceException {
        // Arrange
        when(authService.login(user)).thenReturn(null);

        // Act & Assert
        assertThrows(AuthException.class, () -> authResource.login(userDTO));
    }
}
