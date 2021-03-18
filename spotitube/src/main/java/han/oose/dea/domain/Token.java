package han.oose.dea.domain;

import java.sql.Date;

public class Token {
    private String username;
    private String token;
    private Date expiry_date;

    public Token() {
    }

    public Token(String username, String token, Date expiry_date) {
        this.username = username;
        this.token = token;
        this.expiry_date = expiry_date;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }
}
