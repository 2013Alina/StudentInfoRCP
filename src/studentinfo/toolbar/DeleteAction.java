package studentinfo.toolbar;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class DeleteAction extends Action {
    
    public DeleteAction() {
        setText("Delete");
        setToolTipText("Delete Group");
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(org.eclipse.ui.ISharedImages.IMG_ETOOL_DELETE));
    }

    @Override
    public void run() {

    }
}
