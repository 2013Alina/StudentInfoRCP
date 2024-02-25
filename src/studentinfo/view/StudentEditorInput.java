package studentinfo.view;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import studentinfo.model.Student;
//StudentEditorInput - входные данные для StudentEditor!
//передачи объекта Student в редактор StudentEditor, чтобы редактор мог отображать и редактировать информацию о конкретном студенте

public class StudentEditorInput implements IEditorInput {

    private Student student;
    
    public StudentEditorInput(Object object) {
        this.student = (Student) object;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return null;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return student.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Student Editor: " + student.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudentEditorInput other = (StudentEditorInput) obj;
        return student.equals(other.student);
    }

    @Override
    public int hashCode() {
        return student.hashCode();
    }
}
