/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Controlador atualizar finance.
package controle;

import java.net.URL;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Dizimo;
import persistencia.DizimoDAO;

/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorAtualizarFinance implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField campoValor;
    
    @FXML
    private DatePicker campoData;
    
    @FXML
    private TextField campoCulto;
    
    @FXML
    private TextField campoTalao;
    
    @FXML
    private Button botaoSalvar;
    
    @FXML
    private Button botaoFechar;
    
    @FXML
    public void preencher(Dizimo d){
        campoCulto.setText(d.getCulto());
        Date data = (Date) d.getData();
        campoData.setValue(data.toLocalDate());
        campoTalao.setText(d.getTalao());
        // Converto de double para string
        campoValor.setText(String.valueOf(d.getValor()));
    }
    
    @FXML
    public void atualizar(ActionEvent event){
        String valor = campoValor.getText();
        LocalDate data = campoData.getValue();
        String culto = campoCulto.getText();
        String talao = campoTalao.getText();
        
        Date date = Date.valueOf(data);
        
        //Crio um objeto de dizimo e converto o campo de String para Double
        Dizimo d = new Dizimo(Double.parseDouble(valor),date, culto, talao);
        int idDizimo = ControladorFinance.controller.retornaId();
        DizimoDAO dd = new DizimoDAO();
        dd.updateDizimo(Double.parseDouble(valor),date, culto, talao, idDizimo);
        fechar(event);
    }
    
    @FXML
    public void fechar(ActionEvent event){
        ControladorFinance.controller.atualiza();
        Stage st = (Stage) campoCulto.getScene().getWindow();
        st.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
