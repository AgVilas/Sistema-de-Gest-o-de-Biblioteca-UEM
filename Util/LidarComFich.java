package Util;


import java.io.*;
import java.util.ArrayList;

import model.Emprestimo;
import model.usuario.Usuario;
import model.livro.AreaConhecimento;
import model.livro.Autor;
import model.livro.Editora;
import model.livro.Exemplar;
import model.livro.Livro;
import model.livro.PalavraChave;

public class LidarComFich {
    private static String fich_usuario = "usuarios.dat";
    private static String fich_livro = "livros.dat";
    private static String fich_emprestimo = "emprestimos.dat";
    private static String fich_autor = "autores.dat";
    private static String fich_area = "areas.dat";
    private static String fich_palavra = "palavraschave.dat";
    private static String fich_editora = "editoras.dat";
    private static String fich_exemplar = "exemplares.dat";


    private ArrayList<Livro>               livros      = new ArrayList<>();
    private ArrayList<Emprestimo>          emprestimos = new ArrayList<>() ;
    private ArrayList<Usuario>             usuarios    = new ArrayList<>();
    private ArrayList<Autor>               autores = new ArrayList<>();
    private ArrayList<AreaConhecimento>    areas = new ArrayList<>();
    private ArrayList<PalavraChave>        palavras = new ArrayList<>();
    private ArrayList<Editora>             editoras = new ArrayList<>();
    private ArrayList<Exemplar>            exemplares = new ArrayList<>();

    public LidarComFich() {
        inicializarArrays(fich_livro);
        inicializarArrays(fich_usuario);
        inicializarArrays(fich_emprestimo);
        inicializarArrays(fich_autor);
        inicializarArrays(fich_area);
        inicializarArrays(fich_palavra);
        inicializarArrays(fich_editora);
        inicializarArrays(fich_exemplar);

    }

    public Usuario[] getUsuarios() {
        return usuarios.toArray(new Usuario[usuarios.size()]);
    }

    public Livro[] getLivros() {
        return livros.toArray(new Livro[livros.size()]);
    }

    public Emprestimo[] getEmprestimos() {
        return emprestimos.toArray(new Emprestimo[emprestimos.size()]);
    }
    public Autor[] getAutores() {
        return autores.toArray(new Autor[autores.size()]);
    }

    public AreaConhecimento[] getAreas() {
        return areas.toArray(new AreaConhecimento[areas.size()]);
    }

    public Editora[] getEditoras() {
        return editoras.toArray(new Editora[editoras.size()]);
    }

    public Exemplar[] getExemplares() {
        return exemplares.toArray(new Exemplar[exemplares.size()]);
    }

    public PalavraChave[] getPalavrasChave() {
        return palavras.toArray(new PalavraChave[palavras.size()]);
    }

    public void actualizarUsuario(int indice, Usuario est) throws IOException {
        usuarios.set(indice, est);
        actualizarFich("usuario");
    }

    public void actualizarLivro(int indice, Livro est) throws IOException {
        livros.set(indice, est);
        actualizarFich("livro");
    }

    public void actualizarEmprestimo(int indice, Emprestimo est) throws IOException {
        emprestimos.set(indice, est);
        actualizarFich("emprestimo");
    }

    public void actualizarAutor(int indice, Autor est) throws IOException {
        autores.set(indice, est);
        actualizarFich("autor");
    }

    public void actualizarArea(int indice, AreaConhecimento est) throws IOException {
        areas.set(indice, est);
        actualizarFich("area");
    }

    public void actualizarPalavraChave(int indice, PalavraChave est) throws IOException {
        palavras.set(indice, est);
        actualizarFich("palavra");
    }

    public void actualizarEditora(int indice, Editora est) throws IOException {
        editoras.set(indice, est);
        actualizarFich("editora");
    }

    public void actualizarExemplar(int indice, Exemplar est) throws IOException { 
        exemplares.set(indice, est);
        actualizarFich("exemplar");
    }

