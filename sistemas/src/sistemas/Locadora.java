package sistemas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Locadora {
    public List<Cliente> clientes;
    public List<DVD> dvds;
    public List<Locacao> locacoes;
    public List<Genero> generos;

    public Locadora() {
        clientes = new ArrayList<>();
        dvds = new ArrayList<>();
        locacoes = new ArrayList<>();
        generos = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarDVD(DVD dvd) {
        dvds.add(dvd);
    }

    public void cadastrarGenero(Genero genero) {
        generos.add(genero);
    }

    public void cadastrarDependente(Dependente d) {
        d.getClienteVinculado().adicionarDependente(d);
    }

    public void locarDVD(DVD dvd, Cliente cliente) {
        Locacao loc = new Locacao(dvd, cliente, LocalDate.now());
        locacoes.add(loc);
    }

    public void devolverDVD(DVD dvd) {
        for (Locacao l : locacoes) {
            if (l.getDvd().equals(dvd) && !l.estaDevolvido()) {
                l.devolver(LocalDate.now());
                break;
            }
        }
    }

    public Cliente consultarCliente(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) return c;
        }
        return null;
    }

    public Cliente consultarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public DVD consultarDVD(int codigo) {
        for (DVD d : dvds) {
            if (d.getCodigo() == codigo) return d;
        }
        return null;
    }

    public DVD consultarDVD(String titulo) {
        for (DVD d : dvds) {
            if (d.getNome().equalsIgnoreCase(titulo)) return d;
        }
        return null;
    }

    public List<DVD> consultarDVDPorGenero(int codGenero) {
        List<DVD> lista = new ArrayList<>();
        for (DVD d : dvds) {
            if (d.getGenero().getCodigo() == codGenero) lista.add(d);
        }
        return lista;
    }

    public List<Locacao> listarNaoDevolvidos() {
        List<Locacao> lista = new ArrayList<>();
        for (Locacao l : locacoes) {
            if (!l.estaDevolvido()) lista.add(l);
        }
        return lista;
    }

    public List<Cliente> listarClientesComLocacao() {
        List<Cliente> lista = new ArrayList<>();
        for (Locacao l : locacoes) {
            if (!l.estaDevolvido() && !lista.contains(l.getCliente())) {
                lista.add(l.getCliente());
            }
        }
        return lista;
    }

    public List<Locacao> locacoesDoCliente(Cliente cliente) {
        List<Locacao> lista = new ArrayList<>();
        for (Locacao l : locacoes) {
            if (l.getCliente().equals(cliente)) lista.add(l);
        }
        return lista;
    }

    public List<Locacao> locacoesEntre(LocalDate inicio, LocalDate fim) {
        List<Locacao> lista = new ArrayList<>();
        for (Locacao l : locacoes) {
            if ((l.getDataLocacao().isEqual(inicio) || l.getDataLocacao().isAfter(inicio)) &&
                (l.getDataLocacao().isEqual(fim) || l.getDataLocacao().isBefore(fim))) {
                lista.add(l);
            }
        }
        return lista;
    }

    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dados.txt"))) {
            oos.writeObject(clientes);
            oos.writeObject(dvds);
            oos.writeObject(locacoes);
            oos.writeObject(generos);
            oos.flush(); // funcione meu caro, nao aguento mais
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }


    public void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dados.txt"))) {
            clientes = (List<Cliente>) ois.readObject();
            dvds = (List<DVD>) ois.readObject();
            locacoes = (List<Locacao>) ois.readObject();
            generos = (List<Genero>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado ou dados vazios.");
        }
    }
}
