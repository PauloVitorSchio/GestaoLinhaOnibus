package modelos.Usuario;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome;
    private String cpf;


    public Usuario() {
    }

    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario nome(String nome) {
        setNome(nome);
        return this;
    }

    public Usuario cpf(String cpf) {
        setCpf(cpf);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            "}";
    }

}
