package cinema.controller;

import cinema.simple.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MainController {
    private final Cinema cinema = new Cinema(9, 9);
    private ArrayList<Seat> seatsBuy = new ArrayList<>();
    private ArrayList<Client> clientList = new ArrayList<>();
    private Map<UUID, Seat> map = new HashMap<>();
    private Statistics stat = new Statistics(cinema.getTotal_rows() * cinema.getTotal_columns());

    @GetMapping("/seats")
    public Cinema seats() {
        return this.cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat cell) {
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

    @PostMapping("/return")
    public ResponseEntity<?> token (@RequestBody Token token) {
        if (map.containsKey(token.getToken())) {
            stat.setCurrent_income(map.get(token.getToken()).getPrice(), 0);
            stat.setNumber_of_available_seats(1);
            stat.setNumber_of_purchased_tickets(0);
            return new ResponseEntity<>(Map.of("returned_ticket", map.get(token.getToken())), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats (@RequestParam(required = false) String password) {
        if (password != null && password.equals("super_secret")) {
            return new ResponseEntity<>(stat, HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
