package DAO;

import config.ConexaoBD;
import model.BebidaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BebidaDAO {

    public List<BebidaModel> listarTodasBebidas() {
        List<BebidaModel> listaDeBebidas = new ArrayList<>();

        String sql = "SELECT * FROM estoque";


        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             /*o objeto "stmt "recebe a conexão com o banco (objeto conn)
              e o script sql (metodo prepareStatmente(objeto sql))*/

             ResultSet rs = stmt.executeQuery()) {
            /*cada vez que o rs é usado, ele conecta ao banco e
            executa a quary do obj sql*/

            while (rs.next()) {
                BebidaModel b = new BebidaModel(
                        rs.getInt("id"),
                        rs.getString("nome_bebida"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                listaDeBebidas.add(b);
                /*enquanto houver linhas c/ conteudo nas tabelas
                do banco, cria um objeto BebidaModel que é adicionado a lista*/
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar bebidas: " + e.getMessage());
        }
        return listaDeBebidas;
    }

}
