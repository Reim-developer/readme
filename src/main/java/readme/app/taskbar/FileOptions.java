package readme.app.taskbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.*;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;

public class FileOptions {
    public final Table table;
    private final TableColumn tableColumn;
    private final String[] fileOptions;
    private final Color color;
    private boolean isMouseOverTable;

    public FileOptions(@NotNull Application application) {
        this.table = new Table(application.shell, SWT.BORDER);
        this.tableColumn = new TableColumn(table, SWT.CENTER);

        this.fileOptions = new String[]{"Open File", "Open Folder", "New File", "New Folder", "Exit"};
        this.color = new Color(application.display, 47, 49, 54);
    }

    public void setFileOptions() {
        this.table.setBackground(this.color);
        this.tableColumn.setWidth(100);

        for(String option : this.fileOptions)  {
            TableItem tableItem = new TableItem(this.table, SWT.NONE);
            tableItem.setText(0, option);
        }

        FormData formData = new FormData();
        formData.left = new FormAttachment(5, 20);
        formData.top = new FormAttachment(5, 22);
        formData.width = 135;

        table.setLayoutData(formData);
        table.setVisible(false);

        table.addListener(SWT.MouseEnter, event -> isMouseOverTable = true);
        table.addListener(SWT.MouseExit, event -> isMouseOverTable = false);
    }

    public void showFileOptions(boolean visible) {
        this.table.setVisible(visible);
    }

    public boolean isMouseOverTable() {
        return isMouseOverTable;
    }
}
