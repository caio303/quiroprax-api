package com.quiroprax.api.model;

import com.quiroprax.api.model.enums.StatusAgendamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "agendamento")
@Entity
public class Agendamento {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

	@JoinColumn(name = "paciente_id")
	@ManyToOne(targetEntity = Paciente.class, fetch = FetchType.LAZY)
	private Paciente paciente;

	@JoinColumn(name = "horario_disponivel_id")
	@ManyToOne(targetEntity = HorarioDisponivel.class, fetch = FetchType.LAZY)
	private HorarioDisponivel horarioDisponivel;

	@Enumerated(EnumType.ORDINAL)
	private StatusAgendamento status;

	public Agendamento(Long id, Paciente paciente, StatusAgendamento status, HorarioDisponivel horarioDisponivel) {
		this.id = id;
		this.paciente = paciente;
		this.status = status;
		this.horarioDisponivel = horarioDisponivel;
	}

	public Agendamento() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public StatusAgendamento getStatus() {
		return status;
	}

	public void setStatus(StatusAgendamento status) {
		this.status = status;
	}

	public HorarioDisponivel getHorarioDisponivel() {
		return horarioDisponivel;
	}

	public void setHorarioDisponivel(HorarioDisponivel horarioDisponivel) {
		this.horarioDisponivel = horarioDisponivel;
	}
}