import java.io.*;
import java.util.Scanner;
import java.io.Serializable;
import java.io.File;

public class ConEx implements Serializable{
	private String data;
	private int tipo;
	private String horario;

	public ConEx(){}
	public ConEx(String data, int tipo, String horario){
		this.data=data;
		this.tipo=tipo;
		this.horario=horario;
	}

	public String getData(){
		return this.data;
	}

	public void setData(String data){
		this.data=data;
	}

	public int getTipo(){
		return this.tipo;
	}

	public void setTipo(int tipo){
		this.tipo=tipo;
	}

	public String getHorario(){
		return this.horario;
	}

	public void setHorario(String horario){
		this.horario=horario;
	}

	public void imprimeConEx() {
        System.out.println("\nData: ");
        System.out.println(getData());

        System.out.println("Horario: ");
        System.out.println(getHorario());
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

	public void readConEx(){
        // File dir = new File("../../localStorage/conex/");
        // showFiles(dir.listFiles());
    }

    public static void showFiles(File[] files) {
        // int i=1;
        // for (File file : files) {
        //     if (file.isDirectory()) {
        //         System.out.println("Directory: " + file.getAbsolutePath());
        //         showFiles(file.listFiles());
        //     } else {
        //         ConEx ce = (ConEx)ReadObjectFromFile(file.getAbsolutePath());
        //         System.out.print(i + ": ");
        //         System.out.println(ce);
        //     }
        //     i++;
        // }
    }

	public void deleteConEx(){
		// Scanner sc = new Scanner(System.in);
		// System.out.println("Digite o numero do exame/consulta que deseja excluir: ");
		// int number = sc.nextInt();
		// sc.nextLine();

	}
}