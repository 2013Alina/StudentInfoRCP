package studentinfo.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.part.ViewPart;

import studentinfo.model.Group;
import studentinfo.model.Student;
import studentinfo.model.StudentDataManager;

public class StudentTreeView extends ViewPart {
    
    private TreeViewer treeViewer;
    
    public StudentTreeView() {
        
    }

    @Override
    public void createPartControl(Composite parent) {
        String name = parent.getLayout().getClass().getName();
        System.out.println("NAME = " + name);
        
        Color color = new Color(204, 102, 255);
        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        sashForm.setLayoutData(new FillLayout());
        sashForm.setBackground(color);
        
        Composite child1 = new Composite(sashForm,SWT.NONE);
        child1.setLayout(new FillLayout());
        treeViewer = new TreeViewer(child1, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        treeViewer.setContentProvider(new StudentTreeContentProvider());
        treeViewer.setLabelProvider(new StudentTreeLabelProvider());
        
        List<Group> groups = StudentDataManager.getGroups();
        treeViewer.setInput(groups);
        
        Composite child2 = new Composite(sashForm,SWT.NONE);
        child2.setLayout(new FillLayout());
        
        Sash sash = new Sash(sashForm, SWT.SMOOTH);
        sashForm.setSashWidth(5);
       
        sashForm.setWeights(new int[] { 400, 800 });
        
        sash.addListener(SWT.Selection, event -> {
            sashForm.setWeights(new int[] { sashForm.getClientArea().width / 2, sashForm.getClientArea().width / 2 });
        });
        
        for(org.eclipse.swt.widgets.Control child : parent.getChildren()) {
            System.out.println("CHILD = " + child.getClass().getName());
        }
        createContextMenu();
        hookContextMenu();
        
    }

    private void createContextMenu() {
        MenuManager menuManager = new MenuManager();
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
//                menuManager.add(new OpenProfileAction((Student) firstElement));
//                menuManager.add(new DeleteRecordAction((Student) firstElement));
            } else if (firstElement instanceof Group) {
//                menuManager.add(new AddStudentAction((Group) firstElement));
//                menuManager.add(new DeleteGroupAction((Group) firstElement));
            } else {
//                menuManager.add(new AddGroupAction());
//                menuManager.add(new DeleteRootFolderAction());
            }
        }
    }
        
    private void hookContextMenu() {
//        IActionBars actionBars = getViewSite().getActionBars();
//        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), new DeleteRecordAction(treeViewer));
//        actionBars.updateActionBars();
    }
    
    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }
}
