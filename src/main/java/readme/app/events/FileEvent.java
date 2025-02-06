package readme.app.events;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.taskbar.FileOptions;

public class FileEvent {
    private final FileOptions fileOptions;
    private final AppContext appContext;

    public FileEvent(@NotNull AppContext appContext) {
        this.appContext = appContext;
        this.fileOptions = new FileOptions(this.appContext);
        this.fileOptions.setFileOptions();
    }

    public void setFileEvent(@NotNull Button button) {
        button.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseEnter(MouseEvent e) {
                Display.getCurrent().timerExec(100, () -> {
                    fileOptions.showFileOptions(true);
                    appContext.shell.layout();
                });
            }
        });

        button.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
                Display.getCurrent().timerExec(100, () -> {
                    if(fileOptions.isMouseOverTable()) return;

                    fileOptions.showFileOptions(false);
                    appContext.shell.layout();
                });
            }
        });

        this.fileOptions.table.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseExit(MouseEvent e) {
                Display.getCurrent().timerExec(100, () -> {
                    fileOptions.showFileOptions(false);
                    appContext.shell.layout();
                });
            }
        });
    }
}
