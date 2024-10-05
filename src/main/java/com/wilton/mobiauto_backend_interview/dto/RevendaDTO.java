package com.wilton.mobiauto_backend_interview.dto;

import com.wilton.mobiauto_backend_interview.validation.CNPJConstraint;

public class RevendaDTO {
    
    @CNPJConstraint
    private String cnpj;

    private String nome;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
