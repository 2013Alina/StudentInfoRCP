package studentinfo;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import studentinfo.menu.MainMenuManager;
import studentinfo.toolbar.MainToolBarManager;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    @Override
    protected void makeActions(IWorkbenchWindow window) {
        super.makeActions(window);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) { // add menu
        super.fillMenuBar(menuBar);
        MainMenuManager.createMenu(menuBar);
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) { // add toolbar
        super.fillCoolBar(coolBar);
        ToolBarManager toolBarManager = new ToolBarManager(coolBar.getStyle());
        MainToolBarManager.createToolbar(toolBarManager);
        coolBar.add(new ToolBarContributionItem(toolBarManager, "main"));
    }
    
//  @Override
//  protected void fillCoolBar(ICoolBarManager coolBar) { 
//      super.fillCoolBar(coolBar);
//      ToolBarManager toolBarManager = new ToolBarManager(coolBar.getStyle());
//      StudentTreeView studentTreeView = (StudentTreeView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//              .getActivePage().findView(StudentTreeView.ID);
//      if (studentTreeView != null) {
//          MainToolBarManager.createToolbar(toolBarManager, studentTreeView);
//          coolBar.add(new ToolBarContributionItem(toolBarManager, "main"));
//      } else {
//          System.err.println("StudentTreeView not found!");
//      }
//  }
}
