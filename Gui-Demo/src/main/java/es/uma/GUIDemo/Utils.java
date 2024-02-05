package es.uma.GUIDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Utils {

/**
 * This class contains utilities for creating swing GUIs
 * 
 * @author Antonio Maña
 *  
 */

    private static final int GAP = 5;
    
    public static JPanel createGridBagJPanel(Color paneBackgroundColor, int rows, int cols) {
		JPanel newPane = new JPanel();
		newPane.setLayout(new GridBagLayout());
		if (paneBackgroundColor!=null) {
			newPane.setBackground(paneBackgroundColor);			
		}
		newPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		return newPane;
    }
    
    public static JPanel createBorderJPanel(Color paneBackgroundColor) {
		JPanel newPane = new JPanel();
		newPane.setLayout(new BorderLayout());
		if (paneBackgroundColor!=null) {
			newPane.setBackground(paneBackgroundColor);			
		}
		newPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		return newPane;
    }

    public static JPanel createBoxJPanel(Container c, Color paneBackgroundColor) {
		JPanel newPane = new JPanel();
	    BoxLayout boxLayout = new BoxLayout(newPane, BoxLayout.Y_AXIS); // top to bottom
	    newPane.setLayout(boxLayout);
		
		if (paneBackgroundColor!=null) {
			newPane.setBackground(paneBackgroundColor);			
		}
		newPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		return newPane;
    }

    public static JPanel createGridJPanel(int rows, int cols, Color paneBackgroundColor) {
		JPanel newPane = new JPanel();
		
		GridLayout gridLayout = new GridLayout(rows, cols); 
	    newPane.setLayout(gridLayout);
		
		if (paneBackgroundColor!=null) {
			newPane.setBackground(paneBackgroundColor);			
		}
		newPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		return newPane;
    }
    // create constraints that help position components in the GridBagLayout-using container
    public static GridBagConstraints createGBC(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(GAP, GAP, GAP, GAP);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        return gbc;     
    }
	

	/**
	 * Asks whether the user wants to load the model in the editor
	 */
    public static double doubleInputDialog(String dialogMessage, String defaultValue) {
		String amountString = JOptionPane.showInputDialog(dialogMessage, defaultValue);
		if (amountString != null) {
			return Double.parseDouble(amountString);

		}
		return 0.0;
	}

	/**
	 * Asks for user confirmation
	 * 
	 * @param title
	 * @param question
	 * @return boolean (true means confirm)
	 */
	public static boolean userConfirmation(String title, String question, int messageType) {
		return (JOptionPane.showConfirmDialog(new Frame(), question, title, JOptionPane.YES_NO_OPTION,
				messageType) == JOptionPane.YES_OPTION);
	}

	/**
	 * Creates an information dialog
	 * 
	 * @param title
	 * @param question
	 * @return boolean (true means confirm)
	 */
	public static void userInformation(String title, String information) {
		JOptionPane.showMessageDialog(new Frame(), information, title, JOptionPane.PLAIN_MESSAGE);
	}

	
}

