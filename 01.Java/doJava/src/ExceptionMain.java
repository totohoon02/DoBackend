import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExceptionMain {
    public static void main(String[] args) {

    }

    public static String readFile() {
        try {
            return Files.readString(Path.of("path"));
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }

        return null;
    }
}
