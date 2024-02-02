package studentinfo;

import org.eclipse.jface.action.IToolBarManager;

public class MainToolBarManager {
    
    public static void createToolbar(IToolBarManager toolBarManager) {
        toolBarManager.add(new OpenAction());
        toolBarManager.add(new SaveAction());
        toolBarManager.add(new DeleteAction());
        toolBarManager.add(new AddAction());
    }

}
