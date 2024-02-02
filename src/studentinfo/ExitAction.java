package studentinfo;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

public class ExitAction extends Action {

    public ExitAction() {
        setText("Exit");
    }

    @Override
    public void run() {
        Display.getCurrent().getActiveShell().close();
    }

}
