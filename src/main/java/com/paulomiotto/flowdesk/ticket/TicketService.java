package com.paulomiotto.flowdesk.ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket create(String title, String description) {
        Ticket ticket = new Ticket(title, description);
        return repository.save(ticket);
    }

    public Page<Ticket> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Ticket findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    public Ticket updateStatus(Long id, TicketStatus status) {
        Ticket ticket = findById(id);
        ticket.setStatus(status);
        return repository.save(ticket);
    }
}
