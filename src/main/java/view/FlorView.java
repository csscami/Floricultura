package view;

import javax.swing.*;
import java.awt.*;
import model.Flor;
import model.FlorRepository;
import controller.FlorController;

public class FlorView extends JFrame {
    private JTextField txtNome, txtPreco;
    private JComboBox<String> cbCategoria;
    private JList<String> listaFlores;
    private DefaultListModel<String> modeloLista;
    private JButton btnAdicionar, btnRemover;
    
    private FlorRepository repositorio;
    private FlorController controller;
    
    public FlorView() {
        setTitle("Floricultura");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inicializarComponentes();
        configurarLayout();
        
        repositorio = new FlorRepository();
        controller = new FlorController(repositorio, this);
    }
    
    private void inicializarComponentes() {
        txtNome = new JTextField(20);
        txtPreco = new JTextField(10);
        
        String[] categorias = {"Rosas", "Orquídeas", "Girassóis", "Lírios"};
        cbCategoria = new JComboBox<>(categorias);
        
        modeloLista = new DefaultListModel<>();
        listaFlores = new JList<>(modeloLista);
        listaFlores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        btnAdicionar = new JButton("Adicionar Flor");
        btnRemover = new JButton("Remover Flor");
        
        btnAdicionar.addActionListener(e -> adicionarFlor());
        btnRemover.addActionListener(e -> removerFlor());
    }
    
    private void configurarLayout() {
        setLayout(new BorderLayout());
        
        JPanel painelEntrada = new JPanel(new GridLayout(4, 2));
        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(txtNome);
        painelEntrada.add(new JLabel("Preço:"));
        painelEntrada.add(txtPreco);
        painelEntrada.add(new JLabel("Categoria:"));
        painelEntrada.add(cbCategoria);
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        
        JScrollPane scrollLista = new JScrollPane(listaFlores);
        
        add(painelEntrada, BorderLayout.NORTH);
        add(scrollLista, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
    
    private void adicionarFlor() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            String categoria = (String) cbCategoria.getSelectedItem();
            
            Flor novaFlor = new Flor(repositorio.listarTodas().size() + 1, nome, preco, categoria);
            controller.adicionarFlor(novaFlor);
            
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço inválido!");
        }
    }
    
    private void removerFlor() {
        int indice = listaFlores.getSelectedIndex();
        if (indice != -1) {
            Flor florSelecionada = repositorio.listarTodas().get(indice);
            controller.removerFlor(florSelecionada);
        }
    }
    
    public void atualizarListaFlores(java.util.List<Flor> flores) {
        modeloLista.clear();
        for (Flor flor : flores) {
            modeloLista.addElement(flor.getNome() + " - R$" + flor.getPreco());
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        cbCategoria.setSelectedIndex(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FlorView().setVisible(true);
        });
    }
}