/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dalpizzol
 */
@Entity
@Table(name = "exercicio")
public class Exercicio implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_exercicio", sequenceName = "seq_exercicio_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_exercicio", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser branco")
    @Length(max = 80, message = "O nome deve ter no máximo {max} caracteres")
    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name = "autor", length = 40, nullable = true)
    private String autor;
    
    @Column(name = "descricao", columnDefinition = "text", nullable = true)
    private String desccricao;

    @ManyToOne
    @JoinColumn(
            name = "tecnica",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tecnica_id")
    )
    private Tecnica tecnica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }    
    
    public String getDesccricao() {
        return desccricao;
    }

    public void setDesccricao(String desccricao) {
        this.desccricao = desccricao;
    }

    public Tecnica getTecnica() {
        return tecnica;
    }

    public void setTecnica(Tecnica tecnica) {
        this.tecnica = tecnica;
    }    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Exercicio other = (Exercicio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
