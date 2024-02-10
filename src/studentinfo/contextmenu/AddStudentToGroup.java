package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;

import studentinfo.model.Group;

public class AddStudentToGroup extends Action{
    
    private Group group;
    
    public AddStudentToGroup(Group group) {
        super("Add Student to Group.");
        this.group = group;
    }
    
    @Override
    public void run() {
        
    }

}
