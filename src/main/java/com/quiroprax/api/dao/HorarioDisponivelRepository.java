package com.quiroprax.api.dao;

import com.quiroprax.api.model.HorarioDisponivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long> {

    Page<HorarioDisponivel> findAllByAtivoTrueAndStatus(Integer status, Pageable pageable);
}
