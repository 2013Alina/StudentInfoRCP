package studentinfo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import studentinfo.view.StudentEditor;
import studentinfo.view.StudentTreeView;

public class Perspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.setEditorAreaVisible(true); // Hide the default editor area

        // StudentTreeView слева
        layout.addStandaloneView(StudentTreeView.ID, true, IPageLayout.LEFT, 0.25f, layout.getEditorArea());
        
        
//        String editor = layout.getEditorArea();
//        layout.addView("studentinfo.view.StudentTreeView", 
//        IPageLayout.RIGHT, 0f, editor);
//        layout.setEditorAreaVisible(false);
//        layout.setFixed(true);

        // StudentEditor справа
//        layout.addStandaloneView(StudentEditor.ID, true, IPageLayout.RIGHT, 0.75f, layout.getEditorArea());
        
        // Add the placeholder for the editor area
//        layout.addStandaloneViewPlaceholder(StudentEditor.ID, IPageLayout.RIGHT, 0.75f, layout.getEditorArea(), true);
    }
}
