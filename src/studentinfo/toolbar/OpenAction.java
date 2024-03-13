package studentinfo.toolbar;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class OpenAction extends Action {

    public OpenAction() {
        setText("Open");
        setToolTipText("Open");
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(org.eclipse.ui.ISharedImages.IMG_OBJ_FOLDER));
    }

    @Override
    public void run() {
        
    }
}
