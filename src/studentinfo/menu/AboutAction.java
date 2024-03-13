package studentinfo.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class AboutAction extends Action {

    public AboutAction() {
        setText("About");
    }

    @Override
    public void run() {
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "About", "Author: Muntian Alina");
    }

}
