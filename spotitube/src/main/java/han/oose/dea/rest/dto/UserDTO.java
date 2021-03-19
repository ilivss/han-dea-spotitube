package han.oose.dea.rest.dto;

public class UserDTO {
    public String user;
    public String password;

    public UserDTO() {
    }

    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
