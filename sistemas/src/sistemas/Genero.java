package sistemas;

import java.io.Serializable;

public class Genero implements Serializable {
    private int codigo;
    private String descricao;

    public Genero(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Descrição: " + descricao;
    }
}
