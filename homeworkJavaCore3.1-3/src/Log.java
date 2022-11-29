import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Log {
    private StringBuilder logText = new StringBuilder();

    public void addMessageToLog(String message) {
        logText.append(message);
        logText.append("\n");
    }

    public void save(String path) {
        try (FileWriter writer = new FileWriter(path, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            writer.write(java.time.LocalDateTime.now().format(formatter));
            writer.append('\n');
            writer.write(logText.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
