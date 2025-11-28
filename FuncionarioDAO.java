import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios(nome, cpf, matricula, salario, tipo, bonus) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getMatricula());
            pstmt.setDouble(4, funcionario.getSalario());

            if (funcionario instanceof Gerente) {
                pstmt.setString(5, "GERENTE");
                pstmt.setDouble(6, ((Gerente) funcionario).getBonus());
            } else {
                pstmt.setString(5, "FUNCIONARIO");
                pstmt.setNull(6, java.sql.Types.REAL);
            }
            
            pstmt.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public List<Funcionario> consultarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String matricula = rs.getString("matricula");
                double salario = rs.getDouble("salario");

                if ("GERENTE".equals(tipo)) {
                    double bonus = rs.getDouble("bonus");
                    funcionarios.add(new Gerente(nome, cpf, matricula, salario, bonus));
                } else {
                    funcionarios.add(new Funcionario(nome, cpf, matricula, salario));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }
}