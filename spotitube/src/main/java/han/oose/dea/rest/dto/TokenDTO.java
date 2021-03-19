package han.oose.dea.rest.dto;

public class TokenDTO {
    public String user;
    public String token;

    public TokenDTO() {
    }

    public TokenDTO(String user, String token) {
        this.user = user;
        this.token = token;
    }
}
