package gestionBilicence.edition;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gestionBilicence.general.Entity;
import gestionBilicence.general.GTable;
import gestionBilicence.general.GeneralWindow;
import gestionBilicence.general.ListTableModel;
import gestionBilicence.general.editorsRenderers.Delete;

public class EditionWindow extends GeneralWindow {
	/*
	 * Window to edit elements in the database
	 * contains "New line" and "Save/update" buttons,
	 * together with their listeners.
	 * 
	 * NB: GeneralWindow provides a copy of gc (GeneralController)
	 */
	
	private JPanel pan;
	private JButton saveButton;
	private JButton newLineButton;
	private JTabbedPane tabbedPane;

	public EditionWindow(){
		super();
		
		//======================
		// Creation of the different tabs
		//======================
		
		LinkedList<Entity>[] dataVect = (LinkedList<Entity>[]) new LinkedList<?>[3];
		ListTableModel[] listTableModelVect = new ListTableModel[3];
		GTable[] tabEntityVect = new GTable[3];
		
		// Creation of student tab (already initialized):
		listTableModelVect[0] = new ListTableModel(
				new Class[] {String.class, String.class, Delete.class},
				new String[] {"First name","Family Name","Delete"},
				gc.getCurrentData() // at that point, we should have currentEntity=0
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModelVect[0]);
		tabEntityVect[0] = new GTable(listTableModelVect[0]);
		
		// Creation of exams tab:
		listTableModelVect[1] = new ListTableModel(
				new Class[] {String.class, Semester.class, Integer.class,Delete.class},
				new String[] {"Name","Semester","Coefficient", "Delete"},
				new LinkedList<Entity>() // at that point, we should have currentEntity=0
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModelVect[1]);
		tabEntityVect[1] = new GTable(listTableModelVect[1]);
		
		// Creation of semester tab:
		listTableModelVect[2] = new ListTableModel(
				new Class[] {String.class, Delete.class},
				new String[] {"Name","Delete"},
				new LinkedList<Entity>() // at that point, we should have currentEntity=0
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModelVect[2]);
		tabEntityVect[2] = new GTable(listTableModelVect[2]);
		
		// Final assembly into a tabbed panel.
		tabbedPane = new JTabbedPane();
		String[] listTabs = {"Students","Exams","Semesters"};
		for (int i = 0; i <= 2; i++){
			tabbedPane.addTab(listTabs[i],tabEntityVect[i]);
		}
		
		// Listeners of the tabbedPane
		tabbedPane.addChangeListener(gc);
		
		//======================
		// Buttons and listeners
		//======================
		newLineButton = new JButton("New line");
		saveButton = new JButton("Save/update");
	    
		// Keeping the listeners as generic as possible
		// while putting the "data methods" in the TableModel
		
		// listener on the "New line" button
		class AddListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				((GTable) tabbedPane.getSelectedComponent()).getModel().addRow();
			}
		}
		
		// listener on the "Save/update" button
		class SaveListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				((GTable) tabbedPane.getSelectedComponent()).getModel().saveTable();
				// currentEntity == 2 i.e. Semesters
				if (tabbedPane.getSelectedIndex()==2){
					// update comboSemester for Exams tab
					((GTable) tabbedPane.getComponent(1)).updateComboSemester();
				}
			}
		}
	    
	    newLineButton.addActionListener(new AddListener());
	    saveButton.addActionListener(new SaveListener());
		
		
		//======================
		// Final assembly
		//======================
		
		// Assembling pan (with buttons)
	    GridLayout gl = new GridLayout(1,2);
	    gl.setHgap(5);
	    pan = new JPanel(gl);
	    pan.add(newLineButton);
	    pan.add(saveButton);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	    this.add(pan, BorderLayout.SOUTH);
	}
}
