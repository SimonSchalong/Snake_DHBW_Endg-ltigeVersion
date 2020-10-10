package snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import snake.Game;

public class PauseMenu extends Displayable{

	private static final long serialVersionUID = 2498861919401300045L;
	/**
	 * Creates the layout and actions performed in the submenu 'Pause'
	 */
	public PauseMenu() {
		super();
		/*
		 * The GridBagLayout is used to align the buttons along the Gridbagconstaint's grid
		 */
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		/*
		 * Creates and designs the buttons
		 */
		JButton backToMain = new JButton("Give up");
		JButton resume = new JButton("Resume");
		/*
		 * create the behaviour for the buttons
		 */
		backToMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.pauseGame();
				Game.stopCurrentGamePlay();
				Game.openMainMenu();
			}
		});
		/*
		 * create the behaviour for the buttons
		 */
		resume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.pauseGame();
			}
		});
		backToMain.setPreferredSize(new Dimension(250, 70));
		resume.setPreferredSize(new Dimension(250, 70));
		
		/**
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the drop-down-menu in the window via the GridBagConstraints
		 */

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 30, 20);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbl.setConstraints(backToMain, gbc);
		add(backToMain);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbl.setConstraints(resume, gbc);
		add(resume);
		
		JLabel label = new JLabel("Paused");
		label.setForeground(Color.LIGHT_GRAY);
		label.setOpaque(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(450, 150));
		Font f = new Font("labelFont", Font.PLAIN, 50);
		label.setFont(f);
		
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbl.setConstraints(label, gbc);
		add(label);
		
		
		this.setBackground(Colors.POPUP_MENU.getColor());
		this.setBorder(new LineBorder(Colors.POPUP_MENU_BORDER.getColor(), 2));
		this.setOpaque(true);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 300);
	}
	
	@Override
	public Integer getLayer() {
		return Integer.valueOf(0);
	}

}
