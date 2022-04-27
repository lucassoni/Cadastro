
public class Familiar extends Pessoa {
    public Familiar(String nome, String telefone) {
        super(nome, telefone);
    }

    public void imprimeFamiliar() {
        System.out.println("\nNome: ");
        System.out.println(getNome());

        System.out.println("Telefone: ");
        System.out.println(getTelefone());
    }
}