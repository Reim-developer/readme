package readme.app.ulti;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class VirtualTextRenderer {
    private final StyledText styledText;
    private final LazyFileReader lazyFileReader;
    private final CompressedCache compressedCache = new CompressedCache();
    private final LinkedList<Integer> visibleLines = new LinkedList<>();

    public VirtualTextRenderer(StyledText styledText, LazyFileReader lazyFileReader) {
        this.styledText = styledText;
        this.lazyFileReader = lazyFileReader;
    }

    public void updateView() {
        new Thread(this::run).start();
    }

    private void run() {
        try {
            String[] newLines = lazyFileReader.readLines(100);

            synchronized (visibleLines) {
                int BUFFER_SIZE = 200;
                while (visibleLines.size() > BUFFER_SIZE) visibleLines.removeFirst();

                for (String line : newLines) {
                    int index = visibleLines.size();
                    compressedCache.cacheChunk(index, line);
                    visibleLines.add(index);
                }
            }


            synchronized (visibleLines) {
                String fileContent = visibleLines.stream()
                        .map(compressedCache::getChunk)
                        .collect(Collectors.joining(System.lineSeparator()));

                Display.getDefault().asyncExec(() -> this.styledText.replaceTextRange(0, this.styledText.getCharCount(), fileContent));
            }

        } catch (Exception ignored) {
        }
    }
}
