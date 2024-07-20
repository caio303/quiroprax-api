package com.quiroprax.api.dao;

import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Page<Agendamento> findAllByPaciente(Paciente paciente, Pageable pageable);

}
