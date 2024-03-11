package studentinfo.contextmenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import studentinfo.connectionH2.DataDAO;
import studentinfo.model.Group;
import studentinfo.model.Student;

public class CreateNewGroup extends Action {
    
    private TreeViewer treeViewer;
    private DataDAO dataDAO;
    
    public CreateNewGroup(TreeViewer treeViewer, DataDAO dataDAO) {
        super("Create new Group.");
        this.treeViewer = treeViewer;
        this.dataDAO = dataDAO;
    }
    
    @Override
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        InputDialog dialog = new InputDialog(shell, "Add new Group", "Enter the name of the new group:", "Group", null);
        if (dialog.open() == InputDialog.OK) {
            String groupName = dialog.getValue();
            List<Group> allGroups = (List<Group>) treeViewer.getInput();
            Iterator<Group> iterator = allGroups.iterator();
            boolean groupExists = false;
            while (iterator.hasNext()) {
                Group group = iterator.next();
                if (group.getName().equals(groupName)) {
                    groupExists = true;
                    MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Such a Group already exists!");
                    break;
                }
            }
            if (!groupExists) {
                List<Student> students = new ArrayList<>();
                Group newGroup = new Group();
                newGroup.setName(groupName);
                newGroup.setStudentslist(students);
                dataDAO.saveGroup(newGroup, treeViewer);
                allGroups.add(newGroup);
                treeViewer.setInput(allGroups);
                treeViewer.refresh();
                
            }
        }
    }
}
