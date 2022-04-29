import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Cadastro{

    public Paciente lePaciente(Scanner sc) {
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

    public Familiar leFamiliar(Scanner sc) {
        System.out.println("Digite o nome do familiar: ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone para contato de emergencia do familiar: ");
        String telefone = sc.nextLine();

        Familiar familiar = new Familiar(nome, telefone);
        
        Random rand = new Random();

        WriteObjectToFile(familiar, "../../localStorage/familiares/" + nome.split(" ")[0] + rand.nextInt(293092039));

        return familiar;
    }

    public Medico leMedico(Scanner sc) {
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

    public InfoEme leInfoEme(Scanner sc) {
        System.out.println("Digite a info emergencial: ");
        String texto = sc.nextLine();

        InfoEme info = new InfoEme(texto);

        Random rand = new Random();

        WriteObjectToFile(info, "../../localStorage/infoeme/" + "ie" + rand.nextInt(293092039));

        return info;
    }

    public ConEx leConEx(Scanner sc) {
        System.out.println("Digite 1 para cadastrar uma consulta ou 2 para um exame");
        int tipo = sc.nextInt();
        sc.nextLine();
        String horario,data;
        if(tipo == 1){
            System.out.println("Digite o horario da consulta ex:13h00m");
            horario = sc.nextLine();

            System.out.println("Digite a data da consulta ex:16/04");
            data = sc.nextLine();
        }
        else if(tipo == 2){
            System.out.println("Digite o horario do exame 13h00m");
            horario = sc.nextLine();
            System.out.println("Digite a data do exame ex:16/04");
            data = sc.nextLine();
        }
        else{
            horario = "invalido";
            data = "invalido";
            System.out.println("Numero invalido");
        }

        ConEx conex = new ConEx(data, tipo, horario);

        Random rand = new Random();

        WriteObjectToFile(conex, "../../localStorage/conex/" + "ce" + rand.nextInt(293092039));

        return conex;
    }
}