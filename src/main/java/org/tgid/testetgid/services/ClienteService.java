package org.tgid.testetgid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tgid.testetgid.domain.Cliente;
import org.tgid.testetgid.domain.DTO.ClienteDTO;
import org.tgid.testetgid.domain.Empresa;
import org.tgid.testetgid.repositories.ClienteRepository;

import java.util.Optional;

@Service
public class ClienteService {



    @Autowired
    private ClienteRepository repository ;

    @Autowired
    private EmpresaService empresaService;


    public Cliente buscarClientePorCpf(String cpf){
        Optional<Cliente> cliente = this.repository.findByCpf(cpf);
        if(cliente.isEmpty()){
            throw new RuntimeException("cliente não encontrada!");
        }else{

            return cliente.get();
        }
    }

    public String novoCliente(ClienteDTO dto){
        Cliente novoCliente = new Cliente(dto.getNome(),dto.getCPF(),dto.getEmail());
        repository.save(novoCliente);
        return  "Novo cliente criado com sucesso";
    }

    public String atualizarNomeCliente(String nome,String cpf){
        Cliente clienteAtualizado = this.buscarClientePorCpf(cpf);
        clienteAtualizado.setNome(nome);
        repository.save(clienteAtualizado);
        return "Nome do cliente atualizado";
    }

    public String atualizarEmailCliente(String email,String cpf){
        Cliente clienteAtualizado = this.buscarClientePorCpf(cpf);
        clienteAtualizado.setNome(email);
        repository.save(clienteAtualizado);
        return "Email do cliente atualizado";
    }

    public String saque(Integer valor, String cpf,String cnpj){
        Cliente cliente = this.buscarClientePorCpf(cpf);
        Empresa empresa = empresaService.buscarEmpresaPorCnpj(cnpj);
        if(!empresa.getListaDeClientes().contains(cliente)){
            throw new RuntimeException("você não é cliente desta empresa, por favor entre em contato com a central ");
        }else{
            this.empresaService.sacar(cnpj,valor,cliente.getNome());
            return "Saque feito com sucesso !";
        }


    }

    public String deposito (Integer valor , String cpf,String cnpj){
        Cliente cliente = this.buscarClientePorCpf(cpf);
        Empresa empresa = empresaService.buscarEmpresaPorCnpj(cnpj);

        this.empresaService.depositar(empresa, valor, cliente);

        return "O deposito no valor: "+ valor + " foi realizado com sucesso";
    }

}
