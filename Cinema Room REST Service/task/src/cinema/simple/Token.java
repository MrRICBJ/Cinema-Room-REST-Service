package cinema.simple;

import java.util.UUID;

public class Token {
    private UUID token;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Token() {}

    public Token(UUID token) {
        this.token = token;
    }
}
