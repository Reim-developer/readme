package readme.app.ulti;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class ImageSet {
    public Image imageButton(Display display, String imagePath, int width, int height) {
        Image originalImg = new Image(display,
                getClass().getResourceAsStream(imagePath)
        );
        ImageData imageData = originalImg.getImageData();

        originalImg.dispose();
        ImageData scaledImgData = imageData.scaledTo(width, height);

        return new Image(display, scaledImgData);
    }
}
