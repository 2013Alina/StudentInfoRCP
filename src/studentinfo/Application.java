package studentinfo;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import studentinfo.connectionH2.DataDAO;
import studentinfo.connectionH2.H2DatabaseConnection;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

    @Override
    public Object start(IApplicationContext context) throws Exception {
        
        // применение схемы и заполнение данными
        H2DatabaseConnection.applySchema();
        DataDAO dataDAO = new DataDAO();
//        dataDAO.fillWithData();
        
        // запуск приложения
        Display display = PlatformUI.createDisplay();
        try {
            int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
            if (returnCode == PlatformUI.RETURN_RESTART)
                return IApplication.EXIT_RESTART;
            else
                return IApplication.EXIT_OK;
        } finally {
            display.dispose();
        }

    }

    @Override
    public void stop() {
        if (!PlatformUI.isWorkbenchRunning())
            return;
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final Display display = workbench.getDisplay();
        display.syncExec(() -> {
            if (!display.isDisposed())
                workbench.close();
        });
    }
}
