package studentinfo.contextmenu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import studentinfo.model.Group;
import studentinfo.model.Student;
import studentinfo.view.StudentEditor;
import studentinfo.view.StudentEditorInput;

public class AddStudentToGroup extends Action {

    private Group group;
    private TreeViewer treeViewer;

    public AddStudentToGroup(Group group, TreeViewer treeViewer) {
        super("Add Student to Group.");
        this.group = group;
        this.treeViewer = treeViewer;
    }

    @Override
    public void run() {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        shell.setText("Add new Student to this Group:");
        Color green = new Color(204, 255, 204);
        shell.setBackground(green);
        shell.setLayout(new GridLayout(5, false));

        GridData gridDataLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridDataLabel.verticalIndent = 20;

        Label labelName = new Label(shell, SWT.NONE);
        labelName.setLayoutData(gridDataLabel);
        labelName.setText("Name:");

        Text nameText = new Text(shell, SWT.BORDER);
        GridData gridData1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gridData1.verticalIndent = 20;
        nameText.setLayoutData(gridData1);

        Label labelNumberOfGroup = new Label(shell, SWT.NONE);
        labelNumberOfGroup.setLayoutData(gridDataLabel);
        labelNumberOfGroup.setText("Group:");

        Text numberOfGroup = new Text(shell, SWT.BORDER);
        GridData gridData2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gridData2.verticalIndent = 20;
        numberOfGroup.setLayoutData(gridData2);
        numberOfGroup.setText(group.getName());

        Label labelAddress = new Label(shell, SWT.NONE);
        labelAddress.setLayoutData(gridDataLabel);
        labelAddress.setText("Adress:");

        Text addressText = new Text(shell, SWT.BORDER);
        GridData gridData3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gridData3.verticalIndent = 20;
        addressText.setLayoutData(gridData3);

        Label labelCity = new Label(shell, SWT.NONE);
        labelCity.setLayoutData(gridDataLabel);
        labelCity.setText("City:");

        Text cityText = new Text(shell, SWT.BORDER);
        GridData gridData4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gridData4.verticalIndent = 20;
        cityText.setLayoutData(gridData4);

        Label labelGrade = new Label(shell, SWT.NONE);
        labelGrade.setLayoutData(gridDataLabel);
        labelGrade.setText("Result:");

        Text gradeText = new Text(shell, SWT.BORDER);
        GridData gridData5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gridData5.verticalIndent = 20;
        gradeText.setLayoutData(gridData5);

        Label labelPhoto = new Label(shell, SWT.NONE);
        labelPhoto.setLayoutData(gridDataLabel);
        labelPhoto.setText("Photo:");
        // String imagePath = student.getImage();
        // ImageDescriptor imageDescriptor = ImageDescriptor.createFromFile(null, imagePath);
        // image = imageDescriptor.createImage();
        // labelPhoto.setImage(image);

        Text photoText = new Text(shell, SWT.BORDER);
        GridData gridData6 = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
        gridData6.verticalIndent = 20;
        photoText.setLayoutData(gridData6);
        photoText.setText("Enter absolute path to photo!");

        Button okButton = new Button(shell, SWT.PUSH);
        okButton.setText("OK");
        GridData GridDataOkButton = new GridData(SWT.CENTER, SWT.CENTER, false, false);
        GridDataOkButton.verticalIndent = 20;
        Color blue = new Color(102, 255, 255);
        okButton.setBackground(blue);
        okButton.setSize(20, 20);
        okButton.setLayoutData(GridDataOkButton);
        
        okButton.addListener(SWT.Selection, event -> {

            String name = nameText.getText();
            String address = addressText.getText();
            String city = cityText.getText();
            int result = Integer.parseInt(gradeText.getText());
            String image = photoText.getText();

            Student newStudent = new Student();
            newStudent.setName(name);
            newStudent.setGroup(group.getName());
            newStudent.setAddress(address);
            newStudent.setCity(city);
            newStudent.setResult(result);
            newStudent.setImage(image);

            // Открываем новый редактор с новым студентом
            try {
                StudentEditorInput input = new StudentEditorInput(newStudent);
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input,
                        StudentEditor.ID);
            } catch (PartInitException e) {
                e.printStackTrace();
            }

            // Добавляем нового студента в группу и обновляем дерево
            group.getStudentslist().add(newStudent);
            treeViewer.refresh();
            
            shell.close();
        });

        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        GridData gridDataCancelButton = new GridData(SWT.CENTER, SWT.CENTER, false, false);
        gridDataCancelButton.verticalIndent = 20;
        cancelButton.setLayoutData(gridDataCancelButton);
        Color red = new Color(255, 204, 255);
        cancelButton.setBackground(red);
        cancelButton.setSize(30, 20);
        cancelButton.addListener(SWT.Selection, event -> shell.close());

        shell.pack();
        shell.setSize(400, 500);
        shell.open();
    }
}
