package studentinfo.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;

import studentinfo.contextmenu.AddStudentToGroup;
import studentinfo.contextmenu.CreateNewGroup;
import studentinfo.contextmenu.DeleteGroupAction;
import studentinfo.contextmenu.DeleteRecordAction;
import studentinfo.contextmenu.OpenProfileAction;
import studentinfo.model.Group;
import studentinfo.model.Student;
import studentinfo.model.StudentDataManager;

public class StudentTreeView extends ViewPart {
    
    public static final String ID = "studentinfo.view.StudentTreeView";

    private TreeViewer treeViewer;
    private MenuManager menuManager;
    private List<Group> groups = new ArrayList<>();
    private StudentEditor studentEditor;
    private Student student;
    
    public StudentTreeView() {

    }

    @Override
    public void createPartControl(Composite parent) {
        String name = parent.getLayout().getClass().getName();
        System.out.println("NAME = " + name);

        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        sashForm.setLayout(new FillLayout());

        treeViewer = new TreeViewer(sashForm, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        treeViewer.setContentProvider(new StudentTreeContentProvider());
        treeViewer.setLabelProvider(new StudentTreeLabelProvider());

        groups = StudentDataManager.addGroups();
        treeViewer.setInput(groups);

        Sash sash = new Sash(sashForm, SWT.SMOOTH);
        sashForm.setSashWidth(5);

        sash.addListener(SWT.Selection, event -> {
            sashForm.setWeights(new int[] { sashForm.getClientArea().width / 2, sashForm.getClientArea().width / 2 });
        });

        for (org.eclipse.swt.widgets.Control child : parent.getChildren()) {
            System.out.println("CHILD = " + child.getClass().getName());
        }

        createContextMenu();
        bindingContextMenu();

        
        // double click Student from treeViewer to editor
        treeViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                Object selectedElement = selection.getFirstElement();

                if (selectedElement instanceof Student) {
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    try {
                        StudentEditorInput newInput = new StudentEditorInput((Student) selectedElement);
                        page.openEditor(newInput, StudentEditor.ID);
                    } catch (PartInitException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        // add drag-and-drop from treeViewer to editor
        initDragAndDrop();
    }

    private void createContextMenu() {
        menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(this::fillContextMenu);

        Menu contextMenu = menuManager.createContextMenu(treeViewer.getControl());
        treeViewer.getControl().setMenu(contextMenu);

        getSite().registerContextMenu(menuManager, treeViewer);
    }

    private void fillContextMenu(IMenuManager menuManager) {
        IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();

        if (!selection.isEmpty()) {
            Object firstElement = selection.getFirstElement();

            if (firstElement instanceof Student) {
                menuManager.add(new OpenProfileAction((Student) firstElement, treeViewer));
                menuManager.add(new DeleteRecordAction((Student) firstElement, treeViewer));
            } else if (firstElement instanceof Group) {
                menuManager.add(new AddStudentToGroup((Group) firstElement, treeViewer));
                menuManager.add(new DeleteGroupAction((Group) firstElement, treeViewer));
                menuManager.add(new CreateNewGroup(treeViewer));
            }
        }
    }

    private void bindingContextMenu() {
        IActionBars actionBars = getViewSite().getActionBars();
        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), new DeleteRecordAction(
                (Student) ((IStructuredSelection) treeViewer.getSelection()).getFirstElement(), treeViewer));
        actionBars.updateActionBars();
    }

    private void initDragAndDrop() { // DragSource для дерева
        DragSource dragSource = new DragSource(treeViewer.getTree(), DND.DROP_COPY | DND.DROP_MOVE);
        TextTransfer textTransfer = TextTransfer.getInstance();
        System.out.println("textTransfer in SOURCE =  " + textTransfer);
        dragSource.setTransfer(new Transfer[] { textTransfer });

        dragSource.addDragListener(new DragSourceAdapter() {
            @Override
            public void dragStart(DragSourceEvent event) {
                IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
                Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Student) {
                    Student selectedStudent = (Student) selectedElement;
                    event.doit = true;

                    List<Group> groups = StudentDataManager.addGroups();
                    List<Student> allStudents = StudentDataManager.getAllStudents(groups);
                    for (Student st : allStudents) {
                        if (st.equals(selectedStudent)) {
                            student = st;
                            student = student.getStudentWithAllValues();

                            // если тяну студента то откроется эдитор выбраного студента
                            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                            try {
                                StudentEditorInput newInput = new StudentEditorInput(student);
                                page.openEditor(newInput, StudentEditor.ID);
                            } catch (PartInitException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    event.doit = false; // Запрещено перетаскивать все остальное
                }
            }

            @Override
            public void dragSetData(DragSourceEvent event) {
                IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
                Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Student) {
                    // установка данных
                    String data = student.getName() + "\n" + student.getGroup() + "\n" + student.getAddress() + "\n"
                            + student.getCity() + "\n" + student.getResult() + "\n" + student.getImage();
                    event.data = data;
                }
            }

            @Override
            public void dragFinished(DragSourceEvent event) {
            }
        });
    }

    @Override
    public void setFocus() {
        if (treeViewer != null) {
            treeViewer.getControl().setFocus();
        } else if (studentEditor != null) {
            studentEditor.setFocus();
        }
    }

    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> allgroups) {
        if (treeViewer != null && !treeViewer.getControl().isDisposed()) {
            treeViewer.setInput(allgroups);
            treeViewer.refresh();
        }
    }
    
    public TreeViewer getTreeView() {
        return treeViewer;
    }
}
