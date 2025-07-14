package sistemas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// o "Serializable" foi adicionado pq não estava consegindo salvar os dados corretamente
// dai fui pesquisar e vi que ele meio que força a salvar
// Mesmo que apresente algo erro estrenho, não entendi 100%
// Mas aparetimente o java (ou esse copilador) n consegue descrever o que está contecendo que n salvou
// No final, essa foi a solucao que encontrei.
public class Cliente extends PessoaFisica implements Serializable {
    private List<Dependente> dependentes;

    public Cliente(String cpf, String nome, String sexo, LocalDate dataNascimento) {
        super(cpf, nome, sexo, dataNascimento);
        this.dependentes = new ArrayList<>();
    }

    public void adicionarDependente(Dependente d) {
        dependentes.add(d);
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    @Override
    public String toString() {
        return super.toString() + ", Dependentes: " + dependentes.size();
    }
}
