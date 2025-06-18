package model.usuario;

import java.io.Serializable;

public class Usuario implements Serializable {
    
    protected String id;
    protected String nome;
    protected int idade;
    protected String telefone;
    protected String endereco;

    public Usuario(String id, String nome, int idade, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.telefone = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void getDetalhes() {
        System.out.printf("ID:                  %s%n", this.getId());
        System.out.printf("Nome:                %s%n", this.getNome());
        System.out.printf("Telefone:            %s%n", this.getTelefone());
        System.out.printf("Endereço:            %s%n", this.getEndereco());
    }
}

