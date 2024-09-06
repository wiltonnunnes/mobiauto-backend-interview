package com.wilton.mobiauto_backend_interview.entities;

import com.wilton.mobiauto_backend_interview.validations.CNPJConstraint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity(name = "revenda")
@Table(name = "revendas")
public class Revenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private Long id;

    @CNPJConstraint
    private String cnpj;

    private String nome;

    @OneToMany(mappedBy = "revenda")
    private Set<Associacao> associacoes;

    public Revenda() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Associacao> getAssociacoes() {
        return associacoes;
    }

    public void setAssociacoes(Set<Associacao> associacoes) {
        this.associacoes = associacoes;
    }
}
