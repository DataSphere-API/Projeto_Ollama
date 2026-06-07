package config;

import java.io.IOException;
import java.io.InputStream; //lê o properties
import java.sql.Connection; //ponte-> conecta o código ao banco
import java.sql.DriverManager; //comunicação do java com o postgres
import java.sql.SQLException;
import java.util.Properties; //classe que gerencia as chaves do arquivo .properties

public class ConexaoBD {

    public static Connection conectar() throws SQLException {
        Properties props = new Properties();

        try (InputStream input = ConexaoBD.class.getClassLoader().getResourceAsStream("banco.properties")) {
            if (input == null) {
                throw new IOException("Arquivo banco.properties não encontrado!");
            }
            props.load(input);

            return DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("userName"),
                    props.getProperty("password")
            );
        } catch (Exception e) {
            throw new SQLException("Falha ao conectar com o banco de dados: " + e.getMessage(), e);
        }

    }
}
