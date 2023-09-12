package org.tgid.testetgid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tgid.testetgid.domain.Cliente;
import org.tgid.testetgid.domain.DTO.EmpresaDTO;
import org.tgid.testetgid.domain.Empresa;
import org.tgid.testetgid.domain.enums.TipoTransacao;
import org.tgid.testetgid.repositories.EmpresaRepository;
import org.tgid.testetgid.util.Transacao;
import org.tgid.testetgid.util.Webhook;

import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private Webhook webhook;

    @Autowired
    private EmpresaRepository repository;






    public Empresa buscarEmpresaPorCnpj(String cnpj){
        Optional<Empresa> empresa = this.repository.findByCnpj(cnpj);

        if(empresa.isEmpty()){
            throw new RuntimeException("Empresa não encontrada!");
        }
        else{

            return empresa.get() ;
        }
    }

    public String novaEmpresa(EmpresaDTO dto){
        Empresa novaEmpresa = new Empresa(dto.getRazaoSocial(), dto.getCnpj(), dto.getEmail());

        repository.save(novaEmpresa);

        return "nova empresa adicionado com sucesso";

    }

    public String atualizarRazaoSocial(String razaoSocial,String cnpj){
        Empresa empresaAtualizada = this.buscarEmpresaPorCnpj(cnpj);

        empresaAtualizada.setRazaoSocial(razaoSocial);

        repository.save(empresaAtualizada);

        return "Razão social atualizada com sucesso";
    }
    public String atualizarEmail(String email,String cnpj){

        Empresa empresaAtualizada = this.buscarEmpresaPorCnpj(cnpj);

        empresaAtualizada.setEmail(email);

        repository.save(empresaAtualizada);

        return "Email atualizada com sucesso";
    }


    public Double consultarSaldo(String cnpj){
        //taxa de administração

        Empresa empresa = this.buscarEmpresaPorCnpj(cnpj);

        Double saldoFinal = empresa.getSaldo();

        return saldoFinal;


    }

    public void cadastrarCliente(String cnpj,Cliente cliente){
        Empresa empresa = this.buscarEmpresaPorCnpj(cnpj);


        empresa.setListaDeClientes(cliente);

        repository.save(empresa);
    }

    public void sacar(String cnpj,Integer valor,String nomeCliente){
        Empresa empresa = this.buscarEmpresaPorCnpj(cnpj);

        Double saldoAtual = this.consultarSaldo(cnpj)  ;

        Transacao novaTransacao = new Transacao() ;
        novaTransacao.setTransacao(TipoTransacao.SAQUE);
        novaTransacao.setCliente(nomeCliente);

        if((saldoAtual -10) < valor) {
            novaTransacao.setMensagem("Foi realizado uma tentativa de saque no valor de: R$"+ valor +" porem o saldo atual é menor que : " +valor +"-10 (Taxa de manutencao)"  );
            novaTransacao.setSaldoAtual(saldoAtual);

            webhook.enviarWebhook(novaTransacao);

            throw new RuntimeException("Saldo insuficiente para saque");
        }else {
            Double novoSaldo = (saldoAtual - valor)-10;

            empresa.setSaldo(novoSaldo);
            repository.save(empresa);

            novaTransacao.setMensagem("Transacao de saque aprovada, taxa de sistema no valor de R$10.00 foi descontado do saldo total da empresa");
            novaTransacao.setSaldoAtual(novoSaldo);

            webhook.enviarWebhook(novaTransacao);
        }

    }

    public void depositar(Empresa empresa,Integer valor,Cliente cliente){
        Empresa empresaDestino = empresa;

        Double saldoAtual = this.consultarSaldo(empresa.getCnpj());
        Double novoSaldo = saldoAtual+valor;

        Transacao novaTransacao = new Transacao() ;
        novaTransacao.setTransacao(TipoTransacao.DEPOSITO);
        novaTransacao.setCliente(cliente.getNome());

        //caso ele nao seja cliente da emprea que quer depositar, ele e adicionado automaticamente
        if(!empresa.getListaDeClientes().contains(cliente)) {
            this.cadastrarCliente(empresaDestino.getCnpj(), cliente);

            empresaDestino.setSaldo(novoSaldo);
            this.repository.save(empresaDestino);

            novaTransacao.setSaldoAtual(novoSaldo);
            novaTransacao.setMensagem("Transacao aprovada de deposito aprovada, taxa de sistema no valor de R$10.00 foi descontado do saldo total da empresa");

            webhook.enviarWebhook(novaTransacao);



        }else {
            empresaDestino.setSaldo(novoSaldo);
            this.repository.save(empresaDestino);

            novaTransacao.setSaldoAtual(novoSaldo);
            novaTransacao.setMensagem("Transacao de deposito aprovada, taxa de sistema no valor de R$10.00 foi descontado do saldo total da empresa");

            webhook.enviarWebhook(novaTransacao);

        }
    }
}
