package readme.app.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.ulti.Memory;

public class Nothing {
    public final Label label;
    private final Memory memory;

    public Nothing(@NotNull AppContext appContext) {
        this.label = new Label(appContext .shell, SWT.NONE);
        this.memory = new Memory();

        setNothingLabel(appContext.display);
    }

    private void setNothingLabel(Display display) {
        Font font = new Font(display, "Consolas", 12, SWT.NONE);

        label.setText("There is nothing to show.");
        label.setFont(font);

        FormData formData = new FormData();
        formData.left = new FormAttachment(50, -100);
        formData.top = new FormAttachment(50, 0);
        label.setLayoutData(formData);

        label.setVisible(false);
        this.memory.dispose(label, font);
    }
}
