package com.anderson.agendamento.controller;

import com.anderson.agendamento.model.Cliente;
import com.anderson.agendamento.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return repository.save(cliente);

    }
    
    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }
    
}
