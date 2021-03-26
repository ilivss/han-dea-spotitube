package han.oose.dea.rest.mappers;

import han.oose.dea.domain.User;
import han.oose.dea.rest.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRestMapperTest {

    private UserRestMapper userRestMapper;

    @BeforeEach
    void setUp() {
        userRestMapper = new UserRestMapper();
    }

    @Test
    void toDTO() {
        // Arrange
        User user = new User("username", "password");

        // Act
        UserDTO userDTO = userRestMapper.toDTO(user);

        // Assert
        assertEquals(user.getUsername(), userDTO.user);
        assertEquals(user.getPassword(), userDTO.password);
    }

    @Test
    void toDomainObject() {
        // Arrange
        UserDTO userDTO = new UserDTO("username", "password");

        // Act
        User user = userRestMapper.toDomainObject(userDTO);

        // Assert
        assertEquals(userDTO.user, user.getUsername());
        assertEquals(userDTO.password, user.getPassword());
    }
}