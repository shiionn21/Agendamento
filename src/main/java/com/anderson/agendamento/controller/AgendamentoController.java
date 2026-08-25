package com.anderson.agendamento.controller;

import com.anderson.agendamento.model.Agendamento;
import com.anderson.agendamento.service.AgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public Agendamento agendar(@RequestBody Agendamento agendamento) {
        return service.agendar(agendamento);
    }

    @GetMapping
    public List<Agendamento> listar() {
        return service.listar();
    }
    
}