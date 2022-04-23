import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Cadastro {

    public static String leEmail(Scanner sc, String membro) {
        System.out.println(String.format("Digite email do %s: ", membro));
        String email = sc.nextLine();

        if (!email.contains("@") || !email.endsWith(".com")) {
            System.out.println("Email digitado invalido.");
            email = leEmail(sc, membro);
        }

        return email;
    }

    public static String leEndereco(Scanner sc, String membro) {
        System.out.println(String.format("Digite endereco do %s: ", membro));
        String endereco = sc.nextLine();

        return endereco;
    }

    public static String leTelefone(Scanner sc, String membro) {
        System.out.println(String.format("Digite o telefone (com DDD) para contato %s:\nEx: (xx) xxxxx-xxxx", membro));
        String telefone = sc.nextLine();

        if (telefone.charAt(0) != '(' || telefone.charAt(3) != ')' || telefone.charAt(4) != ' '
                || telefone.length() != 15 || telefone.charAt(10) != '-') {
            System.out.println("Numero invalido digitado.\n");
            return leTelefone(sc, membro);
        }
        return telefone;
    }

    public static String leNome(Scanner sc, String membro) {
        System.out.println(String.format("Digite o nome do %s completo: ", membro));
        String nome = sc.nextLine();

        if (countWordsUsingSplit(nome) <= 1) {
            System.out.println("Nome digitado invalido. Precisamos do nome completo");
            return leNome(sc, membro);
        }
        return nome;
    }

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }

    private static void WriteObjectToFile(Object serObj, String filepath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Object ReadObjectFromFile(String filepath) {
        File file = new File(filepath);
        if (file.exists() != true)
            return null;
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Paciente lePaciente(Scanner sc) {
        Paciente paciente = (Paciente) ReadObjectFromFile("../../localStorage/user");

        if (paciente == null) {
            String nome = leNome(sc, "paciente");

            String telefone = leTelefone(sc, "do paciente");

            String endereco = leEndereco(sc, "paciente");

            String email = leEmail(sc, "paciente");

            paciente = new Paciente(nome, telefone, endereco, email);

            WriteObjectToFile(paciente, "../../localStorage/user");
        }
        return paciente;
    }

    public static Familiar leFamiliar(Scanner sc) {

        String nome = leNome(sc, "familiar");

        String telefone = leTelefone(sc, "de emergencia do familiar");

        Familiar familiar = new Familiar(nome, telefone);

        Random rand = new Random();

        WriteObjectToFile(familiar, "../../localStorage/familiares/" + nome.split(" ")[0] + rand.nextInt(293092039));

        return familiar;
    }

    public static Medico leMedico(Scanner sc) {
        String nome = leNome(sc, "medico");

        String telefone = leTelefone(sc, "medico");

        String endereco = leEndereco(sc, "medico");

        Medico medico = new Medico(nome, telefone, endereco);

        Random rand = new Random();

        WriteObjectToFile(medico, "../../localStorage/medicos/" + nome.split(" ")[0] + rand.nextInt(293092039));

        return medico;
    }
}