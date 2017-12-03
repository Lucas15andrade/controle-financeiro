/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Lucas Andrade
 */
public class Dizimo {
    
    private int iddizimo;
    private Double valor;
    private Date data;
    private String culto;
    private String talao;
    
    // Construtor recebendo apenas id, valor e o talao
    
    public Dizimo(Double valor, String talao, Date data){
        this.data = data;
        this.valor = valor;
        this.talao = talao;
    }
    
    // Construtor não recebendo data devido o ControladorAtualizarFinance
    // Ainda não foi convertido de LocalDate para Date
    
    public Dizimo(Double valor, String talao, String culto){
        this.valor = valor;
        this.talao = talao;
        this.culto = culto;
    }
    
    // Construtor recebendo todos os campos de dizimo
    
    public Dizimo(int iddizimo, Double valor, Date data, String culto, String talao){
        this.iddizimo = iddizimo;
        this.valor = valor;
        this.data = data;
        this.culto = culto;
        this.talao = talao;
    }
    
    public Dizimo(Double valor, Date data, String culto, String talao){
        this.iddizimo = iddizimo;
        this.valor = valor;
        this.data = data;
        this.culto = culto;
        this.talao = talao;
    }

    // Gets e Sets
    
    public int getIddizimo() {
        return iddizimo;
    }

    public void setIddizimo(int iddizimo) {
        this.iddizimo = iddizimo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCulto() {
        return culto;
    }

    public void setCulto(String culto) {
        this.culto = culto;
    }

    public String getTalao() {
        return talao;
    }

    public void setTalao(String talao) {
        this.talao = talao;
    }
    
    
}
