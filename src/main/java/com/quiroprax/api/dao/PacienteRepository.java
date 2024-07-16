package com.quiroprax.api.dao;

import com.quiroprax.api.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    Optional<Paciente> findByIdAndAtivoTrue(Long id);
    Optional<Paciente> findByNomeAndAtivoTrue(String nome);
    Optional<Paciente> findByEmailAndAtivoTrue(String email);
    Optional<Paciente> findByCpfAndAtivoTrue(String cpf);

    boolean existsByCpfAndAtivoTrue(String cpf);
    boolean existsByEmailAndAtivoTrue(String email);
    boolean existsByEmailAndIdNot(String email, Long pacienteId);
    boolean existsByCpfAndIdNot(String cpf, Long pacienteId);
}
