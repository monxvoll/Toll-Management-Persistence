package co.edu.uptc.persistence;

import co.edu.uptc.config.Config;
import co.edu.uptc.constants.CommonConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilePlain {
    protected Config confValue;
    private static final String ERROR_WRITING_FILE = "Error al escribir en el archivo: ";

    public FilePlain() {
        confValue = Config.getInstance();
    }

    /**
     * @author monxvoll
     **/

    protected void writeFile(String rutaNombreArchivo, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaNombreArchivo))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println(ERROR_WRITING_FILE + e.getMessage());
        }
    }

    /**
     * @author monxvoll
     **/

    protected void writer(String rutaNombre, List<String> file) {
        StringBuilder strContent = new StringBuilder();
        file.forEach(record -> strContent.append(record).append(CommonConstants.NEXT_LINE));
        writeFile(rutaNombre, strContent.toString());
    }

    public Config getConfValue() {
        return confValue;
    }
}
