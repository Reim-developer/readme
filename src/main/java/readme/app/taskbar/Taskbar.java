package readme.app.taskbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.events.FileEvent;
import readme.app.events.ViewEvent;
import readme.app.ulti.Memory;

public class Taskbar {
    private Composite taskbar;
    private final FileEvent fileEvent;
    private final Memory memory;
    private final ViewEvent viewEvent;

    public Taskbar(@NotNull  Application application) {
        this.fileEvent = new FileEvent(application);
        this.viewEvent = new ViewEvent(application);
        this.memory = new Memory();
    }

    public void setTaskbarUI(Composite composite, Display display) {
        this.taskbar = new Composite(composite, SWT.NONE);
        this.taskbar.setLayout(new FormLayout());

        Color color = new Color(display, 47, 49, 54);
        this.taskbar.setBackground(color);

        FormData taskbarFormData = new FormData();
        taskbarFormData.left = new FormAttachment(6, 0);
        taskbarFormData.right = new FormAttachment(100, 10);
        taskbarFormData.height = 55;
        this.taskbar.setLayoutData(taskbarFormData);

        Button openFile = new Button(taskbar, SWT.PUSH);
        openFile.setText("File");
        openFile.setBackground(color);

        Button viewButton = new Button(taskbar, SWT.PUSH);
        viewButton.setText("View");
        viewButton.setBackground(color);

        FormData openFileData = new FormData();
        openFileData.left = new FormAttachment(0, 10);
        openFileData.top = new FormAttachment(35, 0);
        openFileData.height = 30;
        openFileData.width = 70;

        FormData viewData = new FormData();
        viewData.left = new FormAttachment(openFile, 15);
        viewData.top = new FormAttachment(35, 0);
        viewData.height  = 30;
        viewData.width = 70;

        viewButton.setLayoutData(viewData);
        openFile.setLayoutData(openFileData);

        this.taskbar.setVisible(false);

        this.fileEvent.setFileEvent(openFile);
        this.viewEvent.setViewEvent(viewButton);

        this.memory.dispose(openFile, color);
        this.memory.dispose(viewButton, color);
    }

    public void showTaskbar(boolean visible) {
        taskbar.setVisible(visible);
    }
}
