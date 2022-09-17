package cinema.simple;

import java.util.UUID;

public class Client {
    private UUID token = UUID.randomUUID();
    private Seat ticket;

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
