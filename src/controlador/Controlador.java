package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Libro;
import model.Marshaller;
import model.Parser;
import vista.Menu;
import vista.VistaLibro;

public class Controlador {

	Menu vista;
	Parser model;
	
	ActionListener actionListener_parser, actionListener_marshaller;
	
	public Controlador(Menu vista, Parser model){
		this.vista=vista;
		this.model=model;
	}
	
	public void control() {
		actionListener_parser = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_parser();
			}
		};
		vista.getPrimerBoto().addActionListener(actionListener_parser);
		
		actionListener_marshaller = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent){
				//call_marshaller();
			}
		};
		vista.getSegonBoto().addActionListener(actionListener_marshaller);
		
	}	

	public void call_parser() {
		if (vista.getFichero().getText().length() > 0) {
			try {
				model.parseFicheroXml(vista.getFichero().getText());

			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				new JOptionPane().showMessageDialog(vista, "Error en el proceso", "Error", JOptionPane.ERROR_MESSAGE);
			}
			model.parseDocument();
			vista.getTexto().setText(model.getResultado());
		} else {
			new JOptionPane().showMessageDialog(vista, "Campo vacio", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*public void call_marshaller(){
		ArrayList<Libro> al = new ArrayList<Libro>();
		VistaLibro vl = new VistaLibro();
		Marshaller m = new Marshaller(al);
		ControladorVistaLibro cvl = new ControladorVistaLibro(vl, m);
		cvl.control(vista.getFichero().getText());
	}*/	

}
