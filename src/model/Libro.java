package model;

import java.util.ArrayList;

public class Libro {
	String titulo;
	String ano;
	ArrayList<String> autor;
	String editor;
	String paginas;
	
	public Libro(String titulo, ArrayList<String> autor, String ano, String editor, String paginas){
		this.titulo=titulo;
		this.autor=autor;
		this.ano=ano;
		this.editor=editor;
		this.paginas=paginas;		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public ArrayList<String> getAutor() {
		return autor;
	}

	public void setAutor(ArrayList<String> autor) {
		this.autor = autor;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
	
	public void print(){
		System.out.print("Titulo: ");
		System.out.println(this.titulo);
		System.out.print("Autor: ");
		for (String s: this.autor) {
			System.out.print(s+" ");
		}
		System.out.println("");
		System.out.print("Año: ");
		System.out.println(this.ano);
		System.out.print("Editor: ");
		System.out.println(this.editor);
		System.out.print("Paginas: ");
		System.out.println(this.paginas);
		System.out.println();
	}
	
}
