package readme.app.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.taskbar.Taskbar;

public class MenuEvent {

    private final Application application;
    private final Taskbar taskbar = new Taskbar();
    private boolean isVisible = false;

    public MenuEvent(Application application) {
        this.application = application;
        this.taskbar.setTaskbarUI(application.shell, application.display);
    }

    public void openMenu(@NotNull Button button) {
        button.addListener(SWT.Selection,  event -> {
            isVisible = !isVisible;

            taskbar.showTaskbar(isVisible);
            application.shell.layout();
        });
    }
}
