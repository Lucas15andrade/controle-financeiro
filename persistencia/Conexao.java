/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

//Imports gerais

import java.sql.*;

/**
 * Classe que faz a conexão com o banco de dados
 * @author Lucas Andrade
 */

// Classe principal da conexão com o banco de dados

public class Conexao {
    
    private String usuario;
    private String senha;
    private String caminho;
    private String driverjdbc;
    private Connection conexao;
    
    
    public Conexao(String local, String porta, String database, String usuario, String senha){
        this.caminho = "jdbc:mysql://" + local + ":" + porta + "/" + database;
        this.usuario = usuario;
        this.senha = senha;
        this.driverjdbc = "com.mysql.jdbc.Driver";
    }
    
    public void conecta(){
        try {
            Class.forName(driverjdbc);
            conexao = DriverManager.getConnection(caminho, usuario, senha);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
    
    public void desconecta(){
        try {
            conexao.close();
        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
    
    public Connection getConexao(){
        return conexao;
    }
    
}
