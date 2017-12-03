/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Dizimo;
import modelo.Pessoa;
import modelo.PessoaDizimo;
import persistencia.DizimoDAO;
import persistencia.PessoaDizimoDAO;

/**
 * FXML Controller class
 *
 * @author Lucas Andrade
 */
public class ControladorAdicionarDizimo implements Initializable {

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
    public void adicionarDizimo(ActionEvent event){
        
        Double valor = Double.parseDouble(campoValor.getText().replace(',', '.'));
        LocalDate data = campoData.getValue();
        String culto = campoCulto.getText();
        String talao = campoTalao.getText();
        
        Date date = java.sql.Date.valueOf(data);
        int idpessoa = ControladorMember.controller.retornaId();
        //System.out.println(""+idpessoa);
        
        Dizimo dizimo = new Dizimo(valor, date, culto, talao);
       
        DizimoDAO d = new DizimoDAO();
        
        // O método inserir de DizimoDAO retorna um inteiro, que é o ID
        // Que foi gerado no banco, sendo auto incremento.
        int id = d.inserir(dizimo);
        
        //System.out.println(""+id);
        
        PessoaDizimo pessoaDizimo = new PessoaDizimo(idpessoa, id);
        PessoaDizimoDAO pdd = new PessoaDizimoDAO();
        
        pdd.inserir(pessoaDizimo);
               
        fechar();
    }
    
    
    @FXML
    public void fechar(){
        ControladorMember.controller.atualiza();
        Stage st = (Stage) campoValor.getScene().getWindow();
	st.close();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
