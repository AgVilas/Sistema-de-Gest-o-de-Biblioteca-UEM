package Service;


import Util.ConsoleUtils;
import Util.Validacao;
import Service.Livro.*;
import Util.LidarComFich;
import Service.usuario.*;

import java.io.IOException;

public class MenuUI {

    private LidarComFich lidarComFich;
    private Validacao validar;

    // Serviços
    private UsuarioService usuarioService;
    private LivroService livroService;
    private ExemplarService exemplarService;
    private AreaConhecimentoService areaConhecimentoService;
    private AutorService autorService;
    private EditoraService editoraService;
    private PalavraChaveService palavraChaveService;
    private EmprestimoService emprestimoService;


    public MenuUI() {
        this.lidarComFich = new LidarComFich();
        this.validar = new Validacao();

        this.usuarioService = new UsuarioService(lidarComFich, validar);
        this.livroService = new LivroService(lidarComFich, validar);
        this.exemplarService = new ExemplarService(lidarComFich, validar);
        this.areaConhecimentoService = new AreaConhecimentoService(lidarComFich, validar);
        this.autorService = new AutorService(lidarComFich, validar);
        this.editoraService = new EditoraService(lidarComFich, validar);
        this.palavraChaveService = new PalavraChaveService(lidarComFich, validar);
        this.emprestimoService = new EmprestimoService(lidarComFich, validar, usuarioService, exemplarService);
    }


    public void menuPrincipal() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Principal ==========");
            System.out.println("1. Menu Usuários");
            System.out.println("2. Menu Livros");
            System.out.println("3. Menu Exemplares");
            System.out.println("4. Menu Areas de Conhecimento");
            System.out.println("5. Menu Autores");
            System.out.println("6. Menu Editoras");
            System.out.println("7. Menu Palavras-Chave");
            System.out.println("8. Menu Empréstimos");
            System.out.println("0. Sair");
            System.out.println("====================================");
            opcao = validar.validarInt("Escolha uma opcao: ", 8, 0);

            switch (opcao) {
                case 1: menuUsuarios(); break;
                case 2: menuLivros(); break;
                case 3: menuExemplares(); break;
                case 4: menuAreasConhecimento(); break;
                case 5: menuAutores(); break;
                case 6: menuEditoras(); break;
                case 7: menuPalavrasChave(); break;
                case 8: menuEmprestimos(); break;
                case 0:
                    ConsoleUtils.limpar();
                    System.out.println("=======================================================================");
                    System.out.println("Saindo...");
                    System.out.println("=======================================================================");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    private void menuUsuarios() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("============ Menu Usuário ======");
            System.out.println("1. Cadastrar Estudante");
            System.out.println("2. Cadastrar Docente");
            System.out.println("3. Cadastrar Funcionário");
            System.out.println("4. Listar Usuários");
            System.out.println("0. Voltar (ao Menu Principal)");
            System.out.println("================================");
            opcao = validar.validarInt("Escolha uma opcaoo(0 - 4): ", 4, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    usuarioService.cadastrarUsuario("Estudante");
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    usuarioService.cadastrarUsuario("Docente");
                    validar.esperarParaContinuar();
                    break;
                case 3:
                    usuarioService.cadastrarUsuario("Funcionario");
                    validar.esperarParaContinuar();
                    break;
                case 4:
                    usuarioService.listarTodosOsUsuarios();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    private void menuLivros() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Livros ==========");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("0. Voltar");
            System.out.println("=================================");
            opcao = validar.validarInt("Escolha uma opcao (0 - 2): ", 2, 0);
            
            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    livroService.cadastrarLivro();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    livroService.listarTodosOsLivros();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    private void menuEmprestimos() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Empréstimos ==========");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Registar Devolução");
            System.out.println("3. Listar Empréstimos de um Usuário");
            System.out.println("4. Ver Legibilidade de Usuário para Empréstimo");
            System.out.println("5. Listar Todos os Empréstimos");
            System.out.println("0. Voltar");
            System.out.println("======================================");
            opcao = validar.validarInt("Escolha uma opcao: ", 5, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    emprestimoService.registrarEmprestimo();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    emprestimoService.registrarDevolucao();
                    validar.esperarParaContinuar();
                    break;
                case 3:
                    emprestimoService.listarEmprestimosDeUsuario();
                    validar.esperarParaContinuar();
                    break;
                case 4:
                    emprestimoService.verificarLegibilidadeUsuario();
                    validar.esperarParaContinuar();
                    break;
                case 5:
                    emprestimoService.listarTodosEmprestimos();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    // ========================= MENU EXEMPLARES =========================
    private void menuExemplares() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Exemplares ==========");
            System.out.println("1. Cadastrar Exemplar");
            System.out.println("2. Listar Exemplares");
            System.out.println("0. Voltar");
            System.out.println("=====================================");
            opcao = validar.validarInt("Escolha uma opcao (0 - 2): ", 2, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    exemplarService.cadastrarExemplar();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    exemplarService.listarTodosExemplares();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    // ========================= MENU ÁREAS DE CONHECIMENTO =========================
    private void menuAreasConhecimento() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("====== Menu Áreas de Conhecimento ======");
            System.out.println("1. Cadastrar Area de conhecimento");
            System.out.println("2. Listar Areas de Conhecimento");
            System.out.println("0. Voltar");
            System.out.println("========================================");
            opcao = validar.validarInt("Escolha uma opcao (0 - 2): ", 2, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    areaConhecimentoService.cadastrarAreaConhecimento();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    areaConhecimentoService.listarTodasAreasConhecimento();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    // ========================= MENU AUTORES =========================
    private void menuAutores() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Autores ==========");
            System.out.println("1. Cadastrar Autor");
            System.out.println("2. Listar Autores");
            System.out.println("0. Voltar");
            System.out.println("==================================");
            opcao = validar.validarInt("Escolha uma opcao (0 - 2): ", 2, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    autorService.cadastrarAutor();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    autorService.listarTodosAutores();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    // ========================= MENU EDITORAS =========================
    private void menuEditoras() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Editoras ==========");
            System.out.println("1. Cadastrar Editora");
            System.out.println("2. Listar Editoras");
            System.out.println("0. Voltar");
            System.out.println("===================================");
            opcao = validar.validarInt("Escolha uma opcao (0 - 2): ", 2, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    editoraService.cadastrarEditora();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    editoraService.listarTodasEditoras();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }

    // ========================= MENU PALAVRAS-CHAVE =========================
    private void menuPalavrasChave() throws IOException {
        int opcao;
        do {
            ConsoleUtils.limpar();
            System.out.println("========== Menu Palavras-Chave ==========");
            System.out.println("1. Cadastrar Palavra-Chave");
            System.out.println("2. Listar Palavras-Chave");
            System.out.println("0. Voltar");
            System.out.println("=========================================");
            opcao = validar.validarInt("Escolha uma opcao (0 - 2): ", 2, 0);

            ConsoleUtils.limpar();
            switch (opcao) {
                case 1:
                    palavraChaveService.cadastrarPalavraChave();
                    validar.esperarParaContinuar();
                    break;
                case 2:
                    palavraChaveService.listarTodasPalavrasChave();
                    validar.esperarParaContinuar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    validar.esperarParaContinuar();
            }
        } while (opcao != 0);
    }
}