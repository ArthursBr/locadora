package sistemas;

import java.io.Serializable;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
//Mds n aguento mais

public class Locacao implements Serializable {
    private DVD dvd;
    private Cliente cliente;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;

    public Locacao(DVD dvd, Cliente cliente, LocalDate dataLocacao) {
        this.dvd = dvd;
        this.cliente = cliente;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = null;
    }

    public DVD getDvd() {
        return dvd;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void devolver(LocalDate data) {
        this.dataDevolucao = data;
    }

    public boolean estaDevolvido() {
        return dataDevolucao != null;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String locacao = dataLocacao.format(formatter);
        String devolucao = (dataDevolucao != null) ? dataDevolucao.format(formatter) : "Não devolvido";
//Ve se imprime certo agora
        return "DVD: " + dvd.getNome() + ", Cliente: " + cliente.getNome() +
               ", Locação: " + locacao + ", Devolução: " + devolucao;
    }
}
