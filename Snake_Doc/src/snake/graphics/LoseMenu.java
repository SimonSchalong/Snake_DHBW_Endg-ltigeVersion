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


public class LoseMenu extends Displayable {

	private static final long serialVersionUID = 873467881181222283L;
	public LoseMenu() {
		super();
		/*
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the labels in the window via the GridBagConstraints
		 */
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		/*
		 * Creates two Buttons one for the Return to menu and one to play again
		 */
		JButton backToMain = new JButton("Return to main menu");
		JButton retry = new JButton("Play again");

		/*
		 * Adds an ActionListener to Return to menu button
		 */
		backToMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.openMainMenu();
			}
		});
		/*
		 * Adds an ActionListener to Create a new game
		 */
		retry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setupNewGamePlay();
			}
		});

		backToMain.setPreferredSize(new Dimension(250, 70));
		retry.setPreferredSize(new Dimension(250, 70));

		/**
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the drop-down-menu in the window via the GridBagConstraints
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 0, 5);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbl.setConstraints(backToMain, gbc);
		add(backToMain);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbl.setConstraints(retry, gbc);
		add(retry);
		
		/*
		 * Style and content of the You lose label
		 */
		JLabel label = new JLabel("You lose!");
		label.setForeground(Color.RED);
		label.setOpaque(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(450, 150));
		Font f = new Font("labelFont", Font.BOLD, 50);
		label.setFont(f);
		
		gbc.ipady = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbl.setConstraints(label, gbc);
		add(label);
		
		/**
		 * Style and content of the Score label
		 */
		JLabel score = new JLabel("Your Score was: " + Game.getRunningGame().getScore());
		score.setForeground(Color.RED);
		score.setOpaque(false);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setPreferredSize(new Dimension(450, 50));
		f = new Font("labelFont", Font.PLAIN, 18);
		score.setFont(f);
		
		gbc.insets = new Insets(5, 5, 30, 5);
		gbc.ipady = 30;
		gbc.gridy = 1;
		gbl.setConstraints(score, gbc);
		add(score);
		
		this.setBackground(Colors.POPUP_MENU.getColor());
		this.setBorder(new LineBorder(Colors.POPUP_MENU_BORDER.getColor(), 2));
		this.setOpaque(true);
		
	}

//	@Override
//	public void setToFrame(JLayeredPane pane) {
//		this.setOpaque(true);
//		pane.add(this, 0);
//	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 300);
	}
	
	@Override
	public Integer getLayer() {
		return Integer.valueOf(0);
	}
	
	
	

}
