import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Cadastro {

    public static Paciente lePaciente(Scanner sc) {
        Paciente paciente = (Paciente)ReadObjectFromFile("../../localStorage/user");

        if(paciente == null){
            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();

            System.out.println("Digite seu telefone: ");
            String telefone = sc.nextLine();

            System.out.println("Digite seu endereco: ");
            String endereco = sc.nextLine();

            System.out.println("Digite seu email: ");
            String email = sc.nextLine();

            paciente = new Paciente(nome, telefone, endereco, email);

            WriteObjectToFile(paciente, "../../localStorage/user");
        }
        return paciente;
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
        if(file.exists() != true)
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

    public static Familiar leFamiliar(Scanner sc) {
        System.out.println("Digite o nome do familiar: ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone para contato de emergencia do familiar: ");
        String telefone = sc.nextLine();

        Familiar familiar = new Familiar(nome, telefone);
        
        Random rand = new Random();

        WriteObjectToFile(familiar, "../../localStorage/familiares/" + nome.split(" ")[0] + rand.nextInt(293092039));

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

        Random rand = new Random();

        WriteObjectToFile(medico, "../../localStorage/medicos/" + nome.split(" ")[0] + rand.nextInt(293092039));

        return medico;
    }
}