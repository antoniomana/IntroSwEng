package es.uma.GUIDemo;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;

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
class ToolbarPane1 extends JPanel {

	private static final long serialVersionUID = 3061303004804112427L;

	// TODO Make it relative to the project path

	private final String iconsPath = "./src/resources/images/";

	// Parameters from constructor
	private GUIDemo gui;

	// the actual ToolBar
	JToolBar toolBar = null;

	// Definitions of button components
	private String selectImage1IconFile = iconsPath + "image1icon.png";
	private String selectImage2IconFile = iconsPath + "image2icon.png";
	private String selectImage3IconFile = iconsPath + "image3icon.png";

	private final String selectImage1IconAction = "Selected Image 1 Pane 1";
	private final String selectImage2IconAction = "Selected Image 2 Pane 1";
	private final String selectImage3IconAction = "Selected Image 3 Pane 1";

	private String selectImage1IconTooltip = "Select Image 1";
	private String selectImage2IconTooltip = "Select Image 2";
	private String selectImage3IconTooltip = "Select Image 3";

	private JButton selectImage1Button;
	private JButton selectImage2Button;
	private JButton selectImage3Button;

	/**
	 * Constructor for the ToolBar
	 * 
	 * @param gui:          DefinitionsEditorGUI that contains the CMSToolbar
	 * @param toolBarTitle: String representing the ToolBar title
	 * @param cardInserted: boolean representing the initial view selected used to
	 *                      activate the appropriate buttons
	 */
	ToolbarPane1(GUIDemo gui, String toolBarTitle, Color bkgColor) {
		this.gui = gui;

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

		selectImage1Button = createButton(selectImage1IconFile, selectImage1IconAction,
				selectImage1IconTooltip, "", true, true);
		selectImage2Button = createButton(selectImage2IconFile, selectImage2IconAction,
				selectImage2IconTooltip, "", true, true);
		selectImage3Button = createButton(selectImage3IconFile, selectImage3IconAction,
				selectImage3IconTooltip, "", true, true);

		toolBar.add(selectImage1Button);
		toolBar.add(selectImage2Button);
		toolBar.add(selectImage3Button);
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
//					System.out.println(actionString);
				if (hasLocalAction) {
					doLocalAction(actionString);
				}
				if (hasDriverAction) {
					gui.actionPerformed(new java.awt.event.ActionEvent(this, 0, actionString));
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
			case selectImage1IconAction:
				System.out.println("Selected Image 1 Pane 1");
				break;
			case selectImage2IconAction:
				System.out.println("Selected Image 2 Pane 1");
				break;
			case selectImage3IconAction:
				System.out.println("Selected Image 3 Pane 1");
				break;
		}
	}
//
//	void updateStatus(boolean walletHasFunds, boolean machineHasFunds) {
//		cashInToSlotMachineButton.setEnabled(walletHasFunds);
//		cashOutToWalletButton.setEnabled(machineHasFunds);
//		this.walletHasFunds = walletHasFunds;
//		this.machinetHasFunds = machineHasFunds;
//	}
}
