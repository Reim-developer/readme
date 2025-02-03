package readme.app.events;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ScrollBar;
import org.jetbrains.annotations.NotNull;
import readme.app.ulti.LazyFileReader;

public class LazyLoad {
    private SelectionAdapter selectionAdapter;

    /**
     * Load content file with scroll
     * @param styledText Styled text
     * @param lazyFileReader Lazy File Reader class
     */
    public void loadWithScroll(@NotNull StyledText styledText, @NotNull LazyFileReader lazyFileReader) {
        ScrollBar scrollBar = styledText.getVerticalBar();

        if(selectionAdapter != null) { scrollBar.removeSelectionListener(this.selectionAdapter); }

       selectionAdapter = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int lineCount = styledText.getLineCount();
                int topIndex = styledText.getTopIndex();
                int visibleLines = styledText.getClientArea().height / styledText.getLineHeight();

                if (topIndex + visibleLines >= lineCount - 10) {
                    try {
                        String moreContent = lazyFileReader.readNextLines(100);
                        if (!moreContent.isEmpty()) styledText.append(moreContent);

                    } catch (Exception exception) {
                        throw new RuntimeException("Error when loading file");
                    }
                }
            }
        };

        scrollBar.addSelectionListener(selectionAdapter);
    }
}
