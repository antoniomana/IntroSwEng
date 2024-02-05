package es.uma.GUIDemo;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
// import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.util.SystemInfo;

public class GUIDemo implements ActionListener {

	/*
	 * Class attributes
	 */
	private static final String appTitle = "GUIDemo";
	private static final String aboutText = "GUI Demo Application\n"
			+ "Copyright � by University of M�laga 2023\n" + "\n"
			+ "Developed by Antonio Maña";
	
	private final String imagesPath = "./src/resources/images/";
	private String selectedPane1Image = "image1.png";
	private String selectedPane2Image = "image1.png";
	private String selectedPane3Image = "image1.png";
	
	/*
	 * Auxiliary variables
	 */
	
	// Selects key to be used in menu accelerators
	private final int CTRL_CMD = CTRL_DOWN_MASK; 
	
 	private final int WINDOW_H_SIZE = 1200;
	private final int WINDOW_V_SIZE = 600;
	
	/*
	 * GUI Components
	 */
	protected JFrame frame;

	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;

	private final Color leftPanelBackgroundColor = new Color(200, 200, 200);
	private final Color centerPanelBackgroundColor = new Color(240, 240, 240);
	private final Color rightPanelBackgroundColor = new Color(210, 210, 220);
	private final Color toolBarColor = new Color(75, 150, 190);

	// ToolBars
	private JToolBar mainToolBar;
	private ToolbarPane1 leftToolBar;
	private ToolbarPane2 centerToolBar;
	private ToolbarPane3 rightToolBar;

	// MenuBar Components
	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu, preferencesMenu;

	// File Menu Components
	private JMenuItem itemNew;

	// Not for OSx
	private JMenuItem itemExit;

	// Edit Menu Components
	private JMenuItem itemUndo;
	private JMenuItem itemRedo;
	private JMenuItem itemFind;
	private JMenuItem itemReplace;

	// Preferences Menu Components
	private JMenuItem itemBackGround;
	private JMenuItem itemShowPopup;

	// Auxiliary resources
	protected JColorChooser colorChooser;

	/**
	 * Detects system and calls the constructor GUIDemo()
	 * 
	 * @param args - command line arguments
     * 
	 */
	public static void main(String[] args)
//	throws UnsupportedLookAndFeelException
	{
		// macOS (see https://www.formdev.com/flatlaf/macos/)

		if (SystemInfo.isMacOS) {
			// enable screen menu bar
			// (moves menu bar from JFrame window to top of screen)
			System.setProperty("apple.laf.useScreenMenuBar", "true");

			// application name used in screen menu bar
			// (in first menu after the "apple" menu)
			System.setProperty("apple.awt.application.name", appTitle);

			// appearance of window title bars
			// possible values:
			// - "system": use current macOS appearance (light or dark)
			// - "NSAppearanceNameAqua": use light appearance
			// - "NSAppearanceNameDarkAqua": use dark appearance
			// (needs to be set on main thread; setting it on AWT thread does not work)
			System.setProperty("apple.awt.application.appearance", "system");

			// Get Desktop to be able to modify some parameters
			Desktop desktop = Desktop.getDesktop();

			// Integrating the About Dialog
			if (desktop.isSupported(Desktop.Action.APP_ABOUT)) {
				desktop.setAboutHandler(e -> Utils.userInformation("About GUIDemo", aboutText));
			}

			// Integrating the Preferences Dialog
			if (desktop.isSupported(Desktop.Action.APP_PREFERENCES)) {
				desktop.setPreferencesHandler(e -> {
					// show preferences dialog
				});
			}

			// hide menu items that are in macOS application menu

			// Handle quit with a confirmation pop-up
			if (desktop.isSupported(Desktop.Action.APP_QUIT_HANDLER)) {
				desktop.setQuitHandler((e, response) -> {
					if (Utils.userConfirmation("About to exit GUIDemo", "Are you sure you want to exit?",
							JOptionPane.WARNING_MESSAGE))
						response.performQuit();
					else response.cancelQuit();
				});
			}
		}
		SwingUtilities.invokeLater(FlatLightLaf::setup);
		EventQueue.invokeLater(GUIDemo::new);
	}

	/**
	 * Creates the GUI components
	 * 
	 */
	public GUIDemo() {
		initializeModel();
		createFrame();
		createToolbar();
		createLeftPane(selectedPane1Image);
		createCenterPane(selectedPane2Image);
		createRightPane(selectedPane3Image);
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createPreferencesMenu();
//		if (SystemInfo.isMacOS) {
//		    
//// hide menu items that are in macOS application menu
//			iWrap.setVisible(false);
//			iExit.setVisible(false);
//			preferencesMenu.setVisible(false);
//		}
		frame.setVisible(true);
	}

