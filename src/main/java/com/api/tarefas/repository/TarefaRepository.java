package com.api.tarefas.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.tarefas.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	List<Tarefa> findAllByOrderByOrdemAsc();

	@Query("SELECT COALESCE(MAX(t.ordem), 0) + 1 FROM Tarefa t")
    Integer obterProximaOrdem();

	boolean existsByNome(String nome);
}
