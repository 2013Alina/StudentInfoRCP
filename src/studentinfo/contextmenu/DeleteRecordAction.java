package studentinfo.contextmenu;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import studentinfo.connectionH2.DataDAO;
import studentinfo.model.Group;
import studentinfo.model.Student;

public class DeleteRecordAction extends Action {

    private TreeViewer treeViewer;
    private Student student;
    private DataDAO dataDAO;

    public DeleteRecordAction(Student student, TreeViewer treeViewer, DataDAO dataDAO) {
        super("Delete record.");
        this.treeViewer = treeViewer;
        this.student = student;
        this.dataDAO = dataDAO;
    }

    @Override
    public void run() {
        if (student != null) {
            List<Group> groups = (List<Group>) treeViewer.getInput();
            for (Group group : groups) {
                if (group.getStudentslist().contains(student)) {
                    group.getStudentslist().remove(student);
                    dataDAO.deleteStudent(student.getId());
                    treeViewer.setInput(groups);
                    break;
                }
            }
        } else {
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "No Student selected for deletion!");
        }
    }
}
