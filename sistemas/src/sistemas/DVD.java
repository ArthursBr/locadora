package sistemas;

import java.io.Serializable;

public class DVD implements Serializable {
    private int codigo;
    private String nome;
    private Genero genero;

    public DVD(int codigo, String nome, Genero genero) {
        this.codigo = codigo;
        this.nome = nome;
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Genero getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nome: " + nome + ", Gênero: " + genero.getDescricao();
    }
}
