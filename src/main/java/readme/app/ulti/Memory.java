package readme.app.ulti;

import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Control;
import org.jetbrains.annotations.NotNull;

public class Memory {
    /**
     * This function fixes memory leaks
     * @param control Widget control, such as Button, Label, etc.
     * @param resources Resources, such as images, icons, and fonts.
     */
    public void dispose(@NotNull Control control, @NotNull Resource ... resources) {
        if(control.isDisposed() || resources.length == 0) return;

        control.addDisposeListener(event -> {
            for(Resource resource : resources) resource.dispose();
        });
    }
}
