package readme.app;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import readme.app.area.TextView;
import readme.app.labels.Nothing;

public class AppContext {
    public final Display display;
    public final Shell shell;
    private final Nothing nothing;
    private final TextView textView;

    public AppContext() {
        this.display = new Display();
        this.shell = new Shell(this.display);
        this.nothing = new Nothing(this);
        this.textView = new TextView(this);
    }

    public Nothing getNothingLabel() {
        return this.nothing;
    }

    public TextView getTextView() {
        return this.textView;
    }
}
