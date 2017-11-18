import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

import controlador.Controlador;
import model.Accion;
import model.Marshaller;
import model.Operacion;
import model.Parser;
import vista.Menu;

public class Inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu vista = new Menu();
		Parser model = new Parser();
				
		
		Controlador c = new Controlador(vista, model);
		c.control();
		
		
		//Veamos si funciona el Marshaller
		ArrayList<Operacion> operaciones = new ArrayList<Operacion>();
		Operacion o1 = new Operacion("compra","40","2");
		Operacion o2 = new Operacion("venta", "30", "5");
		operaciones.add(o1);
		operaciones.add(o2);
		Accion a = new Accion("Bankia",operaciones);
		ArrayList<Accion> acciones = new ArrayList<Accion>();
		acciones.add(a);

		Marshaller marshaller = new Marshaller(acciones);
		
		marshaller.crearDocumento();
		marshaller.crearArbolDOM();
		
		File file = new File("fichero3.xml");
		
		try {
			System.out.println("Hola");
			marshaller.escribirDocumentAXml(file);
		} catch (TransformerException e) {			
			e.printStackTrace();
		}

		
		
	}

}
