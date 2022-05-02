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
    private ArrayList<Medico> medicos = new ArrayList<Medico>();
    private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
    private ArrayList<Familiar> familiares = new ArrayList<Familiar>();
    private ArrayList<Exame> exames = new ArrayList<Exame>();
    private ArrayList<InfoEme> infoeme = new ArrayList<InfoEme>();

    public Listagem() {
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

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public ArrayList<InfoEme> getInfoEme() {
        return infoeme;
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
        System.out.println("\nMédicos cadastrados:");
        for (Medico m : medicos) {
            m.imprimeMedico();
        }
    }

    public void listaFamiliares() {
        System.out.println("\nFamiliares cadastrados:");
        for (Familiar f : familiares) {
            f.imprimeFamiliar();
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
            if (c.getData().before(hoje)) {
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

    public static void DisplayImage(String image) throws IOException {
        System.out.println("\nCurrent path: " + System.getProperty("user.dir"));
        System.out.println( "imagem path: " + image);
        String path = "./resources/"+ image;

        //new File(path);
        // if(file.exists()) {
        //     System.out.println("Existe!");
        //     System.out.println(file.getCanonicalPath());
        // }
        BufferedImage img = ImageIO.read(Listagem.class.getResource("./localStorage/imagens/59006_2Q.png"));
        JLabel picLabel = new JLabel(new ImageIcon(img));
        // add(picLabel);
        // ImageIcon icon = new ImageIcon(img);
        // JFrame frame = new JFrame();
        // frame.setLayout(new FlowLayout());
        // frame.setSize(200, 300);
        // JLabel lbl = new JLabel();
        // lbl.setIcon(icon);
        // frame.add(lbl);
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
