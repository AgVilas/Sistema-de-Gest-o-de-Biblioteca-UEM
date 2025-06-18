package Service.Livro;


import Util.LidarComFich;
import Util.Validacao;
import model.livro.Exemplar;
import model.livro.Livro;

import java.io.IOException;

public class ExemplarService {
    private LidarComFich lidarComFich;
    private Validacao validar;
   

    public ExemplarService(LidarComFich lidarComFich, Validacao validar) {
        this.lidarComFich = lidarComFich;
        this.validar = validar;
    }

    public Exemplar buscarExemplarPorId(String id) {
        Exemplar[] exemplares = lidarComFich.getExemplares();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getId().equals(id)) {
                return exemplar;
            }
        }
        return null;
    }

    public void cadastrarExemplar() throws IOException {
        System.out.println("--- Cadastrar Exemplar ---");
        Livro[] livrosDisponiveis = lidarComFich.getLivros();

        if (livrosDisponiveis.length == 0) {
            System.out.println("Nenhum livro cadastrado. Cadastre livros antes de adicionar exemplares.");
            return;
        }

        String id;
        Exemplar[] exemplaresAtuais = lidarComFich.getExemplares();
        if (exemplaresAtuais.length == 0) {
            id = validar.validarID(5); // ID para Exemplar (conforme original)
        } else {
            id = validar.validarID(exemplaresAtuais[exemplaresAtuais.length - 1].getId());
        }

        System.out.println("Livros disponíveis para associar o exemplar:");
        for(int i=0; i < livrosDisponiveis.length; i++){
            System.out.println((i+1) + ". " + livrosDisponiveis[i].getNome() + " (ID: " + livrosDisponiveis[i].getId() + ")");
        }
        
        int escolhaLivro = validar.validarInt("Escolha o número do livro para este exemplar: ", livrosDisponiveis.length, 1);
        Livro livroAssociado = livrosDisponiveis[escolhaLivro - 1];


        Exemplar exemplar = new Exemplar(id, livroAssociado); // Estado padrão é "Disponivel"
        lidarComFich.salvarExemplar(exemplar);
        System.out.println("Exemplar cadastrado com sucesso! ID: " + exemplar.getId());
        System.out.println("Associado ao Livro: " + livroAssociado.getNome() + " (ID: " + livroAssociado.getId() + ")");
        System.out.println("Estado: " + exemplar.getEstado());
    }

    public void listarTodosExemplares() {
        System.out.println("--- Lista de Exemplares ---");
        Exemplar[] exemplares = lidarComFich.getExemplares();
        if (exemplares.length == 0) {
            System.out.println("Nenhum exemplar cadastrado.");
        } else {
            for (Exemplar exemplar : exemplares) {
                exemplar.getDetalhes();
                System.out.println("-------------------------");
            }
        }
    }

    public boolean atualizarExemplar(Exemplar exemplarAtualizado) throws IOException {
        Exemplar[] exemplares = lidarComFich.getExemplares();
        for (int i = 0; i < exemplares.length; i++) {
            if (exemplares[i].getId().equals(exemplarAtualizado.getId())) {
                lidarComFich.actualizarExemplar(i, exemplarAtualizado);
                return true;
            }
        }
        System.out.println("Erro: Exemplar com ID " + exemplarAtualizado.getId() + " não encontrado para atualização.");
        return false;
    }
}