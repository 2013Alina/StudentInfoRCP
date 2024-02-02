package studentinfo;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

public class MainMenuManager {
    
    public static void createMenu(IMenuManager menuBar) {
        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        menuBar.add(fileMenu);
        fileMenu.add(new Separator());
        fileMenu.add(new ExitAction());
        
        MenuManager editMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
        menuBar.add(editMenu);
        editMenu.add(new Separator());
        editMenu.add(new SaveFileAction());
        editMenu.add(new OpenFileAction());
        
        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        menuBar.add(helpMenu);
        helpMenu.add(new AboutAction());
    }

}
