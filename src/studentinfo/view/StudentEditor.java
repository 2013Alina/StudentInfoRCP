package studentinfo.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import studentinfo.model.Student;

public class StudentEditor extends EditorPart {

    public static final String ID = "studentinfo.view.StudentEditor";

    private Text text;
    private GridData gridDataLabel;
    private GridData gridDataText;

    private Student student;

    @Override
    public void doSave(IProgressMonitor monitor) {
        // Реализация сохранения изменений

    }

    @Override
    public void doSaveAs() {
        // Реализация сохранения изменений в другой файл

    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof StudentEditorInput)) {
            throw new PartInitException("Invalid Input: Must be StudentEditorInput!");
        }
        setSite(site);
        setInput(input);
        StudentEditorInput studentInput = (StudentEditorInput) input;
        this.student = studentInput.getStudent();
        setPartName(student.getName()); // имя редактора
        updateStudentInfo(student.toString()); // информация о студенте в текстовых полях
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        // Разрешить сохранение как?
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(6, true));

        gridDataLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridDataLabel.verticalIndent = 20;

        gridDataText = new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1);
        gridDataText.verticalIndent = 20;

        Label[] labels = new Label[6];
        Text[] texts = new Text[6];

        for (int i = 0; i < 6; i++) {
            labels[i] = new Label(container, SWT.NONE);
            labels[i].setLayoutData(gridDataLabel);

            texts[i] = new Text(container, SWT.BORDER | SWT.MULTI);
            texts[i].setEditable(true);
            texts[i].setLayoutData(gridDataText);
        }

        DropTarget target = new DropTarget(container, DND.DROP_MOVE);
        target.setTransfer(new Transfer[] { TextTransfer.getInstance() });
        target.addDropListener(new DropTargetAdapter() {
            @Override
            public void drop(DropTargetEvent event) {
                if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
                    String data = (String) event.data;
                    String[] dataArray = data.split("\n");

                    for (int i = 0; i < Math.min(6, dataArray.length); i++) {
                        String[] parts = dataArray[i].split(":");
                        if (parts.length == 2) {
                            labels[i].setText(parts[0]);
                            texts[i].setText(parts[1]);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setFocus() {
        text.setFocus();
    }

    public void updateStudentInfo(String studentData) {
        text.setText(studentData);
    }
}