	/**
	 * Initializes model (list of available cards)
	 */
	private void initializeModel() {

	    // TODO: add model initialization code
	}

	/**
	 * Creates the main frame
	 */
	private void createFrame() {
		frame = new JFrame(appTitle);
		frame.setSize(WINDOW_H_SIZE, WINDOW_V_SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Creates the main toolbar
	 */
	private void createToolbar() {
		mainToolBar = new JToolBar("Main Toolbar");
		mainToolBar.setMaximumSize(new Dimension(185, 79));
		mainToolBar.setPreferredSize(new Dimension(185, 59));
		mainToolBar.add(Box.createHorizontalGlue());
		mainToolBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		mainToolBar.setBackground(toolBarColor);
		mainToolBar.setFloatable(false);
		mainToolBar.setMargin(new Insets(10, 15, 15, 15));
//		DemoToolbar demoToolBar = new DemoToolbar(this, "Demo ToolBar", true, toolBarColor);
		frame.add(mainToolBar, BorderLayout.PAGE_START);
	}

	/**
	 * Creates the LEFT pane
	 */
	public void createLeftPane(String selectedPane1Image) {
		leftPane = Utils.createBorderJPanel(leftPanelBackgroundColor);
		leftPane.setPreferredSize(new Dimension(400, 600));
		leftPane.setFont(new Font("Courier", Font.PLAIN, 14));
		leftPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Add Info Panel at the top of pane
		JPanel infoPane1 = Utils.createBorderJPanel(leftPanelBackgroundColor);
		infoPane1.setPreferredSize(new Dimension(400, 480));
			// Add a Title Label at the top of this pane
		JLabel titleLabel = new JLabel("Panel 1", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		infoPane1.add(titleLabel, BorderLayout.PAGE_START);
		
			// Add an Image in the middle of this pane
		BufferedImage pane1Image = null;
		String pane1ImageFile = selectedPane1Image;
		try {
			pane1Image = ImageIO.read(new File(imagesPath + pane1ImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(pane1Image));
		infoPane1.add(picLabel, BorderLayout.CENTER);
		
			// Add an Info label at the bottom of this pane
		ZoneId here = ZoneId.of("Europe/Madrid");
		ZonedDateTime now = ZonedDateTime.now(here);
		JLabel lowerLabel = new JLabel("Year " + now.getYear(), JLabel.CENTER);
		lowerLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		infoPane1.add(lowerLabel, BorderLayout.PAGE_END);
		
		// Buttons Panel at bottom of this pane
		JPanel pane1ButtonsPane = Utils.createGridJPanel(0, 1, leftPanelBackgroundColor);
		pane1ButtonsPane.setPreferredSize(new Dimension(400, 110));
		
			// Toolbar inside the Buttons Panel 
		leftToolBar = new ToolbarPane1(this, "Pane1 Toolbar", toolBarColor);
		pane1ButtonsPane.add(leftToolBar.toolBar);
		
		// Add internal components to LEFT Panel
		leftPane.add(infoPane1, BorderLayout.PAGE_START);
		leftPane.add(pane1ButtonsPane, BorderLayout.PAGE_END);

		// Add this pane to the main frame
		leftPane.setVisible(true);
		frame.add(leftPane, BorderLayout.WEST);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creates the CENTER pane
	 */
	public void createCenterPane(String selectedPane2Image) {

		centerPane = Utils.createBorderJPanel(centerPanelBackgroundColor);
		centerPane.setPreferredSize(new Dimension(400, 600));
		centerPane.setFont(new Font("Courier", Font.PLAIN, 14));
		centerPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Add Info Panel at the top of pane
		JPanel infoPane2 = Utils.createBorderJPanel(centerPanelBackgroundColor);
		infoPane2.setPreferredSize(new Dimension(400, 480));
			// Add a Title Label at the top of this pane
		JLabel titleLabel = new JLabel("Panel 2", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		infoPane2.add(titleLabel, BorderLayout.PAGE_START);
		
			// Add an Image in the middle of this pane
		BufferedImage pane2Image = null;
		String pane2ImageFile = selectedPane2Image;
		try {
			pane2Image = ImageIO.read(new File(imagesPath + pane2ImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(pane2Image));
		infoPane2.add(picLabel, BorderLayout.CENTER);
		
			// Add an Info label at the bottom of this pane
		ZoneId here = ZoneId.of("Europe/Madrid");
		ZonedDateTime now = ZonedDateTime.now(here);
		JLabel lowerLabel = new JLabel("Month " + now.getMonth(), JLabel.CENTER);
		lowerLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		infoPane2.add(lowerLabel, BorderLayout.PAGE_END);
		centerPane.add(infoPane2, BorderLayout.PAGE_START);
		
		// Buttons Panel at bottom of this pane
		JPanel pane2ButtonsPane = Utils.createGridJPanel(0, 1, rightPanelBackgroundColor);
		pane2ButtonsPane.setPreferredSize(new Dimension(400, 110));
		
			// Toolbar inside the Buttons Panel 
		centerToolBar = new ToolbarPane2(this, "Pane2 Toolbar", toolBarColor, selectedPane2Image);
		pane2ButtonsPane.add(centerToolBar.toolBar);
		
		// Add internal components to LEFT Panel
		centerPane.add(infoPane2, BorderLayout.PAGE_START);
		centerPane.add(pane2ButtonsPane, BorderLayout.PAGE_END);

		// Add this pane to the main frame
		centerPane.setVisible(true);
		frame.add(centerPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	
	/**
	 * Creates the RIGHT pane 
	 */
	public void createRightPane(String selectedPane3Image) {

		rightPane = Utils.createBorderJPanel(rightPanelBackgroundColor);
		rightPane.setPreferredSize(new Dimension(400, 600));
		rightPane.setFont(new Font("Courier", Font.PLAIN, 14));
		rightPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Info Panel at the top of pane
		JPanel infoPane3 = Utils.createBorderJPanel(rightPanelBackgroundColor);
		infoPane3.setPreferredSize(new Dimension(400, 480));

		// Title of this pane
		JLabel titleLabel = new JLabel("Panel 3", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		infoPane3.add(titleLabel, BorderLayout.PAGE_START);

		// Image of this pane
		BufferedImage pane3Image = null;
		String pane3ImageFile = selectedPane3Image;
		try {
			pane3Image = ImageIO.read(new File(imagesPath + pane3ImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(pane3Image));
		infoPane3.add(picLabel, BorderLayout.CENTER);

		// Lower label of this pane
		ZoneId here = ZoneId.of("Europe/Madrid");
		ZonedDateTime now = ZonedDateTime.now(here);
		JLabel lowerLabel = new JLabel("Day " + now.getDayOfMonth(), JLabel.CENTER);
		lowerLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		infoPane3.add(lowerLabel, BorderLayout.PAGE_END);
		rightPane.add(infoPane3, BorderLayout.PAGE_START);

		// Buttons Panel at bottom of this pane
		JPanel pane1ButtonsPane = Utils.createGridJPanel(0, 1, rightPanelBackgroundColor);
		pane1ButtonsPane.setPreferredSize(new Dimension(400, 110));

		rightToolBar = new ToolbarPane3(this, "Pane1 Toolbar", toolBarColor);

		pane1ButtonsPane.add(rightToolBar.toolBar);

		rightPane.add(pane1ButtonsPane, BorderLayout.PAGE_END);

		// Show the slot machine pane
		rightPane.setVisible(true);
		frame.add(rightPane, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creates the Menu Bar
	 */
	private void createMenuBar() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		// Not for OSx
//		if (!SystemInfo.isMacOS) {
			// show menu items that are in macOS application menu
			preferencesMenu = new JMenu("Preferences");
			menuBar.add(preferencesMenu);
//		}
		menuBar.setVisible(true);
	}

	/**
	 * Creates the File Menu
	 */
	private void createFileMenu() {
		itemNew = new JMenuItem("New");
		itemNew.addActionListener(this);
		itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, CTRL_CMD));
		itemNew.setActionCommand("New File");
		fileMenu.add(itemNew);

//   Not for OSx
		if (!SystemInfo.isMacOS) {
			// show menu items that are in macOS application menu
			itemExit = new JMenuItem("Exit");
			itemExit.addActionListener(this);
			itemExit.setActionCommand("Exit");
			fileMenu.add(itemExit);
		}

	}

	/**
	 * Creates the Edit Menu using a dedicated class
	 */
	private void createEditMenu() {

		itemUndo = new JMenuItem("Undo");
		itemUndo.addActionListener(this);
		itemUndo.setAccelerator((KeyStroke) AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_Z, CTRL_CMD));
		itemUndo.setActionCommand("Undo");
		editMenu.add(itemUndo);

		itemRedo = new JMenuItem("Redo");
		itemRedo.addActionListener(this);
		itemRedo.setAccelerator(
				(KeyStroke) AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK | CTRL_CMD));
		itemRedo.setActionCommand("Redo");
		editMenu.add(itemRedo);

		itemFind = new JMenuItem("Find");
		itemFind.addActionListener(this);
		itemFind.setAccelerator((KeyStroke) AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_F, CTRL_CMD));
		itemFind.setActionCommand("Find");
		editMenu.add(itemFind);

		itemReplace = new JMenuItem("Find & Replace");
		itemReplace.addActionListener(this);
		itemReplace.setActionCommand("Replace");
		editMenu.add(itemReplace);

	}

	/**
	 * Creates the Preferences Menu
	 */
	private void createPreferencesMenu() {

		itemBackGround = new JMenuItem("Set Background Color");
		itemBackGround.addActionListener(this);
		itemBackGround.setActionCommand("Set Background color");
		preferencesMenu.add(itemBackGround);

		itemShowPopup = new JMenuItem("Show Popup");
		itemShowPopup.addActionListener(this);
		itemShowPopup.setActionCommand("Show Popup");
		preferencesMenu.add(itemShowPopup);
	}


	private void refreshLeftPane(String selectedPane1Image) {
		createLeftPane(selectedPane1Image);
	}

	private void refreshCenterPane(String selectedPane2Image) {
		createCenterPane(selectedPane2Image);
	}

	private void refreshRightPane(String selectedPane3Image) {
		createRightPane(selectedPane3Image);
	}

	@SuppressWarnings("unused")
	private void refreshFrame() {
		createLeftPane(selectedPane1Image);
		createCenterPane(selectedPane2Image);
		createRightPane(selectedPane3Image);
	}
    
	/**
	 * Updates color of the panes
	 * 
	 */
	@SuppressWarnings("unused")
	private void updateToolBarColor(JToolBar targetToolBar) {
		this.colorChooser = new JColorChooser();
        @SuppressWarnings("static-access")
		Color color = this.colorChooser.showDialog(frame,"Choose a background color for the toolbar",new Color(0x1f1f1f));
  
		targetToolBar.setBackground(color);
	}
	
	/**
	 * Action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "Selected Image 1 Pane 1":
			selectedPane1Image = "image1.png";
			refreshLeftPane(selectedPane1Image);
			break;
		case "Selected Image 2 Pane 1":
			selectedPane1Image = "image2.png";
			refreshLeftPane(selectedPane1Image);
			break;
		case "Selected Image 3 Pane 1":
			selectedPane1Image = "image3.png";
			refreshLeftPane(selectedPane1Image);
			break;
		case "Selected Image 1 Pane 2":
			selectedPane2Image = "image1.png";
			refreshCenterPane(selectedPane2Image);
			break;
		case "Selected Image 2 Pane 2":
			selectedPane2Image = "image2.png";
			refreshCenterPane(selectedPane2Image);
			break;
		case "Selected Image 3 Pane 2":
			selectedPane2Image = "image3.png";
			refreshCenterPane(selectedPane2Image);
			break;
		case "Selected Image 1 Pane 3":
			selectedPane3Image = "image1.png";
			refreshRightPane(selectedPane3Image);
			break;
		case "Selected Image 2 Pane 3":
			selectedPane3Image = "image2.png";
			refreshRightPane(selectedPane3Image);
			break;
		case "Selected Image 3 Pane 3":
			selectedPane3Image = "image3.png";
			refreshRightPane(selectedPane3Image);
			break;
		case "Synchronize Icons":
			selectedPane1Image = selectedPane2Image;
			refreshLeftPane(selectedPane1Image);
			selectedPane3Image = selectedPane2Image;
			refreshRightPane(selectedPane3Image);
			break;
		case "Reset Images":
			selectedPane1Image = "image1.png";
			refreshLeftPane(selectedPane1Image);
			selectedPane2Image = "image1.png";
			refreshCenterPane(selectedPane2Image);
			selectedPane3Image = "image1.png";
			refreshRightPane(selectedPane3Image);
			break;
		case "Settings":
			Utils.userInformation("Settings Window", "would open here");
			break;
		case "Set Background color":
			updateToolBarColor(mainToolBar);
			break;
		case "Show Popup":
			Utils.userInformation("A pop-up window", "would open here");
			break;
		}
	}
}
