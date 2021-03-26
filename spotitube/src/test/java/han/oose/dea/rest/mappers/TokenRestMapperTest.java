package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Token;
import han.oose.dea.rest.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class TokenRestMapperTest {
    private TokenRestMapper tokenRestMapper;

    @BeforeEach
    void setUp() {
        tokenRestMapper = new TokenRestMapper();
    }

    @Test
    void toDTO() {
        // Arrange
        Token token = new Token("username", "token", new java.sql.Date(System.currentTimeMillis()));

        // Act
        TokenDTO tokenDTO = tokenRestMapper.toDTO(token);

        // Assert
        assertEquals(token.getToken(), tokenDTO.token);
        assertEquals(token.getUsername(), tokenDTO.user);
    }
}