package org.tgid.testetgid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tgid.testetgid.domain.DTO.ClienteDTO;
import org.tgid.testetgid.domain.DTO.EmpresaDTO;
import org.tgid.testetgid.services.EmpresaService;

@RestController
public class EmpresaController {
    @Autowired
    private EmpresaService service;

    @PostMapping("/empresa/novo")
    public String novaEmpresa (@RequestBody EmpresaDTO dto){
        return this.service.novaEmpresa(dto);
    }
    @PostMapping("/empresa/novoNome")
    public String atualizarRazaoSocial (@RequestParam String nome,@RequestParam String cnpj){
        return this.service.atualizarRazaoSocial(nome,cnpj);
    }
    @PostMapping("/empresa/novoEmail")
    public String atualizarEmailEmpresa (@RequestParam String emailNovo,@RequestParam String cnpj){
        return this.service.atualizarEmail(emailNovo,cnpj);
    }
}
