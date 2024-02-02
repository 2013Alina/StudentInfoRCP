package studentinfo;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class SaveAction extends Action {
    
    public SaveAction() {
        setText("Save");
        setToolTipText("Save");
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(org.eclipse.ui.ISharedImages.IMG_ETOOL_SAVE_EDIT));
    }

    @Override
    public void run() {
        
    }
}
