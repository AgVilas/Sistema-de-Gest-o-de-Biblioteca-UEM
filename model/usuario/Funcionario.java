package model.usuario;

import Util.Validacao;

public class Funcionario extends Usuario {

    private String estado;
    private Validacao validar = new Validacao();
    
    public Funcionario(String id, String nome, int idade, String telefone, String endereco, String estado) {
        super(id, nome, idade, telefone, endereco);
        this.estado = validar.validarString("Introduza o estado do funcionario(a): ");
        //Alterar  esta estrutura de dados para aceitar apenas "Ativo",  "Inativo", ou "Afastado";
    }

    public String getEstado() {
        return this.estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void getDetalhes() {
        System.out.println("==================================================");
        System.out.println("              Detalhes do Funcionario             ");
        System.out.println("==================================================");
        super.getDetalhes();
        System.out.printf("Estado:              %s%n", this.estado);
        System.out.println("==================================================");
    }
}
