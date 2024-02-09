package studentinfo.toolbar;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class AddAction extends Action {
    
    public AddAction() {
        setText("Add");
        setToolTipText("Add");
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(org.eclipse.ui.ISharedImages.IMG_OBJ_ADD));
    }

    @Override
    public void run() {

    }
}
