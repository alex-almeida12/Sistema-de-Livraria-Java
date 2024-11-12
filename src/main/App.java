package main;

import main.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        int menu = 10;
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        Autor autor1 = new Autor("J.K. Rowling", new Date());
        Autor autor2 = new Autor("J.R.R. Tolkien", new Date());

        biblioteca.adicionarAutor(autor1);
        biblioteca.adicionarAutor(autor2);

        // Adicionando livros
        Livro livro1 = new Livro("Harry Potter e a Pedra Filosofal", autor1);
        Livro livro2 = new Livro("O Senhor dos Anéis: A Sociedade do Anel", autor2);
        Livro livro3 = new Livro("Harry Potter e a Câmara Secreta", autor1);

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);

        // Adicionando usuario
        // Adicionando usuários
        Date dataNascimento1 = new Date(93, 4, 26);
        Date dataNascimento2 = new Date(100, 4, 15);
        Date dataNascimento3 = new Date(95, 2, 20);

        Usuario usuario1 = new Usuario("Alex", dataNascimento1, "alex.almeida12@gmail.com");
        Usuario usuario2 = new Usuario("João", dataNascimento2, "joao@email.com");
        Usuario usuario3 = new Usuario("Maria", dataNascimento3, "maria@email.com");


        biblioteca.adicionarUsuario(usuario1);
        biblioteca.adicionarUsuario(usuario2);
        biblioteca.adicionarUsuario(usuario3);

        // Emprestando livro
        biblioteca.emprestarLivro(livro1, usuario1);
        biblioteca.emprestarLivro(livro2, usuario1);
        biblioteca.emprestarLivro(livro3, usuario2);

        biblioteca.devolverLivro(2);


        while (menu != 0) {
            System.out.println("Digite uma das opções abaixo:");
            System.out.println("1. Adicionar um livro.");
            System.out.println("2. Adicionar um Autor:");
            System.out.println("3. Adicionar um Usuário.");
            System.out.println("4. Fazer um Emprestimo.");
            System.out.println("5. Consultar Livros.");
            System.out.println("6. Consultar Usuários.");
            System.out.println("7. Consultar Autores.");
            System.out.println("8. Consultar Histórico de Emprestimos.");
            System.out.println("9. Consultar Emprestimos ativos.");
            System.out.println("10. Consultar Emprestimos por Usuário.");
            System.out.println("11. Devolver Livro.");
            System.out.println("12: Atualizar Livro.");
            System.out.println("13: Atualizar Usuário.");
            System.out.println("14: Atualizar Autor.");
            System.out.println("15: Remover Livro.");
            System.out.println("16: Remover Usuário.");
            System.out.println("17: Remover Autor.");
            System.out.println("0. Sair");
            menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 1) {
                System.out.println("Digite o Título do Livro:");
                String titulo = scanner.nextLine();
                System.out.println("Digite o ID do Autor conforme a lista abaixo:");
                if (biblioteca.listarAutores().isEmpty()) {
                    System.out.println("A lista de Autores está vázia, Adicione um autor.");
                } else {
                    List<Autor> autoresAux = biblioteca.listarAutores();

                    for (Autor autorAux : autoresAux) {
                        System.out.println(autorAux.getId() + " " + autorAux.getNome());
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Autor autor = biblioteca.buscarAutorPorId(id);
                    if (autor != null) {
                        Livro livroAux = new Livro(titulo, autor);
                        biblioteca.adicionarLivro(livroAux);
                        System.out.println("Livro Adicionado Com Sucesso.");
                    } else {
                        System.out.println("Autor não encontrado com o ID fornecido.");
                    }
                }

            } else if (menu == 2) {
                System.out.println("Digite o Nome do Autor:");
                String nome = scanner.nextLine();

                Date data = null;
                boolean dataValida = false;

                while (!dataValida) {
                    System.out.println("Digite a Data de Nascimento do Autor no formato dia/mes/ano:");
                    String dataStr = scanner.nextLine();

                    try {
                        data = formatoData.parse(dataStr);
                        dataValida = true;
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Certifique-se de usar o formato dia/mês/ano.");
                    }
                }

                Autor autorAux = new Autor(nome, data);
                biblioteca.adicionarAutor(autorAux);
                System.out.println("Autor adicionado com sucesso");
            } else if (menu == 3) {
                System.out.println("Digite o nome do Usuário:");
                String nome = scanner.nextLine();

                Date data = null;
                boolean dataValida = false;
                while (!dataValida) {
                    System.out.println("Digite a Data de Nascimento do Usuário no formato dia/mês/ano:");
                    String dataStr = scanner.nextLine();

                    try {
                        data = formatoData.parse(dataStr);
                        dataValida = true;
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Certifique-se de usar o formato dia/mês/ano.");
                    }
                }

                boolean emailValido = false;
                while (!emailValido) {
                    System.out.println("Digite o email do Usuário:");
                    String email = scanner.nextLine();

                    try {
                        Usuario usuarioTeste = new Usuario(nome, data, email);  // Testa o email
                        biblioteca.adicionarUsuario(usuarioTeste); // Adiciona o usuário se for válido
                        emailValido = true;
                        System.out.println("Usuário adicionado com sucesso");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Email inválido. Tente novamente.");
                    }
                }
            } else if (menu == 4) {
                System.out.println("Digite o ID do Livro que você quer pegar emprestado, conforme a lista abaixo:");

                // Verifica se há livros disponíveis para empréstimo
                if (biblioteca.listarLivrosDisponiveis().isEmpty()) {
                    System.out.println("Não há livros disponíveis para empréstimo.");
                } else {
                    List<Livro> livrosAux2 = biblioteca.listarLivrosDisponiveis();


                    for (Livro livroAux : livrosAux2) {
                        System.out.println(livroAux.getId() + " " + livroAux.getTitulo());
                    }


                    int id = scanner.nextInt();
                    scanner.nextLine();


                    Livro livro = biblioteca.buscarLivroPorId(id);

                    if (livro != null) {
                        System.out.println("Digite o ID do Usuário que quer pegar o Livro emprestado, conforme a lista abaixo:");


                        if (biblioteca.listarUsuarios().isEmpty()) {
                            System.out.println("Não há usuários cadastrados, adicione um usuário.");
                        } else {
                            List<Usuario> usuariosAux2 = biblioteca.listarUsuarios();


                            for (Usuario usuarioAux : usuariosAux2) {
                                System.out.println(usuarioAux.getId() + " " + usuarioAux.getNome());
                            }


                            int id2 = scanner.nextInt();
                            scanner.nextLine();


                            Usuario usuario = biblioteca.buscarUsuarioPorId(id2);

                            if (usuario != null) {

                                biblioteca.emprestarLivro(livro, usuario);
                                System.out.println("Empréstimo Realizado Com Sucesso.");
                            } else {
                                System.out.println("Usuário não encontrado com o ID fornecido.");
                            }
                        }
                    } else {
                        System.out.println("Livro não encontrado com o ID fornecido.");
                    }
                }

            } else if (menu == 5) {
                if (biblioteca.listarLivros().isEmpty()) {
                    System.out.println("Não há livros cadastrados, adicione um livro");
                } else {
                    List<Livro> livrosAux2 = biblioteca.listarLivros();

                    for (Livro livroAux : livrosAux2) {
                        System.out.println(livroAux.getId() + " " + livroAux.getTitulo() + " " + (livroAux.isDisponivel() ? "Disponível" : "Emprestado"));
                    }
                }
            } else if (menu == 6) {
                if (biblioteca.listarUsuarios().isEmpty()) {
                    System.out.println("Não há Usuários cadastrados, adicione um Usuário");
                } else {
                    List<Usuario> usuariosAux2 = biblioteca.listarUsuarios();

                    for (Usuario usuarioAux : usuariosAux2) {
                        System.out.println(usuarioAux.getId() + " " + usuarioAux.getNome());
                    }
                }
            } else if (menu == 7) {
                if (biblioteca.listarAutores().isEmpty()) {
                    System.out.println("Não há Autores cadastrados, adicione um Autor");
                } else {
                    List<Autor> autoresAux2 = biblioteca.listarAutores();


                    for (Autor autoresAux : autoresAux2) {
                        System.out.println(autoresAux.getId() + " " + autoresAux.getNome());
                    }
                }
            } else if (menu == 8) {
                if (biblioteca.listarEmprestimos().isEmpty()) {
                    System.out.println("Não há emprestimos");
                } else {
                    List<Emprestimo> emprestimosAux2 = biblioteca.listarEmprestimos();


                    for (Emprestimo emprestimoAux : emprestimosAux2) {
                        System.out.println(emprestimoAux.getId() + " " + emprestimoAux.getLivro().getTitulo() + " " + emprestimoAux.getUsuario().getNome() + " " + (emprestimoAux.isAtivo() ? "Ativo" : "Devolvido"));
                    }
                }
            } else if (menu == 9) {
                if (biblioteca.listarEmprestimos().isEmpty()) {
                    System.out.println("Não há emprestimos");
                } else {
                    List<Emprestimo> emprestimosAux2 = biblioteca.listarEmprestimos().stream()
                            .filter(Emprestimo::isAtivo)
                            .collect(Collectors.toList());
                    for (Emprestimo emprestimoAux : emprestimosAux2) {
                        System.out.println(emprestimoAux.getId() + " " + emprestimoAux.getLivro().getTitulo() + " " + emprestimoAux.getUsuario().getNome() + " " + (emprestimoAux.isAtivo() ? "Ativo" : "Devolvido"));
                    }
                }
            } else if (menu == 10) {
                System.out.println("Digite o ID do Usuário que quer pegar o Livro emprestado, conforme a lista abaixo:");
                if (biblioteca.listarUsuarios().isEmpty()) {
                    System.out.println("Não há usuários cadastrados, adicione um usuário");
                } else {
                    List<Usuario> usuariosAux2 = biblioteca.listarUsuarios();

                    for (Usuario usuarioAux : usuariosAux2) {
                        System.out.println(usuarioAux.getId() + " " + usuarioAux.getNome());
                    }
                }

                int id2 = scanner.nextInt();
                scanner.nextLine();

                Usuario usuario = biblioteca.buscarUsuarioPorId(id2);

                if (usuario != null) {

                    List<Emprestimo> emprestimosUsuario = biblioteca.listarEmprestimosPorUsuario(id2);

                    if (emprestimosUsuario.isEmpty()) {
                        System.out.println("O usuário não possui empréstimos.");
                    } else {

                        System.out.println("Empréstimos do usuário " + usuario.getNome() + ":");
                        for (Emprestimo emprestimo : emprestimosUsuario) {
                            Livro livro = emprestimo.getLivro();
                            System.out.println("Título: " + livro.getTitulo());
                            System.out.println("Autor: " + livro.getAutor().getNome());
                            System.out.println("Data de Empréstimo: " + emprestimo.getDataEmprestimo());
                            System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
                            System.out.println("Status: " + (emprestimo.isAtivo() ? "Ativo" : "Devolvido"));
                            System.out.println("--------------------------------------------");
                        }
                    }
                } else {
                    System.out.println("Usuário não encontrado com o ID fornecido.");
                }
            } else if (menu == 11) {
                if (biblioteca.listarEmprestimos().isEmpty()) {
                    System.out.println("Não há emprestimos");
                } else {
                    List<Emprestimo> emprestimosAux2 = biblioteca.listarEmprestimos().stream()
                            .filter(Emprestimo::isAtivo)
                            .collect(Collectors.toList());
                    for (Emprestimo emprestimoAux : emprestimosAux2) {
                        System.out.println(emprestimoAux.getId() + " " + emprestimoAux.getLivro().getTitulo() + " " + emprestimoAux.getUsuario().getNome() + " " + (emprestimoAux.isAtivo() ? "Ativo" : "Devolvido"));
                    }
                    System.out.println("Digite o ID do empréstimo que deseja devolver:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (emprestimosAux2.stream().anyMatch(emprestimo -> emprestimo.getId() == id)) {
                        biblioteca.devolverLivro(id);
                        System.out.println("Livro Devolvido com Sucesso.");
                    } else {
                        System.out.println("Não há Emprestimos com esse ID.");
                    }
                }

            }else if (menu == 12) {
                if (biblioteca.listarLivros().isEmpty()) {
                    System.out.println("Não há livros cadastrados, adicione um livro");
                } else {
                    List<Livro> livrosAux2 = biblioteca.listarLivros();

                    for (Livro livroAux : livrosAux2) {
                        System.out.println(livroAux.getId() + " " + livroAux.getTitulo() + " " + (livroAux.isDisponivel() ? "Disponível" : "Emprestado"));
                    }
                    System.out.println("Digite o ID do Livro que você Atualizar o Título:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (livrosAux2.stream().anyMatch(livro -> livro.getId() == id)) {
                        System.out.println("Digite o novo Título do Livro:");
                        String titulo = scanner.nextLine();
                        biblioteca.atualizarLivro(id,titulo);
                        System.out.println("Livro Atualizado.");
                    } else {
                        System.out.println("Não há Livros com esse ID.");
                    }

                }
            } else if (menu == 13) {
                if (biblioteca.listarUsuarios().isEmpty()) {
                    System.out.println("Não há Usuários cadastrados, adicione um Usuário");
                } else {
                    List<Usuario> usuariosAux2 = biblioteca.listarUsuarios();


                    for (Usuario usuarioAux : usuariosAux2) {
                        System.out.println(usuarioAux.getId() + " " + usuarioAux.getNome());
                    }

                    System.out.println("Digite o ID do Usuário que você Atualizar o Nome:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (usuariosAux2.stream().anyMatch(usuario -> usuario.getId() == id)) {
                        System.out.println("Digite o novo Nome do Usuário:");
                        String nome = scanner.nextLine();
                        biblioteca.atualizarUsuario(id,nome);
                        System.out.println("Usuário Atualizado.");
                    } else {
                        System.out.println("Não há Usuários com esse ID.");
                    }
                }
            } else if (menu == 14) {
                if (biblioteca.listarAutores().isEmpty()) {
                    System.out.println("Não há Autores cadastrados, adicione um Autor");
                } else {
                    List<Autor> autoresAux2 = biblioteca.listarAutores();


                    for (Autor autoresAux : autoresAux2) {
                        System.out.println(autoresAux.getId() + " " + autoresAux.getNome());
                    }

                    System.out.println("Digite o ID do Autor que você Atualizar o Nome:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (autoresAux2.stream().anyMatch(autor -> autor.getId() == id)) {
                        System.out.println("Digite o novo Nome do Autor:");
                        String nome = scanner.nextLine();
                        biblioteca.atualizarAutor(id,nome);
                        System.out.println("Autor Atualizado.");
                    } else {
                        System.out.println("Não há Autores com esse ID.");
                    }
                }
            }else if (menu == 15) {
                if (biblioteca.listarLivros().isEmpty()) {
                    System.out.println("Não há livros cadastrados, adicione um livro");
                } else {
                    List<Livro> livrosAux2 = biblioteca.listarLivros();

                    for (Livro livroAux : livrosAux2) {
                        System.out.println(livroAux.getId() + " " + livroAux.getTitulo() + " " + (livroAux.isDisponivel() ? "Disponível" : "Emprestado"));
                    }
                    System.out.println("Digite o ID do Livro que você quer Remover:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (livrosAux2.stream().anyMatch(livro -> livro.getId() == id)) {
                        biblioteca.removerLivro(id);
                        System.out.println("Livro Removido.");
                    } else {
                        System.out.println("Não há Livros com esse ID.");
                    }

                }
            } else if (menu == 16) {
                if (biblioteca.listarUsuarios().isEmpty()) {
                    System.out.println("Não há Usuários cadastrados, adicione um Usuário");
                } else {
                    List<Usuario> usuariosAux2 = biblioteca.listarUsuarios();


                    for (Usuario usuarioAux : usuariosAux2) {
                        System.out.println(usuarioAux.getId() + " " + usuarioAux.getNome());
                    }

                    System.out.println("Digite o ID do Usuário que você quer Remover:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (usuariosAux2.stream().anyMatch(usuario -> usuario.getId() == id)) {
                        biblioteca.removerUsuario(id);
                        System.out.println("Usuario Removido.");
                    } else {
                        System.out.println("Não há Usuários com esse ID.");
                    }
                }
            } else if (menu == 17) {
                if (biblioteca.listarAutores().isEmpty()) {
                    System.out.println("Não há Autores cadastrados, adicione um Autor");
                } else {
                    List<Autor> autoresAux2 = biblioteca.listarAutores();


                    for (Autor autoresAux : autoresAux2) {
                        System.out.println(autoresAux.getId() + " " + autoresAux.getNome());
                    }

                    System.out.println("Digite o ID do Autor que você Remover:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (autoresAux2.stream().anyMatch(autor -> autor.getId() == id)) {
                        biblioteca.removerAutor(id);
                        System.out.println("Autor Removido.");
                    } else {
                        System.out.println("Não há Autores com esse ID.");
                    }
                }
            }else if (menu == 0) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        }
    }
}

