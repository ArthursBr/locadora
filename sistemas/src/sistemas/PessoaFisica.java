package sistemas;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaFisica implements Serializable {
    private String cpf;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;

    public PessoaFisica(String cpf, String nome, String sexo, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "CPF: " + cpf + ", Nome: " + nome + ", Sexo: " + sexo +
               ", Data de Nascimento: " + dataNascimento.format(formatter);
    }
}
