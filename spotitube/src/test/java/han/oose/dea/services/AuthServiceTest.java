package han.oose.dea.services;

import han.oose.dea.dao.UserDAO;
import han.oose.dea.domain.User;
import han.oose.dea.services.dto.TokenDTO;
import han.oose.dea.services.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    private AuthService authService;
    private UserDTO requestUserDTO;

    @BeforeEach
    public void setup() {
        this.authService = new AuthService();
        this.requestUserDTO = new UserDTO();
        requestUserDTO.user = "john";
        requestUserDTO.password = "doe";
    }

    @Test
    public void loginSuccess() {
        // Arrange
        User expectedUser = new User(0, this.requestUserDTO.user, this.requestUserDTO.password);

        UserDAO mockUserDAO = mock(UserDAO.class);
        when(mockUserDAO.authenticate(this.requestUserDTO.user, this.requestUserDTO.password)).thenReturn(expectedUser);
        this.authService.setUserDAO(mockUserDAO);

        // Act
        Response response = this.authService.login(this.requestUserDTO);
        TokenDTO tokenDTO = (TokenDTO) response.getEntity();

        // Assert
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(this.requestUserDTO.user, tokenDTO.user);
    }

    @Test
    public void loginFailed() {
        // Arrange
        UserDAO mockUserDAO = mock(UserDAO.class);
        when(mockUserDAO.authenticate(this.requestUserDTO.user, this.requestUserDTO.password)).thenReturn(null);
        this.authService.setUserDAO(mockUserDAO);

        // Act
        Response response = this.authService.login(this.requestUserDTO);

        // Assert
        assertEquals(Response.Status.UNAUTHORIZED, response.getStatusInfo());
    }
}