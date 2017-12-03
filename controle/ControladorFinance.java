/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Dizimo;
import modelo.Pessoa;
import persistencia.DizimoDAO;


/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorFinance implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    static ControladorFinance controller;
    
    private DizimoDAO dizimoDAO = new DizimoDAO();
    
    private ObservableList<Dizimo> dizimos = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Dizimo> tabela;
    
    @FXML
    private TableColumn<Dizimo, Integer> colunaId;
    
    @FXML
    private TableColumn<Dizimo, Double> colunaValor;
    
    @FXML
    private TableColumn<Dizimo, Date> colunaData;
    
    @FXML
    private TableColumn<Dizimo, String> colunaCulto;
    
    @FXML
    private TableColumn<Dizimo, String> colunaTalao;
    
    @FXML
    private Button botaoCriar;
    
    @FXML
    private Button botaoRemover;
    
    @FXML
    private Button botaoEditar;
    
    @FXML
    public void cliqueFinance(ActionEvent event){
        
        if(event.getSource().equals(botaoCriar)){
            try {
                Parent cena = FXMLLoader.load(getClass().getResource("/visao/AdicionarDizimoNull.fxml"));
                Scene scene = new Scene(cena);
                Stage st = new Stage();
                st.setScene(scene);
                st.setResizable(false);
                st.setTitle("Cadastro de Pessoa");
                st.show();
            } catch (Exception e) {
                System.err.println("Erro ao abrir AdicionarDizimoNull");
            }
        } else if(event.getSource().equals(botaoRemover)){
            Dizimo d = tabela.getSelectionModel().getSelectedItem();
            
            if(dizimoDAO.delete(d.getIddizimo()) == true){
                tabela.getItems().remove(d);
            }else{
                
            }  
        } else if(event.getSource().equals(botaoEditar)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/visao/AtualizarFinance.fxml"));
            try {
                Parent cena = loader.load();
                Scene scene = new Scene(cena);
                Stage st = new Stage();
                st.setScene(scene);
                st.setTitle("Atualizar dízimo");
                st.setResizable(false);
                st.show();
                Dizimo d = tabela.getSelectionModel().getSelectedItem();
                ControladorAtualizarFinance controller;
                
                controller = loader.getController();
                controller.preencher(d);
                
            } catch (IOException e) {
                System.out.println("Erro ao abir atualizarfinance");
            }
        }
    }
    @FXML
    public void atualiza(){
        tabela.getItems().setAll(dizimoDAO.listardizimos());
    }
    
    public int retornaId(){
        Dizimo d = tabela.getSelectionModel().getSelectedItem();
        int id = d.getIddizimo();
        return id;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        dizimos.addAll(dizimoDAO.listardizimos());
        tabela.setItems(dizimos);
        colunaId.setCellValueFactory(new PropertyValueFactory<Dizimo, Integer>("iddizimo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<Dizimo, Double>("valor"));
        colunaData.setCellValueFactory(new PropertyValueFactory<Dizimo, Date>("data"));
        colunaCulto.setCellValueFactory(new PropertyValueFactory<Dizimo, String>("culto"));
        colunaTalao.setCellValueFactory(new PropertyValueFactory<Dizimo, String>("talao"));
    }    
    
}
