package readme.app.taskbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.ulti.Memory;

public class ViewOptions {
    public final Table table;
    private final TableColumn tableColumn;
    private final String[] viewOptions;
    private final Color color;
    private final Memory memory;
    private boolean isMouseOverTable;

    public ViewOptions(@NotNull Application application) {
        this.table = new Table(application.shell, SWT.FULL_SELECTION);
        this.tableColumn = new TableColumn(this.table, SWT.CENTER);
        this.viewOptions = new String[]{"Enable word wrap", "Zoom in", "Zoom out"};
        this.color = new Color(application.display, 47, 49, 54);
        this.memory = new Memory();

        setViewOptions();
    }


    private void setViewOptions() {
        this.table.setBackground(this.color);
        this.tableColumn.setWidth(100);

        FormData formData = new FormData();
        formData.left = new FormAttachment(14, 25);
        formData.top = new FormAttachment(5, 22);
        formData.width = 135;
        this.table.setLayoutData(formData);

        for(String option : this.viewOptions) {
            TableItem tableItem = new TableItem(this.table, SWT.NONE);
            tableItem.setText(0, option);
        }

        this.table.addListener(SWT.MouseEnter, event -> this.isMouseOverTable = true);
        this.table.addListener(SWT.MouseExit, event -> this.isMouseOverTable = false);

        this.table.setVisible(false);
        this.memory.dispose(this.table, this.color);
    }

    public void showViewOptions(boolean visible) {
        this.table.setVisible(visible);
    }

    public boolean isMouseOverTable() { return isMouseOverTable; }
}
