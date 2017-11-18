package vista;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VistaLibro extends JFrame {

	JLabel titulo, autor, editor, ano, numpag;
	JTextField jtTitulo, jtAutor, jtEditor, jtAno, jtNumpag;
	JButton botoAltre, botoGuardar;
	JPanel panel;
	
	public VistaLibro() {
		setBounds(0, 0, 320, 240);
		setTitle("Guardar Libro");

		botoAltre = new JButton("Introducir otro libro");
		botoGuardar = new JButton("Guardar todos los libros");
		
		titulo=new JLabel("Titulo: ");
		autor=new JLabel("Autor: ");
		editor=new JLabel("Editor: ");
		ano=new JLabel("Año: ");
		numpag=new JLabel("Numero de páginas:");
		
		jtTitulo=new JTextField(20);
		jtAutor=new JTextField(20);
		jtEditor=new JTextField(20);
		jtAno=new JTextField(20);
		jtNumpag=new JTextField(5);
		
		panel = new JPanel();
		
		panel.add(titulo);
		panel.add(jtTitulo);
		panel.add(autor);
		panel.add(jtAutor);
		panel.add(editor);
		panel.add(jtEditor);
		panel.add(ano);
		panel.add(jtAno);
		panel.add(numpag);
		panel.add(jtNumpag);
		panel.add(botoAltre);
		panel.add(botoGuardar);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JTextField getJtTitulo() {
		return jtTitulo;
	}

	public void setJtTitulo(JTextField jtTitulo) {
		this.jtTitulo = jtTitulo;
	}

	public JTextField getJtAutor() {
		return jtAutor;
	}

	public void setJtAutor(JTextField jtAutor) {
		this.jtAutor = jtAutor;
	}

	public JTextField getJtEditor() {
		return jtEditor;
	}

	public void setJtEditor(JTextField jtEditor) {
		this.jtEditor = jtEditor;
	}

	public JTextField getJtAno() {
		return jtAno;
	}

	public void setJtAno(JTextField jtAno) {
		this.jtAno = jtAno;
	}

	public JTextField getJtNumpag() {
		return jtNumpag;
	}

	public void setJtNumpag(JTextField jtNumpag) {
		this.jtNumpag = jtNumpag;
	}

	public JButton getBotoAltre() {
		return botoAltre;
	}

	public void setBotoAltre(JButton botoAltre) {
		this.botoAltre = botoAltre;
	}

	public JButton getBotoGuardar() {
		return botoGuardar;
	}

	public void setBotoGuardar(JButton botoGuardar) {
		this.botoGuardar = botoGuardar;
	}
	

}
