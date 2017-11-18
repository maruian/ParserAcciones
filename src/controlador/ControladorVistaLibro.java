package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;

import model.Libro;
import model.Marshaller;
import vista.Menu;
import vista.VistaLibro;

public class ControladorVistaLibro {

	VistaLibro vista;
	Marshaller model;
	//ArrayList<Libro> biblioteca;
	
	ActionListener actionListener_altre, actionListener_guardar;
	
	public ControladorVistaLibro(VistaLibro vista, Marshaller model){
		this.vista=vista;
		this.model=model;
	}
	
	public void control(String fichero) {
		actionListener_altre = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_altre();
			}
		};
		vista.getBotoAltre().addActionListener(actionListener_altre);
		
		actionListener_guardar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent){
				call_guardar(fichero);
			}
		};
		vista.getBotoGuardar().addActionListener(actionListener_guardar);
		
	}	

	public void call_altre(){
		String titulo;
		String autorAux;
		ArrayList<String> autor=new ArrayList<>();
		String ano;
		String editor;
		String paginas;
		titulo = vista.getJtTitulo().getText();
		autorAux = vista.getJtAutor().getText();
		ano = vista.getJtAno().getText();
		editor = vista.getJtEditor().getText();
		paginas = vista.getJtNumpag().getText();
		if ( (titulo.length()==0) || (autorAux.length()==0) || (ano.length()==0) ||
				(editor.length()==0) || (paginas.length() == 0) ) {
			new JOptionPane()
			.showMessageDialog(vista, "Formato de campos incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String[] aAutor = autorAux.split(" ");
			for (String s: aAutor) {
				autor.add(s);
			}
			Libro l = new Libro(titulo,autor,ano,editor,paginas);
	//		model.addLibro(l);
			vista.getJtTitulo().setText("");
			vista.getJtAutor().setText("");
			vista.getJtNumpag().setText("");
			vista.getJtAno().setText("");
			vista.getJtEditor().setText("");
			vista.getJtNumpag().setText("");
		}
		
	}
	
	public void call_guardar(String fichero){
		
		model.crearDocumento();
		model.crearArbolDOM();
		
		File file = new File(fichero);
		
		try {
			model.escribirDocumentAXml(file);
		} catch (TransformerException e) {			
			new JOptionPane()
			.showMessageDialog(vista, "Error en el proceso", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		new JOptionPane()
			.showMessageDialog(vista, "Archivo guardado con exito");
		
		vista.dispose();
		
	}	

}

