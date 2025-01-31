package readme.app;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import readme.app.buttons.MainMenu;
import readme.app.labels.Nothing;
import readme.app.props.Size;

public class Application {
    private final Display display = new Display();
    private final Shell shell = new Shell(display);
    private final MainMenu mainMenu = new MainMenu();
    private final Nothing nothing = new Nothing();

    public static void main(String[] args) {
        Application application = new Application();

        application.init();
    }

    private void init() {
        Color color = new Color(47, 49, 54);

        shell.setText("readme!");
        shell.setSize(new Point(Size.APP_WIDTH, Size.APP_HEIGHT));
        shell.setLayout(new FormLayout());

        mainMenu.setMainMenuButton(shell, display);
        nothing.setNothingToShow(shell, display);

        shell.open();
        while (!shell.isDisposed()) {
            if(!display.readAndDispatch()) { display.sleep(); }
        }
        color.dispose();
        shell.dispose();
    }
}
