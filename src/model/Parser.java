package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Parser {
	// Definimos los atributos de la clase Parser
	// dom sera de tipo Document
	private Document dom = null;
	private ArrayList<Accion> acciones = null;

	// Inicializamos libros en el constructor
	public Parser() {
		acciones = new ArrayList<Accion>();
	}

	public void parseFicheroXml(String fichero) throws ParserConfigurationException, SAXException, IOException {

		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// creamos un documentbuilder
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		dom = db.parse(fichero);

	}

	public void parseDocument() {
		// obtenemos el elemento raiz acciones
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos accion
		NodeList nl = docEle.getElementsByTagName("accion");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (accion)
				Element el = (Element) nl.item(i);

				// obtenemos una accion
				Accion a = getAccion(el);

				// y la añadimos al array
				acciones.add(a);
			}
		}
	}

	private Accion getAccion(Element accionEle) {
		// para cada elemento accion, obtenemos sus datos
		String titulo = getTextValue(accionEle, "nombre");
		
		// creamos un arrayList para cada tipo de operacion
		ArrayList<Operacion> operacionesCompra = getOperaciones(accionEle, "compra");
		ArrayList<Operacion> operacionesVenta = getOperaciones(accionEle, "venta");

		// creamos un arraylist con el conjunto completo de operaciones
		ArrayList<Operacion> operacionesTotales = operacionesCompra;

		for (Operacion o : operacionesVenta) {
			operacionesTotales.add(o);
		}

		// creamos una nueva accion con los datos obtenidos del dom
		Accion a = new Accion(titulo, operacionesTotales);
		return a;
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		// Creamos una lista de nodos
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			// para cade elemento de la lista obtenemos su valor segun pos
			textVal = nl.item(0).getFirstChild().getNodeValue();
		}
		return textVal;
	}

	// al metodo getOperaciones le pasamos el elemento accion y el tipo de operacion que queremos recuperar
	private ArrayList<Operacion> getOperaciones(Element accionEle, String tipo) {
		ArrayList<Operacion> al = new ArrayList<>();
		String cantidad = null;
		String precio = null;
		Operacion op = null;

		// para esta accion en concreto obtendremos su lista de nodos de operaciones
		NodeList nl = accionEle.getElementsByTagName("operaciones");

		// si la lista de nodos de operaciones no esta vacia
		if (nl != null && nl.getLength() > 0) {
			
			// nos quedamos con el elemento operaciones (un unico elemento en cada accion)
			Element eleOperacion = (Element) nl.item(0);
			
			// del que generamos otra lista de nodos segun el tipo de operacion que hayamos pasado por parametro
			NodeList nlOperacion = eleOperacion.getElementsByTagName(tipo);
			
			// si la lista de operaciones compra o venta no esta vacia
			if (nlOperacion != null && nlOperacion.getLength() > 0) {
				
				// para cada elemento del listado de nodos
				for (int i = 0; i < nlOperacion.getLength(); i++) {
					
					// nos quedamos con el elemento compra o venta
					Element tipoEle = (Element) nlOperacion.item(i);
					
					// del que nos generamos dos listados de nodos 
					NodeList nlCantidad = tipoEle.getElementsByTagName("cantidad");
					NodeList nlPrecio = tipoEle.getElementsByTagName("precio");
					
					// cada uno de los cuales al tener un unico elemento
					if (nlCantidad != null && nlCantidad.getLength() > 0) {
						
						// podemos tomar el elemento cantidad como item 0 de la lista
						Element elCantidad = (Element) nlCantidad.item(0);
						// y el valor de la cantidad accediendo al valor de su unico hijo 
						cantidad = elCantidad.getFirstChild().getNodeValue();
					}
					
					// con la lista de nodos de tipo precio ocurre lo mismo
					if (nlPrecio != null && nlPrecio.getLength() > 0) {
						// nos quedamos con el unico elemento de tipo precio
						Element elPrecio = (Element) nlPrecio.item(0);
						// y accemos al valor de su unico hijo 
						precio = elPrecio.getChildNodes().item(0).getNodeValue();
					}
					
					// cuando terminamos de recorrer el arbol de nodos y 
					// hemos extraido los valores que necesitabamos
					// podemos crear una nueva operacion 
					op = new Operacion(tipo, cantidad, precio);
					
					// que añadimos al arraylist que devolveremos
					al.add(op);
				}
			}
		}
		return al;
	}

	public String getResultado() {
		StringBuffer cadena = new StringBuffer();
		Iterator it = acciones.iterator();
		while (it.hasNext()) {
			Accion a = (Accion) it.next();
			cadena.append("Accion: ");
			cadena.append(a.getNombre() + "\n");
			cadena.append("Operaciones:");
			for (Operacion o : a.getOperaciones()) {
				cadena.append(o.getResultado());
			}
		}
		return cadena.toString();
	}
}
