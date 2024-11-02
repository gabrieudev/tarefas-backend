package com.api.tarefas.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tarefas.rest.dto.TarefaDTO;
import com.api.tarefas.service.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/tarefas")
public class TarefaController {
    
    private TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(
        summary = "Salva uma tarefa", 
        description = "Salva uma nova tarefa no banco de dados", 
        tags = { "Tarefa" }
    )
    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody TarefaDTO tarefaDTO) {
        tarefaService.salvar(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
        summary = "Atualiza uma tarefa", 
        description = "Atualiza os dados de uma tarefa no banco de dados", 
        tags = { "Tarefa" }
    )
    @PutMapping
    public ResponseEntity<TarefaDTO> atualizar(@Valid @RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.atualizar(tarefaDTO));
    }

    @Operation(
        summary = "Obtém uma tarefa", 
        description = "Obtém uma tarefa por seu id", 
        tags = { "Tarefa" }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.buscarPorId(id));
    }

    @Operation(
        summary = "Obtém tarefas", 
        description = "Obtém todas as tarefas ordenadas", 
        tags = { "Tarefa" }
    )
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarTodas() {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.buscarTodas());
    }

    @Operation(
        summary = "Deleta uma tarefa", 
        description = "Exclui uma tarefa do banco de dados por seu id",
        tags = { "Tarefa" }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
