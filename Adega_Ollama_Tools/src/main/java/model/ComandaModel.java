package model;

import Enum.Status;
import java.time.LocalDateTime;

public class ComandaModel {

    private int id;
    private LocalDateTime dataCriacao;
    private double valorTotal;
    private Status status;

    public ComandaModel(int id, LocalDateTime dataCriacao, double valorTotal, Status status){
        this.id=id;
        this.dataCriacao=dataCriacao;
        this.valorTotal=valorTotal;
        this.status=status;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public LocalDateTime getDataCriacao() {return dataCriacao;}

    public void setDataCriacao(LocalDateTime dataCriacao) {this.dataCriacao = dataCriacao;}

    public double getValorTotal() {return valorTotal;}

    public void setValorTotal(double valorTotal) {this.valorTotal = valorTotal;}

    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status;}

    @Override
    public String toString(){
        return "Comanda: " + id + " | Data: " + dataCriacao + " | Valor Total: R$" + valorTotal + " | Status: " + status;
    }
}
