package model.livro;

import java.io.Serializable;

public class Exemplar implements Serializable {

    String id;
    String estado;
    Livro livro;
    public Exemplar(String id, Livro livro) {
        this.id = id;
        this.estado = "Disponivel";
        this.estado = "Danificado";
        this.livro = livro;
    }
    
    public String getId() {
        return this.id;
    }

    public String getEstado() {
        return this.estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public void getDetalhes() {
        System.out.println("============================================");
        System.out.println("            Detalhes do Exemplar            ");
        System.out.println("===========================================");
        System.out.printf("ID - Exemplar:       %s%n", this.id);
        System.out.printf("ID - Livro:          %s%n", this.livro.getId());
        System.out.printf("Nome do Livro:       %s%n", this.livro.getNome());
        System.out.printf("Estado:              %s%n", this.estado);
        System.out.println("============================================");
    }
}
