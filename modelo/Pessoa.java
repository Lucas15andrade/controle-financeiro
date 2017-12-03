/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Lucas Andrade
 */
public class Pessoa {
    
    private int idpessoa;
    private String nome;
    private String telefone;
    private String rol;
    private String endereco;
    
    // Construtor recebendo apenas o id, nome e o rol da pessoa
    
    public Pessoa(String nome, String rol){
        this.idpessoa = idpessoa;
        this.nome = nome;
        this.rol = rol;
    }
    
    // Construtor recebendo todos os atributos de pessoa
    
    public Pessoa(int idpessoa, String nome, String telefone, String rol, String endereco){
        this.idpessoa = idpessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.rol = rol;
        this.endereco = endereco;
    }
    
    public Pessoa(String nome, String telefone, String rol, String endereco){
        this.idpessoa = idpessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.rol = rol;
        this.endereco = endereco;
    }
    // Gets e Sets

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
    
}
