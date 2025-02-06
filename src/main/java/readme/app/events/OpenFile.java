package readme.app.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.dialog.File;

public class OpenFile {
    private final File file;

    public OpenFile(@NotNull AppContext appContext) {
        this.file = new File(appContext);
    }

    /**
     * Handling event when user want to choose file
     * @param table Table to handling this event
     */

    public void setOpenFile(@NotNull Table table) {
        table.addListener(SWT.DefaultSelection, event -> {
            int selectedOption = table.getSelectionIndex();

            if(Integer.valueOf(0).equals(selectedOption)) this.file.setFileDialog();
        });
    }
}
