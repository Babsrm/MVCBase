/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EditorialDAO;
import modelo.Editorial;
import vista.DialogoEditoriales;
import vista.NuevaEditorial;
import vista.VentanaPpal;

public class Controlador {

	// Ventanas del sistema. ventanas de la aplicación
	private VentanaPpal ventanaPpal;
	private DialogoEditoriales dialogoEditoriales;
	private NuevaEditorial NuevaEditorial;
	
	// Objetos DAO para el acceso a la base de datos
	private EditorialDAO editorialDAO;
	
	
	public Controlador() {
		// Creo las ventanas de la aplicación, las instancio
		this.ventanaPpal = new VentanaPpal();
		this.dialogoEditoriales = new DialogoEditoriales();
		this.NuevaEditorial = new NuevaEditorial();
		
		// Dando acceso al controlador desde las vistas
		this.ventanaPpal.setControlador(this); 
		//que a la ventana principal le va a controlar un controlador que sea este mismo que estoy creando
		this.dialogoEditoriales.setControlador(this);
		this.NuevaEditorial.setControlador(this);
		
		// Creamos los objetos DAO
		editorialDAO = new EditorialDAO();
	}
	
	
	public void inciarPrograma() {
		//muestra la ventana principal para que se empiece a interactuar con ella
		this.ventanaPpal.setVisible(true);
	}
	
	public void mostrarEditoriales() {
		ArrayList<Editorial> lista = editorialDAO.obtenerTodasEditoriales();
		dialogoEditoriales.setListaEditoriales(lista);
		dialogoEditoriales.setVisible(true);
	}
	
	public void mostrarNuevaEditorial() {
		NuevaEditorial.setEditorial(null);
		NuevaEditorial.setVisible(true);
	}
	
	public void insertarEditorial(Editorial ed) {
		int res=editorialDAO.insertarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error no se ha podido insertar");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial a�adido correctamente.");
			NuevaEditorial.setVisible(false);
		}
	}

	public void mostrarActualizarEditorial(int codEditorial) {
		Editorial e = editorialDAO.obtenerUnaEditorial(codEditorial);
		NuevaEditorial.setEditorial(e);
		NuevaEditorial.setVisible(true);
	}

	public void actualizarEditorial(Editorial ed) {
		int res=editorialDAO.actualizarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error no se ha podido actualizar");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial actualizado correctamente.");
			NuevaEditorial.setVisible(false);
		}
		mostrarEditoriales();
	}
}
