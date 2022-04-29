import java.io.*;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

    public void carregarConsultas() {
        File file = new File("../../localStorage/consultas");
        if (file.exists()) {
            File[] consultas = file.listFiles();
            for (File c : consultas) {
                this.consultas.add((Consulta) ReadObjectFromFile(c.getPath()));
            }
        }
    }

    public void carregarMedicos() {
        File file = new File("../../localStorage/medicos");
        if (file.exists()) {
            File[] medicos = file.listFiles();
            for (File m : medicos) {
                this.medicos.add((Medico) ReadObjectFromFile(m.getPath()));
            }
        }
    }

    public void carregarFamiliares() {
        File file = new File("../../localStorage/familiares");
        if (file.exists()) {
            File[] familiares = file.listFiles();
            for (File f : familiares) {
                this.familiares.add((Familiar) ReadObjectFromFile(f.getPath()));
            }
        }
    }

    public void carregarInfoEme() {
        File file = new File("../../localStorage/infoeme");
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

    public static void DisplayImage(String image) throws IOException {
        System.out.println("imagem path: " + image);
        BufferedImage img = ImageIO.read(new File(image));
        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200, 300);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
