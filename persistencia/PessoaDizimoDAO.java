/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modelo.PessoaDizimo;


/**
 *
 * @author Lucas Andrade
 */
public class PessoaDizimoDAO {
    
    private final String INSERT = "INSERT INTO PESSOADIZIMO (IDPESSOA, IDDIZIMO) VALUES(?,?)";
    //private final String DELETE = "DELETE FROM PESSOADIZIMO WHERE IDDIZIMO = ?";
    
    private final String DELETE = "CALL DELETAR(?)";
    //private final String DELETE = "DELETE DIZIMO.*, PESSOADIZIMO.* FROM PESSOADIZIMO, DIZIMO WHERE DIZIMO.IDDIZIMO = ?  AND PESSOADIZIMO.IDDIZIMO = ?";
    
    private final String LISTARDIZIMOS = "SELECT pessoadizimo.idpessoa, pessoadizimo.iddizimo, pessoa.nome, dizimo.valor from pessoadizimo join pessoa on pessoadizimo.idpessoa = pessoa.idpessoa join dizimo on dizimo.iddizimo = pessoadizimo.iddizimo";
    
    private final String CONSULTADIZIMO = "SELECT pessoadizimo.idpessoa, pessoadizimo.iddizimo, pessoa.nome, dizimo.valor from pessoadizimo\n" +
    "join pessoa on pessoadizimo.idpessoa = pessoa.idpessoa\n" +
    "join dizimo on dizimo.iddizimo = pessoadizimo.iddizimo where pessoa.nome like ?";
    
    private final String UPDATE = "UPDATE PESSOADIZIMO SET IDPESSOA = ?, IDDIZIMO = ? WHERE IDDIZIMO = ?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "bancoigreja", "root", "");
    
    
    
    
    public void inserir(PessoaDizimo pd){
        
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(INSERT);
            preparainstrucao.setInt(1, pd.getIdpessoa());
            preparainstrucao.setInt(2, pd.getIddizimo());
            preparainstrucao.execute();
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Erro ao cadastrar pessoadizimo no banco de dados!");
            alert.showAndWait();
            //e.printStackTrace();
        }
 
        
    }
    
    public boolean delete(Integer id){
         //int id2 = 0;
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(DELETE);
            preparainstrucao.setInt(1, id);
            preparainstrucao.execute();           
            con.desconecta();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pessoadizimo do banco de dados");
            e.printStackTrace();
            return false;
        }
        //return id2;
    }
    
    /*
    public boolean delete(Integer iddizimo){
         //int id2 = 0;
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(DELETE);
            preparainstrucao.setInt(1, iddizimo);
            preparainstrucao.setInt(2, iddizimo);
            //preparainstrucao.setInt(3, iddizimo);
            preparainstrucao.execute();
            
            
            con.desconecta();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pessoadizimo do banco de dados");
            e.printStackTrace();
            return false;
        }
        //return id2;
    }
    */
    
    
    
    public ArrayList<PessoaDizimo> listardizimos(){
        
        ArrayList<PessoaDizimo> pessoadizimo = new ArrayList<>();
        
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(LISTARDIZIMOS);
            
            ResultSet rs = preparainstrucao.executeQuery();
            
            while(rs.next() == true){
                
                PessoaDizimo pd = new PessoaDizimo(rs.getInt("idpessoa"), rs.getInt("iddizimo"), rs.getString("nome") ,rs.getDouble("valor"));
                pessoadizimo.add(pd);
                //System.out.println(""+pd.toString());
            }
            
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao listardizimos do banco de dados");
            e.printStackTrace();
        }
        
        return pessoadizimo;
    }
    
    public ArrayList<PessoaDizimo> dizimosPorConsulta(String nomePessoa){
        ArrayList<PessoaDizimo> pd = new ArrayList<>();
        
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(CONSULTADIZIMO);
            preparainstrucao.setString(1, "%"+nomePessoa+"%");
            ResultSet rs = preparainstrucao.executeQuery();
            
            while(rs.next() == true){
                PessoaDizimo pessoadizimo = new PessoaDizimo(rs.getInt("idpessoa"), rs.getInt("iddizimo"), rs.getString("nome") ,rs.getDouble("valor"));
                pd.add(pessoadizimo);
            }
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dizimo por campo");
            e.printStackTrace();
        }
        return pd;
    }
    
    public void atualizar(Integer id1, Integer id2, Integer id3){
        
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(UPDATE);
            preparainstrucao.setInt(1, id1);
            preparainstrucao.setInt(2, id2);
            preparainstrucao.setInt(3, id3);
            preparainstrucao.execute();
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pessoadizimo");
            e.printStackTrace();
        }
        
    }
    
}
