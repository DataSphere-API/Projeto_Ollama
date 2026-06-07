package service;

import DAO.BebidaDAO;
import DAO.ComandaDAO;
import DAO.FornecedorDAO;
import DAO.ItemComandaDAO;
import config.ConexaoBD;
import model.BebidaModel;
import model.FornecedorModel;
import java.sql.*;

import java.util.List;

public class AdegaService {

    private BebidaDAO bebidaDAO = new BebidaDAO();
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();


    public String consultarEstoqueIA() {
        List<BebidaModel> bebidas = bebidaDAO.listarTodasBebidas();

        /*transforma a lista de bebidas que criamos, a qual recebeu a
        lista completa do banco através do metodo de listarTodasBebidas da
        classe conexaoDAO, em um texto único para a IA ler*/
        StringBuilder resposta = new StringBuilder("Estoque atual da Adega:\n");
        for (BebidaModel b : bebidas) {
            resposta.append(b.toString()).append("\n");
        }
        /*"resposta" irá colar "Estoque atual da Adega:" e pular uma linha
         e puxará nas próximas linhas o "toString" definido na BebidaModel*/

        return resposta.toString();
    }


    public String consultarFornecedoresIA() {
        List<FornecedorModel> fornecedores = fornecedorDAO.listarTodosFornecedores();

        StringBuilder resposta = new StringBuilder("Fornecedores cadastrados:\n");
        for (FornecedorModel f : fornecedores) {
            resposta.append(f.toString()).append("\n");
        }
        return resposta.toString();
    }

    public String abrirNovaComandaIA() {
        ComandaDAO comandaDAO = new ComandaDAO();
        int id = comandaDAO.criarComanda();

        if (id != -1) {
            return "Comanda número " + id + " foi aberta com sucesso e está pronta para receber itens.";
        } else {
            return "Erro: Não foi possível abrir uma nova comanda no sistema.";
        }
    }

    public String adicionarItemNaComandaIA(int comandaId, int bebidaId, int quantidade) {
        String sqlUpdateEstoque = "UPDATE estoque SET quantidade = quantidade - ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = ConexaoBD.conectar();
            conn.setAutoCommit(false); //inicia a transação s/ salvar no banco

            /*adiciona o item (o conn está como parametro pois irá utilizar a mesma
            conexão que foi aberta nesse metodo).*/
            new ItemComandaDAO().adicionarItem(conn, comandaId, bebidaId, quantidade);

            // 2. Tira do estoque
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateEstoque)) {
                stmt.setInt(1, quantidade);
                stmt.setInt(2, bebidaId);
                int linhasAfetadas = stmt.executeUpdate();

                // Validação de segurança: se não afetou linha, o produto não existe
                if (linhasAfetadas == 0) {
                    throw new SQLException("Bebida com ID " + bebidaId + " não encontrada no estoque.");
                }
            }

            conn.commit(); //se não der erro, tudo será salvo.
            return "Adicionado " + quantidade + " unidade(s) da bebida " + bebidaId + " na comanda " + comandaId + ". Estoque atualizado.";

        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { } //se der erro, nada é salvo (rollback)
            }
            return "Erro ao processar venda: " + e.getMessage() + ". Nenhuma alteração foi feita.";
        } finally {
            //fechamento manual do túnel
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { }
            }
        }
    }

    public String fecharComandaIA(int comandaId) {
        ComandaDAO comandaDAO = new ComandaDAO();
        boolean sucesso = comandaDAO.fecharComanda(comandaId);

        if (sucesso) {
            return "A comanda " + comandaId + " foi fechada com sucesso.";
        } else {
            return "Erro: Não foi possível fechar a comanda " + comandaId + ". Verifique se o ID está correto.";
        }
    }

}