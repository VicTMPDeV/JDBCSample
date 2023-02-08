package basedatosjava.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basedatosjava.modelo.Departamento;
import basedatosjava.modelo.Persona;
import basedatosjava.modelo.PersonaDpto;

//ESTA CLASE VIENE A REALIZAR LA FUNCION DE CONTROLADOR DE INYECCION DE DEPENDENCIA ENTRE EL MODELO Y LA BD
public class ControladorPersona {
	// ATRIBUTOS -> PATRON SINGLETON
	private static ControladorPersona controladorP;
	ControladorBD controladorBD;

	// CONSTRUCTOR
	private ControladorPersona() {
		controladorBD = ControladorBD.getInstance();
	}

	// GET INSTANCE
	public static ControladorPersona getInstance() {
		if (controladorP == null) {
			controladorP = new ControladorPersona();
		} else {
			System.out.println("NO SE PUEDE INSTANCIAR OTRO OBJETO DE ESTA CLASE");
		}
		return controladorP;
	}

	/*****************************
	 * CONTROLADOR DE CONSULTAS
	 *****************************/

	// LISTAR TODOS
	public ArrayList<PersonaDpto> listarTodos() {
		ArrayList<PersonaDpto> lista = new ArrayList<PersonaDpto>(); // LISTA PARA CONTENER LOS RESULTADOS ENCONTRADOS
		controladorBD.conectar(); // CONECTO CON LA BASE DE DATOS
		ResultSet resultados; // OBJETO QUE ALMACENA LOS DATOS DEVULETOS POR LA CONSUTA
		resultados = controladorBD.consultarBD("SELECT C.identificador, C.nombrePersona, D.nombre " 
											 + "FROM persona C, departamento D "
											 + "WHERE C.numDepartamento = D.numDepto " 
											 + "ORDER BY C.identificador"); // CONSULTA CON YUNCION
		// resultados = controladorBD.consultarBD("SELECT * FROM persona ORDER BY identificador"); //CONSULTA SIMPLE
		try {
			while (resultados.next()) { // MIENTRAS RESULTSET TENGA RESULTADOS ALMACENADOS...
				// MODELO UN OBJETO DE LA CLASE POJO PERSONA
				PersonaDpto pR = new PersonaDpto(resultados.getInt("C.identificador"),
												 resultados.getString("C.nombrePersona"), 
												 resultados.getString("D.nombre"));
				// Y LO AÑADO A LA LISTA
				lista.add(pR);
			}
			controladorBD.desconectar();// DESCONEXION
		} catch (SQLException ex) {
			System.err.println("ControladorPerona->Error al listar\n-------------------------------------------------\n"
					+ ex.getMessage());
		}
		return lista;
	}

	// METODO PARA BUSCAR POR NOMBRE
	public ArrayList<Persona> buscarPorNombre(String filtro) {
		ArrayList<Persona> lista = new ArrayList<Persona>(); // LISTA PARA CONTENER LOS RESULTADOS ENCONTRADOS
		controladorBD.conectar(); // CONECTO CON LA BASE DE DATOS
		ResultSet resultados; // OBJETO QUE ALMACENA LOS DATOS DEVULETOS POR LA CONSUTA
		/*
		 * resultados = controladorBD.
		 * consultarBD("SELECT C.identificador, C.nombrePersona, D.nombre " +
		 * "FROM persona C JOIN departamento D " +
		 * "WHERE C.numDepartamento = D.numDepto " + "AND nombrePersona LIKE '%" +
		 * filtro + "%' ORDER BY C.identificador ASC"); // CONSULTA CON
		 * YUNCION->REQUIERE USAR CLASE PERSONADPTO
		 */
		resultados = controladorBD.consultarBD(
				"SELECT * FROM persona WHERE nombrePersona LIKE '%" + filtro + "%' ORDER BY identificador"); // CONSULTA
																												// SIMPLE
		try {
			while (resultados.next()) { // MIENTRAS RESULTSET TENGA RESULTADOS ALMACENADOS...
				// MODELO UN OBJETO DE LA CLASE POJO PERSONA
				Persona p = new Persona(resultados.getInt("identificador"), resultados.getInt("numDepartamento"),
						resultados.getString("nombrePersona"));
				// Y LO AÑADO A LA LISTA
				lista.add(p);
			}
			controladorBD.desconectar();// DESCONEXION
		} catch (SQLException ex) {
			System.err.println("ControladorPerona->Error al listar\n-------------------------------------------------\n"
					+ ex.getMessage());
		}
		return lista;
	}

