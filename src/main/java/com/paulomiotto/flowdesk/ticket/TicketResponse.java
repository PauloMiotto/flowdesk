package com.paulomiotto.flowdesk.ticket;

import java.time.LocalDateTime;

public class TicketResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final TicketStatus status;
    private final LocalDateTime createdAt;

    public TicketResponse(Long id, String title, String description,
                          TicketStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TicketStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}