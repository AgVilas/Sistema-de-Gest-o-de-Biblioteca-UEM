package model.livro;
import java.io.Serializable;
import java.util.Date;

import Util.Validacao;

public class Autor implements Serializable  {

    //private static int quant;
    private String id;
    private String nome;
    private Date dataNascimento ;
    
    public Validacao validar = new Validacao();

    public Autor(String id) {
        this.nome = validar.validarString("Nome do Autor(a): ");
        this.id = id;
        this.dataNascimento = validar.validarData("Data Nascimento: "); 
    }

    //Getters
    public String getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public Date getDataNascimento() {
        return this.dataNascimento;
    }


    
    public void getDetalhes() {
        System.out.println("========================================================");
        System.out.println("                    Detalhes do Autor                   ");
        System.out.println("========================================================");
        System.out.printf("Código do Estudante: %s%n", this.getId());
        System.out.printf("Nome:                %s%n", this.getNome());
        System.out.printf("Data de Nascimento:  %s%n", this.getDataNascimento());
        System.out.println("========================================================");
    }
}
