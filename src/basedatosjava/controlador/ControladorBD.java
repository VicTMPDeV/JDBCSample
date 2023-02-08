package basedatosjava.controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorBD {
	/*
	 * ATRIBUTOS
	 */
	//Realizo una DECLARACION COMPLETA CUALIFICADA de la Clase (incluyendo el paquete)
	private java.sql.Connection conexion; 
	private java.sql.Statement query; //SI EMPLEO LA CLASE PreparedStatement MEJOR
	private java.sql.ResultSet resultado;
	private String bbdd, servidor, puerto, user, password, conector;
	private static ControladorBD bd; //OBJETO QUE ES REPRESENTADO POR EL PATRON SINGLETON
	/*
	 * CONSTRUCTOR PRIVADO --> PATRÓN SINGLETON
	 */
	private ControladorBD() {
		this.bbdd = "empresavictor";
		this.servidor = "localhost";
		this.puerto = "3306"; //ESCRIBO LO SIGUIENTE EN PHPMYADMIN: SHOW VARIABLES WHERE VARIABLE_NAME IN('port')
		this.user = "root"; //USUARIO POR DEFECTO EN MYSQL
		this.password = ""; //CLAVE POR DEFECTO EN MYSQL
		this.conector = "org.gjt.mm.mysql.Driver";
	}
	/*
	 * INSTANCIADOR --> SINGLETON
	 */
	public static ControladorBD getInstance() {
		if(bd == null) {
			bd = new ControladorBD();
		}else {
			System.out.println("YA EXISTE UNA INSTANCIA, NO SE PUEDE CREAR OTRA");
		}
		return bd;
	}
	/*
	 * METODO: ESTABLECER CONEXION CON LA BASE DE DATOS
	 */
	public void conectar() {	
		try {
			//DRIVER MYSQL
			Class.forName(conector); //conector = "org.mysql.jdbc.Driver"
			//URL CONEXION
			String url = "jdbc:mysql://"+this.servidor+":"+this.puerto+"/"+this.bbdd;
			//INICIALIZAMOS EL ATRIBUTO CONEXION --> EN ESTE PUNTO YA ESTAMOS CONECTADOS CON LA BASE DE DATOS
			conexion = DriverManager.getConnection(url,this.user,this.password);
			/****************************************************************************************/
			query = conexion.createStatement(); //CON ESTE OBJETO CONTROLAMOS TODAS LAS CONSULTAS
			/****************************************************************************************/		
		} catch (ClassNotFoundException e) {
			System.err.println("ERROR DE CONEXION 1: ES POSIBLE QUE NO "
					+ "HAYAS IMPORTADO LA LIBRERIA QUE CONTIENE EL DRIVER..."
					+ " \n--------------------------------------------------"
					+ "----------------------------------------------\n"+e.getMessage());
		}catch (SQLException e) {
			System.err.println("ERROR DE CONEXION 2\n--------------\n"+e.getMessage());
		}
		
	}
	/*
	 * METODO: DESCONECTAR DE LA BASE DE DATOS
	 */
	public void desconectar() {
		try {
			this.conexion.close();
		} catch (SQLException e) {
			System.err.println("ERROR DE CONEXION 3\n--------------\n"+e.getMessage());
		}
	}
    
    /*
     * CRUD
     * ---------------------------------------
     * TODAS LAS OPERACIONES CON BASE DE DATOS
     * SE RESUMEN EN CONSULTAR (READ o SELECT)
     * O ACTUALIZAR (CREATE, UPDATE, DELETE)
     */
    public ResultSet consultarBD(String sql) {
    	this.resultado = null;
    	try {
			resultado = query.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("ERROR EN LA CONSULTA\n-------------------------");
			System.err.println("Mensaje:"+e.getMessage());
            System.err.println("Estado:"+e.getSQLState());
            System.err.println("Codigo del error:"+e.getErrorCode());
		}
    	return resultado;
    }
    
    public int actualizarBD(String sql) {
    	int numFilas = 0;	
    	try {
			numFilas = query.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("ERROR EN LA ACTUALIZACION\n-------------------------");
            System.err.println("Mensaje:"+e.getMessage());
            System.err.println("Estado:"+e.getSQLState());
            System.err.println("Codigo del error:"+e.getErrorCode());
		}	
    	return numFilas;
    }
}
