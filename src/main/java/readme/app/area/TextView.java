package readme.app.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.ulti.Memory;

public class TextView {
    public StyledText styledText;
    private final Memory memory;
    private final Color color;
    private Composite textViewContainer;

    public TextView(@NotNull Application application) {
        this.memory = new Memory();
        this.color = new Color(application.display, 47, 49,54);
    }

    /**
     * Display text view when user open a file
     * @param composite Main composite layout, such as Shell
     */

    public void setTextView(@NotNull Composite composite) {
        textViewContainer = new Composite(composite, SWT.NONE);
        textViewContainer.setLayout(new FormLayout());
        textViewContainer.setBackground(color);

        FormData textViewData = new FormData();
        textViewData.left = new FormAttachment(20, 0);
        textViewData.right = new FormAttachment(100, 10);
        textViewData.top = new FormAttachment(20, 10);
        textViewData.bottom = new FormAttachment(90, 100);
        textViewContainer.setLayoutData(textViewData);

        Canvas lineNumber = new Canvas(textViewContainer, SWT.NONE);
        lineNumber.setBackground(color);
        lineNumber.setForeground(new Color(255, 255, 255));

        FormData lineNumberData = new FormData(40, SWT.NONE);
        lineNumberData.left = new FormAttachment(0, 0);
        lineNumberData.bottom = new FormAttachment(90, 0);
        lineNumber.setLayoutData(lineNumberData);

        this.styledText = new StyledText(textViewContainer,
                SWT.NONE |
                        SWT.MULTI |
                        SWT.V_SCROLL |
                        SWT.H_SCROLL
                );

        Font font  = new Font(composite.getDisplay(), "Consolas", 10, SWT.NORMAL);
        this.styledText.setWordWrap(false);
        this.styledText.setEditable(false);
        this.styledText.setFont(font);
        this.styledText.setBackground(color);

        FormData styledTextData = new FormData();
        styledTextData.top = new FormAttachment(0, 0);
        styledTextData.left = new FormAttachment(lineNumber, 10);
        styledTextData.right = new FormAttachment(98, 0);
        styledTextData.bottom =  new FormAttachment(90, 0);
        this.styledText.setLayoutData(styledTextData);

        lineNumberData.top = new FormAttachment(this.styledText, 0, SWT.TOP);

        this.styledText.addModifyListener(event -> lineNumber.redraw());
        this.styledText.addListener(SWT.V_SCROLL, event -> lineNumber.redraw());

        lineNumber.addPaintListener(event -> {
            GC gc = event.gc;
            gc.setFont(this.styledText.getFont());

            int lineHeight = this.styledText.getLineHeight();
            int topPixel = this.styledText.getTopPixel();
            int firstLine = topPixel / lineHeight;
            int visibleLines = this.styledText.getClientArea().height / lineHeight;
            int maxLineNumber = this.styledText.getLineCount();

            Point textSize = gc.textExtent(String.valueOf(maxLineNumber));
            int newWidth = textSize.x + 10;

            if (newWidth != lineNumber.getSize().x) {
                FormData formData = (FormData) lineNumber.getLayoutData();
                formData.width = newWidth;
                textViewContainer.layout();
            }

            for (int i = 0; i < visibleLines; i++) {
                int lineCount = firstLine + i + 1;
                if (lineCount > this.styledText.getLineCount()) break;

                int y = i * lineHeight - (topPixel % lineHeight);
                gc.drawText(String.valueOf(lineCount), 5, y, true);
            }
        });

        textViewContainer.setVisible(false);
        this.memory.dispose(styledText, color);
        this.memory.dispose(lineNumber, color);
        this.memory.dispose(this.styledText, font);

        this.styledText.getVerticalBar().addListener(SWT.Selection, event -> lineNumber.redraw());
    }

    public void showTextView(boolean isVisible) { this.textViewContainer.setVisible(isVisible); }
}
