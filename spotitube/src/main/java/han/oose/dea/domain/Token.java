package han.oose.dea.domain;

import java.sql.Date;

public class Token {
    private String username;
    private String token;
    private Date expiryDate;

    public Token() {
    }

    public Token(String username, String token, Date expiryDate) {
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
