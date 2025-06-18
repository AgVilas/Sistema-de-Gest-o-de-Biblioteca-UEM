package model.usuario;
;

public class Docente extends Usuario {

    private String departamento;

    public Docente(String id, String nome, int idade, String telefone, String endereco, String departamento) {
        super(id, nome, idade, telefone, endereco);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void getDetalhes() {
        System.out.println("==================================================");
        System.out.println("                 Detalhes do Docente              ");
        System.out.println("==================================================");
        super.getDetalhes();
        System.out.printf("Departamento:        %s%n", this.departamento);
        System.out.println("==================================================");
    }
}


