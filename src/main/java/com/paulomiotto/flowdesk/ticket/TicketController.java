package com.paulomiotto.flowdesk.ticket;

import com.paulomiotto.flowdesk.common.PageResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;
    private final TicketMapper mapper;

    public TicketController(TicketService service, TicketMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest request) {
        Ticket ticket = service.create(
                request.getTitle(),
                request.getDescription()
        );

        return mapper.toResponse(ticket);
    }

    @GetMapping
    public PageResponse<TicketResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        var ticketPage = service.findAll(page, size);

        var content = ticketPage.getContent()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return new PageResponse<>(
                content,
                ticketPage.getNumber(),
                ticketPage.getSize(),
                ticketPage.getTotalElements(),
                ticketPage.getTotalPages()
        );
    }

    @GetMapping("/{id}")
    public TicketResponse findById(@PathVariable Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @PatchMapping("/{id}/status")
    public TicketResponse updateStatus(@PathVariable Long id,
                                       @RequestParam TicketStatus status) {
        return mapper.toResponse(service.updateStatus(id, status));
    }
}

