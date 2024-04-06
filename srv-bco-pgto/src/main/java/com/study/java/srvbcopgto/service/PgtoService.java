package com.study.java.srvbcopgto.service;

import com.study.java.srvbcopgto.Enum.StatusPgto;
import com.study.java.srvbcopgto.Model.PgtoModel;
import com.study.java.srvbcopgto.dto.PgtoDto;
import com.study.java.srvbcopgto.repository.PgtoRepositoy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PgtoService {

    private final ModelMapper modelMapper;

    private final PgtoRepositoy repository;

    public PgtoDto criarPagamento(PgtoDto dto) {
        PgtoModel pagamento = modelMapper.map(dto, PgtoModel.class);
        pagamento.setStatus(StatusPgto.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PgtoDto.class);
    }

    public PgtoDto obterPorId(UUID uuid) {
        PgtoModel pagamento = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PgtoDto.class);
    }
}
