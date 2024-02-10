package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;

import studentinfo.model.Student;

public class DeleteRecordAction extends Action  {
    
    private Student student;
    
    public DeleteRecordAction(Student student) {
        super("Delete record.");
        this.student = student;
    }
    
    @Override
    public void run() {
        
    }

}
