package readme.app.dialog;


import org.eclipse.swt.widgets.FileDialog;
import org.jetbrains.annotations.NotNull;
import readme.app.Application;
import readme.app.area.TextView;
import readme.app.events.LazyLoad;
import readme.app.labels.Nothing;
import readme.app.ulti.LazyFileReader;

public class File {
    private final FileDialog fileDialog;
    private final TextView textView;
    private LazyFileReader lazyFileReader;
    private final LazyLoad lazyLoad;
    private final Nothing nothing;

    public File(@NotNull Application application) {
        this.fileDialog = new FileDialog(application.shell);
        this.textView = new TextView(application);
        this.textView.setTextView(application.shell);
        this.lazyLoad = new LazyLoad();
        this.nothing = new Nothing(application);
        this.nothing.setNothingLabel(application.display);
    }

    public void setFileDialog() {
        fileDialog.setText("Open file");
        fileDialog.setFilterPath(System.getProperty("user.home"));

        String filePath = fileDialog.open();
        if(filePath == null) return;

        this.nothing.showNothingLabel(false);
        this.textView.showTextView(true);
        String fileContent = "";
        this.textView.styledText.setText(fileContent);

        try {
            lazyFileReader = new LazyFileReader(filePath);
            fileContent = lazyFileReader.readNextLines(100);
            this.textView.styledText.append(fileContent);
        }
        catch (Exception exception) { System.out.println("Error when read file"); }

        this.lazyLoad.loadWithScroll(this.textView.styledText, this.lazyFileReader);
    }
}
