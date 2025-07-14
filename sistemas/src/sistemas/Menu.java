package sistemas;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
//pq o brasil n adotou o formatoo internacional

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locadora locadora = new Locadora();
        locadora.carregarDados(); // Isso aqui deu trabalho, espero que n dê bug de novo

        int opcao;
        // Os dados só são salvos ao final do programa pq enfrentei muitos bugs tentando
        //salvar a cada confirmacao 
        do {
            System.out.println("\n==== MENU LOCADORA ====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Dependente");
            System.out.println("3 - Cadastrar Gênero");
            System.out.println("4 - Cadastrar DVD");
            System.out.println("5 - Locar DVD");
            System.out.println("6 - Devolver DVD");
            System.out.println("7 - Consultar Cliente por CPF");
            System.out.println("8 - Consultar Cliente por Nome");
            System.out.println("9 - Consultar DVD por Código");
            System.out.println("10 - Consultar DVD por Título");
            System.out.println("11 - Consultar DVDs por Gênero");
            System.out.println("12 - DVDs não devolvidos");
            System.out.println("13 - Clientes com filmes locados");
            System.out.println("14 - Filmes alugados por cliente");
            System.out.println("15 - Locações por período");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
		        case 1: {
		            System.out.print("CPF: ");
		            String cpf = sc.nextLine();
		            System.out.print("Nome: ");
		            String nome = sc.nextLine();
		            System.out.print("Sexo: ");
		            String sexo = sc.nextLine();//Lembre de testar com "/" -> 22/07/2006 não está quebrado
		            System.out.print("Data Nascimento (DD/MM/AAAA): ");
		            String data = sc.nextLine();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		            LocalDate dataNascimento = LocalDate.parse(data, formatter);

		            Cliente cliente = new Cliente(cpf, nome, sexo, dataNascimento);

		            locadora.cadastrarCliente(cliente);
		            System.out.println("Cliente cadastrado!");
		            break;
		        }
		
		        case 2: {
		            System.out.print("CPF do Cliente responsável: ");
		            String cpf = sc.nextLine();
		            Cliente cliente = locadora.consultarClientePorCpf(cpf);
		            if (cliente == null) {
		                System.out.println("Cliente não encontrado.");
		            } else {
		                System.out.print("CPF do Dependente: ");
		                String cpfD = sc.nextLine();
		                System.out.print("Nome do Dependente: ");
		                String nomeD = sc.nextLine();
		                System.out.print("Sexo: ");
		                String sexo = sc.nextLine();//Lembre de testar com "/" -> 22/07/2006 não está quebrado
		                System.out.print("Data Nascimento (DD/MM/AAAA): ");
		                String data = sc.nextLine();
		                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		                LocalDate dataNascimento = LocalDate.parse(data, formatter);

		                Dependente dep = new Dependente(cpfD, nomeD, sexo, dataNascimento, cliente);

		                locadora.cadastrarDependente(dep);
		                System.out.println("Dependente cadastrado.");
		            }
		            break;
		        }
		
		        case 3: {
		            System.out.print("Código do Gênero: ");
		            int cod = sc.nextInt();
		            sc.nextLine();
		            System.out.print("Descrição: ");
		            String desc = sc.nextLine();
		            Genero g = new Genero(cod, desc);
		            locadora.cadastrarGenero(g);
		            System.out.println("Gênero cadastrado.");
		            break;
		        }
		
		        case 4: {
		            System.out.print("Código do DVD: ");
		            int cod = sc.nextInt();
		            sc.nextLine();
		            System.out.print("Título: ");
		            String titulo = sc.nextLine();
		            System.out.print("Código do Gênero: ");
		            int codGenero = sc.nextInt();
		            sc.nextLine();
		            Genero genero = null;
		            for (Genero g : locadora.generos) {
		                if (g.getCodigo() == codGenero) {
		                    genero = g;
		                    break;
		                }
		            }
		            if (genero == null) {
		                System.out.println("Gênero não encontrado.");
		            } else {
		                DVD dvd = new DVD(cod, titulo, genero);
		                locadora.cadastrarDVD(dvd);
		                System.out.println("DVD cadastrado.");
		            }
		            break;
		        }
		
		        case 5: {
		            System.out.print("CPF do Cliente: ");
		            String cpf = sc.nextLine();
		            Cliente c = locadora.consultarClientePorCpf(cpf);
		            if (c == null) {
		                System.out.println("Cliente não encontrado.");
		            } else {
		                System.out.print("Código do DVD: ");
		                int cod = sc.nextInt();
		                sc.nextLine();
		                DVD dvd = locadora.consultarDVD(cod);
		                if (dvd == null) {
		                    System.out.println("DVD não encontrado.");
		                } else {
		                    locadora.locarDVD(dvd, c);
		                    System.out.println("Locação realizada.");
		                }
		            }
		            break;
		        }
		
		        case 6: {
		            System.out.print("Código do DVD: ");
		            int cod = sc.nextInt();
		            sc.nextLine();
		            DVD dvd = locadora.consultarDVD(cod);
		            if (dvd != null) {
		                locadora.devolverDVD(dvd);
		                System.out.println("DVD devolvido.");
		            } else {
		                System.out.println("DVD não encontrado.");
		            }
		            break;
		        }
		
		        case 7: {
		            System.out.print("CPF: ");
		            String cpf = sc.nextLine();
		            Cliente c = locadora.consultarClientePorCpf(cpf);
		            System.out.println(c == null ? "Cliente não encontrado." : c);
		            break;
		        }
		
		        case 8: {
		            System.out.print("Nome: ");
		            String nome = sc.nextLine();
		            Cliente c = locadora.consultarCliente(nome);
		            System.out.println(c == null ? "Cliente não encontrado." : c);
		            break;
		        }
		
		        case 9: {
		            System.out.print("Código: ");
		            int cod = sc.nextInt();
		            sc.nextLine();
		            DVD d = locadora.consultarDVD(cod);
		            System.out.println(d == null ? "DVD não encontrado." : d);
		            break;
		        }
		
		        case 10: {
		            System.out.print("Título: ");
		            String titulo = sc.nextLine();
		            DVD d = locadora.consultarDVD(titulo);
		            System.out.println(d == null ? "DVD não encontrado." : d);
		            break;
		        }
		
		        case 11: {
		            System.out.print("Código do Gênero: ");
		            int cod = sc.nextInt();
		            sc.nextLine();
		            List<DVD> lista = locadora.consultarDVDPorGenero(cod);
		            lista.forEach(System.out::println);
		            break;
		        }
		
		        case 12: {
		            List<Locacao> lista = locadora.listarNaoDevolvidos();
		            lista.forEach(System.out::println);
		            break;
		        }
		
		        case 13: {
		            List<Cliente> lista = locadora.listarClientesComLocacao();
		            lista.forEach(System.out::println);
		            break;
		        }
		
		        case 14: {
		            System.out.print("CPF do Cliente: ");
		            String cpf = sc.nextLine();
		            Cliente c = locadora.consultarClientePorCpf(cpf);
		            if (c != null) {
		                List<Locacao> locs = locadora.locacoesDoCliente(c);
		                locs.forEach(System.out::println);
		            } else {
		                System.out.println("Cliente não encontrado.");
		            }
		            break;
		        }
		
		        case 15: {
		            System.out.print("Data inicial (DD/MM/AAAA): ");
		            String ini = sc.nextLine();
		            System.out.print("Data final (DD/MM/AAAA): ");
		            String fim = sc.nextLine();

		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		            try {
		                LocalDate dataInicio = LocalDate.parse(ini, formatter);
		                LocalDate dataFim = LocalDate.parse(fim, formatter);

		                List<Locacao> lista = locadora.locacoesEntre(dataInicio, dataFim);
		                if (lista.isEmpty()) {
		                    System.out.println("Nenhuma locação encontrada nesse período.");
		                } else {
		                    lista.forEach(System.out::println);
		                }
		            } catch (Exception e) {
		                System.out.println("Formato de data inválido. Use o formato DD/MM/AAAA.");
		            }
		            break;
		        }
		
		        case 0: {
		            locadora.salvarDados();
		            System.out.println("Sistema encerrado. Dados salvos.");
		            break;
		        }
		        // Os dados só são salvos ao final do programa pq enfrentei muitos bugs tentando
		        //salvar a cada confirmacao 
		
		        default:
		            System.out.println("Opção inválida!");
		            break;
		    }

        } while (opcao != 0);

        sc.close();
    }
}