package com.api.tarefas.rest.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.api.tarefas.model.Tarefa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    @Schema(
        description = "Identificador único da tarefa (ignore para operação POST)", 
        example = "1"
    )
    private Long id;

    @Schema(
        description = "Nome da tarefa", 
        example = "Desenvolver API de tarefas"
    )
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(
        description = "Custo da tarefa", 
        example = "100.00"
    )
    @NotNull(message = "Custo é obrigatório")
    private BigDecimal custo;

    @Schema(
        description = "Data limite para conclusão da tarefa", 
        example = "2024-12-31"
    )
    @NotNull(message = "Data limite é obrigatória")
    private Date dataLimite;

    @Schema(
        description = "Ordem de exibição da tarefa (ignore para operação POST)", 
        example = "1"
    )
    private Integer ordem;

    public Tarefa toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Tarefa.class);
    }
}
