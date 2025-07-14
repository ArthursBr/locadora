package sistemas;

import java.io.Serializable;
import java.time.LocalDate;

public class Dependente extends PessoaFisica implements Serializable {
    private Cliente clienteVinculado;

    public Dependente(String cpf, String nome, String sexo, LocalDate dataNascimento, Cliente cliente) {
        super(cpf, nome, sexo, dataNascimento);
        this.clienteVinculado = cliente;
    }

    public Cliente getClienteVinculado() {
        return clienteVinculado;
    }

    @Override
    public String toString() {
        return super.toString() + " (Dependente de " + clienteVinculado.getNome() + ")";
    }
}
