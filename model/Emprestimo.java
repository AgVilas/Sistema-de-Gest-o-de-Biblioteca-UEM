package model;
import java.util.Date;
import model.livro.Exemplar;
import model.usuario.Usuario;

import java.io.Serializable;

public class Emprestimo implements Serializable {

    private String id;
    private Usuario usuario;
    private Exemplar exemplar;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;
    private String estadoEmprestimo;
    
    public Emprestimo(String id, Usuario usuario, Exemplar exemplar) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = new Date();
        this.dataDevolucaoPrevista = new Date(this.dataEmprestimo.getTime() + 7L * 24 * 60 * 60 * 1000);
        this.estadoEmprestimo = "Pendente";
    }
    
    public String getId(){
        return this.id;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public Exemplar  getExemplar(){
        return this.exemplar;
    }
    
    public Date getDataEmprestimo(){
        return this.dataEmprestimo;
    }
    
    public Date getDataDevolucaoPrevista(){
        return this.dataDevolucaoPrevista;
    }
    
    public Date getDataDevolucaoReal(){
        return this.dataDevolucaoReal;
    }
    
    public String getEstadoEmprestimo(){
        return this.estadoEmprestimo;
    }
    
    public void setDataDevolucaoReal(){
        this.dataDevolucaoReal = new Date();
    }
    
    public void setEstadoEmprestimo(String estadoEmprestimo){
        this.estadoEmprestimo=estadoEmprestimo;
    }
    
    public void getDetalhes() {
        System.out.println("======================================================");
        System.out.println("                  Detalhes do Emprestimo              ");
        System.out.println("=====================================================");
        System.out.printf("Código do Emprestimo:      %s%n", this.getId());
        System.out.printf("Usuario:                   %s%n", this.usuario.getNome());
        System.out.printf("Exemplar:                  %s%n", this.exemplar.getId());
        System.out.printf("Data de Emprestimo:        %s%n", this.dataEmprestimo);
        System.out.printf("Data de Devolucao Prevista:%s%n", this.dataDevolucaoPrevista);
        System.out.printf("Data de Devolucao Real:    %s%n", (this.dataDevolucaoReal != null ? this.dataDevolucaoReal : "Nao devolvido"));
        System.out.println("======================================================");
    }
}
