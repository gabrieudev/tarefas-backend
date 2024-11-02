package com.api.tarefas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.tarefas.exception.EntityNotFoundException;
import com.api.tarefas.exception.TaskAlreadyExistsException;
import com.api.tarefas.model.Tarefa;
import com.api.tarefas.repository.TarefaRepository;
import com.api.tarefas.rest.dto.TarefaDTO;

@Service
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Transactional
    public TarefaDTO salvar(TarefaDTO tarefaDTO) {
        if (tarefaRepository.existsByNome(tarefaDTO.getNome())) {
            throw new TaskAlreadyExistsException("Tarefa com esse nome já existe!");
        }
        tarefaDTO.setOrdem(tarefaRepository.obterProximaOrdem());
        return tarefaRepository.save(tarefaDTO.toModel()).toDto();
    }

    @Transactional(readOnly = true)
    public TarefaDTO buscarPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        return tarefa.toDto();
    }

    @Transactional
    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    @Transactional
    public TarefaDTO atualizar(TarefaDTO tarefaDTO) {
        TarefaDTO tarefaDoBanco = buscarPorId(tarefaDTO.getId());

        if (tarefaDTO.getOrdem() != tarefaDoBanco.getOrdem()) {
            int novaOrdem = tarefaDTO.getOrdem();

            List<Tarefa> tarefas = tarefaRepository.findAllByOrderByOrdemAsc();

            tarefas.remove(tarefaDoBanco.toModel());

            if (novaOrdem < 1) novaOrdem = 1;
            if (novaOrdem > tarefas.size() + 1) novaOrdem = tarefas.size() + 1;

            tarefas.add(novaOrdem - 1, tarefaDoBanco.toModel());

            for (int i = 0; i < tarefas.size(); i++) {
                tarefas.get(i).setOrdem(i + 1);
            }

            tarefaRepository.saveAll(tarefas);
        }
        
        return tarefaRepository.save(tarefaDTO.toModel()).toDto();
    }

    @Transactional
    public List<TarefaDTO> buscarTodas() {
        return tarefaRepository.findAllByOrderByOrdemAsc().stream()
            .map(Tarefa::toDto).toList();
    }

}