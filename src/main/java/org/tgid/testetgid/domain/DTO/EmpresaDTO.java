package org.tgid.testetgid.domain.DTO;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmpresaDTO {
    @NotBlank(message = "A razão social não pode ser em branco")
    private String razaoSocial;

    @CNPJ(message = "Por favor insira um cnpj inválido")
    private String cnpj;

    @Email(message = "Insira um email válido")
    private String email;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
