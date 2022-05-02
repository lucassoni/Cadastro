import java.util.Scanner;
import java.io.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class Edicao {
    public static void menuEdicaoConsulta(Scanner sc, Listagem listagem) {
        System.out.println("Escolha a consulta a ser editada\n");
        listagem.listaConsultas();
        int option = sc.nextInt();
        sc.nextLine();

        ArrayList<Consulta> consultas = listagem.getConsultas();

        Consulta consultaEscolhida = consultas.get(option - 1);

        System.out.println("\nConsulta escolhida:\n");
        consultaEscolhida.imprimeConsulta();

        boolean sair = false;
        while (!sair) {
            System.out.println("Quer editar ou cancelar a consulta?\nDigite 1 para editar\nDigite 2 para cancelar\n");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaConsulta(sc, consultaEscolhida, listagem);
                    sair = true;
                    break;
                case 2:
                    deletaConsulta(consultaEscolhida, listagem, option - 1);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }

    public static File procuraConsulta(Consulta consulta, Listagem listagem) {
        File file = new File("./resources/localStorage/consultas");
        File fileConsulta = null;
        if (file.exists()) {
            File[] consultas = file.listFiles();
            for (File c : consultas) {
                Consulta candidata = (Consulta) ReadObjectFromFile(c.getPath());
                if (comparaConsulta(candidata, consulta)) {
                    fileConsulta = c;
                }
            }
        }
        return fileConsulta;
    }

    public static void deletaConsulta(Consulta consulta, Listagem listagem, int index) {
        File fileConsulta = procuraConsulta(consulta, listagem);
        fileConsulta.delete();

        ArrayList<Consulta> consultas = listagem.getConsultas();
        consultas.clear();
        listagem.carregarConsultas();
    }

    public static void editaConsulta(Scanner sc, Consulta consulta, Listagem listagem) {
        File fileConsulta = procuraConsulta(consulta, listagem);

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Para editar o nome do medico digite 1\nPara editar o horario e data da consulta digite 2\n");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaNome(sc, consulta, listagem);
                    sair = true;
                    break;
                case 2:
                    editaData(sc, consulta);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }

        WriteObjectToFile(consulta, fileConsulta.getPath());
    }

    public static void menuUpload(Scanner sc, Listagem listagem, ArrayList<Consulta> consultas) {
        if (consultas.isEmpty()) {
            System.out.println("Nao existem consultas expiradas para fazer upload");
            return;
        }

        System.out.println("Essas sao as consultas disponiveis para upload de diagnostico e prescricao:\n");
        int i = 0;
        for (Consulta c : consultas) {
            System.out.println(String.format("Consulta %d", i + 1));
            c.imprimeConsulta();
            i++;
        }

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Favor digitar o numero da consulta para fazer upload\n");
            int option = sc.nextInt();
            sc.nextLine();
            if (consultas.size() + 1 > option && option > 0) {
                File fileConsulta = procuraConsulta(consultas.get(option - 1), listagem);

                System.out.println("Favor digitar diagnostico da consulta\n");
                consultas.get(option - 1).setDiagnostico(sc.nextLine());

                System.out.println("Favor digitar a prescricao da consulta\n");
                consultas.get(option - 1).setPrescricoes(sc.nextLine());

                WriteObjectToFile(consultas.get(option - 1), fileConsulta.getPath());

                sair = true;
                listagem.getConsultas().clear();
                listagem.carregarConsultas();
            } else {
                System.out.println("Numero invalido");
            }

        }
    }

    public static void editaData(Scanner sc, Consulta consulta) {
        Calendar novaData = Cadastro.leData(sc, "da consulta");
        consulta.setData(novaData);
    }

    public static void editaNome(Scanner sc, Consulta consulta, Listagem listagem) {
        Medico medico = Cadastro.procuraMedico(sc, listagem);
        consulta.setMedico(medico);
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

    private static void WriteObjectToFile(Object serObj, String filepath) {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean comparaMedico(Medico x, Medico y) {
        if (x == y)
            return true;
        if (x == null || y == null || x.getClass() != y.getClass())
            return false;
        return x.getNome().equals(y.getNome()) &&
                x.getTelefone().equals(y.getTelefone()) &&
                x.getEndereco().equals(y.getEndereco());

    }

    public static boolean comparaConsulta(Consulta x, Consulta y) {
        if (x == y)
            return true;
        if (x == null || y == null || x.getClass() != y.getClass())
            return false;
        return comparaMedico(x.getMedico(), y.getMedico()) &&
                x.getData().equals(y.getData());
    }

}
