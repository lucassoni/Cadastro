package src.main;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Listagem {
    private static Listagem objeto;
    private ArrayList<Medico> medicos = new ArrayList<Medico>();
    private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
    private ArrayList<Familiar> familiares = new ArrayList<Familiar>();
    private ArrayList<Exame> exames = new ArrayList<Exame>();
    private ArrayList<InfoEme> infoeme = new ArrayList<InfoEme>();

    private Listagem() {
    }

    public static Listagem getInstance(){
        if(objeto == null){
            objeto = new Listagem();
        }
        return objeto;
    }

    public void addMedico(Medico medico) {
        medicos.add(medico);
    }

    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void addFamiliar(Familiar familiar) {
        familiares.add(familiar);
    }

    public void addExame(Exame exame) {
        exames.add(exame);
    }

    public void addInfoEme(InfoEme infoeme) {
        this.infoeme.add(infoeme);
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public ArrayList<Familiar> getFamiliares() {
        return familiares;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public ArrayList<InfoEme> getInfoEme() {
        return infoeme;
    }

    public ArrayList<Exame> getExames() {
        return exames;
    }

    public void carregarConsultas() {
        File file = new File("./resources/localStorage/consultas");
        if (file.exists()) {
            File[] consultas = file.listFiles();
            for (File c : consultas) {
                this.consultas.add((Consulta) ReadObjectFromFile(c.getPath()));
            }
        }
    }

    public void carregarExames() {
        File file = new File("./resources/localStorage/exames");
        if (file.exists()) {
            File[] exames = file.listFiles();
            for (File e : exames) {
                this.exames.add((Exame) ReadObjectFromFile(e.getPath()));
            }
        }
    }

    public void carregarMedicos() {
        File file = new File("./resources/localStorage/medicos");
        if (file.exists()) {
            File[] medicos = file.listFiles();
            for (File m : medicos) {
                this.medicos.add((Medico) ReadObjectFromFile(m.getPath()));
            }
        }
    }

    public void carregarFamiliares() {
        File file = new File("./resources/localStorage/familiares");
        if (file.exists()) {
            File[] familiares = file.listFiles();
            for (File f : familiares) {
                this.familiares.add((Familiar) ReadObjectFromFile(f.getPath()));
            }
        }
    }

    public void carregarInfoEme() {
        File file = new File("./resources/localStorage/infoeme");
        if (file.exists()) {
            File[] infoeme = file.listFiles();
            for (File i : infoeme) {
                this.infoeme.add((InfoEme) ReadObjectFromFile(i.getPath()));
            }
        }
    }

    private Object ReadObjectFromFile(String filepath) {
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

    public void listaConsultas() {
        int i = 0;
        System.out.println("\nConsultas cadastradas:\n");
        for (Consulta c : consultas) {
            System.out.println(String.format("Consulta %d", i + 1));
            c.imprimeConsulta();
            i++;
        }
    }

    public void listaMedicos() {
        int i = 0;
        System.out.println("\nMédicos cadastrados:");
        for (Medico m : medicos) {
            System.out.println(String.format("Medico %d", i + 1));
            m.imprimeMedico();
            i++;
        }
    }

    public void listaFamiliares() {
        int i = 0;
        System.out.println("\nFamiliares cadastrados:");
        for (Familiar f : familiares) {
            System.out.println(String.format("Familiar %d", i + 1));
            f.imprimeFamiliar();
            i++;
        }
    }

    public void listaInfoEme() {
        System.out.println("\nInformacoes cadastradas:");
        int cont = 1;
        for (InfoEme i : infoeme) {
            System.out.print(cont + ": ");
            i.imprimeInfoEme();
            cont++;
        }
    }

    public void listaExames() {
        int i = 0;
        System.out.println("\nExames cadastrados:");
        for (Exame e : exames) {
            System.out.println(String.format("Exame %d", i + 1));
            e.imprimeExame();
            i++;
        }
    }

    public void procuraConsultasDoDia() {
        Calendar hoje = Calendar.getInstance();
        ArrayList<Consulta> consultasHoje = new ArrayList<Consulta>();
        for (Consulta c : this.consultas) {
            if (c.getData().get(Calendar.YEAR) == hoje.get(Calendar.YEAR) &&
                    c.getData().get(Calendar.MONTH) == hoje.get(Calendar.MONTH) &&
                    c.getData().get(Calendar.DAY_OF_MONTH) == hoje.get(Calendar.DAY_OF_MONTH) &&
                    c.getData().get(Calendar.HOUR_OF_DAY) >= hoje.get(Calendar.HOUR_OF_DAY) &&
                    c.getData().get(Calendar.MINUTE) > hoje.get(Calendar.MINUTE)) {
                consultasHoje.add(c);
            }
        }
        if (!consultasHoje.isEmpty()) {
            System.out.println("Estas consultas estao marcadas para hoje:");
            for (Consulta c : consultasHoje) {
                c.imprimeConsulta();
            }
        } else {
            System.out.println("Nao existem consultas marcadas para hoje");
        }
    }

    public void procuraConsultasExpiradas() {
        Calendar hoje = Calendar.getInstance();
        ArrayList<Consulta> consultasExpiradas = new ArrayList<Consulta>();
        for (Consulta c : this.consultas) {
            if (c.getData().before(hoje) && c.getDiagnostico().equals("O diagnóstico ainda não foi cadastrado")) {
                consultasExpiradas.add(c);
            }
        }
        if (!consultasExpiradas.isEmpty()) {
            System.out.println("Estas consultas estao expiradas:");
            for (Consulta c : consultasExpiradas) {
                c.imprimeConsulta();
            }
            System.out.println("Favor fazer upload do diagnostico e das prescricoes dessas consultas!\n");
        }
    }

    public ArrayList<Consulta> resgataConsultasExpiradas() {
        Calendar hoje = Calendar.getInstance();
        ArrayList<Consulta> consultasExpiradas = new ArrayList<Consulta>();
        for (Consulta c : this.consultas) {
            if (c.getData().before(hoje)) {
                consultasExpiradas.add(c);
            }
        }
        return consultasExpiradas;
    }   

    public void procuraExamesDoDia() {
        Calendar hoje = Calendar.getInstance();
        ArrayList<Exame> examesHoje = new ArrayList<Exame>();
        for (Exame e : this.exames) {
            if (e.getData().get(Calendar.YEAR) == hoje.get(Calendar.YEAR) &&
                    e.getData().get(Calendar.MONTH) == hoje.get(Calendar.MONTH) &&
                    e.getData().get(Calendar.DAY_OF_MONTH) == hoje.get(Calendar.DAY_OF_MONTH) &&
                    e.getData().get(Calendar.HOUR_OF_DAY) >= hoje.get(Calendar.HOUR_OF_DAY) &&
                    e.getData().get(Calendar.MINUTE) > hoje.get(Calendar.MINUTE)) {
                examesHoje.add(e);
            }
        }
        if (!examesHoje.isEmpty()) {
            System.out.println("Estes exames estao marcados para hoje:");
            for (Exame e : examesHoje) {
                e.imprimeExame();
            }
        } else {
            System.out.println("Nao existem exames marcados para hoje");
        }
    }

    public void procuraExamesExpirados() {
        Calendar hoje = Calendar.getInstance();
        ArrayList<Exame> examesExpirados = new ArrayList<Exame>();
        for (Exame e : this.exames) {
            if (e.getData().before(hoje) && e.getResultado().equals("O resultado ainda não foi cadastrado")) {
                examesExpirados.add(e);
            }
        }
        if (!examesExpirados.isEmpty()) {
            System.out.println("Estes exames estao expirados:");
            for (Exame e : examesExpirados) {
                e.imprimeExame();
            }
            System.out.println("Favor fazer upload do resultado desses exames!\n");
        }
    }

    public ArrayList<Exame> resgataExamesExpirados() {
        Calendar hoje = Calendar.getInstance();
        ArrayList<Exame> examesExpirados = new ArrayList<Exame>();
        for (Exame e : this.exames) {
            if (e.getData().before(hoje)) {
                examesExpirados.add(e);
            }
        }
        return examesExpirados;
    }

    public static void DisplayImage(String path) throws IOException {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(path);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(10, 10, 400, 400);
        imageLabel.setVisible(true);
        frame.add(imageLabel);
        frame.pack();
        frame.setLayout(new FlowLayout());
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // DisplayVideo
    public static void DisplayVideo(String path) throws IOException {
        Desktop.getDesktop().open(new File(path));
        // JFrame frame = new JFrame();
        // JLabel label = new JLabel();
        // frame.add(label);
        // frame.pack();
        // frame.setLayout(new FlowLayout());
        // frame.setSize(800, 600);
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setTitle("Video");
        // frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        // frame.setAlwaysOnTop(true);
        // frame.setIconImage(Toolkit.getDefaultToolkit().getImage(path));
    }
}
