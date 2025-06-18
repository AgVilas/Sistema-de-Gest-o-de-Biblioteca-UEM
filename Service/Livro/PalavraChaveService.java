package Service.Livro;

import Util.LidarComFich;
import Util.Validacao;
import model.livro.PalavraChave;
import java.io.IOException;

public class PalavraChaveService {
    private LidarComFich lidarComFich;
    private Validacao validar;

    public PalavraChaveService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public void cadastrarPalavraChave() throws IOException {
        System.out.println("--- Cadastrar Palavra-Chave ---");
        PalavraChave[] palavrasAtuais = lidarComFich.getPalavrasChave();
        String id;
        if (palavrasAtuais.length == 0) {
            id = validar.validarID(10); // ID para PalavraChave
        } else {
            id = validar.validarID(palavrasAtuais[palavrasAtuais.length - 1].getId());
        }
        
        // Cria uma nova palavra-chave com o ID gerado
        PalavraChave palavra = new PalavraChave(id);
        lidarComFich.salvarPalavraChave(palavra);
        System.out.println("Palavra-chave '" + palavra.getPalavra() + "' cadastrada com sucesso! ID: " + palavra.getId());
    }

    public void listarTodasPalavrasChave() {
        System.out.println("--- Lista de Palavras-Chave ---");
        PalavraChave[] palavras = lidarComFich.getPalavrasChave();
        if (palavras.length == 0) {
            System.out.println("Nenhuma palavra-chave cadastrada.");
        } else {
            for (PalavraChave palavra : palavras) {
                palavra.getDetalhes();
                System.out.println("-------------------------");
            }
        }
    }
}