package com.api.tarefas.model;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.api.tarefas.rest.dto.TarefaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "Tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private BigDecimal custo;

    @Column(nullable = false)
    private Date dataLimite;

    @Column(nullable = false)
    private Integer ordem;
    
    public TarefaDTO toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TarefaDTO.class);
    }
}
