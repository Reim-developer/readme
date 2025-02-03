package readme.app;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import readme.app.buttons.MainMenu;
import readme.app.labels.Nothing;
import readme.app.props.Size;
import readme.app.ulti.Memory;


public class Application {
    public final Display display;
    public final Shell shell;
    private final MainMenu mainMenu;
    private final Nothing nothingLabel;
    private final Memory memory;

    public Application() {
        this.display = new Display();
        this.shell = new Shell(this.display);
        this.mainMenu = new MainMenu(this);
        this.nothingLabel = new Nothing(this);
        this.memory = new Memory();
    }

    public static void main(String[] args) {
        Application application = new Application();

        application.init();
    }

    private void init() {
        Color color = new Color(this.display, 47, 49, 54);

        this.shell.setText("readme!");
        this.shell.setSize(new Point(Size.APP_WIDTH, Size.APP_HEIGHT));
        this.shell.setLayout(new FormLayout());

        this.mainMenu.setMainMenuButton(shell, display);
        this.nothingLabel.setNothingLabel(display);
        this.nothingLabel.showNothingLabel(true);

        this.shell.setBackground(color);
        this.shell.open();
        this.memory.dispose(shell, color);

        while (!this.shell.isDisposed()) {
            if(!this.display.readAndDispatch()) this.display.sleep();
        }
        this.shell.dispose();
    }
}
