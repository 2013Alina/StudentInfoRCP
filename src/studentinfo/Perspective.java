package studentinfo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import studentinfo.view.StudentEditor;
import studentinfo.view.StudentTreeView;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {

	    layout.setEditorAreaVisible(false);
	    
	    // StudentTreeView слева
	    layout.addStandaloneView(StudentTreeView.class.getName(), true, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
	    // StudentEditor справа
	    //layout.addStandaloneView(StudentEditor.class.getName(), false, IPageLayout.RIGHT, 0.75f, layout.getEditorArea());

	}
}
