package Service.Livro;

import Util.LidarComFich;
import Util.Validacao;

import model.livro.*;
import java.io.IOException;

public class LivroService {
    private LidarComFich lidarComFich;
    private Validacao validar;
   
    public LivroService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public Livro buscarLivroPorId(String id) { // Se necessário
        for (Livro livro : lidarComFich.getLivros()) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }

    public Livro cadastrarLivro() throws IOException {
        System.out.println("--- Cadastrar Livro ---");
        String id;
        Livro[] livrosAtuais = lidarComFich.getLivros();
        if (livrosAtuais.length == 0) {
            id = validar.validarID(4); // Prefixo para Livro
        } else {
            id = validar.validarID(livrosAtuais[livrosAtuais.length - 1].getId());
        }
        String nome = validar.validarString("Nome do Livro: ");

        AreaConhecimento[] areas = lidarComFich.getAreas();
        if (areas.length == 0) {
            System.out.println("Nenhuma área de conhecimento cadastrada. Cadastre uma antes!");
            return null;
        }
        System.out.println("Selecione a Área de Conhecimento:");
        for (int i = 0; i < areas.length; i++) {
            System.out.println((i+1) + ". " + areas[i].getNome());
        }
        int areaIndex = validar.validarInt("Escolha: ", areas.length, 1) - 1;
        AreaConhecimento area = areas[areaIndex];

        Editora[] editoras = lidarComFich.getEditoras();
        if (editoras.length == 0) {
            System.out.println("Nenhuma editora cadastrada. Cadastre uma antes!");
            return null;
        }
        System.out.println("Selecione a Editora:");
        for (int i = 0; i < editoras.length; i++) {
            System.out.println((i+1) + ". " + editoras[i].getNome());
        }
        int editoraIndex = validar.validarInt("Escolha: ", editoras.length, 1) - 1;
        Editora editora = editoras[editoraIndex];

        Autor[] autoresDisponiveis = lidarComFich.getAutores();
        if (autoresDisponiveis.length == 0) {
            System.out.println("Nenhum autor cadastrado. Cadastre um antes!");
            return null;
        }
        System.out.println("Quantos autores este livro possui?");
        int qtdAutores = validar.validarInt("Quantidade: ", autoresDisponiveis.length, 1);
        Autor[] autoresLivro = new Autor[qtdAutores];
        for (int i = 0; i < qtdAutores; i++) {
            System.out.println("Selecione o autor " + (i+1) + ":");
            for (int j = 0; j < autoresDisponiveis.length; j++) {
                System.out.println((j+1) + ". " + autoresDisponiveis[j].getNome());
            }
            int autorIndex = validar.validarInt("Escolha: ", autoresDisponiveis.length, 1) - 1;
            autoresLivro[i] = autoresDisponiveis[autorIndex];
        }

        PalavraChave[] palavrasDisponiveis = lidarComFich.getPalavrasChave();
        if (palavrasDisponiveis.length == 0) {
            System.out.println("Nenhuma palavra-chave cadastrada. Cadastre uma antes!");
            return null;
        }
        System.out.println("Quantas palavras-chave este livro possui?");
        int qtdPalavras = validar.validarInt("Quantidade: ", palavrasDisponiveis.length, 1);
        PalavraChave[] palavrasLivro = new PalavraChave[qtdPalavras];
        for (int i = 0; i < qtdPalavras; i++) {
            System.out.println("Selecione a palavra-chave " + (i+1) + ":");
            for (int j = 0; j < palavrasDisponiveis.length; j++) {
                System.out.println((j+1) + ". " + palavrasDisponiveis[j].getPalavra());
            }
            int palavraIndex = validar.validarInt("Escolha: ", palavrasDisponiveis.length, 1) - 1;
            palavrasLivro[i] = palavrasDisponiveis[palavraIndex];
        }

        Livro livro = new Livro(id, nome, area, editora, autoresLivro, palavrasLivro);
        lidarComFich.salvarLivro(livro);
        System.out.println("Livro cadastrado com sucesso! ID: " + livro.getId());
        return livro;
    }

    public void listarTodosOsLivros() {
        System.out.println("--- Lista de Livros ---");
        Livro[] livros = lidarComFich.getLivros();
        if (livros.length == 0) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                livro.getDetalhes();
                System.out.println("--------------------------");
            }
        }
    }
}