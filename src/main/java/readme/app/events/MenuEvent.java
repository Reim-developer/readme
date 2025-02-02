package readme.app.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.taskbar.Taskbar;

public class MenuEvent {

    private final Application application;
    private final Taskbar taskbar;
    private boolean isVisible = false;

    public MenuEvent(@NotNull Application application) {
        this.application = application;
        this.taskbar = new Taskbar(this.application);
        this.taskbar.setTaskbarUI(application.shell, application.display);
    }

    /**
     * When user click to Menu Button,
     * we show a Taskbar Menu.
     * @param button Button to add SWT Selection event listener
     */

    public void openMenu(@NotNull Button button) {
        button.addListener(SWT.Selection,  event -> {
            this.isVisible = !isVisible;

            this.taskbar.showTaskbar(isVisible);
            this.application.shell.layout();

            if(isVisible) { button.setToolTipText("Hide Main Menu"); return; }
            button.setToolTipText("Show Main Menu");
        });
    }
}
