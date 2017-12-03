/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import modelo.Pessoa;

/**
 *
 * @author Lucas Andrade
 */
public class PessoaDAO {
    
    private final String INSERT = "INSERT INTO PESSOA (NOME, TELEFONE, ROL, ENDERECO) VALUES (?, ?, ?, ?)";
    private final String DELETE = "DELETE FROM PESSOA WHERE IDPESSOA = ?";
    private final String LIST = "SELECT * FROM PESSOA";
    private final String BUSCAIDPESSOA = "SELECT IDPESSOA FROM PESSOA WHERE ROL = ?";
    private final String UPDATE = "UPDATE PESSOA SET NOME = ?, TELEFONE = ?, ROL = ?, ENDERECO = ? WHERE IDPESSOA = ?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "bancoigreja", "root", "");
    
    public void inserir(Pessoa p){
        
        try {
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(INSERT);
            
            //preparainstrucao.setInt(1, p.getIdpessoa());
            preparainstrucao.setString(1, p.getNome());
            preparainstrucao.setString(2, p.getTelefone());
            preparainstrucao.setString(3, p.getRol());
            preparainstrucao.setString(4, p.getEndereco());
            
            preparainstrucao.execute();
            
            con.desconecta();
           
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pessoa no banco de dados");
            e.printStackTrace();
        }
        
    }
    
    
    public boolean deletar(Integer id){
        
        try {
            
            con.conecta();
        
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(DELETE);
            
            preparainstrucao.setInt(1, id);
            preparainstrucao.execute();
            
            con.desconecta();
            return true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Erro ao deletar pessoa do banco de dados!");
            alert.showAndWait();
            return false;
        }
        
        
    }
    
    //Método que retorna todas a pessoas cadastradas
    
    public ArrayList<Pessoa> listarPessoas(){
        
        //Criando arraylist da classe pessoa
        
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();
        
        try {
            
            //Conecta com o banco
            con.conecta();
            
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(LIST);
            
            //Executa a instrução
            ResultSet rs = preparainstrucao.executeQuery();
            
            while(rs.next() == true){
                // Criando objeto de pessoa
                /* Pega cada campo da tabela que é retornada para passar no construtor de Pessoa ao criar
                o objeto p */
                Pessoa p = new Pessoa(rs.getInt("idpessoa"), rs.getString("nome"), rs.getString("telefone"), rs.getString("rol"), rs.getString("endereco"));
                
                //Adiciona o objeto de pessoa ao ArrayList para em seguida retornar a lista
                
                listaPessoa.add(p);
            }
            
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao listar pesssoas cadastradas no banco de dados");
            e.printStackTrace();
        }
        
        return listaPessoa;
    }
    
    public int idPessoa(String rol){
        int idp = 0;
        try {
            
            con.conecta();
            PreparedStatement preparainstrucao;
            preparainstrucao = con.getConexao().prepareStatement(BUSCAIDPESSOA);
            preparainstrucao.setString(1, rol);
            
            ResultSet rs = preparainstrucao.executeQuery();
            
            while(rs.next() == true){
                idp = rs.getInt("idpessoa");
            }
            
            con.desconecta();
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar idpessoa pelo número do rol");
            e.printStackTrace();
        }
        return idp;
    }
    
    public void updatePessoa(String nome, String telefone, String rol, String endereco, int id){
        System.out.println(""+id);
        //Integer id = idPessoa(rol);
        //id = idPessoa(rol);
        try {
            
            //Conecta com o banco
            con.conecta();
  
            //Exeuta as instruções
            
            PreparedStatement preparainstrucao;
            /*
            PreparedStatement preparainstrucao2;
            preparainstrucao2 = con.getConexao().prepareStatement(BUSCAIDPESSOA);
            preparainstrucao2.setString(1, rol);
            //int id = preparainstrucao2.execute()Query;
            
            ResultSet rs = preparainstrucao2.executeQuery();

            while(rs.next()){
                id = rs.getInt("idpessoa");
            }
            */
            preparainstrucao = con.getConexao().prepareStatement(UPDATE);
            
            preparainstrucao.setString(1, nome);
            preparainstrucao.setString(2, telefone);
            preparainstrucao.setString(3, rol);
            preparainstrucao.setString(4, endereco);
            preparainstrucao.setInt(5, id);
            preparainstrucao.execute();
            //Desconecta do banco
            con.desconecta();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar informações da pessoa");
            e.printStackTrace();
        }
    }
    
}
