package readme.app.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.taskbar.FileOptions;

public class FileEvent {
    private final FileOptions fileOptions;
    private final Application application;

    public FileEvent(Application application) {
        this.application = application;
        this.fileOptions = new FileOptions(this.application);
        this.fileOptions.setFileOptions();
    }

    public void setFileEvent(@NotNull Button button) {
        button.addListener(SWT.MouseEnter, event -> {
           this.fileOptions.showFileOptions(true);
           this.application.shell.layout();
        });

        button.addListener(SWT.MouseExit, event ->
                Display.getCurrent().timerExec(100, () -> {
                    if(!this.fileOptions.isMouseOverTable()) {
                        this.fileOptions.showFileOptions(false);
                        this.application.shell.layout();
                    }
        }));

        this.fileOptions.table.addListener(SWT.MouseExit, event -> {
            this.fileOptions.table.setVisible(false);
            this.application.shell.layout();
        });
    }
}
