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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ControladorPrincipal implements Initializable {
	
        @FXML
        private Button botaoMembros;
        
        @FXML
        private Button botaoFinance;
        
        @FXML
        private Button botaoBusca;
        
        @FXML
        private BorderPane borderpane;
        
        @FXML
        private BorderPane borderPrincipal;
        
        @FXML
        private AnchorPane ancoraPrincipal;
        
        @FXML
        public void cliqueBotao(ActionEvent event) throws IOException{
            
            if(event.getSource().equals(botaoMembros)){
                
                try {
                BorderPane cena = FXMLLoader.load(getClass().getResource("/visao/Member.fxml"));
                borderPrincipal.setCenter(cena);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Erro ao carregar membros");
                }
                
            }
            else if(event.getSource().equals(botaoFinance)){
                
                try {
                BorderPane cena = FXMLLoader.load(getClass().getResource("/visao/Finance.fxml"));
                borderPrincipal.setCenter(cena);
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.err.println("Erro ao carregar financeiro");
                }
                
            }
            else if(event.getSource().equals(botaoBusca)){
                try {
                BorderPane cena = FXMLLoader.load(getClass().getResource("/visao/Busca.fxml"));
                borderPrincipal.setCenter(cena);
                } catch (IOException e) {
                    System.err.println("Erro ao carregar busca");
                }
            }
            
        
            
        }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            
	}
	
	
}
