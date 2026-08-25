package com.anderson.Agendamento;

import com.anderson.agendamento.model.Agendamento;
import com.anderson.agendamento.model.Profissional;
import com.anderson.agendamento.repository.ProfissionalRepository;
import com.anderson.agendamento.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AgendamentoServiceTest {

    @Autowired
    private AgendamentoService service;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Test
    void naoDevePermitirDoisAgendamentosNoMesmoHorario() {
        // Cria um barbeiro
        Profissional barbeiro = profissionalRepository.save(new Profissional());
        LocalDateTime horario = LocalDateTime.of(2026, 8, 25, 14, 0);

        // Primeiro agendamento: deve passar
        Agendamento primeiro = new Agendamento();
        primeiro.setProfissional(barbeiro);
        primeiro.setDataHora(horario);
        service.agendar(primeiro);

        // Segundo no mesmo horário: a regra TEM que barrar
        Agendamento segundo = new Agendamento();
        segundo.setProfissional(barbeiro);
        segundo.setDataHora(horario);

        assertThrows(IllegalArgumentException.class, () -> service.agendar(segundo));
    }
}