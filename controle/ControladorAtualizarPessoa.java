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
 *
 * @author Lucas Andrade
 */
public class ControladorAtualizarPessoa implements Initializable{
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextField campoTelefone;
    
    @FXML
    private TextField campoEndereco;
    
    @FXML
    private TextField campoRol;
    
    @FXML
    private TextField campoId;
    
    @FXML
    private Button botaoSalvar;
    
    @FXML
    private Button botaoLimpar;
    
    @FXML
    private Button botaoFechar;
    
    @FXML
    public void preencher(Pessoa p){
        campoNome.setText(p.getNome());
        campoEndereco.setText(p.getEndereco());
        campoRol.setText(p.getRol());
        campoTelefone.setText(p.getTelefone());
    }
    
    @FXML
    public void atualizarPessoa(ActionEvent event){
        String nome = campoNome.getText();
        String telefone = campoTelefone.getText();
        String endereco = campoEndereco.getText();
        String rol = campoRol.getText();
        //Integer id = Integer.parseInt(campoId.getText());
        int idpessoa = ControladorMember.controller.retornaId();
        PessoaDAO pd = new PessoaDAO();
        pd.updatePessoa(nome, telefone, rol, endereco, idpessoa);
        fechar(event);
    }

    @FXML
    public void fechar(ActionEvent event){
        ControladorMember.controller.atualiza();
        Stage st = (Stage) campoNome.getScene().getWindow();
	st.close();
    }
    
    @FXML
    public void limpar(ActionEvent event){
       campoNome.setText("");
       campoEndereco.setText("");
       campoRol.setText("");
       campoTelefone.setText("");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
