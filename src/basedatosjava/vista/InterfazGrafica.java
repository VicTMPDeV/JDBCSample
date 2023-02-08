package basedatosjava.vista;

import java.util.ArrayList;

import basedatosjava.controlador.ControladorPersona;
import basedatosjava.modelo.Persona;
import basedatosjava.modelo.PersonaDpto;
import utilidades.Teclado;
import utilidades.Utilidades;

public class InterfazGrafica {
	// ATRIBUTOS
	private static InterfazGrafica interfGraf; // OBJETO QUE ES REPRESENTADO POR EL PATRON SINGLETON
	ControladorPersona contrPers;
	// OPCIONES
	private final int CREATE = 1;
	private final int READ = 2;
	private final int UPDATE = 3;
	private final int DELETE = 4;
	private final int TODOS = 5;
	private final int SALIR = 0;

	// CONSTRUCTOR --> PATRÓN SINGLETON
	private InterfazGrafica() {
		contrPers = ControladorPersona.getInstance();
	}

	// GET INSTANCE
	public static InterfazGrafica getInstance() {
		if (interfGraf == null) {
			interfGraf = new InterfazGrafica();
		} else {
			System.out.println("YA EXISTE UNA INSTANCIA, NO SE PUEDE CREAR OTRA");
		}
		return interfGraf;
	}

	// METODOS DE FUNCIONALIDAD 
	public void iniciar() {
		menuInicio();
		Utilidades.esperar(1);
		menuPrincipal();
	}
	
	private void menuInicio() {
		System.out.println("|-----------------------------------------|");
		System.out.println("|-----CONEXION CON BASE DE DATOS MYSQL----|");
		System.out.println("|----USANDO JAVA DATABASE CONNECTIVITY----|");
		System.out.println("|-----------------------------------------|");
		System.out.println("|--------------____________---------------|");
		System.out.println("|--------------            ---------------|");
		System.out.println("|--------------    CRUD    ---------------|");
		System.out.println("|--------------____________---------------|");
		System.out.println("|---------------C  R  U  D----------------|");
		System.out.println("|---------------R  E  P  E----------------|");
		System.out.println("|---------------E  A  D  L----------------|");
		System.out.println("|---------------A  D  A  E----------------|");
		System.out.println("|---------------T     T  T----------------|");
		System.out.println("|---------------E     E  E----------------|");
		System.out.println("|---------------__________----------------|");
		System.out.println("|-----------------------------------------|");
		System.out.println("|-----------------------------------------|");
		System.out.println("|-------AUTOR: VICTOR MORALES PEREZ-------|");
		System.out.println("|-----------------------------------------|");
		Utilidades.esperar(2);
		Teclado.leerCadena("\n       PULSE ENTER PARA COMENZAR...");
	}

	private void menuPrincipal() {

		int opcion = 1;

		while (opcion > 0 && opcion <= 5) {
			System.out.println("|-----------------------------------------|");
			System.out.println("|----------------CRUD JDBC----------------|");
			System.out.println("|-----------------------------------------|");
			System.out.println("| 1.- CREAR REGISTRO                      |");
			System.out.println("| 2.- BUSCAR REGISTRO                     |");
			System.out.println("| 3.- ACTUALIZAR REGISTRO                 |");
			System.out.println("| 4.- BORRAR REGISTRO                     |");
			System.out.println("| 5.- LISTAR TODOS LOS REGISTROS          |");
			System.out.println("|                                         |");
			System.out.println("| 0.- SALIR                               |");
			System.out.println("|-----------------------------------------|");
			System.out.println("|-----------------------------------------|");
			System.out.println("|-----------------------------------------|");
			Utilidades.esperar(1);
			opcion = Teclado.leerEntero("SELECCIONA UNA OPCION: ");
			if (opcion < 0 || opcion > 5) {
				System.out.println("LA OPCION SELECCIONADA NO ES CORRECTA, SELECCIONA OTRA");
			}
			funcionalidadMenuPrincipal(opcion);
		}
	}

	private void funcionalidadMenuPrincipal(int opcion) {
		switch (opcion) {
		case CREATE:
			listarTodos();
			Utilidades.esperar(1);
			insertarPersona();
			break;
		case READ:
			buscarPersonas();
			break;
		case UPDATE:
			modificarPersona();
			break;
		case DELETE:
			borrarPersona();
			break;
		case TODOS:
			listarTodos();
			break;
		case SALIR:
			Utilidades.esperar(1);
			System.out.println("SALIENDO DE LA APLICACION...");
			break;
		}
	}
	
