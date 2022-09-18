package cinema.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Client {
    private final UUID token = UUID.randomUUID();
    private final Seat ticket;

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public Client(Seat ticket) {
        this.ticket = ticket;
    }
}
