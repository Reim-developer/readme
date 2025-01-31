package readme.app.buttons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import readme.app.ulti.ImageSet;

public class MainMenu {
    private final ImageSet imageSet = new ImageSet();

    public void setMainMenuButton(Composite composite, Display display) {
        Button menuButton = new Button(composite, SWT.PUSH);
        menuButton.setBackground(new Color(composite.getDisplay(), 47, 49, 54));

        menuButton.setImage(imageSet.imageButton(
                display,
                "/Menu.png",
                30, 30
        ));

        FormData formData = new FormData();
        formData.left = new FormAttachment(2, -10);
        formData.top = new FormAttachment(2, 0);
        menuButton.setLayoutData(formData);
    }
}