	//METODOS DE FUNCIONALIDAD
	private void listarTodos() {
		ArrayList<PersonaDpto> todos = contrPers.listarTodos();
		for (PersonaDpto pR : todos) {
			System.out.println("|-----------------------------------------");
			System.out.println("|\tID:"+pR.getIdentificador());
			System.out.println("|\tNOMBRE:"+pR.getNombrePersona());
			System.out.println("|\tDEPARTAMENTO:"+pR.getNombreDpto());
			System.out.println("|-----------------------------------------");
		}
	}

	private void buscarPersonas() {
		int opt = -1;
		while(opt < 1 || opt > 2) {
			opt = Teclado.leerEntero("PULSE 1 PARA BUSCAR POR NOMBRE\nPULSE 2 PARA BUSCAR POR ID\nOPCION:  ");
			if(opt < 1 && opt > 2) {
				System.out.println("OPCION INCORRECTA, SELECCIONA OTRA");
			}
		}
		if(opt==1) {
			String nombre = Teclado.leerCadena("INTRODUCE EL NOMBRE DE LA PERSONA A BUSCAR: ");
			ArrayList<Persona> lista = contrPers.buscarPorNombre(nombre);
			if(lista.size() > 0) {
				System.out.println("NUMERO DE REGISTROS ENCONTRADOS: "+lista.size());
				Utilidades.esperar(2);
				for (Persona p : lista) {
					System.out.println("|-----------------------------------------");
					System.out.println("|\tID:"+p.getIdentificador());
					System.out.println("|\tNOMBRE:"+p.getNombrePersona());
					System.out.println("|\tDEPARTAMENTO:"+p.getNumDepto());
					System.out.println("|-----------------------------------------");
				}
			}else {
				System.out.println("NO SE ENCONTRARON REGISTROS CON ESE NOMBRE");
			}
		}else {
			int id = Teclado.leerEntero("INTRODUCE EL IDENTIFICADOR DE LA PERSONA A BUSCAR: ");
			Persona p = contrPers.buscarPorId(id);
			if(p != null) {
				Utilidades.esperar(2);
				System.out.println("|--------------------------------------------");
				System.out.println("|\tID:"+p.getIdentificador());
				System.out.println("|\tNOMBRE:"+p.getNombrePersona());
				System.out.println("|\tDEPARTAMENTO:"+p.getNumDepto());
				System.out.println("|--------------------------------------------");	
			}else {
				System.out.println("NO SE ENCONTRO REGISTRO CON ESE IDENTIFICADOR");
			}
		}
	}
	
	private void insertarPersona() {
		String nom = Teclado.leerCadena("INTRODUCE EL NOMBRE DE LA PERSONA: ");
		String ape1 = Teclado.leerCadena("INTRODUCE EL PRIMER APELLIDO DE LA PERSONA: ");
		String ape2 = Teclado.leerCadena("INTRODUCE EL SEGUNDO APELLIDO DE LA PERSONA: ");
		String nombre = nom+" "+ape1+" "+ape2;
		int departamento = Teclado.leerEntero("INTRODUCE EL DEPARTAMENTO AL QUE PERTENECE: ");
		Persona p = new Persona(0, departamento, nombre);
		int resultado = contrPers.insertarPersona(p);
		if(resultado == 0) {
			System.out.println("NO SE HA PODIDO INSERTAR NUEVA PERSONA");
		}else {
			System.out.println("REGISTRO INSERTADO CORRECTAMENTE");
		}
	}
	
	private void modificarPersona() {
		int identificador = Teclado.leerEntero("INTRODUCE EL IDENTIFICADOR DE LA PERSONA A MODIFICAR: ");
		String nom = Teclado.leerCadena("INTRODUCE EL NUEVO NOMBRE: ");
		String ape1 = Teclado.leerCadena("INTRODUCE EL NUEVO PRIMER APELLIDO: ");
		String ape2 = Teclado.leerCadena("INTRODUCE EL NUEVO SEGUNDO APELLIDO: ");
		String nombre = nom+" "+ape1+" "+ape2;
		
		Persona p = new Persona(identificador, 1, nombre);
		
		int resultado = contrPers.modificarPersona(p);
		if(resultado == 0) {
			System.out.println("EL REGISTRO A MODIFICAR NO EXISTE");
		}else {
			System.out.println("REGISTRO MODIFICADO CORRECTAMENTE");
		}
	}
	
	private void borrarPersona() {
		int identificador = Teclado.leerEntero("INTRODUCE EL IDENTIFICADOR DE LA PERSONA A BORRAR: ");
		
		int resultado = contrPers.borrarPersona(identificador);
		if(resultado == 0) {
			System.out.println("EL REGISTRO A BORRAR NO EXISTE");
		}else {
			System.out.println("REGISTRO BORRADO CORRECTAMENTE");
		}
	}

}