	// METODO PARA BUSCAR POR ID
	public Persona buscarPorId(int filtro) {
		// EL ID NO SE PUEDE REPETIR, POR TANTO EL RESULTADO SERA UN OBJETO DE LA CLASE
		// POJO
		Persona p = null;
		// ESTABLECEMOS CONEXION
		controladorBD.conectar(); // CONECTO CON LA BASE DE DATOS
		// OBJETO RESULTSET QUE ALMACENA EL RESULTADO DE LA CONSULTA
		/*
		 * ResultSet resultados =
		 * controladorBD.consultarBD("SELECT C.identificador, C.nombrePersona, D.nombre"
		 * + "FROM persona C JOIN departamento D" +
		 * "WHERE C.numDepartamento = D.numDepto AND C.identificador = " + filtro); //
		 * CONSULTA CON YUNCION->REQUIERE USAR CLASE PERSONADPTO
		 */
		ResultSet resultados = controladorBD.consultarBD("SELECT * FROM persona WHERE identificador = " + filtro); // CONSULTA
																													// SIMPLE
		try {
			while (resultados.next()) {
				p = new Persona(resultados.getInt("identificador"), resultados.getInt("numDepartamento"),
						resultados.getString("nombrePersona"));
			}
			controladorBD.desconectar();
		} catch (SQLException ex) {
			System.err.println("ControladorPerona->Error al listar\n-------------------------------------------------\n"
					+ ex.getMessage());
		}
		return p;
	}

	// METODO PARA INSERTAR / CREAR PERSONA....CUIDADIN CON EL IDENTIFICADOR
	public int insertarPersona(Persona p) {
		int registros = 0;
		controladorBD.conectar();
		// DEBERIAMOS COMPROBAR QUE NO EXISTE YA EL REGISTRO QUE VAMOS A INSERTAR
		// PERO UN NOMBRE DE UNA PERSONA Y SUS DOS APELLIDOS SE PUEDEN REPETIR
		
		// COMPROBAMOS SI EXISTE EL NUMERO DE DEPARTAMENTO INDICADO POR PARAMETRO
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		ResultSet subconsulta1 = controladorBD.consultarBD("SELECT * FROM departamento");
		try {
			while (subconsulta1.next()) { // MIENTRAS RESULTSET TENGA RESULTADOS ALMACENADOS...
				// MODELO UN OBJETO DE LA CLASE POJO PERSONA
				Departamento d = new Departamento(subconsulta1.getInt("numDepto"), subconsulta1.getString("nombre"));
				// Y LO AÑADO A LA LISTA
				departamentos.add(d);
			}
		} catch (SQLException ex) {
			System.err.println("ControladorPerona->Error al listar\n-------------------------------------------------\n"
					+ ex.getMessage());
		}
		boolean flag = false;
		for (int i = 0; i < departamentos.size(); i++) {
			if (p.getNumDepto() == departamentos.get(i).getNumDepto()) {
				flag = true; // EL NUMERO DE DEPARTAMENTO PASADO POR PARAMETROS EXISTE Y SE PUEDE AÑADIR EL REGISTRO A LA BD
			}
		}
		if (flag == false) {
			System.out.println("EL NUMERO DE DEPARTAMENTO NO EXISTE");
		} else {
			// COMPROBAMOS CUAL ES EL MAXIMO IDENTIFICADOR EN LA BD...
			// ESTO NO SERIA NECESARIO, DECLARANDO EN LA BD EL IDENTIFICADOR COMO AUTO
			// INCREMENTAL
			ArrayList<Integer> listaIds = getIdentificadores();
//			ResultSet subconsulta2 = controladorBD.consultarBD("SELECT MAX(identificador) maxid FROM persona");
//			try {
//				while (subconsulta2.next()) { // MIENTRAS RESULTSET TENGA RESULTADOS ALMACENADOS...
//					identificadorMaximo = subconsulta2.getInt("maxid"); // SOLO ME DEVUELVE UN VALOR
//				}
//			} catch (SQLException ex) {
//				System.err.println(
//						"ControladorPerona->Error al listar\n-------------------------------------------------\n"
//								+ ex.getMessage());
//			}
			p.setIdentificador(listaIds.size() + 1);
		}
		// HABIENDO OBTENIDO EL MAXIMO IDENTIFICADOR DE LA BD
		// FINALMENTE PODEMOS INSERTAR EL REGISTRO EN LA BD
		String sql = "INSERT INTO persona (nombrePersona, identificador, numDepartamento) " + "VALUES ('"
				+ p.getNombrePersona() + "','" + p.getIdentificador() + "','" + p.getNumDepto() + "')";
		registros = controladorBD.actualizarBD(sql);

		controladorBD.desconectar();
		return registros;
	}

