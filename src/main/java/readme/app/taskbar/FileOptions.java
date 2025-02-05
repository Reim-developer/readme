package readme.app.taskbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.*;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.events.OpenFile;
import readme.app.ulti.Memory;

public class FileOptions {
    public final Table table;
    private final TableColumn tableColumn;
    private final String[] fileOptions;
    private final Color color;
    private boolean isMouseOverTable;
    private final OpenFile openFile;
    private final Memory memory;

    public FileOptions(@NotNull Application application) {
        this.table = new Table(application.shell, SWT.FULL_SELECTION);
        this.tableColumn = new TableColumn(table, SWT.CENTER);

        this.fileOptions = new String[]{"Open File", "Open Folder", "Exit"};
        this.color = new Color(application.display, 47, 49, 54);
        this.openFile = new OpenFile(application);
        this.memory = new Memory();
    }

    public void setFileOptions() {
        this.table.setBackground(this.color);
        this.tableColumn.setWidth(100);

        for(String option : this.fileOptions)  {
            TableItem tableItem = new TableItem(this.table, SWT.NONE);
            tableItem.setText(0, option);
        }

        FormData formData = new FormData();
        formData.left = new FormAttachment(1, 20);
        formData.top = new FormAttachment(5, 22);
        formData.width = 135;

        this.table.setLayoutData(formData);
        this.table.setVisible(false);

        this.table.addListener(SWT.MouseEnter, event -> this.isMouseOverTable = true);
        this.table.addListener(SWT.MouseExit, event -> this.isMouseOverTable = false);
        this.openFile.setOpenFile(this.table);
        this.memory.dispose(this.table, this.color);
    }

    public void showFileOptions(boolean visible) { this.table.setVisible(visible); }

    public boolean isMouseOverTable() { return this.isMouseOverTable; }
}
