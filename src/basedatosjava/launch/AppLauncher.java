package basedatosjava.launch;

import basedatosjava.vista.InterfazGrafica;

public class AppLauncher {
	
	public static void main(String[] args) {
		InterfazGrafica GUI = InterfazGrafica.getInstance();
		GUI.iniciar();
	}

}
