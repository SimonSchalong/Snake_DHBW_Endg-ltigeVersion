package snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import snake.Game;
import snake.Util;
import snake.configuration.Config;

public class OptionMenu extends Displayable {
	private static final long serialVersionUID = 429352424345908597L;
	/**
	 * Creates the layout and actions performed in the submenu 'Options'
	 */
	JFormattedTextField sizeX, sizeY, speed, length;
	JButton back, save, restore;
	
	private boolean sizeXValid = true, sizeYValid = true, speedValid = true, lengthValid = true;
	
	public OptionMenu() {
		super();
		GridBagLayout gbl = new GridBagLayout(); 
		setLayout(gbl);
		/**
		 * Creates and designs the buttons
		 */
		back = new JButton("Back to main menu");
		save = new JButton("Save options");
		restore = new JButton("Restore defaults");
		/**
		 * Creates and designs the labels
		 */
		JLabel l1, l2, l3, l4;
		l1 = new JLabel("X-Size:");
		l2 = new JLabel("Y-Size:");
		l3 = new JLabel("Speed-Modifier:");
		l4 = new JLabel("Start-Length:");
		
		sizeX = new JFormattedTextField();
		sizeY = new JFormattedTextField();
		speed = new JFormattedTextField();
		length = new JFormattedTextField();
		
		sizeX.setText(String.valueOf(Game.getConfig().getSquareSizeX()));
		sizeY.setText(String.valueOf(Game.getConfig().getSquareSizeY()));
		speed.setText(String.valueOf(Game.getConfig().getSpeedModifier()));
		length.setText(String.valueOf(Game.getConfig().getStartLength()));
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.openMainMenu();
			}
		});
		/**
		 * ActionLister which is responsible for saving the level-config
		 */
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getConfig().setSquareSizeX(Integer.parseInt(sizeX.getText()));
				Game.getConfig().setSquareSizeY(Integer.parseInt(sizeY.getText()));
				Game.getConfig().setSpeedModifier(Double.parseDouble(speed.getText()));
				Game.getConfig().setStartLength(Integer.parseInt(length.getText()));
				Game.getConfig().save();
			}
		});
		/**
		 * ActionLister which is responsible for loading the level-config
		 */
		restore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.restoreConfig();
				sizeX.setText(String.valueOf(Game.getConfig().getSquareSizeX()));
				sizeY.setText(String.valueOf(Game.getConfig().getSquareSizeY()));
				speed.setText(String.valueOf(Game.getConfig().getSpeedModifier()));
				length.setText(String.valueOf(Game.getConfig().getStartLength()));
			}
		});
		/*
		 * The DocumentListener is waiting for the changes of the text field and processes those changes.
		 */
		sizeX.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				verifySizeX();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				verifySizeX();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				verifySizeX();
			}
		});
		sizeY.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				verifySizeY();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				verifySizeY();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				verifySizeY();
			}
		});
		speed.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				verifySpeed();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				verifySpeed();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				verifySpeed();
			}
		});
		length.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				verifyLength();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				verifyLength();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				verifyLength();
			}
		});
		
		/**
		 * Sets the sizes of the configuration boxes to the size of Dimension d and align them to the center 
		 */
		Dimension d = new Dimension(100, 40);
		sizeX.setPreferredSize(d);
		sizeY.setPreferredSize(d);
		speed.setPreferredSize(d);
		length.setPreferredSize(d);
		sizeX.setHorizontalAlignment(SwingConstants.CENTER);
		sizeY.setHorizontalAlignment(SwingConstants.CENTER);
		speed.setHorizontalAlignment(SwingConstants.CENTER);
		length.setHorizontalAlignment(SwingConstants.CENTER);
		
		d = new Dimension(90, 40);
		
		l1.setPreferredSize(d);
		l2.setPreferredSize(d);
		l3.setPreferredSize(d);
		l4.setPreferredSize(d);
		
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.setHorizontalAlignment(SwingConstants.RIGHT);
		l4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		d = new Dimension(270, 70);
		back.setPreferredSize(d);
		d = new Dimension(130, 70);
		save.setPreferredSize(d);
		restore.setPreferredSize(d);
		
		/**
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the drop-down-menu in the window via the GridBagConstraints
		 */
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridheight = 1;
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbl.setConstraints(sizeX, gbc);
		add(sizeX);
		
		gbc.gridx = 0;
		gbl.setConstraints(l1, gbc);
		add(l1);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbl.setConstraints(sizeY, gbc);
		add(sizeY);
		
		gbc.gridx = 0;
		gbl.setConstraints(l2, gbc);
		add(l2);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(speed, gbc);
		add(speed);
		
		gbc.gridx = 0;
		gbl.setConstraints(l3, gbc);
		add(l3);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbl.setConstraints(length, gbc);
		add(length);
		
		gbc.gridx = 0;
		gbl.setConstraints(l4, gbc);
		add(l4);
		
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbl.setConstraints(back, gbc);
		add(back);
		
		gbc.gridwidth = 1;
		gbc.gridy = 5;
		
		gbc.gridx = 1;
		gbl.setConstraints(restore, gbc);
		add(restore);
		
		gbc.gridx = 0;
		gbl.setConstraints(save, gbc);
		add(save);

	}
	private Border getDefaultBorder() {
		return UIManager.getBorder("TextField.border");
	}
	/**
	 *  this method checks whether the given parameter is valid, if not the button gets a red 
	 *  frame and is locked
	 */
	public void verifySizeX() {
		String text = sizeX.getText();
		sizeX.setBorder(getDefaultBorder());
		sizeXValid = true;
		try {
			int i = Integer.parseInt(text);
			if(!Util.isInRange(Config.MIN_SIZE, Config.MAX_SIZE, i)) {
				sizeX.setBorder(new LineBorder(Color.RED));
				sizeXValid = false;
			}
		} catch (NumberFormatException e) {
			sizeX.setBorder(new LineBorder(Color.RED));
			sizeXValid = false;
		}
		disableSafeOnInvalidValues();
	}
	/**
	 *  this method checks whether the given parameter is valid, if not the button gets a red 
	 *  frame and is locked
	 */
	public void verifySizeY() {
		String text = sizeY.getText();
		sizeY.setBorder(getDefaultBorder());
		sizeYValid = true;
		try {
			int i = Integer.parseInt(text);
			if(!Util.isInRange(Config.MIN_SIZE, Config.MAX_SIZE, i)) {
				sizeY.setBorder(new LineBorder(Color.RED));
				sizeYValid = false;
			}
		} catch (NumberFormatException e) {
			sizeY.setBorder(new LineBorder(Color.RED));
			sizeYValid = false;
		}
		disableSafeOnInvalidValues();
	}
	/**
	 *  this method checks whether the given parameter is valid, if not the button gets a red 
	 *  frame and is locked
	 */
	public void verifySpeed() {
		String text = speed.getText();
		speed.setBorder(getDefaultBorder());
		speedValid = true;
		try {
			double d = Double.parseDouble(text);
			if(!Util.isInRange(Config.MIN_SPEED, Config.MAX_SPEED, d)) {
				speed.setBorder(new LineBorder(Color.RED));
				speedValid = false;
			}
		} catch (NumberFormatException e) {
			speed.setBorder(new LineBorder(Color.RED));
			speedValid = false;
		}
		disableSafeOnInvalidValues();
	}
	/**
	 *  this method checks whether the given parameter is valid, if not the button gets a red 
	 *  frame and is locked
	 */
	public void verifyLength() {
		String text = length.getText();
		length.setBorder(getDefaultBorder());
		lengthValid = true;
		try {
			int i = Integer.parseInt(text);
			if(!Util.isInRange(Config.MIN_SPEED, Integer.MAX_VALUE, i)) {
				length.setBorder(new LineBorder(Color.RED));
				lengthValid = false;
			}
		} catch (NumberFormatException e) {
			length.setBorder(new LineBorder(Color.RED));
			lengthValid = false;
		}
		disableSafeOnInvalidValues();
	}
	/**
	 * this method checks whether all parameters are valid and unlocks the safe button if true
	 */
	private void disableSafeOnInvalidValues() {
		save.setEnabled((lengthValid && speedValid && sizeXValid && sizeYValid));
	}
	
	
}
