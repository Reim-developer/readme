package readme.app.ulti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
}