package readme.app.events;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.taskbar.ViewOptions;

public class ViewEvent {
    private final ViewOptions viewOptions;
    private final Application application;

    public ViewEvent(@NotNull Application application) {
        this.application = application;
        this.viewOptions = new ViewOptions(this.application);
    }

    /**
     * Handles view button event
     * @param button The button that triggers the display of the view table
     */
    public void setViewEvent(@NotNull Button button) {
        button.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseEnter(MouseEvent e) {
                Display.getCurrent().timerExec(100, () -> {
                    viewOptions.table.setVisible(true);
                    application.shell.layout();
                });
            }
        });

        button.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
                Display.getCurrent().timerExec(100, () -> {
                    if(viewOptions.isMouseOverTable()) return;

                    viewOptions.showViewOptions(false);
                    application.shell.layout();
                });
            }
        });

        this.viewOptions.table.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
               Display.getCurrent().timerExec(100, () -> {
                   viewOptions.showViewOptions(false);
                   application.shell.layout();
               });
            }
        });
    }
}
