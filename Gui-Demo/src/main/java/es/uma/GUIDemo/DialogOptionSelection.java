package es.uma.GUIDemo;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * This class shows a dialog for selecting an option
 * 
 * @author Antonio Maña
 * 
 */
class DialogOptionSelection extends JDialog implements ActionListener {

	private static final long serialVersionUID = 2640852911800143131L;

	private LinkedList<String> availableOptionsStrings;
	private String dialogTitle = null;
	private boolean anOptionIsSelected = false;
	private JComboBox<String> selectionCombo = new JComboBox<String>();
	private JButton btnSelect = new JButton("Select");
	private JButton btnCancel = new JButton("Cancel");

	private static final int GAP = 5;

	DialogOptionSelection(String dialogTitle, LinkedList<String> availableOptions) {
		super();
		this.dialogTitle = dialogTitle;
		this.availableOptionsStrings = availableOptions;
		setModal(true);
	}

	/**
	 * Action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the state combobox is changed
		if (e.getSource() == btnSelect) {
			anOptionIsSelected = true;
			this.dispose();
		}
		if (e.getSource() == btnCancel) {
			anOptionIsSelected = false;
			this.dispose();
		}
	}

	void run() {
		JPanel window = new JPanel();
		window.setLayout(new GridBagLayout());
		window.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

		for (String c : availableOptionsStrings) {
			selectionCombo.addItem(c);
		}

		selectionCombo.setSize(50, 20);
		selectionCombo.setBounds(10, 10, 80, 20);
		window.add(selectionCombo, createGBC(1, 0));

		window.add(Box.createRigidArea(new Dimension(10, 15)));

		btnSelect.setSize(new Dimension(80, 20));
		window.add(btnSelect, createGBC(2, 0));

		window.add(Box.createRigidArea(new Dimension(10, 15)));

		btnCancel.setSize(new Dimension(80, 20));
		window.add(btnCancel, createGBC(3, 0));

		window.add(Box.createRigidArea(new Dimension(10, 15)));

		selectionCombo.addActionListener(this);
		btnSelect.addActionListener(this);
		btnCancel.addActionListener(this);

		add(window);
		pack();

		setTitle(dialogTitle);
		setLocationRelativeTo(null);
	}

	String getSelectedOption() {
		if (anOptionIsSelected) {
			return availableOptionsStrings.get(selectionCombo.getSelectedIndex());
		}
		return null;
	}

	// create constraints that help position components in the GridBagLayout-using
	// container
	private GridBagConstraints createGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.insets = new Insets(GAP, GAP, GAP, GAP);
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		return gbc;
	}
}
