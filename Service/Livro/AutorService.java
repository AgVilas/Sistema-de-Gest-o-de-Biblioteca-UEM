package Service.Livro;
import Util.LidarComFich;
import Util.Validacao;
import model.livro.Autor;
import java.io.IOException;

public class AutorService {
    private LidarComFich lidarComFich;
    private Validacao validar;

    public AutorService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public void cadastrarAutor() throws IOException {
        System.out.println("--- Cadastrar Autor ---");
        Autor[] autoresAtuais = lidarComFich.getAutores();
        String id;
        if (autoresAtuais.length == 0) {
            id = validar.validarID(7); // ID para Autor
        } else {
            id = validar.validarID(autoresAtuais[autoresAtuais.length - 1].getId());
        }
        
        Autor autor = new Autor(id); 
        lidarComFich.salvarAutor(autor);
        System.out.println("Autor '" + autor.getNome() + "' cadastrado com sucesso! ID: " + autor.getId());
    }

    public void listarTodosAutores() {
        System.out.println("--- Lista de Autores ---");
        Autor[] autores = lidarComFich.getAutores();
        if (autores.length == 0) {
            System.out.println("Nenhum autor cadastrado.");
        } else {
            for (Autor autor : autores) {
                autor.getDetalhes();
                System.out.println("----------------------");
            }
        }
    }
}