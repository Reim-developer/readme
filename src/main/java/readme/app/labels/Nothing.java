package readme.app.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.ulti.Memory;

public class Nothing {
    private final Label label;
    private final Memory memory;

    public Nothing(@NotNull Application application) {
        this.label = new Label(application.shell, SWT.NONE);
        this.memory = new Memory();
    }

    public void setNothingLabel(Display display) {
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

    public void showNothingLabel(boolean visible) {
        this.label.setVisible(visible);
    }
}
