package readme.app.dialog;

import org.eclipse.swt.widgets.FileDialog;
import org.jetbrains.annotations.NotNull;
import readme.app.AppContext;
import readme.app.events.LazyLoad;
import readme.app.ulti.LazyFileReader;
import readme.app.ulti.VirtualTextRenderer;

public class File {
    private final FileDialog fileDialog;
    private final LazyLoad lazyLoad;
    private final AppContext appContext;

    public File(@NotNull AppContext appContext) {
        this.appContext = appContext;
        this.fileDialog = new FileDialog(this.appContext.shell);
        this.lazyLoad = new LazyLoad();
    }

    public void setFileDialog() {
        fileDialog.setText("Open file");
        fileDialog.setFilterPath(System.getProperty("user.home"));

        String filePath = fileDialog.open();
        if(filePath == null) return;

        this.appContext.getNothingLabel().label.setVisible(false);
        this.appContext.getTextView().textViewContainer.setVisible(true);

        try {
            LazyFileReader lazyFileReader = new LazyFileReader(filePath);
            VirtualTextRenderer virtualTextRenderer = new VirtualTextRenderer(this.appContext.getTextView().styledText, lazyFileReader);
            virtualTextRenderer.updateView();

            this.lazyLoad.loadWithScroll(this.appContext.getTextView().styledText, lazyFileReader);
        } catch (Exception ignored) {}
    }
}
