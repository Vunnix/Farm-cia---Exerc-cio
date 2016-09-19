package Exercicios;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by handyc on 17/09/16.
 */
public class Medicamento {

    private static double desconto = 0.15;
    private int codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private float preco;
    private int estoque;

    private float precoCompra;
    private int qtMedicamento

;



    public Medicamento(){}

    public Medicamento(int codigo, String nome, String descricao, String tipo, float preco){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getQtMedicamento() {
        return qtMedicamento;
    }

    public void setQtMedicamento(int qtMedicamento) {
        this.qtMedicamento = qtMedicamento;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }
    public void imprimir(){
        JOptionPane.showMessageDialog(null, "Código: "+getCodigo()+
                "\nNome: "+getNome()+
                "\nDescrição: "+getDescricao()+
                "\nTipo: "+getTipo()+
                "\nPreço: "+getPreco()+
                "\nQuantidade do medicamento em estoque: "+getEstoque());
    }

    public static float aplicaDesconto(){
        return (float) desconto;
    }

}
