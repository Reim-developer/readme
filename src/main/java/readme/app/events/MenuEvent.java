package readme.app.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.taskbar.Taskbar;

public class MenuEvent {

    private final AppContext appContext;
    private final Taskbar taskbar;
    private boolean isVisible = false;

    public MenuEvent(@NotNull AppContext appContext) {
        this.appContext = appContext;
        this.taskbar = new Taskbar(this.appContext);
        this.taskbar.setTaskbarUI(this.appContext.shell,this.appContext.display);
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
            this.appContext.shell.layout();

            if(isVisible) { button.setToolTipText("Hide Main Menu"); return; }
            button.setToolTipText("Show Main Menu");
        });
    }
}
