public class Gerente extends Funcionario {
    private double bonus;

    public Gerente(String nome, String cpf, String matricula, double salario, double bonus) {
        super(nome, cpf, matricula, salario);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("Tipo: Gerente | Nome: %s | CPF: %s | Matrícula: %s | Salário: R$ %.2f | Bônus: R$ %.2f",
            getNome(), getCpf(), getMatricula(), getSalario(), getBonus());
    }
}