package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import studentinfo.model.Student;
import studentinfo.view.StudentEditor;
import studentinfo.view.StudentEditorInput;

public class OpenProfileAction extends Action {

    private Student student;
    private TreeViewer treeViewer;

    public OpenProfileAction(Student student, TreeViewer treeViewer) {
        super("Open profile Student.");
        this.student = student;
        this.treeViewer = treeViewer;
    }

    @Override
    public void run() {
        try {
            StudentEditorInput input = new StudentEditorInput(student);
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, StudentEditor.ID);
        } catch (PartInitException e) {
            e.printStackTrace();
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                    "Could not open editor: " + e.getMessage());
        }
    }
}
