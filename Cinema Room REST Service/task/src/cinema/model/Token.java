package cinema.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
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
