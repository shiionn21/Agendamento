package com.anderson.agendamento.repository;

import com.anderson.agendamento.model.Agendamento; 
import com.anderson.agendamento.model.Profissional;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByProfissionalAndDataHora(Profissional profissional, LocalDateTime dataHora);
}