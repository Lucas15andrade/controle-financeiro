/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import static controle.ControladorFinance.controller;
import static controle.ControladorMember.controller;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Dizimo;
import modelo.Pessoa;
import modelo.PessoaDizimo;
import persistencia.PessoaDizimoDAO;

/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorBusca implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    static ControladorBusca controller;
    
    private PessoaDizimoDAO pdDAO = new PessoaDizimoDAO();

    private ObservableList<PessoaDizimo> pessoadizimo = FXCollections.observableArrayList();
    
    @FXML
    private TableView<PessoaDizimo> tabela;
    
    @FXML
    private TableColumn<PessoaDizimo, Integer> colunaIdPessoa;
    
    @FXML
    private TableColumn<PessoaDizimo, Integer> colunaIdDizimo;
    
    @FXML
    private TableColumn<PessoaDizimo, String>  nome;
    
    @FXML
    private TableColumn<PessoaDizimo, Double> valor;
    
    @FXML
    private TextField campoConsulta;
    
    @FXML
    private Button botaoBusca;
    
    @FXML
    private Button botaoRemover;
    
    @FXML
    public void buscar(ActionEvent event){
        try {
            String nomeBusca = campoConsulta.getText();
            pessoadizimo.clear();
            pessoadizimo.addAll(pdDAO.dizimosPorConsulta(nomeBusca));
            tabela.setItems(pessoadizimo);
            colunaIdPessoa.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, Integer>("idpessoa"));
            colunaIdDizimo.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, Integer>("iddizimo"));
            nome.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, String>("nome"));
            valor.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, Double>("valor"));
        } catch (Exception e) {
        }
    }
    
    @FXML
    public void remover(ActionEvent event){
        PessoaDizimo pessoadizimo = tabela.getSelectionModel().getSelectedItem();
        System.out.println("id da pessoa"+pessoadizimo.getIdpessoa());
        pdDAO.delete(pessoadizimo.getIddizimo());
        tabela.getItems().remove(pessoadizimo);
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        pessoadizimo.addAll(pdDAO.listardizimos());
        tabela.setItems(pessoadizimo);
        colunaIdPessoa.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, Integer>("idpessoa"));
        colunaIdDizimo.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, Integer>("iddizimo"));
        nome.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, String>("nome"));
        valor.setCellValueFactory(new PropertyValueFactory<PessoaDizimo, Double>("valor"));
        
    }    
    
}