	// METODO PARA EDITAR PERSONA -> SOLO CAMBIAREMOS EL NOMBRE
	public int modificarPersona(Persona p) {
		int registros = 0;
		controladorBD.conectar();
		// CREO UNA LISTA CON TODOS LOS IDENTIFICADORES DE LA TABLA PERSONA
		ArrayList<Integer> listaIds = getIdentificadores();
		boolean flag = false;
		for (int i = 0; i < listaIds.size(); i++) {
			if (p.getIdentificador() == listaIds.get(i)) {
				flag = true; // EL IDENTIFICADOR PASADO POR PARAMETROS EXISTE EN LA BD
			}
		}
		if (flag == false) {
			System.out.println("EL IDENTIFICADOR INDICADO NO EXISTE");
		} else {
			String sql = "UPDATE persona SET nombrePersona= '" + p.getNombrePersona() + "' WHERE identificador = "
					+ p.getIdentificador();
			registros = controladorBD.actualizarBD(sql);
		}
		controladorBD.desconectar();
		return registros;
	}

	// METODO PARA BORRAR PERSONA -> SU IDENTIFICADOR NO SE VOLVERA A USAR
	public int borrarPersona(int identificador) {
		int registros = 0;
		controladorBD.conectar();
		// CREO UNA LISTA CON TODOS LOS IDENTIFICADORES DE LA TABLA PERSONA
		ArrayList<Integer> listaIds = getIdentificadores();
		boolean flag = false;
		for (int i = 0; i < listaIds.size(); i++) {
			if (identificador == listaIds.get(i)) {
				flag = true; // EL IDENTIFICADOR PASADO POR PARAMETROS EXISTE EN LA BD
			}
		}
		if (flag == false) {
			System.out.println("EL IDENTIFICADOR INDICADO NO EXISTE");
		} else {
			String sql = "DELETE FROM persona WHERE identificador = " + identificador;
			registros = controladorBD.actualizarBD(sql);
		}
		controladorBD.desconectar();
		return registros;
	}

	// METODO PARA LISTAR LOS IDENTIFICADORES EXISTENTES EN LA TABLA PERSONA
	public ArrayList<Integer> getIdentificadores() {
		controladorBD.conectar();
		ArrayList<Integer> listaIds = new ArrayList<Integer>();
		ResultSet subconsulta1 = controladorBD.consultarBD("SELECT identificador FROM persona");
		try {
			while (subconsulta1.next()) { // MIENTRAS RESULTSET TENGA RESULTADOS ALMACENADOS...
				listaIds.add(subconsulta1.getInt("identificador"));
			}
		} catch (SQLException ex) {
			System.err.println("ControladorPerona->Error al listar\n-------------------------------------------------\n"
					+ ex.getMessage());
		}
		controladorBD.desconectar();
		return listaIds;
	}
}
