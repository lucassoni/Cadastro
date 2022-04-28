public class ConEx{
	private String data;
	private String tipo;
	private String horario;

	public ConEx(String data, String tipo, String horario){
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

	public String getTipo(){
		return this.tipo;
	}

	public void setTipo(String tipo){
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