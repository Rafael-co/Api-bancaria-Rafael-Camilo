package org.tgid.testetgid.domain.DTO;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClienteDTO {

    @NotBlank(message = "O nome não pode ser em branco")
    private String nome;

    @CPF(message = "Por favor insira um CPF válido")
    private String cpf;

    @Email(message = "Por favor insira um email válido")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
