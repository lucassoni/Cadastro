package src.main;
import java.io.*;
import java.io.Serializable;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class InfoEme implements Serializable {
    private String info;

    public InfoEme() {
    };

    public InfoEme(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void imprimeInfoEme() {
        System.out.println(getInfo());
    }

    public void deleta(Scanner sc, Listagem listagem) {
        ArrayList<InfoEme> inf = listagem.getInfoEme();
        inf.clear();
        listagem.carregarInfoEme();

        listagem.listaInfoEme();

        System.out.println("\nDigite o numero da informacao que deseja deletar: ");
        int ndelete = sc.nextInt();
        int cont = 1;
        sc.nextLine();

        File file = new File("./resources/localStorage/infoeme");
        File[] infoeme = file.listFiles();
        for (int i = 0; i < infoeme.length; i++) {
            if (cont == ndelete) {
                if (infoeme[i].delete()) {
                    System.out.println("\nInformacao deletada!");
                }
            }
            cont++;
        }
        if (ndelete > infoeme.length) {
            System.out.println("\nNumero invalido!");
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

    public void edita(Scanner sc, Listagem listagem) {
        ArrayList<InfoEme> inf = listagem.getInfoEme();
        inf.clear();
        listagem.carregarInfoEme();

        listagem.listaInfoEme();

        System.out.println("\nDigite o numero da informacao que deseja editar: ");
        int nedita = sc.nextInt();
        int cont = 1;
        sc.nextLine();

        File file = new File("./resources/localStorage/infoeme");
        File[] infoeme = file.listFiles();
        if (nedita <= infoeme.length) {
            for (int i = 0; i < infoeme.length; i++) {
                if (cont == nedita) {
                    System.out.print("\nInformacao a ser editada: ");
                    InfoEme info = (InfoEme) ReadObjectFromFile(infoeme[i].getPath());
                    info.imprimeInfoEme();
                }
                cont++;
            }
            cont = 1;

            InfoEme replace = new InfoEme();
            System.out.println("\nDigite a nova informacao: ");
            String info = sc.nextLine();
            replace.setInfo(info);

            Random rand = new Random();

            for (int i = 0; i < infoeme.length; i++) {
                if (cont == nedita) {
                    WriteObjectToFile(replace, infoeme[i].getPath());
                }
                cont++;
            }

        } else
            System.out.println("Numero invalido");
    }
}