package com.anderson.agendamento.service;

import com.anderson.agendamento.model.Agendamento;
import com.anderson.agendamento.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {
    private final AgendamentoRepository repository;
    
    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public Agendamento agendar(Agendamento agendamento) {
        boolean ocupado = repository.existsByProfissionalAndDataHora(
            agendamento.getProfissional(), agendamento.getDataHora());


        if (ocupado) {
            throw new IllegalArgumentException("O profissional já está ocupado nesse horário.");
        }
        return repository.save(agendamento);
    }

    public List<Agendamento> listar() {
        return repository.findAll();
    }
}