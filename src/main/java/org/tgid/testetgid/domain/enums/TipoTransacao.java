package org.tgid.testetgid.domain.enums;

public enum TipoTransacao {
    DEPOSITO("Deposito"),
    SAQUE("Saque");
    private String descricao;

    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }

}
