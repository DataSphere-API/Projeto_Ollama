package model;

public class FornecedorModel {

    private int id;
    private String nomeFornecedor;
    private String telefone;
    private String produtoFornecido;

    public FornecedorModel(int id, String nomeFornecedor, String telefone, String produtoFornecido){
        this.id=id;
        this.nomeFornecedor=nomeFornecedor;
        this.telefone=telefone;
        this.produtoFornecido=produtoFornecido;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNomeFornecedor() {return nomeFornecedor;}

    public void setNomeFornecedor(String nomeFornecedor) {this.nomeFornecedor = nomeFornecedor;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getProdutoFornecido() {return produtoFornecido;}

    public void setProdutoFornecido(String produtoFornecido) {this.produtoFornecido = produtoFornecido;}

    @Override
    public String toString() {
        return "Fornecedor: " + nomeFornecedor + " | Contato: " + telefone + " | Produto: " + produtoFornecido;
    }

}
