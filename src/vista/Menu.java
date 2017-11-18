package vista;

import model.Libro;
import model.Marshaller;
import model.Parser;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;

public class Menu extends JFrame {
	
	private JButton primerBoto;
	private JButton segonBoto;
	private JPanel panel;
	private JPanel panel_fichero;
	private JTextArea texto;
	private JScrollPane scroll;
	private JLabel label_fichero;
	private JTextField fichero;
	
	public Menu(){
		
		setBounds(0,0,640,480);
		
		setTitle("Menu ParserBiblioteca - MarshallerBiblioteca / Matias Ruiz 2 DAM");
		panel_fichero = new JPanel();
		panel_fichero.setLayout(new BoxLayout(panel_fichero, BoxLayout.X_AXIS));
		panel_fichero.setMaximumSize(new Dimension(300,100));
		primerBoto = new JButton("Parser Biblioteca");
		segonBoto = new JButton("Marshaller Biblioteca");
		label_fichero = new JLabel("Fichero: ");
		fichero = new JTextField(20);
		panel_fichero.add(label_fichero);
		panel_fichero.add(fichero);
		
		
		texto = new JTextArea();
		scroll = new JScrollPane(texto);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		panel.add(panel_fichero);
		
		panel.add(primerBoto);
		panel.add(segonBoto);
		
		panel.add(scroll);

		panel.setAlignmentX(LEFT_ALIGNMENT);
		
		primerBoto.setActionCommand("primerboto");
		segonBoto.setActionCommand("segonboto");
			
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JButton getPrimerBoto() {
		return primerBoto;
	}

	public void setPrimerBoto(JButton primerBoto) {
		this.primerBoto = primerBoto;
	}

	public JButton getSegonBoto() {
		return segonBoto;
	}

	public void setSegonBoto(JButton segonBoto) {
		this.segonBoto = segonBoto;
	}

	public JTextArea getTexto() {
		return texto;
	}

	public void setTexto(JTextArea texto) {
		this.texto = texto;
	}
	
	public JTextField getFichero() {
		return fichero;
	}
	
	
}
