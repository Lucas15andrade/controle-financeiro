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
public class PessoaDizimo {
    
    private int idpessoa;
    private int iddizimo;
    private String nome;
    private Double valor;
    
    
    public PessoaDizimo(int idpessoa, int iddizimo){
        this.idpessoa = idpessoa;
        this.iddizimo = iddizimo;
    }
    
    public PessoaDizimo(int idpessoa, int iddizimo, String nome, Double valor){
        this.idpessoa = idpessoa;
        this.iddizimo = iddizimo;
        this.nome = nome;
        this.valor = valor;
    }
    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getIddizimo() {
        return iddizimo;
    }

    public void setIddizimo(int iddizimo) {
        this.iddizimo = iddizimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    

    @Override
    public String toString() {
        return "PessoaDizimo{" + "idpessoa=" + idpessoa + ", iddizimo=" + iddizimo + ", valor=" + valor + ", nome=" + nome + '}';
    }
    
    
    
}
