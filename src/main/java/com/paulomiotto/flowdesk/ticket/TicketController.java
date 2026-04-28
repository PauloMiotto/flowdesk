package com.paulomiotto.flowdesk.ticket;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository repository;

    public TicketController(TicketRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Ticket create(@RequestParam String title,
                         @RequestParam String description) {

        Ticket ticket = new Ticket(title, description);
        return repository.save(ticket);
    }

    @GetMapping
    public List<Ticket> list() {
        return repository.findAll();
    }
}
