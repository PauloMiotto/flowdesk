package com.paulomiotto.flowdesk.ticket;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest request) {
        Ticket ticket = service.create(
                request.getTitle(),
                request.getDescription()
        );

        return toResponse(ticket);
    }

    @GetMapping
    public List<TicketResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size) {
        return service.findAll(page, size)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public TicketResponse findById(@PathVariable Long id) {
        return toResponse(service.findById(id));
    }

    @PatchMapping("/{id}/status")
    public TicketResponse updateStatus(@PathVariable Long id,
                                       @RequestParam TicketStatus status) {
        return toResponse(service.updateStatus(id, status));
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