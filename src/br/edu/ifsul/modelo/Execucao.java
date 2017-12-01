/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dalpizzol
 */
@Entity
@Table(name = "execucao")
public class Execucao implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_execucao", sequenceName = "seq_execucao_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_execucao", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "O BPM não pode ser nulo")
    @Min(value = 1, message = "O BPM não pode ser menor que 1")
    @Column(name = "bpm", nullable = false)
    private Integer bpm;
    
    @NotNull(message = "O início não pode ser nulo")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inicio", nullable = true)
    private Calendar inicio = new GregorianCalendar();
    
    @NotNull(message = "O fim não pode se rnulo")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fim", nullable = true)
    private Calendar fim = new GregorianCalendar();
    
    @Column(name = "feedback", columnDefinition = "text", nullable = true)
    private String feedback;
    
    @ManyToOne
    @JoinColumn(
            name = "exercicio",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_exercicio_id")
    )
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(
            name = "estudo",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_estudo_id")
    )
    private Estudo estudo;    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFim() {
        return fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Estudo getEstudo() {
        return estudo;
    }

    public void setEstudo(Estudo estudo) {
        this.estudo = estudo;
    }    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Execucao other = (Execucao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
