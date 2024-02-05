package es.uma.GUIDemo;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.util.SystemInfo;

/**
 * 
 * @author Antonio Maña
 *
 */
class ToolbarPane2 extends JPanel {

	private static final long serialVersionUID = 3061303004804112427L;

	// TODO Make it relative to the project path

	private final String iconsPath = "./src/resources/images/";
	private String actionString1 = "Selected Image 1 Pane 2";
	private String actionString2 = "Selected Image 2 Pane 2";
	private String actionString3 = "Selected Image 3 Pane 2";
	 
	private LinkedList<String> availableActionStrings = new LinkedList<>();
	private static int selectedActionStringNumber = 0;
	private static String selectedActionString = "Selected Image 1 Pane 2";

	// Parameters from constructor
	private GUIDemo gui;

	// the actual ToolBar
	JToolBar toolBar = null;

	// Definitions of button components
	private String changeImageIconFile = iconsPath + "changeIcon.png";

	private final String changeIconAction = "Change Icon";

	private String changeImageIconTooltip = "Change Image";

	private JButton changeImageButton;
	
	private String synchImageIconFile = iconsPath + "synchImagesIcon.png";

	private final String synchIconAction = "Synchronize Icons";

	private String synchImageIconTooltip = "Synchronize Images";

	private JButton synchImageButton;	
	/**
	 * Constructor for the ToolBar
	 * 
	 * @param gui:          DefinitionsEditorGUI that contains the CMSToolbar
	 * @param toolBarTitle: String representing the ToolBar title
	 * @param cardInserted: boolean representing the initial view selected used to
	 *                      activate the appropriate buttons
	 */
	ToolbarPane2(GUIDemo gui, String toolBarTitle, Color bkgColor, String selectedPaneImage) {
		this.gui = gui;

		availableActionStrings.add(actionString1);
		availableActionStrings.add(actionString2);
		availableActionStrings.add(actionString3);

		selectedActionStringNumber = numberOf(selectedPaneImage);
		
		// Create the toolbar
		createToolBar(toolBarTitle, bkgColor);

		if (SystemInfo.isMacFullWindowContentSupported) toolBar.add(Box.createHorizontalStrut(0), 0);
	}

	/**
	 * Configures the toolbar and adds the components to it
	 * 
	 * @param toolBarTitle
	 */
	private void createToolBar(String toolBarTitle, Color bkgColor) {
		
		toolBar = new JToolBar(toolBarTitle);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		toolBar.setBackground(bkgColor);
		toolBar.setFloatable(false);
		toolBar.setMargin(new Insets(10, 15, 15, 15));

		synchImageButton = createButton(synchImageIconFile, synchIconAction,
				synchImageIconTooltip, "", true, true);
		toolBar.add(synchImageButton);
		changeImageButton = createButton(changeImageIconFile, changeIconAction,
				changeImageIconTooltip, "", true, true);
		toolBar.add(changeImageButton);
	}

	/**
	 * 
	 * @param iconFile:        String with the file of the image of the icon
	 * @param actionString:    String with the action identifier to be used by the
	 *                         ActionListeners
	 * @param theTooltip:      String that will be associated as tooltip to the
	 *                         button
	 * @param theText:         String that will be shown in the button
	 * @param hasLocalAction:  boolean that defines whether a local action is
	 *                         necessary
	 * @param hasDriverAction: boolean that defines whether a driver action is
	 *                         necessary
	 * @return the JButton to be included in the toolbar
	 */
	private JButton createButton(String iconFile, String actionString, String theTooltip, String theText,
			boolean hasLocalAction, boolean hasDriverAction) {
		// First we load the image on an ImageIcon object
		Action theAction = createAction(iconFile, actionString, hasLocalAction, hasDriverAction);

		// then we create the button with the corresponding action
		JButton theButton = new JButton(theAction);
		theButton.setText(theText);
		theButton.setToolTipText(theTooltip);
		theButton.setSize(100, 100);

		// finally we add the button
		return theButton;
	}

	/**
	 * Creates an Action to be associated with the button
	 * 
	 * @param iconFile:        String with the file of the image of the icon
	 * @param actionString:    String with the action identifier to be used by the
	 *                         ActionListeners
	 * @param hasLocalAction:  boolean that defines whether a local action is
	 *                         necessary
	 * @param hasDriverAction: boolean that defines whether a driver action is
	 *                         necessary
	 * @return the Action to be associated with the button
	 */
	private Action createAction(String iconFile, String actionString, boolean hasLocalAction, boolean hasDriverAction) {
		ImageIcon theIcon = new ImageIcon(iconFile);

		// then we create the action with the corresponding icon
		Action theAction = new AbstractAction(actionString, theIcon) {
			private static final long serialVersionUID = 358030575940781277L;

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedActionString = actionString;
				if (hasLocalAction) {
					doLocalAction(selectedActionString);
				}
				if (hasDriverAction) {
					gui.actionPerformed(new java.awt.event.ActionEvent(this, 0, selectedActionString));
				}
			}
		};
		return theAction;
	}

	/**
	 * Manages local response to actions
	 * 
	 * @param actionString
	 */
	private void doLocalAction(String actionString) {
		switch (actionString) {
		case changeIconAction:
			changeActionString();
			break;
		case synchIconAction:
			System.out.println("Synchronizing Images");
			break;
		}
	}
	
	private void changeActionString() {
		selectedActionStringNumber++;
		System.out.println("size: " + availableActionStrings.size() + " / index: " + selectedActionStringNumber);
		if (selectedActionStringNumber >= availableActionStrings.size()) {
			selectedActionStringNumber = 0;
			System.out.println("index reset!");
		}
		selectedActionString = availableActionStrings.get(selectedActionStringNumber);
	}
	
	private int numberOf(String imageFile) {
		if (imageFile.contains("1")) {
			return 0;
		} else if (imageFile.contains("2")) {
			return 1;
		} if (imageFile.contains("3")) {
			return 2;
		} 
		return 1;
	}
}
