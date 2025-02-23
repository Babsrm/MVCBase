/**
 * 
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author David
 * Clase que modela una conexión a la base de datos.
 * Utiliza un patrón singleton para no tener que recrear
 * la conexion si ya se ha creado previamente
 * Si se quiere cambiar los detalles de la conexión se 
 * deberán modificar las constantes.
 */
public class ConexionBD {

	private static final String database = "biblioteca";
	private static final String usuario = "root";
	private static final String contraseña = "1234";
	private static final String url="jdbc:mysql://localhost/"+database;
	
	private Connection conexion=null;
	
	/**
	 * Método getter de la conexión. Se encarga de registrar el driver de MySQL y
	 * solicitar un objeto conection con los detalles de conexion especificados 
	 * por las constantes de clase
	 * Controla las excepciones de la clase, con lo que nos las propaga hacia la 
	 * interfaz de usuario
	 * @return
	 */
	public Connection getConexion() {
		if (conexion!=null) {
			return conexion;
		}
		
		// RE-egistra el driver de MySQL
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexion = DriverManager.getConnection(url, usuario, contraseña);
			System.out.println("Conexion a bilioteca correcta");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no registrado");
		} catch (SQLException e) {
			System.out.println("Error SQLException: "+e.getMessage());
		}
		return conexion;
	}
	
	//desconecta de la base de datos
	public void desconectar() {
		try {
			conexion.close();
			conexion=null;
			System.out.println("Conexión liberada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error cerrando la conexion "+ e.getMessage());
		}
	}

}
