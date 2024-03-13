package studentinfo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import studentinfo.view.StudentTreeView;

public class Perspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.setEditorAreaVisible(true); // Hide the default editor area

        // StudentTreeView слева все остальное Editor
        layout.addStandaloneView(StudentTreeView.ID, true, IPageLayout.LEFT, 0.25f, layout.getEditorArea());

    }
}
