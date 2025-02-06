package readme.app.events;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.taskbar.ViewOptions;

public class ViewEvent {
    private final AppContext appContext;
    private final ViewOptions viewOptions;

    public ViewEvent(@NotNull AppContext appContext) {
        this.appContext = appContext;
        this.viewOptions = new ViewOptions(this.appContext);
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
                    appContext.shell.layout();
                });
            }
        });

        button.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
                Display.getCurrent().timerExec(100, () -> {
                    if(viewOptions.isMouseOverTable()) return;

                    viewOptions.showViewOptions(false);
                    appContext.shell.layout();
                });
            }
        });

        this.viewOptions.table.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
               Display.getCurrent().timerExec(100, () -> {
                   viewOptions.showViewOptions(false);
                   appContext.shell.layout();
               });
            }
        });
    }
}
