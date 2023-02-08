package basedatosjava.modelo;
//CLASE POJO QUE REPRESENTA EL MODELO DE DATOS DE LA TABLA PERSONA SQL
public class Departamento {
	//ATRIBUTOS
	private int numDepto;
	private String nombre;
	//CONSTRUCTOR
	public Departamento(int numDepto, String nombre) {
		this.numDepto = numDepto;
		this.nombre = nombre;
	}
	//GETTERS
	public int getNumDepto() {
		return numDepto;
	}
	public String getNombre() {
		return nombre;
	}
	//SETTERS
	public void setNumDepto(int numDepto) {
		this.numDepto = numDepto;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
