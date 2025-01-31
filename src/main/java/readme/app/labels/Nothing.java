package readme.app.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class Nothing {
    public void setNothingToShow(Composite composite, Display display) {
        Label label = new Label(composite, SWT.NONE);

        label.setText("There is nothing to show.");
        label.setFont(new Font(display, "Consolas", 12, SWT.NONE));

        FormData formData = new FormData();
        formData.left = new FormAttachment(50, -100);
        formData.top = new FormAttachment(50, 0);
        label.setLayoutData(formData);
    }
}
