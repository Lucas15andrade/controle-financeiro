/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.PessoaDizimo;
import persistencia.DizimoDAO;
import persistencia.PessoaDAO;
import persistencia.PessoaDizimoDAO;

/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorMember implements Initializable {

    
    /**
     * Initializes the controller class.
     * 
     * 
     */
    
    static ControladorMember controller;
    
    private ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
    
    private PessoaDAO pd = new PessoaDAO();
    
    @FXML
    private TableView<Pessoa> tabela;
    
    @FXML
    private TableColumn<Pessoa, Integer> colunaId;
    
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    
    @FXML
    private TableColumn<Pessoa, String> colunaTelefone;
    
    @FXML
    private TableColumn<Pessoa, String> colunaRol;
    
    @FXML
    private TableColumn<Pessoa, String> colunaEndereco;
    
    @FXML
    private Button botaoCadastroPessoa;
    
    @FXML
    private Button botaoRemoverPessoa;
    
    @FXML
    private Button botaoEditarPessoa;
    
    @FXML
    private Button botaoAdicionar;
    
    @FXML
    private Button botaoAddDizimo;
    
    @FXML
    public void cliqueBotaoPessoa(ActionEvent event){
        if(event.getSource().equals(botaoCadastroPessoa)){
            try {
                Parent cena = FXMLLoader.load(getClass().getResource("/visao/CadastroPessoa.fxml"));
                Scene scene = new Scene(cena);
                Stage st = new Stage();
                st.setScene(scene);
                st.setResizable(false);
                st.setTitle("Cadastro de Pessoa");
                st.show();
                
                } catch (IOException e) {
                    e.toString();
                    System.err.println("Erro no botão cadastrar pessoa");
                }
        }else{
            
        }
    }
    
    @FXML
    public void cliqueEditar(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/visao/AtualizarPessoa.fxml"));
        try {
            Parent cena = loader.load();
            Scene scene = new Scene(cena);
            Stage st = new Stage();
            st.setScene(scene);
            st.setTitle("Atualizar membro");
            st.setResizable(false);
            
            Pessoa p = tabela.getSelectionModel().getSelectedItem();
            
            ControladorAtualizarPessoa controller;
            controller = loader.getController();
            controller.preencher(p);
            //p.setNome(p.);
            //pd.updatePessoa(p.getIdpessoa(), p.getNome(), p.getTelefone(), p.getRol(), p.getEndereco());
            //atualiza();
            st.show();
        } catch (IOException ex) {
            System.err.println("Erro ao chamar o atualizar pessoa");
        }
        
    }
    
    @FXML 
    public void cliqueDeletar(ActionEvent event){
        Pessoa p = tabela.getSelectionModel().getSelectedItem();
        if(pd.deletar(p.getIdpessoa()) == true){
            tabela.getItems().remove(p);
        }else{
            
        }
        
        
        
    }
    
    @FXML
    public void addDizimo(ActionEvent event){
        try {
            Parent cena = FXMLLoader.load(getClass().getResource("/visao/AdicionarDizimo.fxml"));
            Scene scene = new Scene(cena);
            Stage st = new Stage();
            st.setScene(scene);
            st.setResizable(false);
            st.setTitle("Adicionar Dizimo");
            st.show();
            /*
            Pessoa p = tabela.getSelectionModel().getSelectedItem();
            
            PessoaDizimo pd = new PessoaDizimo(p.getIdpessoa(), 0);
            
            PessoaDizimoDAO dd = new PessoaDizimoDAO();
            dd.inserir(pd);
            */
        } catch (IOException e) {
        }
    }
    
    public int retornaId(){
        Pessoa p = tabela.getSelectionModel().getSelectedItem();
        int id = p.getIdpessoa();
        
        return id;
    }
    
    @FXML
    public void atualiza(){
        tabela.getItems().setAll(pd.listarPessoas());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        pessoas.addAll(pd.listarPessoas());
        tabela.setItems(pessoas);
        colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idpessoa"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("telefone"));
        colunaRol.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("rol"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("endereco"));
        
    }    
    
}
