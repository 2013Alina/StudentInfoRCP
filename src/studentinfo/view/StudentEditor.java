package studentinfo.view;

import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.osgi.framework.Bundle;

import studentinfo.Activator;
import studentinfo.model.Student;

public class StudentEditor extends EditorPart {

    public static final String ID = "studentinfo.view.StudentEditor";

    private GridData gridDataLabel;
    
    private Label labelName;
    private Label labelNumberOfGroup;
    private Label labelAdress;
    private Label labelCity;
    private Label labelGrade;
    private Label labelPhoto;
    
    private Text textName;
    private Text textNumberOfGroup;
    private Text textAdress;
    private Text textCity;
    private Text textGrade;
    private Image image;
    private GC gc;

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
        setSite(site); //это объект, представляющий место, в котором находится редактор
        setInput(input); //это объект, представляющий входные данные, с которыми будет работать редактор

        StudentEditorInput studentInput = (StudentEditorInput) input;
        this.student = studentInput.getStudent();
        if (student != null) {
            setPartName(student.getName()); // Set the name of the editor
        } else {
            setPartName(null);
        }
    }

    @Override
    public boolean isDirty() { //были ли внесены изменения в данные, открытые в редакторе
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() { //разрешено ли сохранение данных в качестве нового файла
        return false;
    }

    @Override
    public void createPartControl(Composite parent) { //пользовательский интерфейс редактора
        System.out.println("Create part control!");
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(5, true);
        container.setLayout(layout);
        
        Color blue = new Color(204, 255, 255);
        container.setBackground(blue);
        String name = parent.getClass().getName();
        System.out.println("NAME container = " + name);
    
        gridDataLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridDataLabel.verticalIndent = 20;

        labelName = new Label(container, SWT.NONE);
        labelName.setLayoutData(gridDataLabel);
        labelName.setText("Name");
        
        GridData gridDataText1 = new GridData(SWT.DEFAULT, SWT.CENTER, true, false, 4, 1);
        gridDataText1.verticalIndent = 20;
        textName = new Text(container, SWT.BORDER | SWT.MULTI);
        textName.setEditable(true);
        gc = new GC(textName);
        Point textSizeName = gc.textExtent(student.getName());
        gc.dispose();
        gridDataText1.widthHint = textSizeName.x;
        gridDataText1.minimumWidth = textSizeName.x;
        textName.setLayoutData(gridDataText1);
        textName.setText(student.getName());
        
        labelNumberOfGroup = new Label(container, SWT.NONE);
        labelNumberOfGroup.setLayoutData(gridDataLabel);
        labelNumberOfGroup.setText("Group");
        
        GridData gridDataText2 = new GridData(SWT.DEFAULT, SWT.CENTER, true, false, 4, 1);
        gridDataText2.verticalIndent = 20;
        textNumberOfGroup = new Text(container, SWT.BORDER | SWT.MULTI);
        textNumberOfGroup.setEditable(true);
        gc = new GC(textNumberOfGroup);
        Point textSizeNumberOfGroup = gc.textExtent(student.getGroup());
        gc.dispose();
        gridDataText2.widthHint = textSizeNumberOfGroup.x;
        gridDataText2.minimumWidth = textSizeNumberOfGroup.x;
        textNumberOfGroup.setLayoutData(gridDataText2);
        textNumberOfGroup.setText(student.getGroup());
        
        labelAdress = new Label(container, SWT.NONE);
        labelAdress.setLayoutData(gridDataLabel);
        labelAdress.setText("Adress");
        
        GridData gridDataText3 = new GridData(SWT.DEFAULT, SWT.CENTER, true, false, 4, 1);
        gridDataText3.verticalIndent = 20;
        textAdress = new Text(container, SWT.BORDER | SWT.MULTI);
        textAdress.setEditable(true);
        gc = new GC(textAdress);
        Point textSizeAdress = gc.textExtent(student.getAddress());
        gc.dispose();
        gridDataText3.widthHint = textSizeAdress.x;
        gridDataText3.minimumWidth = textSizeAdress.x;
        textAdress.setLayoutData(gridDataText3);
        textAdress.setText(student.getAddress());
        
        labelCity = new Label(container, SWT.NONE);
        labelCity.setLayoutData(gridDataLabel);
        labelCity.setText("City");
        
        GridData gridDataText4 = new GridData(SWT.DEFAULT, SWT.CENTER, true, false, 4, 1);
        gridDataText4.verticalIndent = 20;
        textCity = new Text(container, SWT.BORDER | SWT.MULTI);
        textCity.setEditable(true);
        gc = new GC(textCity);
        Point textSizeCity = gc.textExtent(student.getCity());
        gridDataText4.widthHint = textSizeCity.x;
        gridDataText4.minimumWidth = textSizeCity.x;
        gc.dispose();
        textCity.setLayoutData(gridDataText4);
        textCity.setText(student.getCity());
        
        labelGrade = new Label(container, SWT.NONE);
        labelGrade.setLayoutData(gridDataLabel);
        labelGrade.setText("Result");
        
        GridData gridDataText5 = new GridData(SWT.DEFAULT, SWT.CENTER, true, false, 4, 1);
        gridDataText5.verticalIndent = 20;
        textGrade = new Text(container, SWT.BORDER | SWT.MULTI);
        textGrade.setEditable(true);
        gc = new GC(textGrade);
        Point textSizeGrade = gc.textExtent(String.valueOf(student.getResult()));
        gridDataText5.widthHint = textSizeGrade.x;
        gridDataText5.minimumWidth = textSizeGrade.x;
        gc.dispose();
        textGrade.setLayoutData(gridDataText5);
        textGrade.setText(String.valueOf(student.getResult()));

        labelPhoto = new Label(container, SWT.NONE);
        gridDataLabel = new GridData(SWT.FILL, SWT.FILL, true, false, 5, 5);
        gridDataLabel.horizontalIndent = 450;
        gridDataLabel.verticalIndent = -250;
        labelPhoto.setLayoutData(gridDataLabel);
        String imagePath = student.getImage();
        ImageDescriptor imageDescriptor = ImageDescriptor.createFromFile(null, imagePath);
        image = imageDescriptor.createImage();
        labelPhoto.setImage(image);
        
        
        // DropTarget у эдитора
        int operations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_DEFAULT;
        DropTarget dropTarget = new DropTarget(container, operations); 
        final TextTransfer textTransfer = TextTransfer.getInstance();
        System.out.println("textTransfer in TARGET =  " + textTransfer);
        Transfer[] type = new Transfer[] { textTransfer };
        dropTarget.setTransfer(type);
        dropTarget.addDropListener(new DropTargetAdapter() {
//            Этот код обрабатывает событие сброса объекта в целевую область и извлекает данные из сброшенного объекта для последующей обработки или отображения в интерфейсе приложения
            @Override
            public void drop(DropTargetEvent event) {

                if (textTransfer.isSupportedType(event.currentDataType)) {
                    String data = (String) event.data;
                    String[] dataArray = data.split("\n");
                    for (int i = 0; i < Math.min(6, dataArray.length); i++) {
                        String[] parts = dataArray[i].split(":");
                        if (parts.length == 2) {
                            textName.setText(parts[1]);
                            textNumberOfGroup.setText(parts[1]);
                            textAdress.setText(parts[1]);
                            textCity.setText(parts[1]);
                            textGrade.setText(parts[1]);
                        }
                    }
                }
            }
        });
    }
    
    @Override
    public void setFocus() { //устанавливает фокус на редакторе, чтобы пользователь мог начать вводить данные
        textName.setFocus();
    }
    
    @Override
    public void dispose() {
        if (image != null && !image.isDisposed()) {
            image.dispose();
        }
        super.dispose();
    }
}
