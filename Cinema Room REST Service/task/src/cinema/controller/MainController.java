package cinema.controller;

import cinema.model.*;
import cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final Cinema cinema;
    private final TicketService ticketService;

    @Autowired
    public MainController(Cinema cinema, TicketService ticketService) {
        this.cinema = cinema;
        this.ticketService = ticketService;
    }

    @GetMapping("/seats")
    public Cinema seats() {
        return this.cinema;
    }



    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat cell) {
        return ticketService.buyTicket(cell);
    }
    @PostMapping("/return")
    public ResponseEntity<?> token (@RequestBody Token token) {
        return ticketService.returnTicket(token);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats (@RequestParam(required = false) String password) {
        return ticketService.statistics(password);
    }
}
