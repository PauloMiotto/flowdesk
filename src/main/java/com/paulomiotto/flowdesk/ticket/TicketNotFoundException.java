package com.paulomiotto.flowdesk.ticket;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long id) {
        super("Ticket not found with id: " + id);
    }
}
