import java.io.*;
import java.util.Scanner;

public class Cadastro {

    public static Paciente lePaciente(Scanner sc) {
        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Digite seu telefone: ");
        String telefone = sc.nextLine();

        System.out.println("Digite seu endereco: ");
        String endereco = sc.nextLine();

        System.out.println("Digite seu email: ");
        String email = sc.nextLine();

        Paciente paciente = new Paciente(nome, telefone, endereco, email);

        return paciente;
    }

    public static Familiar leFamiliar(Scanner sc) {
        System.out.println("Digite o nome do familiar: ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone para contato de emergencia do familiar: ");
        String telefone = sc.nextLine();

        Familiar familiar = new Familiar(nome, telefone);

        return familiar;
    }

    public static Medico leMedico(Scanner sc) {
        System.out.println("Digite o nome do medico: ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone do medico: ");
        String telefone = sc.nextLine();

        System.out.println("Digite o endereco do medico: ");
        String endereco = sc.nextLine();

        Medico medico = new Medico(nome, telefone, endereco);

        return medico;
    }

    public static void main(String[] args) {
        System.out.println(
                "Para cadastrar se cadastrar digite 1, para cadastrar um familiar digite 2, para cadastrar um medico digite 3");

        Scanner sc = new Scanner(System.in);

        int people = sc.nextInt();
        sc.nextLine();
        if (people == 1) {

            Paciente patient = lePaciente(sc);
            patient.imprimePaciente();

        } else if (people == 2) {

            Familiar familiar = leFamiliar(sc);
            familiar.imprimeFamiliar();

        } else if (people == 3) {

            Medico medico = leMedico(sc);
            medico.imprimeMedico();

        } else
            System.out.println("Numero invalido");
    }
}