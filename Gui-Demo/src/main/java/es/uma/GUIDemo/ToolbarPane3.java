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
 * @author Antonio Mana
 *
 */
class ToolbarPane3 extends JPanel {

	private static final long serialVersionUID = 3061303004804112427L;

	// TODO Make it relative to the project path

	private final String iconsPath = "./src/resources/images/";
	private String actionString1 = "Selected Image 1 Pane 3";
	private String actionString2 = "Selected Image 2 Pane 3";
	private String actionString3 = "Selected Image 3 Pane 3";
	 
	private LinkedList<String> availableActionStrings = new LinkedList<>();
	private static String selectedActionString = null;

	// Parameters from constructor
	private GUIDemo gui;

	// the actual ToolBar
	JToolBar toolBar = null;

	// Definitions of button components
	
	private String resetImageIconFile = iconsPath + "resetImagesIcon.png";

	private final String resetIconAction = "Reset Images";

	private String resetImageIconTooltip = "Reset Images";

	private JButton resetImageButton;	
	
	private String selectImageIconFile = iconsPath + "selectImageIcon.png";

	private final String selectImageIconAction = "Select Image Pane 3";

	private String selectImageIconTooltip = "Select Image";

	private JButton selectImageButton;

	/**
	 * Constructor for the ToolBar
	 * 
	 * @param gui:          DefinitionsEditorGUI that contains the CMSToolbar
	 * @param toolBarTitle: String representing the ToolBar title
	 * @param cardInserted: boolean representing the initial view selected used to
	 *                      activate the appropriate buttons
	 */
	ToolbarPane3(GUIDemo gui, String toolBarTitle, Color bkgColor) {
		this.gui = gui;

		availableActionStrings.add(actionString1);
		availableActionStrings.add(actionString2);
		availableActionStrings.add(actionString3);
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
//		toolBar.setMaximumSize(new Dimension(185, 180));
//		toolBar.setPreferredSize(new Dimension(185, 130));
		toolBar.add(Box.createHorizontalGlue());
		toolBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		toolBar.setBackground(bkgColor);
		toolBar.setFloatable(false);
		toolBar.setMargin(new Insets(10, 15, 15, 15));

		resetImageButton = createButton(resetImageIconFile, resetIconAction,
				resetImageIconTooltip, "", false , true);
		toolBar.add(resetImageButton);
		selectImageButton = createButton(selectImageIconFile, selectImageIconAction,
				selectImageIconTooltip, "", true, true);

		toolBar.add(selectImageButton);
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
		case selectImageIconAction:
			selectImage();
			System.out.println(selectedActionString);
			break;
		}
	}
	
	private void selectImage() {
		selectedActionString = showImageSelectionDialog(availableActionStrings);
	}
	
	/**
	 * Shows a dialog to select the credit card to use
	 * 
	 * @param availableSlotMachinesStrings list of card names for the selection
	 * @return the selected card name
	 */
	private String showImageSelectionDialog(LinkedList<String> availableActionStrings) {
		DialogOptionSelection csd = new DialogOptionSelection("Select image to use",
				availableActionStrings);
		csd.run();
		csd.setVisible(true);
		return csd.getSelectedOption();
	}

}