package model.livro;
import java.io.IOException;
import java.io.Serializable;

import Util.Validacao;
public class Editora implements Serializable{

    private String id;
    private String nome;
    private String telefone;
    private String endereco;
    private Validacao validar = new Validacao();

    public Editora(String id) throws IOException{
        this.id = id;
        this.nome = validar.validarString("Nome da Editora: ");
        this.telefone = validar.validarTelefone("Numero do telefone: ");
        this.endereco = validar.validarString("Introduza o endereco: "); 
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
        System.out.println("====================================================");
        System.out.println("                   Detalhes da Editora              ");
        System.out.println("====================================================");
        System.out.printf("ID - Editora:        %s%n", this.id);
        System.out.printf("Nome:                %s%n", this.nome);
        System.out.printf("Telefone:            %s%n", this.telefone);
        System.out.printf("Endereco:            %s%n", this.endereco);
        System.out.println("====================================================");
    }
}
