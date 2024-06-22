package co.edu.uptc.persistence;


import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Toll;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagementToll extends FilePlain  {

	private List<Toll> tollList;


	public ManagementToll() {
		this.tollList = new ArrayList<>();
	}

	/**
	 * @author monxvoll
	 **/

	public void loadToll(ETypeFile eTypeFile) {
		if (eTypeFile == ETypeFile.CSV) {
			if (confValue != null && confValue.getPath() != null) {
				String filePath = confValue.getPath().concat(confValue.getNameFileCSV());

				this.loadFilePlainCsv(filePath);
			}
				//System.err.println("Error: Config or path is null.");
				// Manejo de error o lanzamiento de excepción según sea necesario

		}
	}


	/**
	 * @author monxvoll
	 **/

	//Metodo que lee el archivo y añade cada linea ( en este caso cada objeto ) a la lista de peajes
	public void loadFilePlainCsv(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(";");
				if (fields.length == 10) {
					String ciudadOrigen = fields[0].trim();
					String ciudadDestino = fields[1].trim();
					String via = fields[2].trim();
					String tipoVia = fields[3].trim();
					String peaje = fields[4].trim();
					String categoria = fields[5].trim();
					int costo = Integer.parseInt(fields[6].trim());
					String tiempo = fields[7].trim();
					int distancia = Integer.parseInt(fields[8].trim());
					boolean enOperacion = fields[9].trim().equalsIgnoreCase("SI");

					Toll toll = new Toll(ciudadOrigen, ciudadDestino, via, tipoVia, peaje,
							categoria, costo, tiempo, distancia, enOperacion);
					tollList.add(toll);
				} else {
					System.err.println("Línea con formato incorrecto: " + line);
				}
			}

		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir número: " + e.getMessage());
		}
	}

	//Getters
	public List<Toll> getTollList() {
		return tollList;
	}
}