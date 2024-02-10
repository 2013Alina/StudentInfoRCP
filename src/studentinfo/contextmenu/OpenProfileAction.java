package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;

import studentinfo.model.Student;

public class OpenProfileAction extends Action {
    
    private Student student;
    
    public OpenProfileAction(Student student) {
        super("Open profile Student.");
        this.student = student;
    }
    
    @Override
    public void run() {
        
    }


}
