package cinema.service;

import cinema.model.Client;
import cinema.model.Seat;
import cinema.model.Statistics;
import cinema.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Service
public class TicketService {
    private final ArrayList<Seat> seatsBuy;
    private final Map<UUID, Seat> map;
    private final ArrayList<Client> clientList;
    private final Statistics stat;

    @Autowired
    public TicketService(ArrayList<Seat> seatsBuy, Map<UUID, Seat> map, ArrayList<Client> clientList, Statistics stat) {
        this.seatsBuy = seatsBuy;
        this.map = map;
        this.clientList = clientList;
        this.stat = stat;
    }

    public ResponseEntity<?> buyTicket(Seat cell){
        if (cell.getRow() >= 1 && cell.getRow() <= 9 && cell.getColumn() >= 1 && cell.getColumn() <= 9) {
            if (seatsBuy.contains(cell))
                return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            seatsBuy.add(cell);
            cell.setPrice();
            stat.setCurrent_income(cell.getPrice(), 1);
            stat.setNumber_of_available_seats(0);
            stat.setNumber_of_purchased_tickets(1);
            clientList.add(new Client(cell));
            map.put(clientList.get(clientList.size() - 1).getToken(), clientList.get(clientList.size() - 1).getTicket());
            return new ResponseEntity<>(clientList.get(clientList.size() - 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> returnTicket(Token token) {
        if (map.containsKey(token.getToken())) {
            stat.setCurrent_income(map.get(token.getToken()).getPrice(), 0);
            stat.setNumber_of_available_seats(1);
            stat.setNumber_of_purchased_tickets(0);
            return new ResponseEntity<>(Map.of("returned_ticket", map.get(token.getToken())), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> statistics(String password) {
        if (password != null && password.equals("super_secret")) {
            return new ResponseEntity<>(stat, HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
