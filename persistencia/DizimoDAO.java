/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import modelo.Dizimo;

/**
 *
 * @author Lucas Andrade
 */
public class DizimoDAO {
    
    private final String INSERT = "INSERT INTO DIZIMO (VALOR, DATA, CULTO, TALAO) VALUES (?, ?, ?, ?)";
    private final String DELETE = "DELETE FROM DIZIMO WHERE IDDIZIMO = ?";
    private final String LIST = "SELECT * FROM DIZIMO";
    private final String BUSCAIDDIZIMO = "SELECT IDDIZIMO FROM DIZIMO WHERE IDDIZIMO = ? ";
    private final String UPDATE = "UPDATE DIZIMO SET VALOR = ?, DATA = ?, CULTO = ?, TALAO = ? WHERE IDDIZIMO = ?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "bancoigreja", "root", "");
    
    public int inserir(Dizimo d){
        int id = 0;
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            
            //O Statemente.RETURN_GERENATED_KEYS faz a chamada que retortna o id que foi gerado automaticamente no banco
            preparainstrucao = con.getConexao().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            //preparainstrucao.setInt(1, d.getIddizimo());
            preparainstrucao.setDouble(1, d.getValor());
            preparainstrucao.setDate(2, (Date) d.getData());
            preparainstrucao.setString(3, d.getCulto());
            preparainstrucao.setString(4, d.getTalao());
            
            preparainstrucao.execute();
            
            // Recebe o ID que foi gerado no banco
            ResultSet rs = preparainstrucao.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
            //System.out.println(""+d.getIddizimo());
            con.desconecta();
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar dízimo");
            e.printStackTrace();
        }
        //Retorna o ID que foi gerado no banco
        return id;
    }
    public int recebeId(int id){
        return id;
    }
    
    public boolean delete(Integer iddizimo){
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(DELETE);
            preparainstrucao.setInt(1, iddizimo);
            preparainstrucao.execute();
            con.desconecta();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Erro ao deletar dízimo do banco de dados!");
            alert.showAndWait();
            return false;
        }
    }
    
    public ArrayList<Dizimo> listardizimos(){
        ArrayList<Dizimo> dizimos = new ArrayList<>();
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(LIST);
            ResultSet rs = preparainstrucao.executeQuery();
            while(rs.next() == true){
                Dizimo d = new Dizimo(rs.getInt("iddizimo"), rs.getDouble("valor"), rs.getDate("data"), rs.getString("culto"), rs.getString("talao"));
                dizimos.add(d);
            }
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os dizimos");
            e.printStackTrace();
        }
        return dizimos;
    }
    
    public int idDizimo(Integer id){
        
        int idd = 0;
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(BUSCAIDDIZIMO);
            preparainstrucao.setInt(1, id);
            ResultSet rs = preparainstrucao.executeQuery();
            while(rs.next() == true){
                idd = rs.getInt("iddizimo");
            }
            
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar iddizimo no banco de dados");
            e.printStackTrace();
            
        }
        return idd;
    }
    
    public void updateDizimo(Double valor, Date data, String culto, String talao, int iddizimo){
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(UPDATE);
            preparainstrucao.setDouble(1, valor);
            //Removendo data
            preparainstrucao.setDate(2, data);
            preparainstrucao.setString(3, culto);
            preparainstrucao.setString(4, talao);
            preparainstrucao.setInt(5, iddizimo);
            preparainstrucao.execute();
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o dízimo no banco de dados");
            e.printStackTrace();
        }
    }
}
