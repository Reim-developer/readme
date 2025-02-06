package readme.app;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import readme.app.buttons.MainMenu;
import readme.app.props.Size;

public class Application {
    private final MainMenu mainMenu;
    private final AppContext appContext;

    public Application() {
        this.appContext = new AppContext();
        this.mainMenu = new MainMenu(this.appContext);
    }

    public static void main(String[] args) {
        Application application = new Application();

        application.init();
    }

    private void init() {
        Color color = new Color(this.appContext.display, 47, 49, 54);

        this.appContext.shell.setText("readme!");
        this.appContext.shell.setSize(new Point(Size.APP_WIDTH, Size.APP_HEIGHT));
        this.appContext.shell.setLayout(new FormLayout());
        this.mainMenu.setMainMenuButton(this.appContext.shell, appContext.display);
        this.appContext.shell.setBackground(color);

        this.appContext.getNothingLabel().label.setVisible(true);

        this.appContext.shell.open();

        while (!this.appContext.shell.isDisposed()) {
            if(!this.appContext.display.readAndDispatch()) this.appContext.display.sleep();
        }
        color.dispose();
        this.appContext.shell.dispose();
    }
}
