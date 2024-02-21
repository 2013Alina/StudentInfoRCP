package studentinfo.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import studentinfo.model.Student;

public class StudentEditor extends EditorPart {

    public static final String ID = "studentinfo.view.StudentEditor";

    private GridData gridDataLabel;
    private GridData gridDataText;
    
    private Label labelName;
    private Label labelNumberOfGroup;
    private Label labelAdress;
    private Label labelCity;
    private Label labelGrade;
    private Label labePhoto;
    
    private Text textName;
    private Text textNumberOfGroup;
    private Text textAdress;
    private Text textCity;
    private Text textGrade;
    private Text textPhoto;

    private Student student;
    private String baseName;
    private IEditorSite baseSite;

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
        baseName = student.getName();
        setPartName(baseName); // Set the name of the editor
        baseSite = site;
        
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
        container.setLayout(new GridLayout(6, true));
        Color blue = new Color(204, 255, 255);
        container.setBackground(blue);
        String name = parent.getClass().getName();
        System.out.println("NAME container = " + name);

        gridDataLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridDataLabel.verticalIndent = 20;

        gridDataText = new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1);
        gridDataText.verticalIndent = 20;

        labelName = new Label(container, SWT.NONE);
        labelName.setLayoutData(gridDataLabel);
        labelName.setText("Name");
        
        textName = new Text(container, SWT.BORDER | SWT.MULTI);
        textName.setEditable(true);
        textName.setLayoutData(gridDataText);
        textName.setText(student.getName());
        
        labelNumberOfGroup = new Label(container, SWT.NONE);
        labelNumberOfGroup.setLayoutData(gridDataLabel);
        labelNumberOfGroup.setText("Group");
        
        textNumberOfGroup = new Text(container, SWT.BORDER | SWT.MULTI);
        textNumberOfGroup.setEditable(true);
        textNumberOfGroup.setLayoutData(gridDataText);
        textNumberOfGroup.setText(student.getGroup());
        
        labelAdress = new Label(container, SWT.NONE);
        labelAdress.setLayoutData(gridDataLabel);
        labelAdress.setText("Adress");
        
        textAdress = new Text(container, SWT.BORDER | SWT.MULTI);
        textAdress.setEditable(true);
        textAdress.setLayoutData(gridDataText);
        textAdress.setText(student.getAddress());
        
        labelCity = new Label(container, SWT.NONE);
        labelCity.setLayoutData(gridDataLabel);
        labelCity.setText("City");
        
        textCity = new Text(container, SWT.BORDER | SWT.MULTI);
        textCity.setEditable(true);
        textCity.setLayoutData(gridDataText);
        textCity.setText(student.getCity());
        
        labelGrade = new Label(container, SWT.NONE);
        labelGrade.setLayoutData(gridDataLabel);
        labelGrade.setText("Result");
        
        textGrade = new Text(container, SWT.BORDER | SWT.MULTI);
        textGrade.setEditable(true);
        textGrade.setLayoutData(gridDataText);
        textGrade.setText(String.valueOf(student.getResult()));
        
        labePhoto = new Label(container, SWT.NONE);
        labePhoto.setLayoutData(gridDataLabel);
        labePhoto.setText("Photo");
        
        textPhoto = new Text(container, SWT.BORDER | SWT.MULTI);
        textPhoto.setEditable(true);
        textPhoto.setLayoutData(gridDataText);
        textPhoto.setText(student.getImage());
        
        int operations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_DEFAULT;
        DropTarget dropTarget = new DropTarget(container, operations); // DropTarget для контейнера
        final TextTransfer textTransfer = TextTransfer.getInstance();
//        final FileTransfer fileTransfer = FileTransfer.getInstance();
        System.out.println("textTransfer in createPartControl() =  " + textTransfer);
        Transfer[] type = new Transfer[] { textTransfer };
        dropTarget.setTransfer(type);
        dropTarget.addDropListener(new DropTargetAdapter() {
//            public void dragEnter(DropTargetEvent event) {
//                if (event.detail == DND.DROP_DEFAULT) {
//                    if ((event.operations & DND.DROP_COPY) != 0) {
//                        event.detail = DND.DROP_COPY;
//                    } else {
//                        event.detail = DND.DROP_NONE;
//                    }
//                }
//                for (int i = 0; i < event.dataTypes.length; i++) {
//                    if (fileTransfer.isSupportedType(event.dataTypes[i])) {
//                        event.currentDataType = event.dataTypes[i];
//                        // files should only be copied
//                        if (event.detail != DND.DROP_COPY) {
//                            event.detail = DND.DROP_NONE;
//                        }
//                        break;
//                    }
//                }
//            }

//            этот метод позволяет обработать событие перетаскивания в область и выводит содержимое перетаскиваемого текста на консоль, если это текстовые данные
            public void dragOver(DropTargetEvent event) {
                event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
                if (textTransfer.isSupportedType(event.currentDataType)) {
                    Object o = textTransfer.nativeToJava(event.currentDataType);
                    String t = (String) o;
                    if (t != null)
                        System.out.println(t);
                }
            }
//            этот метод устанавливает обратную связь для операции перетаскивания и выводит содержимое перетаскиваемого текста на консоль, если он является строкой
            public void dragOperationChanged(DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
//                if (fileTransfer.isSupportedType(event.currentDataType)) {
//                    if (event.detail != DND.DROP_COPY) {
//                        event.detail = DND.DROP_NONE;
//                    }
//                }
            }
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
                            textPhoto.setText(parts[1]);
                        }
                    }

                }
//                if(fileTransfer.isSupportedType(event.currentDataType)) {
//                    String[] files = (String[])event.data;
//                    for (int i = 0; i < files.length; i++) {
//                        
//                    }
//                }
            }
        });
    }
    
    @Override
    public void setFocus() { //устанавливает фокус на редакторе, чтобы пользователь мог начать вводить данные
        textName.setFocus();
    }
}
