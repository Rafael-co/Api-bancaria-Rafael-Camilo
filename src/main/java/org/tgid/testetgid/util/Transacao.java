package org.tgid.testetgid.util;

import org.tgid.testetgid.domain.enums.TipoTransacao;

public class Transacao {
    private TipoTransacao transacao ;
    private Double saldoAtual;
    private String cliente;

    private String mensagem;

    public Transacao(){}

    public Transacao(TipoTransacao transacao, Double saldoAtual, String cliente,String mensagem) {
        this.transacao = transacao;
        this.saldoAtual = saldoAtual;
        this.cliente = cliente;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TipoTransacao getTransacao() {
        return transacao;
    }

    public void setTransacao(TipoTransacao transacao) {
        this.transacao = transacao;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
