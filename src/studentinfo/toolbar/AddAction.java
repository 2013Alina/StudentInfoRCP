package studentinfo.toolbar;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class AddAction extends Action {
    
//    private TreeViewer treeViewer;

    public AddAction() {
//        this.treeViewer = treeViewer;
        setText("Add");
        setToolTipText("Add");
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
                .getImageDescriptor(org.eclipse.ui.ISharedImages.IMG_OBJ_ADD));
    }

    @Override
    public void run() {
//        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//        InputDialog dialog = new InputDialog(shell, "Add new Group",
//                "Enter the name of the new group:", "Group", null);
//        if (dialog.open() == InputDialog.OK) {
//            String groupName = dialog.getValue();
//            List<Group> allGroups = (List<Group>) treeViewer.getInput();
//            for(Group group : allGroups) {
//                if(!group.getName().equals(groupName)) {
//                    Group newGroup = new Group(groupName, new ArrayList<>());
//                    allGroups.add(newGroup);
//                    treeViewer.setInput(allGroups);
//                    treeViewer.refresh();
//                }else {
//                    MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Such a Group already exists!");
//                }
//            }
//        }
    }
}
