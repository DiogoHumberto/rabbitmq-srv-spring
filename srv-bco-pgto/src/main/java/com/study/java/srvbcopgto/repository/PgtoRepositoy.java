package com.study.java.srvbcopgto.repository;

import com.study.java.srvbcopgto.Model.PgtoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PgtoRepositoy extends JpaRepository<PgtoModel, UUID> {

}
