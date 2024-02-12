package studentinfo.contextmenu;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import studentinfo.model.Group;
import studentinfo.model.Student;
import studentinfo.view.StudentTreeView;

public class DeleteRecordAction extends Action {

    private TreeViewer treeViewer;
    private Student student;

    public DeleteRecordAction(Student student, TreeViewer treeViewer) {
        super("Delete record.");
        this.treeViewer = treeViewer;
        this.student = student;
    }

    @Override
    public void run() {
        if (student != null) {
            List<Group> groups = (List<Group>) treeViewer.getInput();
            for (Group group : groups) {
                if (group.getStudentslist().contains(student)) {
                    group.getStudentslist().remove(student);
                    treeViewer.setInput(groups);
                    break;
                }
            }
        } else {
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "No Student selected for deletion!");
        }
    }
}
