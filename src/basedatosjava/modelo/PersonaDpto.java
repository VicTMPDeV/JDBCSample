package basedatosjava.modelo;
//CLASE POJO ADAPTADA A LOS RESULTADOS OBTENIDOS DE TABLAS DE YUNCION
public class PersonaDpto {
	// ATRIBUTOS
	private int identificador;
	private String nombrePersona, nombreDpto;
	// CONSTRUCTOR
	public PersonaDpto(int identificador, String nombrePersona, String nombreDpto) {
		this.identificador = identificador;
		this.nombrePersona = nombrePersona;
		this.nombreDpto = nombreDpto;
	}
	//GETTERS
	public int getIdentificador() {
		return identificador;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public String getNombreDpto() {
		return nombreDpto;
	}
	//SETTERS
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public void setNombreDpto(String nombreDpto) {
		this.nombreDpto = nombreDpto;
	}
	
	

}
