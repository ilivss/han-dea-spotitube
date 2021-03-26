package han.oose.dea.rest.mappers;

import han.oose.dea.domain.Token;
import han.oose.dea.rest.dto.TokenDTO;

public class TokenRestMapper implements IRestMapper<TokenDTO, Token> {
    @Override
    public TokenDTO toDTO(Token token) {
        return new TokenDTO(token.getUsername(), token.getToken());
    }
}
