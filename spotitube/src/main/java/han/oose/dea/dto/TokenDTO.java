package han.oose.dea.dto;

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
