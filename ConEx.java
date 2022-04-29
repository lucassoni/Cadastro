import java.io.Serializable;
public class ConEx implements Serializable{
	private String data;
	private int tipo;
	private String horario;

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

        System.out.println("Tipo: ");
        System.out.println(getTipo());

        System.out.println("Horario: ");
        System.out.println(getHorario());
    }
}