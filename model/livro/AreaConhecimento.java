package model.livro;
import java.io.Serializable;

import Util.Validacao;
public class AreaConhecimento implements Serializable {

    String id;
    String nome;
    String descricao;
    private Validacao validar = new Validacao();
    public AreaConhecimento(String id) {
        this.id = id;
        this.nome = validar.validarString("Introduza o nome da area de conhecimento: ");
        this.descricao = validar.validarString("Introduza a descricao da area de conhecimento: ");
    }

    public String getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getDescricao() {
        return this.descricao;
    }

    public void getDetalhes() {
        System.out.println("=========================================================================================================");
        System.out.printf("Id da Area da Area de Conhecimento:    %s%n", this.id);
        System.out.printf("Nome da Area de Conhecimento:          %s%n", this.nome);
        System.out.println("Descricao da mesma: \n" + this.descricao);
        System.out.println("==========================================================================================================");
    }

}
