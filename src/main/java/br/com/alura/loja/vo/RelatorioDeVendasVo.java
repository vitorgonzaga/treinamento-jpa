package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {

    private String nomeProduto;

    private Long quantidadeVendida;

    private LocalDate dataULtimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataULtimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataULtimaVenda = dataULtimaVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDate getDataULtimaVenda() {
        return dataULtimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataULtimaVenda=" + dataULtimaVenda +
                '}';
    }
}
