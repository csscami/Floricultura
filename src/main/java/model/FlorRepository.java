package model;

import java.util.ArrayList;
import java.util.List;

public class FlorRepository {
    private List<Flor> flores = new ArrayList<>();
    
    public void adicionar(Flor flor) {
        flores.add(flor);
    }
    
    public List<Flor> listarTodas() {
        return new ArrayList<>(flores);
    }
    
    public void remover(Flor flor) {
        flores.remove(flor);
    }
    
    public Flor buscarPorId(int id) {
        return flores.stream()
                .filter(flor -> flor.getId() == id)
                .findFirst()
                .orElse(null);
    }
}