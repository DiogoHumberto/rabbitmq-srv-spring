package com.study.java.srvbcopgto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.java.srvbcopgto.Enum.StatusPgto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PgtoDto {

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @NotBlank
    @Size(max=19)
    @JsonProperty("numero")
    private String numero;

    @NotNull
    @Positive
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotBlank
    @Size(max=100)
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("dtExpiracao")
    @Future
    private LocalDateTime dtExpiracao;

    @NotBlank
    @Size(min=3, max=3)
    @JsonProperty("codigo")
    private String codigo;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private StatusPgto status;

    @NotNull
    @JsonProperty("tpPgtoId")
    private Long tpPgtoId;

    //@NotNull
    private UUID pedidoUuid;

}
