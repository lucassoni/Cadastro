import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        Paciente user = Cadastro.lePaciente(sc);
        while (true) {
            System.out.printf(
                    "Bem vindo %s\nPara cadastrar um familiar digite 1\nPara cadastrar um medico digite 2\nPara sair digite 3\n",
                    user.getNome());

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Familiar familiar = cadastro.leFamiliar(sc);
                    familiar.imprimeFamiliar();
                    break;
                case 2:
                    Medico medico = cadastro.leMedico(sc);
                    medico.imprimeMedico();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
}
