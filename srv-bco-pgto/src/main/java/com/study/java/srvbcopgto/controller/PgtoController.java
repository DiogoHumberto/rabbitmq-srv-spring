package com.study.java.srvbcopgto.controller;

import com.study.java.srvbcopgto.dto.PgtoDto;
import com.study.java.srvbcopgto.service.PgtoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
@RequiredArgsConstructor
public class PgtoController {

    private final PgtoService service;

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PgtoDto dto, UriComponentsBuilder uriBuilder){

        PgtoDto respDto = service.criarPagamento(dto);

        URI url = uriBuilder.path("/pagamentos/{id}").buildAndExpand(respDto.getUuid()).toUri();

        rabbitTemplate.send("pagamento.concluido", new Message(("criando pagamento uuid " + respDto.getUuid()).getBytes()));
       // rabbitTemplate.convertAndSend("pagamentos.ex","", respDto);

        return ResponseEntity.created(url).body(respDto);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PgtoDto> detalhar(@PathVariable @NotNull UUID uuid) {

        return ResponseEntity.ok(service.obterPorId(uuid));
    }

}
