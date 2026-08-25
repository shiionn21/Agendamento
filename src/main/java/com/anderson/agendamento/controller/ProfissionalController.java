package com.anderson.agendamento.controller;

import com.anderson.agendamento.model.Profissional;
import com.anderson.agendamento.repository.ProfissionalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalRepository repository;
    
    public ProfissionalController(ProfissionalRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Profissional criar(@RequestBody Profissional profissional) {
        return repository.save(profissional);
    }

    @GetMapping
    public List<Profissional> listar() {
        return repository.findAll();
    }

}


