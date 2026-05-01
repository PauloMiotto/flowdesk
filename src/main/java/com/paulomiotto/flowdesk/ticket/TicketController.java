package com.paulomiotto.flowdesk.ticket;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository repository;

    public TicketController(TicketRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public Ticket create(@Valid @RequestBody CreateTicketRequest request) {

        Ticket ticket = new Ticket(
                request.getTitle(),
                request.getDescription()
        );

        return repository.save(ticket);
    }


    @GetMapping
    public List<Ticket> list() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }
}
