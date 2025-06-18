package Service.usuario;

import model.*;
import Util.LidarComFich;
import Util.Validacao;
import model.usuario.*;


import java.io.IOException;
import java.util.ArrayList;

public class UsuarioService {
    private LidarComFich lidarComFich;
    private Validacao validar;

    public UsuarioService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario usuario : lidarComFich.getUsuarios()) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public Estudante[] getEstudantes() {
        ArrayList<Estudante> lista = new ArrayList<>();
        for (Usuario u : lidarComFich.getUsuarios()) {
            if (u instanceof Estudante) lista.add((Estudante) u);
        }
        return lista.toArray(new Estudante[0]);
    }

    public Docente[] getDocentes() {
        ArrayList<Docente> lista = new ArrayList<>();
        for (Usuario u : lidarComFich.getUsuarios()) {
            if (u instanceof Docente) lista.add((Docente) u);
        }
        return lista.toArray(new Docente[0]);
    }

    public Funcionario[] getFuncionarios() {
        ArrayList<Funcionario> lista = new ArrayList<>();
        for (Usuario u : lidarComFich.getUsuarios()) {
            if (u instanceof Funcionario) lista.add((Funcionario) u);
        }
        return lista.toArray(new Funcionario[0]);
    }

    public Usuario cadastrarUsuario(String tipo) throws IOException {
        String id;
        System.out.println("--- Cadastro de " + tipo + " ---");
        String nome = validar.validarString("Nome: ");
        int idade = validar.validarInt("Sua idade (18-50): ", 50, 18);
        String telefone = validar.validarTelefone("Telefone: ");
        String endereco = validar.validarString("Digite o Endereço: ");

        Usuario usuario;
        Usuario[] todosUsuarios = lidarComFich.getUsuarios(); // Para pegar o último ID corretamente

        if (tipo.equals("Estudante")) {
            Estudante[] estudantesAtuais = getEstudantes();
            if (estudantesAtuais.length == 0) {
                id = validar.validarID(1); // Prefixo para Estudante
            } else {
                id = validar.validarID(estudantesAtuais[estudantesAtuais.length-1].getId());
            }
            System.out.println("Curso: ");
            int esco = validar.validarInt("1. Engenharia Informatica\n2. Engenharia Mecanica\n3. Engenharia Ambiental\n4. Engenharia Quimica\n5. Engenharia Civil\n6. Engenharia Electrica\n7. Engenharia Electronica\nSua escolha: ", 7, 1);
            String curso = switch (esco) {
                case 1   -> "Engenharia Informatica";
                case 2   -> "Engenharia Mecanica";
                case 3   -> "Engenharia Ambiental";
                case 4   -> "Engenharia Quimica";
                case 5   -> "Engenharia Civil";
                case 6   -> "Engenharia Electrica";
                default  -> "Engenharia Electronica";
            };
            usuario = new Estudante(id, nome, idade, telefone, endereco, curso);

        } else if (tipo.equals("Docente")) {
            Docente[] docentesAtuais = getDocentes();
            if (docentesAtuais.length == 0) {
                id = validar.validarID(2); // Prefixo para Docente
            } else {
                id = validar.validarID(docentesAtuais[docentesAtuais.length-1].getId());
            }
            int esco = validar.validarInt("Departament: \n1. Cadeiras Gerais\n2.DEEL\n3. DEMA\n4. DEQUI\n5. Civil\nSua escolha: ", 5, 1);
            String departamento = switch (esco) {
                case 1  ->  "Cadeiras gerais";
                case 2  ->  "DEEL";
                case 3  ->  "DEMA";
                case 4  ->  "DEQUI";
                default ->  "Civil";
            };
            usuario = new Docente(id, nome, idade, telefone, endereco, departamento);
        } else { // Funcionario
            Funcionario[] funcionariosAtuais = getFuncionarios();
            if (funcionariosAtuais.length == 0) {
                id = validar.validarID(3); // Prefixo para Funcionario
            } else {
                id = validar.validarID(funcionariosAtuais[funcionariosAtuais.length-1].getId());
            }
            int esco = validar.validarInt("Digite o estado\n1. Activo\n2. Reformado\nSua escolha: ", 2, 1);
            String estado = (esco == 1) ? "Activo" : "Reformado";
            usuario = new Funcionario(id, nome, idade, telefone, endereco, estado);
        }
        lidarComFich.salvarUsuario(usuario);
        System.out.println(tipo + " cadastrado com sucesso! ID: " + usuario.getId());
        return usuario;
    }

    public void listarTodosOsUsuarios() {
        System.out.println("--- Lista de Usuários ---");
        boolean algumUsuario = false;

        Estudante[] estudantes = getEstudantes();
        System.out.println("\n-- Estudantes --");
        if (estudantes.length > 0) {
            for(Estudante est: estudantes){
                est.getDetalhes();
                System.out.println("--------------------");
                algumUsuario = true;
            }
        } else {
            System.out.println("Nenhum Estudante Cadastrado.");
        }

        Docente[] docentes = getDocentes();
        System.out.println("\n-- Docentes --");
        if (docentes.length > 0) {
            for(Docente doc: docentes){
                doc.getDetalhes();
                System.out.println("--------------------");
                algumUsuario = true;
            }
        } else {
            System.out.println("Nenhum Docente Cadastrado.");
        }

        Funcionario[] funcionarios = getFuncionarios();
        System.out.println("\n-- Funcionários --");
        if (funcionarios.length > 0) {
            for(Funcionario fun: funcionarios) {
                fun.getDetalhes();
                System.out.println("--------------------");
                algumUsuario = true;
            }
        } else {
            System.out.println("Nenhum Funcionario Cadastrado.");
        }

        if (!algumUsuario) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
        }
    }
}