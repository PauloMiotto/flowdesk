package com.paulomiotto.flowdesk.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
public class HealthController {
    @GetMapping("/health")
    public String health() {

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String dataFormatada = agora.format(formatador);

        return "OKAY - " + dataFormatada;
    }
}
