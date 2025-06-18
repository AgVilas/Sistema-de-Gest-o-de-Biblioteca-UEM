package Service;

import Util.LidarComFich;
import Util.Validacao;
import model.Emprestimo;
import model.livro.Exemplar;
import Service.Livro.ExemplarService; // Importa o serviço de Exemplar
import model.usuario.Usuario; // Importa a classe base Usuario
import java.io.IOException;
import Service.usuario.UsuarioService; // Importa o serviço de Usuario

public class EmprestimoService {
    private LidarComFich lidarComFich;
    private Validacao validar;
    private UsuarioService usuarioService;
    private ExemplarService exemplarService;

    public EmprestimoService(LidarComFich lidarComFich, Validacao validar,
                             UsuarioService usuarioService, ExemplarService exemplarService) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
        this.usuarioService = usuarioService;
        this.exemplarService = exemplarService;
    }

    public Emprestimo buscarEmprestimoPorId(String id) {
        for (Emprestimo emp : lidarComFich.getEmprestimos()) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }

    public void registrarEmprestimo() throws IOException {
        System.out.println("--- Registrar Empréstimo ---");

        if (lidarComFich.getUsuarios().length == 0) {
            System.out.println("Nenhum usuário cadastrado. Cadastre usuários primeiro.");
            return;
        }
        if (lidarComFich.getExemplares().length == 0) {
            System.out.println("Nenhum exemplar cadastrado. Cadastre exemplares primeiro.");
            return;
        }

        String usuarioId = validar.validarString("Digite o ID do usuário (Ex: EST001, DOC001, FUC001): ");
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId); // Usa o service de usuário

        if (usuario == null) {
            System.out.println("Usuário com ID '" + usuarioId + "' não encontrado.");
            return;
        }

        // Verifica se o usuário possui empréstimo pendente
        for (Emprestimo emp : lidarComFich.getEmprestimos()) {
            if (emp.getUsuario().getId().equals(usuario.getId()) && "Pendente".equals(emp.getEstadoEmprestimo())) {
                System.out.println("Usuário já possui um empréstimo pendente. Não pode efetuar novo empréstimo.");
                emp.getDetalhes();
                return;
            }
        }

        String exemplarId = validar.validarID("Digite o ID do exemplar (Ex: EXE001 - apenas os dígitos) :", "EXE");
        Exemplar exemplar = exemplarService.buscarExemplarPorId(exemplarId); // Usa o service de exemplar

        if (exemplar == null) {
            System.out.println("Exemplar com ID '" + exemplarId + "' não encontrado.");
            return;
        } else if ("Emprestado".equals(exemplar.getEstado())) {
            System.out.println("Exemplar com ID '" + exemplarId + "' já está emprestado.");
            for (Emprestimo emp : lidarComFich.getEmprestimos()) {
                if (emp.getExemplar().getId().equals(exemplar.getId()) && "Pendente".equals(emp.getEstadoEmprestimo())) {
                    System.out.println("Detalhes do empréstimo pendente deste exemplar:");
                    emp.getDetalhes();
                    break;
                }
            }
            return;
        }

        String idEmprestimo;
        Emprestimo[] emprestimosAtuais = lidarComFich.getEmprestimos();
        if (emprestimosAtuais.length == 0) {
            idEmprestimo = validar.validarID(9); // ID para Emprestimos
        } else {
            idEmprestimo = validar.validarID(emprestimosAtuais[emprestimosAtuais.length - 1].getId());
        }

        Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, usuario, exemplar);
        lidarComFich.salvarEmprestimo(novoEmprestimo);

        exemplar.setEstado("Emprestado");
        // O método atualizarExemplar em ExemplarService lida com encontrar o índice e salvar.
        if (!exemplarService.atualizarExemplar(exemplar)) {
            System.out.println("ALERTA: Empréstimo registrado, mas houve falha ao atualizar o estado do exemplar ID " + exemplar.getId() + " no arquivo.");
            System.out.println("Considere verificar os dados ou reverter o empréstimo manualmente se necessário.");
            // Poderia-se tentar reverter o empréstimo aqui, mas isso adiciona mais complexidade.
        }


        System.out.println("Empréstimo registrado com sucesso! ID: " + novoEmprestimo.getId());
        novoEmprestimo.getDetalhes();
    }

    public void registrarDevolucao() throws IOException {
        System.out.println("--- Registrar Devolução ---");
        Emprestimo[] emprestimos = lidarComFich.getEmprestimos();

        if (emprestimos.length == 0) {
            System.out.println("Nenhum empréstimo registrado para devolução.");
            return;
        }

        String emprestimoId = validar.validarID("Digite o ID do empréstimo a ser devolvido (Ex: EMP001 - apenas dígitos): ", "EMP");
        Emprestimo emprestimoParaDevolver = buscarEmprestimoPorId(emprestimoId);

        if (emprestimoParaDevolver == null) {
            System.out.println("Empréstimo com ID '" + emprestimoId + "' não encontrado.");
            return;
        }

        if ("Devolvido".equals(emprestimoParaDevolver.getEstadoEmprestimo())) {
            System.out.println("Este empréstimo já foi devolvido anteriormente.");
            emprestimoParaDevolver.getDetalhes();
            return;
        }

        Exemplar exemplarDevolvido = emprestimoParaDevolver.getExemplar();
        exemplarDevolvido.setEstado("Disponivel");

        if (!exemplarService.atualizarExemplar(exemplarDevolvido)) {
             System.out.println("ALERTA: Falha ao atualizar o estado do exemplar ID " + exemplarDevolvido.getId() + " para Disponível no arquivo.");
        }

        emprestimoParaDevolver.setDataDevolucaoReal(); // Define a data atual
        emprestimoParaDevolver.setEstadoEmprestimo("Devolvido");

        // Atualizar o empréstimo no arquivo
        boolean atualizado = false;
        for (int i = 0; i < emprestimos.length; i++) {
            if (emprestimos[i].getId().equals(emprestimoParaDevolver.getId())) {
                lidarComFich.actualizarEmprestimo(i, emprestimoParaDevolver);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            System.out.println("Devolução registrada com sucesso!");
            emprestimoParaDevolver.getDetalhes();
        } else {
            System.out.println("Erro: Empréstimo não encontrado na lista para atualização final da devolução.");
            // Se chegou aqui, algo está inconsistente, pois o exemplar já pode ter sido atualizado.
        }
    }

    public void listarEmprestimosDeUsuario() throws IOException {
        System.out.println("--- Listar Empréstimos de um Usuário ---");

        if (lidarComFich.getUsuarios().length == 0) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
            return;
        }
        if (lidarComFich.getEmprestimos().length == 0) {
            System.out.println("Nenhum empréstimo registrado no sistema.");
            return;
        }

        String usuarioId = validar.validarString("Digite o ID do usuário (Ex: EST001, DOC001, FUC001): ");
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

        if (usuario == null) {
            System.out.println("Usuário com ID '" + usuarioId + "' não encontrado.");
            return;
        }

        boolean encontrou = false;
        System.out.println("\nEmpréstimos para o usuário: " + usuario.getNome() + " (ID: " + usuario.getId() + ")");
        System.out.println("----------------------------------------------------");
        for (Emprestimo emp : lidarComFich.getEmprestimos()) {
            if (emp.getUsuario().getId().equals(usuarioId)) {
                emp.getDetalhes();
                System.out.println("--------------------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum empréstimo encontrado para o usuário com ID: " + usuarioId);
        }
    }

    public void verificarLegibilidadeUsuario() throws IOException {
        System.out.println("--- Verificar Legibilidade do Usuário para Empréstimo ---");

        if (lidarComFich.getUsuarios().length == 0) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
            return;
        }

        String usuarioId = validar.validarString("Digite o ID do usuário (Ex: EST001, DOC001, FUC001): ");
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

        if (usuario == null) {
            System.out.println("Usuário com ID '" + usuarioId + "' não encontrado.");
            return;
        }

        boolean pendente = false;
        for (Emprestimo emp : lidarComFich.getEmprestimos()) {
            if (emp.getUsuario().getId().equals(usuarioId) && "Pendente".equals(emp.getEstadoEmprestimo())) {
                System.out.println("Usuário com ID " + usuarioId + " (" + usuario.getNome() + ") NÃO PODE realizar novos empréstimos.");
                System.out.println("Possui o seguinte empréstimo pendente:");
                emp.getDetalhes();
                pendente = true;
                break;
            }
        }

        if (!pendente) {
            System.out.println("Usuário com ID " + usuarioId + " (" + usuario.getNome() + ") está APTO a realizar novos empréstimos.");
        }
    }

    public void listarTodosEmprestimos() {
        System.out.println("--- Lista de Todos os Empréstimos ---");
        Emprestimo[] emprestimos = lidarComFich.getEmprestimos();
        if (emprestimos.length == 0) {
            System.out.println("Nenhum empréstimo registrado no sistema.");
        } else {
            for (Emprestimo empItem : emprestimos) {
                empItem.getDetalhes();
                System.out.println("----------------------------------");
            }
        }
    }
}