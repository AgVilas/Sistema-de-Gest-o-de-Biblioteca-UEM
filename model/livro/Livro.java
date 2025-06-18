package model.livro;
import java.io.Serializable;
public class Livro implements Serializable{

    String id;
    String nome;
    AreaConhecimento areaConhecimento;
    Editora editora;
    Autor autor[];
    PalavraChave palavraChave[];

    public Livro(String id, String nome, AreaConhecimento areaConhecimento, Editora editora, Autor autor[], PalavraChave palavraChave[]) {
        this.id = id;
        this.nome = nome;  
        this.areaConhecimento = areaConhecimento;
        this.editora = editora;
        this.autor = autor;
        this.palavraChave = palavraChave;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public AreaConhecimento getAreaConhecimento() {
        return this.areaConhecimento;
    }

    public Editora getEditora() {
        return this.editora;
    }

    public Autor[] getAutor() {
        return this.autor;
    }

    public PalavraChave[] getpalavraChave() {
        return this.palavraChave;
    }

    public void getDetalhes() {
        System.out.println("============================================================");
        System.out.println("                       Detalhes do Livro                    ");
        System.out.println("============================================================");
        System.out.printf("ID - Livro:          %s%n", this.getId());
        System.out.printf("Nome do Livro:       %s%n", this.getNome());
        System.out.printf("Area de Conhecimento:%s%n", this.areaConhecimento.getNome());
        System.out.printf("Editora:             %s%n", this.editora.getNome());
        System.out.println("Autores: ");
        for (int i = 0; i < this.autor.length; i++) {
            System.out.printf("   Autor" + (i+1) + ":%s%n", this.autor[i].getNome());
        }
        System.out.println("=============================================================");
        
    }
}
