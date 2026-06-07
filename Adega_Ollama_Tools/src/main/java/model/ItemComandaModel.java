package model;

public class ItemComandaModel {

    private int id;
    private int comandaId;
    private int bebidaId;
    private int quantidade;

    public ItemComandaModel(int id, int comandaId, int bebidaId, int quantidade) {
        this.id = id;
        this.comandaId = comandaId;
        this.bebidaId = bebidaId;
        this.quantidade = quantidade;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getComandaId() {return comandaId;}

    public void setComandaId(int comandaId) {this.comandaId = comandaId;}

    public int getBebidaId() {return bebidaId;}

    public void setBebidaId(int bebidaId) {this.bebidaId = bebidaId;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    @Override
    public String toString(){
        return "Item: " + id + " | Comanda: " + comandaId + " | Bebida: " + bebidaId + " | Quantidade: " + quantidade;
    }

}
