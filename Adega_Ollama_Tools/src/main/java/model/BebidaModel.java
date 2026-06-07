package model;

public class BebidaModel {

    private int id;
    private String nomeBebida;
    private int quantidade;
    private double preco;

    public BebidaModel(int id, String nomeBebida, int quantidade, double preco){
        this.id=id;
        this.nomeBebida=nomeBebida;
        this.quantidade=quantidade;
        this.preco=preco;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNomeBebida() {return nomeBebida;}

    public void setNomeBebida(String nomeBebida) {this.nomeBebida = nomeBebida;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public double getPreco() {return preco;}

    public void setPreco(double preco) {this.preco = preco;}

    @Override
    public String toString() {
        return "BebidaModel: " + nomeBebida + " | Qtd: " + quantidade + " | Preço: R$" + preco;
    }

}
