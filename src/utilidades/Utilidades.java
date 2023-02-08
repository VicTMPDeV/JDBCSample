package utilidades;

public class Utilidades {
	
	public static void esperar(int tiempo) {
		try {
			int segundos = tiempo * 1000;
			Thread.sleep(segundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void clear() {
		System.out.println("\n\n\n\n\n\n\n\n");
	}

}
