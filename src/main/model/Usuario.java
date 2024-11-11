package main.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Usuario {
    private int id;
    private static int contadorID = 1;
    private String nome;
    private Date dataNascimento;
    private String email;
    private List<Emprestimo> emprestimos;

    public Usuario(String nome, Date dataNascimento, String email) {
        this.id = contadorID++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.emprestimos = new ArrayList<>();
        if (isEmailValido(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    private boolean isEmailValido(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void adicionarEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", email='" + email + '\'' +
                ", emprestimos=" + emprestimos +
                '}';
    }
}

