package han.oose.dea.rest;


import han.oose.dea.domain.Token;
import han.oose.dea.domain.User;
import han.oose.dea.rest.dto.TokenDTO;
import han.oose.dea.rest.dto.UserDTO;
import han.oose.dea.exceptions.TestException;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.rest.mappers.TokenRestMapper;
import han.oose.dea.rest.mappers.UserRestMapper;
import han.oose.dea.services.AuthService;
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
public class AuthEndpointTest {
    private User user;
    private UserDTO userDTO;
    private Token token;
    private TokenDTO tokenDTO;

    @Mock
    private AuthService authService;

    @Mock
    private UserRestMapper userRestMapper;

    @Mock
    private TokenRestMapper tokenMapper;

    @InjectMocks
    private AuthEndpoint authEndpoint;

    @BeforeEach
    public void setup() {
        this.authEndpoint = new AuthEndpoint();
        openMocks(this);

        this.user = new User();
        this.userDTO = new UserDTO();
        this.token = new Token();
        this.tokenDTO = new TokenDTO();

        when(userRestMapper.toDomainObject(userDTO)).thenReturn(user);
        when(tokenMapper.toDTO(token)).thenReturn(tokenDTO);
    }

    @Test
    public void loginSuccess() throws PersistenceException, TestException {
        // Arrange
        when(authService.login(user)).thenReturn(token);

        // Act
        Response response = this.authEndpoint.login(userDTO);

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(tokenDTO, response.getEntity());
    }

    @Test
    public void loginFailure() throws PersistenceException {
        // Arrange
        when(authService.login(user)).thenReturn(null);

        // Act & Assert
        assertThrows(TestException.class, () -> authEndpoint.login(userDTO));
    }
}
