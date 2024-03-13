package studentinfo.view;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import studentinfo.model.Group;
import studentinfo.model.Student;

public class StudentTreeLabelProvider extends LabelProvider implements ILabelProvider {

    @Override
    public Image getImage(Object element) {
        if (element instanceof Group) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        } else if (element instanceof Student) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
        }
        return super.getImage(element);
    }

//    @Override
//    public String getText(Object element) {
//        if (element instanceof Group) {
//            return ((Group) element).getName();
//        } else if (element instanceof Student) {
//            return ((Student) element).getName();
//        }
//        return super.getText(element);
//    }
}
