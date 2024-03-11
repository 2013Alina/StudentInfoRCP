package studentinfo.contextmenu;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import studentinfo.connectionH2.DataDAO;
import studentinfo.model.Group;

public class DeleteGroupAction extends Action {

    private Group group;
    private TreeViewer treeViewer;
    private DataDAO dataDAO;

    public DeleteGroupAction(Group group, TreeViewer treeViewer, DataDAO dataDAO ) {
        super("Delete Group.");
        this.group = group;
        this.treeViewer = treeViewer;
        this.dataDAO = dataDAO;
    }

    @Override
    public void run() {
        if (group != null) {
            List<Group> groups = (List<Group>) treeViewer.getInput();
            groups.remove(group);
            dataDAO.deleteGroup(group.getId());
            treeViewer.setInput(groups);
        } else {
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "No Group selected for deletion!");
        }
    }
}
