import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {

    private static final String URL = "jdbc:sqlite:empresa.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS funcionarios ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "nome TEXT NOT NULL,"
                   + "cpf TEXT NOT NULL UNIQUE,"
                   + "matricula TEXT NOT NULL UNIQUE,"
                   + "salario REAL NOT NULL,"
                   + "tipo TEXT NOT NULL,"
                   + "bonus REAL"
                   + ");";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}