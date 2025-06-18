package model.livro;



import java.io.Serializable;

import Util.Validacao;

public class PalavraChave implements Serializable{
    
    String id;
    String palavra;
    
    private Validacao validar = new Validacao();

    public PalavraChave(String id) {
        this.id = id;
        this.palavra = validar.validarString("Introduza a palavra-chave: ");
    }

    public String getId() {
        return this.id;
    }
    public String getPalavra() {
        return this.palavra;
    }

    public void getDetalhes() {
        System.out.println("==================================================");
        System.out.printf("Código da Palavra:   %s%n", this.id);
        System.out.printf("Palavra-Chave:       %s%n", this.palavra);
        System.out.println("==================================================");
    }

}
