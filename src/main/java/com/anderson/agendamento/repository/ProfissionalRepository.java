package com.anderson.agendamento.repository;

import com.anderson.agendamento.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}