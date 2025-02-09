package readme.app.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;

public class WordWrap {
    private static boolean enable = false;
    private final AppContext appContext;
    private final MessageBox messageBox;

    public WordWrap(@NotNull AppContext appContext) {
        this.appContext = appContext;
        this.messageBox = new MessageBox(appContext.shell, SWT.ICON_INFORMATION | SWT.OK);
    }

    /**
     * Private method to handles event on table
     * @param table Table to handle
     */
    private void setupEvent(@NotNull Table table) {
        if(!appContext.getTextView().textViewContainer.isVisible()) {
            messageBox.setText("Notification");
            messageBox.setMessage("Your need open  a file to enable/disable word wrap");
            messageBox.open();
            return;
        }

        enable = !enable;
        table.getItem(0).setText(enable ? "Disable word wrap" : "Enable word wrap");
        appContext.getTextView().styledText.setWordWrap(enable);
        appContext.getTextView().styledText.redraw();
    }

    /**
     * Public method, setup event for table
     * @param table Table UI component for setup
     */
    public void setWordWrap(@NotNull Table table) {
        table.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                TableItem tableItem = (TableItem) e.item;
                int selection = table.indexOf(tableItem);

                if(selection == 0) setupEvent(table);
            }
        });
    }
}
