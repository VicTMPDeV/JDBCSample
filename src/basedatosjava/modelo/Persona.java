package basedatosjava.modelo;
//CLASE POJO QUE REPRESENTA EL MODELO DE DATOS DE LA TABLA PERSONA SQL
public class Persona {
	//ATRIBUTOS
	private int identificador, numDepto;
	private String nombrePersona;
	//CONSTRUCTOR
	public Persona(int identificador, int numDepto, String nombrePersona) {
		this.identificador = identificador;
		this.numDepto = numDepto;
		this.nombrePersona = nombrePersona;
	}
	//GETTERS
	public int getIdentificador() {
		return identificador;
	}
	public int getNumDepto() {
		return numDepto;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	//SETTERS
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public void setNumDepto(int numDepto) {
		this.numDepto = numDepto;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
}
