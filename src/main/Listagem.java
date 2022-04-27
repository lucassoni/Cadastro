import java.io.*;
import java.util.ArrayList;

public class Listagem {
    private ArrayList<Medico> medicos = new ArrayList<Medico>();

    public Listagem() {}

    public void carregarMedicos(){
        File file = new File("../../localStorage/medicos");
        Integer i = 0;
        if(file.exists()){
            File[] medicos = file.listFiles();
            for(File m : medicos){
                this.medicos.add((Medico)ReadObjectFromFile(m.getPath()));
                i++;
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

    public void listaMedicos(){
        System.out.println("\nMédicos cadastrados:");
        for(Medico m : medicos){
            m.imprimeMedico();
        }
    }

}
