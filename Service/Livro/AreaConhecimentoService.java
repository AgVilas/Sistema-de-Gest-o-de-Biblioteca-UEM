package Service.Livro;
import Util.LidarComFich;
import Util.Validacao;
import model.livro.AreaConhecimento;
import java.io.IOException;

public class AreaConhecimentoService {
    private LidarComFich lidarComFich;
    private Validacao validar;

    public AreaConhecimentoService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public void cadastrarAreaConhecimento() throws IOException {
        System.out.println("--- Cadastrar Área de Conhecimento ---");
        AreaConhecimento[] areasAtuais = lidarComFich.getAreas();
        String id;
        if (areasAtuais.length == 0) {
            id = validar.validarID(6); // ID para AreaConhecimento
        } else {
            id = validar.validarID(areasAtuais[areasAtuais.length - 1].getId());
        }
       
        AreaConhecimento area = new AreaConhecimento(id); 
        lidarComFich.salvarArea(area);
        System.out.println("Área de conhecimento '" + area.getNome() + "' cadastrada com sucesso! ID: " + area.getId());
    }

    public void listarTodasAreasConhecimento() {
        System.out.println("--- Lista de Áreas de Conhecimento ---");
        AreaConhecimento[] areas = lidarComFich.getAreas();
        if (areas.length == 0) {
            System.out.println("Nenhuma área de conhecimento cadastrada.");
        } else {
            for (AreaConhecimento area : areas) {
                area.getDetalhes();
                System.out.println("-----------------------------");
            }
        }
    }
}