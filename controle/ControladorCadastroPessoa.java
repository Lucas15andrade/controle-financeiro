/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pessoa;
import persistencia.PessoaDAO;

/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorCadastroPessoa implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoTelefone;
    @FXML
    private TextField campoEndereco;
    @FXML
    private TextField campoRol;
    
    @FXML
    public void botaoCadastro(ActionEvent event){
        
            String nome = campoNome.getText();
            String telefone = campoTelefone.getText();
            String endereco = campoEndereco.getText();
            String rol = campoRol.getText();
            
            Pessoa p = new Pessoa(nome, telefone, rol, endereco);
            PessoaDAO pd = new PessoaDAO();
            pd.inserir(p);
            fechar(event);
    }
    
    @FXML
    public void limpar(ActionEvent event){
       campoNome.setText("");
       campoEndereco.setText("");
       campoRol.setText("");
       campoTelefone.setText("");
    }
    
    @FXML
    public void fechar(ActionEvent event){
        ControladorMember.controller.atualiza();
        
        Stage st = (Stage) campoNome.getScene().getWindow();
	st.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
