package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;

import studentinfo.model.Group;

public class DeleteGroupAction extends Action {

    private Group group;
    
    public DeleteGroupAction(Group group) {
        super("Delete Group.");
        this.group = group;
    }

    @Override
    public void run() {
        
    }
}
