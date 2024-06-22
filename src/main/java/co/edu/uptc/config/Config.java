package co.edu.uptc.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

	public class Config {
	private static Config config;
	private Properties propiedades;
	private String path;
	private String nameFileCSV;

		//Constructor de la clase
		//Carga la configuracion de la aplicacion desde un archivo properties ubicado en "resources/conf/appconfig.properties".
		//Y lee las propiedades relacionadas con los nombres y rutas de los archivos utilizados por la aplicación.

		/**
		 * @author monxvoll
		 **/

	private Config() {
		this.propiedades= new Properties();
		try (FileInputStream entrada = new FileInputStream("resources/appconfig.properties")) {
			propiedades.load(entrada);
			this.path = propiedades.getProperty("app.file.path");
			this.nameFileCSV= propiedades.getProperty("app.file.name.csv");

		} catch (IOException ex) {
			System.err.println("Error al cargar el archivo properties de configuración: " + ex.getMessage());
		}
	}

		/**
		 * @author monxvoll
		 **/

	//Metodo que devuelve una instanacia de la clase actual
	public static Config getInstance() {
		if (config==null) {
			config= new Config();
		}
		return config;
	}

	//Getters y Setters
	public String getPath() {
		return path;
	}


	public String getNameFileCSV() {
		return nameFileCSV;
	}

}
