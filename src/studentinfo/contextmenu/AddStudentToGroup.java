package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

import studentinfo.model.Group;

public class AddStudentToGroup extends Action{
    
    private Group group;
    private TreeViewer treeViewer;
    
    public AddStudentToGroup(Group group) {
        super("Add Student to Group.");
        this.group = group;
    }
    
    @Override
    public void run() {
        
        
    }

}
