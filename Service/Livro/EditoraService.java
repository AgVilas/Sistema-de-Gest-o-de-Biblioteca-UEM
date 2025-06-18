package Service.Livro;

import Util.LidarComFich;
import Util.Validacao;
import model.livro.Editora;
import java.io.IOException;

public class EditoraService {
    private LidarComFich lidarComFich;
    private Validacao validar;

    public EditoraService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public void cadastrarEditora() throws IOException {
        System.out.println("--- Cadastrar Editora ---");
        Editora[] editorasAtuais = lidarComFich.getEditoras();
        String id;
        if (editorasAtuais.length == 0) {
            id = validar.validarID(8); // ID para Editora
        } else {
            id = validar.validarID(editorasAtuais[editorasAtuais.length - 1].getId());
        }

        Editora editora = new Editora(id);
        lidarComFich.salvarEditora(editora);
        System.out.println("Editora '" + editora.getNome() + "' cadastrada com sucesso! ID: " + editora.getId());
    }

    public void listarTodasEditoras() {
        System.out.println("--- Lista de Editoras ---");
        Editora[] editoras = lidarComFich.getEditoras();
        if (editoras.length == 0) {
            System.out.println("Nenhuma editora cadastrada.");
        } else {
            for (Editora editora : editoras) {
                editora.getDetalhes();
                System.out.println("-----------------------");
            }
        }
    }
}