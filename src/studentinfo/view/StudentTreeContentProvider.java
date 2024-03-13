package studentinfo.view;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;

import studentinfo.model.Group;
import studentinfo.model.Student;

public class StudentTreeContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof List<?>) {
            return ((List<?>) inputElement).toArray();
        }
        return new Object[0];
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Group) {
            return ((Group) parentElement).getStudentslist().toArray();
        }
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Student) {
            return ((Student) element).getGroup();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof Group;
    }
}
