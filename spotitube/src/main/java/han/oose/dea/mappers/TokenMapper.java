package han.oose.dea.mappers;

import han.oose.dea.domain.Token;
import han.oose.dea.dto.TokenDTO;

public class TokenMapper implements IMapper<TokenDTO, Token> {
    @Override
    public TokenDTO toDTO(Token token) {
        return new TokenDTO(token.getUsername(), token.getToken());
    }

    @Override
    public Token fromDTO(TokenDTO tokenDTO) {
        return null;
    }
}
