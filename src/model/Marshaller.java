package model;
 
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {

	private Document dom = null;
	private ArrayList<Accion> acciones = null;

	public Marshaller(ArrayList<Accion> a) {
		acciones = a;
	}

	public void crearDocumento() {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// creamos una instancia de DOM
			dom = db.newDocument();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}

	}

	public void crearArbolDOM() {

		// creamos el elemento raíz "acciones"
		Element docEle = dom.createElement("acciones");
		dom.appendChild(docEle);

		// recorremos todas las acciones
		Iterator it = acciones.iterator();
		while (it.hasNext()) {
			Accion a = (Accion) it.next();
			// para cada objeto accion creamos el elemento <accion> y lo
			// añadimos a la raíz
			Element accionEle = setAccion(a);
			docEle.appendChild(accionEle);
		}

	}

	private Element setAccion(Accion a) {

		// creamos el elemento accion
		Element accionEle = dom.createElement("accion");

		// creamos el elemento nombre, operaciones
		Element tituloEle = dom.createElement("nombre"); 
		Text tituloTexto = dom.createTextNode(a.getNombre());
		tituloEle.appendChild(tituloTexto);
		accionEle.appendChild(tituloEle);
		
		// creamos el elemento operaciones
		Element operacionesEle = dom.createElement("operaciones");
		Element comprasEle, ventasEle;
		
		// para cada una de las acciones
		for (Accion accion: acciones){
			// si existen compras 
			if(estaOperacion("compra", accion.getOperaciones())){
				// creamos el elemento compras en el dom	
				comprasEle = dom.createElement("compras");
					Element compraEle, cantidadEle, precioEle;
					Text cantidadEleText, precioEleText;
					
					// y para cada una de las operaciones compra creamos sus correspondientes elementos
					for (Operacion o: devuelveOperaciones("compra", accion.getOperaciones())){
						compraEle = dom.createElement("compra");
						cantidadEle = dom.createElement("cantidad");
						precioEle = dom.createElement("precio");
						cantidadEleText = dom.createTextNode(o.getCantidad());
						precioEleText = dom.createTextNode(o.getPrecio());
						cantidadEle.appendChild(cantidadEleText);
						precioEle.appendChild(precioEleText);
						compraEle.appendChild(cantidadEle);
						compraEle.appendChild(precioEle);
						comprasEle.appendChild(compraEle);
					}
					// que añadiremos al elemento operaciones
					operacionesEle.appendChild(comprasEle);
			}
			
			// si exenten ventas
			if(estaOperacion("venta", accion.getOperaciones())){
				// creamos el elemento ventas en el dom	
				ventasEle = dom.createElement("ventas");
					Element ventaEle, cantidadEle, precioEle;
					Text cantidadEleText, precioEleText;
					
					// y para cada una de las operaciones de venta creamos sus correspondientes elementos
					for (Operacion o: devuelveOperaciones("venta", accion.getOperaciones())){
						ventaEle = dom.createElement("venta");
						cantidadEle = dom.createElement("cantidad");
						precioEle = dom.createElement("precio");
						cantidadEleText = dom.createTextNode(o.getCantidad());
						precioEleText = dom.createTextNode(o.getPrecio());
						cantidadEle.appendChild(cantidadEleText);
						precioEle.appendChild(precioEleText);
						ventaEle.appendChild(cantidadEle);
						ventaEle.appendChild(precioEle);
						ventasEle.appendChild(ventaEle);
					}
					//que añadiremos al elemento operaciones
					operacionesEle.appendChild(ventasEle);	
			}	
		}
		
		// añadiremos las operaciones al elemento accion
		accionEle.appendChild(operacionesEle);
		
		// y devolvemos la accion
		return accionEle;
	}
	
	// creamos un metodo que indique si existe una operacion en concreto
	public boolean estaOperacion(String operacion, ArrayList<Operacion> operaciones){
		int pos=0;
		boolean esta=false;
		do{
			if (operaciones.get(pos).getNombre().equals(operacion))
				return true;
			pos++;
		}while(!esta);
		return false;
	}
	
	// creamos un metodo que devuelva un coleccion de operaciones de un tipo
	public ArrayList<Operacion> devuelveOperaciones(String operacion, ArrayList<Operacion> operaciones){
		ArrayList<Operacion> resultado = new ArrayList<>();
		for (Operacion o: operaciones){
			if (o.getNombre().equals(operacion)){
				resultado.add(o);
			}
		}
		return resultado;
	}

	public void escribirDocumentAXml(File file) throws TransformerException {
		// creamos una instacia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// especificamos dónde escribimos y la fuente de datos
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);

	}
	
}
