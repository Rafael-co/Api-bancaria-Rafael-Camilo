package org.tgid.testetgid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tgid.testetgid.domain.DTO.ClienteDTO;
import org.tgid.testetgid.services.ClienteService;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente/novo")
    public String novoCliente (@RequestBody ClienteDTO dto){
        return this.clienteService.novoCliente(dto);
    }
    @PostMapping("/cliente/novoNome")
    public String atualizarNomeCliente (@RequestParam String novoNome,@RequestParam String cpf){
        return this.clienteService.atualizarNomeCliente(novoNome,cpf);
    }
    @PostMapping("/cliente/novoEmail")
    public String atualizarEmailCliente (@RequestParam String novoEmail,@RequestParam String cpf){
        return this.clienteService.atualizarEmailCliente(novoEmail,cpf);
    }

    @PostMapping("/cliente/saque")
    public String saque(@RequestParam Integer valor , @RequestParam String cpf, @RequestParam String cnpj){
       return this.clienteService.saque(valor,cpf,cnpj);
    }
    @PostMapping("/cliente/deposito")
    public String deposito(@RequestParam Integer valor , @RequestParam String cpf, @RequestParam String cnpj){
        return this.clienteService.deposito(valor,cpf,cnpj);
    }
}