    public void salvarUsuario(Usuario usuario) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_usuario))) {
            usuarios.add(usuario);
            obout.writeObject(usuarios);
            obout.close();
        } catch (IOException o) {
            System.out.println("IOException");
        }

    }

    public void salvarLivro(Livro livro) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_livro, true))) {
            livros.add(livro);
            obout.writeObject(livros);
        }
    }

    public void salvarEmprestimo(Emprestimo emprestimo) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_emprestimo, true))) {
            emprestimos.add(emprestimo);
            obout.writeObject(emprestimos);
        }
    }

    public void salvarAutor(Autor autor) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_autor))) {
            autores.add(autor);
            obout.writeObject(autores);
        }
    }
    public void salvarArea(AreaConhecimento area) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_area))) {
            areas.add(area);
            obout.writeObject(areas);
        }
    }
    public void salvarPalavraChave(PalavraChave palavra) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_palavra))) {
            palavras.add(palavra);
            obout.writeObject(palavras);
        }
    }
    public void salvarEditora(Editora editora) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_editora))) {
            editoras.add(editora);
            obout.writeObject(editoras);
        }
    }
    public void salvarExemplar(Exemplar exemplar) throws IOException {
        try (ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(fich_exemplar))) {
            exemplares.add(exemplar);
            obout.writeObject(exemplares);
        }
    }

    private void actualizarFich(String tipo) {
        try {
            ObjectOutputStream obout;
            if (tipo.equalsIgnoreCase("usuario")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_usuario));
                obout.writeObject(usuarios);
                obout.close();
            } else if (tipo.equalsIgnoreCase("livro")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_livro));
                obout.writeObject(livros);
                obout.close();
            } else if (tipo.equalsIgnoreCase("emprestimo")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_emprestimo));
                obout.writeObject(emprestimos);
                obout.close();
            } else if (tipo.equalsIgnoreCase("autor")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_autor));
                obout.writeObject(autores);
                obout.close();
            } else if (tipo.equalsIgnoreCase("area")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_area));
                obout.writeObject(areas);
                obout.close();
            } else if (tipo.equalsIgnoreCase("palavra")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_palavra));
                obout.writeObject(palavras);
                obout.close();
            } else if (tipo.equalsIgnoreCase("editora")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_editora));
                obout.writeObject(editoras);
                obout.close();
            } else if (tipo.equalsIgnoreCase("exemplar")) {
                obout = new ObjectOutputStream(new FileOutputStream(fich_exemplar));
                obout.writeObject(exemplares);
                obout.close();
            } else {
                System.out.println("Tipo desconhecido: " + tipo);
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @SuppressWarnings("unchecked")
    private void inicializarArrays(String nome_fich) {
        try (ObjectInputStream obin = new ObjectInputStream(new FileInputStream(nome_fich))){
            if(nome_fich.equalsIgnoreCase(fich_emprestimo)){
                emprestimos = (ArrayList<Emprestimo>)obin.readObject();
            }else if(nome_fich.equalsIgnoreCase(fich_livro)) {
                livros = (ArrayList<Livro>)obin.readObject();
            } else if(nome_fich.equalsIgnoreCase(fich_usuario)) {
                usuarios = (ArrayList<Usuario>)obin.readObject();
            } else if(nome_fich.equalsIgnoreCase(fich_autor)) {
                autores = (ArrayList<Autor>)obin.readObject();
            } else if(nome_fich.equalsIgnoreCase(fich_area)) {
                areas = (ArrayList<AreaConhecimento>)obin.readObject();
            } else if(nome_fich.equalsIgnoreCase(fich_palavra)) {
                palavras = (ArrayList<PalavraChave>)obin.readObject();
            } else if(nome_fich.equalsIgnoreCase(fich_editora)) {
                editoras = (ArrayList<Editora>)obin.readObject();
            } else if(nome_fich.equalsIgnoreCase(fich_exemplar)) {
                exemplares = (ArrayList<Exemplar>)obin.readObject();
            }
            obin.close();
        } catch (ClassNotFoundException t) {
            System.out.println("ClassNotFoundException");
        } catch (FileNotFoundException e) {
            //System.out.println("Um dos 3 arquivos nao foi encontrado");
        } catch(ClassCastException e){}catch (IOException a) {
            System.out.println("Erro");
            System.out.println("");
        }
    }
}