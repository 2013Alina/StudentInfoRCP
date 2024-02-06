package studentinfo;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class StudentTreeLabelProvider extends LabelProvider implements ILabelProvider {

    @Override
    public Image getImage(Object element) {

        return null;
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

    @Override
    public void dispose() {

    }
}
