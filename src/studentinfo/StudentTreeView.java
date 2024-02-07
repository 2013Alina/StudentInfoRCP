package studentinfo;

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

public class StudentTreeView extends ViewPart {
    
    private TreeViewer treeViewer;

    @Override
    public void createPartControl(Composite parent) {
        System.out.println("HHHHELLLO!!!!");
        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        
        treeViewer = new TreeViewer(sashForm, SWT.BORDER | SWT.MULTI);
        treeViewer.setContentProvider(new StudentTreeContentProvider());
        treeViewer.setLabelProvider(new StudentTreeLabelProvider());
        
        List<Group> groups = StudentDataManager.getGroups();
        treeViewer.setInput(groups);
        
        Sash sash = new Sash(sashForm, SWT.SMOOTH);
        sashForm.setSashWidth(5);
        sashForm.setLayoutData(new FillLayout());
        Color color = new Color(204, 102, 255);
        sashForm.setWeights(new int[] { 1, 1 });
        sashForm.setBackground(color);
        
        sash.addListener(SWT.Selection, event -> {
            sashForm.setWeights(new int[] { sashForm.getClientArea().width / 2, sashForm.getClientArea().width / 2 });
        });
        
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