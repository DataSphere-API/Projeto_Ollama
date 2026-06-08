package DAO;

import config.ConexaoBD;
import java.sql.*;

public class ComandaDAO {

    public int criarComanda() {
        String sql = "INSERT INTO comandas (status) VALUES ('Aberta')";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar comanda: " + e.getMessage());
        }
        return -1;
    }

    public boolean fecharComanda(int comandaId) {
        String sql = "UPDATE comandas SET status = 'Fechada' WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comandaId);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.out.println("Erro ao fechar comanda: " + e.getMessage());
            return false;
        }
    }

}
