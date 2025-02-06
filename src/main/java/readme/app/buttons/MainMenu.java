package readme.app.buttons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.events.MenuEvent;
import readme.app.ulti.ImageSet;

public class MainMenu {
    private final ImageSet imageSet = new ImageSet();
    private final MenuEvent menuEvent;

    public MainMenu(@NotNull AppContext appContext) {
        this.menuEvent = new MenuEvent(appContext);
    }

    public void setMainMenuButton(Composite composite, Display display) {
        Button menuButton = new Button(composite, SWT.PUSH);
        menuButton.setBackground(new Color(composite.getDisplay(), 47, 49, 54));
        menuButton.setToolTipText("Show Main Menu");

        menuButton.setImage(imageSet.imageButton(
                display,
                "/Menu.png",
                30, 30
        ));

        FormData formData = new FormData();
        formData.left = new FormAttachment(2, -10);
        formData.top = new FormAttachment(2, 0);
        menuButton.setLayoutData(formData);

        menuEvent.openMenu(menuButton);
    }
}
