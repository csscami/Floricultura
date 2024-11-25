package controller;

import model.Flor;
import model.FlorRepository;
import view.FlorView;

public class FlorController {
    private FlorRepository repositorio;
    private FlorView view;
    
    public FlorController(FlorRepository repositorio, FlorView view) {
        this.repositorio = repositorio;
        this.view = view;
    }
    
    public void adicionarFlor(Flor flor) {
        repositorio.adicionar(flor);
        view.atualizarListaFlores(repositorio.listarTodas());
    }
    
    public void removerFlor(Flor flor) {
        repositorio.remover(flor);
        view.atualizarListaFlores(repositorio.listarTodas());
    }
    
    public Flor buscarFlorPorId(int id) {
        return repositorio.buscarPorId(id);
    }
}