/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dalpizzol
 */
@Entity
@Table(name = "estudo")
public class Estudo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_estudo", sequenceName = "seq_estudo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_estudo", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    
    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_estudo", nullable = false)
    private Calendar data;
    
    @Column(name = "instrumento", nullable = true, length = 20)
    private String instrumento;
    
    @Column(name = "anotacoes", nullable = true, columnDefinition = "text")
    private String anotacoes;        
    
    @OneToMany(mappedBy = "estudo", cascade = CascadeType.ALL, 
           orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Execucao> execucoes = new ArrayList<>();

    public Estudo() {
    }

    public void adicionarExecucao(Execucao obj){
        obj.setEstudo(this);
        this.execucoes.add(obj);
    }
    
    public void removerExecucao(int idx){
        this.execucoes.remove(idx);
    } 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public List<Execucao> getExecucoes() {
        return execucoes;
    }

    public void setExecucoes(List<Execucao> execucoes) {
        this.execucoes = execucoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Estudo other = (Estudo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }
    
}
