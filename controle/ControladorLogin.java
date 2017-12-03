/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorLogin implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField campoLogin;
    
    @FXML
    private PasswordField campoSenha;
    
    @FXML
    private Button botaoLogin;
    
    @FXML
    public void login(ActionEvent event){
        String login = campoLogin.getText();
        String senha = campoSenha.getText();
        
        if(login.equals("admin") && senha.equals("admin")){
            
            try {
            Parent root = FXMLLoader.load(getClass().getResource("/visao/Principal.fxml"));
            Scene scene = new Scene(root,800,600);
            Stage stage  = new Stage();
            stage.getIcons().add(new Image("/img/icon.png"));
            stage.setScene(scene);
            stage.setTitle("Controlador Financeiro");
            stage.show();
            
            } catch (IOException e) {
            }   
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuário e/ou senha incorretos!");
            alert.setHeaderText("Erro logar no sistema");
            alert.setContentText("O usuário e/ou senha está incorreto. Verifique e tente novamente.");
            alert.showAndWait();
            
            
            
        }
        fechar(event);
    }
    
    @FXML
    public void fechar(ActionEvent event){
        Stage st = (Stage) campoLogin.getScene().getWindow();
	st.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
