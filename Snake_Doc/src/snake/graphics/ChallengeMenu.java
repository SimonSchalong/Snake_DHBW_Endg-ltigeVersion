package snake.graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;

import snake.Game;
import snake.gameplay.Level;
import snake.level.LevelFileManager;


public class ChallengeMenu extends Displayable{
	
	private static final long serialVersionUID = 8740093219738297586L;
	/**
	 * Creates the layout and actions performed in the submenu 'Challenges'
	 */
	public ChallengeMenu() {
		super();
		GridBagLayout gbl = new GridBagLayout(); 
		setLayout(gbl);
		/**
		 * Creates and designs the drop down menu with the current Challenges from Storage
		 */
		LevelFileManager lfm = new LevelFileManager();
		Vector<Level> v = new Vector<Level>(lfm.getAllLevelsFromStorage());
		JComboBox<Level> levelBox = new JComboBox<Level>(v);
		levelBox.setFont(new Font("level_display", Font.BOLD, 24));
		levelBox.setPreferredSize(new Dimension(410, 70));
		levelBox.setEditable(false);
		
		/**
		 * Creates and designs the buttons
		 */
		JButton play, back;
		play = new JButton("Play selected Challenge");
		back = new JButton("Back to main menu");
		/**
		 * ActionLister which creates a new game with the selected Level
		 */
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Level selected = (Level) levelBox.getSelectedItem();
				Game.setLevel(selected);
				Game.setupNewGamePlay();
			}
		});
		/**
		 * ActionListener which goes back to the main menu
		 */
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.openMainMenu();
			}
		});
		/**
		 * This dimension sets the size of the buttons below the drop down menu
		 */
        Dimension d = new Dimension(200, 70);
        play.setPreferredSize(d);
        back.setPreferredSize(d);
		/**
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the drop-down-menu in the window via the GridBagConstraints
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.insets = new Insets(20, 5, 20, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
        gbl.setConstraints(levelBox, gbc);
        add(levelBox);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(play, gbc);
        add(play);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(back, gbc);
        add(back);
	}
	
}
