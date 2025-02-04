package readme.app.ulti;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LazyFileReader {
    private final BufferedReader reader;
    int currentLine = 0;

    public LazyFileReader(String filePath) throws IOException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    public String readNextLines(int numLines) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        int linesRead = 0;

        while (linesRead < numLines && (line = reader.readLine()) != null) {
            stringBuilder.append(line).append(System.lineSeparator());
            linesRead++;
            currentLine++;
        }
        return stringBuilder.toString();
    }

    @NotNull
    public String[] readLines(int numLines) throws IOException {
        String[] lines = new String[numLines];
        String line;
        int lineRead = 0;

        while(lineRead < numLines && (line = reader.readLine()) != null) {
            lines[lineRead] = line;
            lineRead++;
            currentLine++;
        }

        return lineRead == numLines ? lines : Arrays.copyOf(lines, lineRead);
    }
}