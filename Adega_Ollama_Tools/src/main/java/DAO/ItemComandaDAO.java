package DAO;

import config.ConexaoBD;
import java.sql.*;

public class ItemComandaDAO {

    public void adicionarItem(Connection conn, int comandaId, int bebidaId, int quantidade) throws SQLException {
        String sql = "INSERT INTO itens_comanda (comandaId, bebidaId, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, comandaId);
            stmt.setInt(2, bebidaId);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
        }
    }

}
