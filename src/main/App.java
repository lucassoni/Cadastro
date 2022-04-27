import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Paciente user = Cadastro.lePaciente(sc);
        Listagem listagem = new Listagem();
        listagem.carregarMedicos();
        while (true) {
            System.out.printf(
                    "Bem vindo %s\nPara cadastrar um familiar digite 1\nPara cadastrar um medico digite 2\nPara listar os medicos digite 3\nPara sair digite 4\n",
                    user.getNome());

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Familiar familiar = Cadastro.leFamiliar(sc);
                    familiar.imprimeFamiliar();
                    break;
                case 2:
                    Medico medico = Cadastro.leMedico(sc);
                    medico.imprimeMedico();
                    break;
                case 3:
                    listagem.listaMedicos();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
}
