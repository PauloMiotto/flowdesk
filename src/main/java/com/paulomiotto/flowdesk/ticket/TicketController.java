package com.paulomiotto.flowdesk.ticket;

import jakarta.validation.Valid;
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
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest request) {
        Ticket ticket = new Ticket(
                request.getTitle(),
                request.getDescription()
        );

        return toResponse(repository.save(ticket));
    }

    /*
    @GetMapping
    public List<TicketResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }*/
    @GetMapping
    public List<TicketResponse> findAll(@RequestParam(defaultValue = "0") int page, //Paginação
                                        @RequestParam(defaultValue = "5") int size) {

        return repository.findAll(
                        org.springframework.data.domain.PageRequest.of(page, size)
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public TicketResponse findById(@PathVariable Long id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        return toResponse(ticket);
    }

    @PatchMapping("/{id}/status")
    public TicketResponse updateStatus(@PathVariable Long id,
                                       @RequestParam TicketStatus status) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        ticket.setStatus(status);

        return toResponse(repository.save(ticket));
    }

    private TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedAt()
        );
    }
}