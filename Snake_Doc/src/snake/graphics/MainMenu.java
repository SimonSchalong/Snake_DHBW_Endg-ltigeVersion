package snake.graphics;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import snake.Game;
import snake.gameplay.Level;


public class MainMenu extends Displayable{

	private static final long serialVersionUID = 673087857349848084L;
	/**
	 * Creates the layout and actions performed in the "MainMenu"
	 */	
	public MainMenu() {
		super();
		GridBagLayout gbl = new GridBagLayout(); 
		setLayout(gbl);
		
		JButton start, freeplay, challenges, options, exit;
		
        start = new JButton("Start");
        freeplay = new JButton("Free-Mode");
        challenges = new JButton("Challenges");
        options = new JButton("Options");
        exit = new JButton("Exit");
        /**
		 * ActionLister which starts the DefaultLevel
		 */
        start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setLevel(Level.getDefaultLevel());
				Game.setupNewGamePlay();
			}
		});
        /**
		 * ActionLister which starts the FreePlayLevel
		 */
        freeplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setLevel(Level.getFreePlayLevel());
				Game.setupNewGamePlay();
			}
		});
        /**
		 * ActionLister which opens the ChallengeMenu
		 */
        challenges.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.openChallengeMenu();
			}
		});
        /**
		 * ActionLister which opens the OptionMenu
		 */
        options.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.openOptionMenu();
			}
		});
        /**
		 * ActionLister which closes the game
		 */
        exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
    	/**
		 * This dimension sets the size of the buttons in the mainMenu
		 */
        Dimension d = new Dimension(200, 70);
        start.setPreferredSize(d);
        freeplay.setPreferredSize(d);
        challenges.setPreferredSize(d);
        options.setPreferredSize(d);
        exit.setPreferredSize(d);
        
        /**
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the drop-down-menu in the window via the GridBagConstraints
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		gbc.gridy = 0;
        gbl.setConstraints(start, gbc);
        add(start);
        
        gbc.gridy = 1;
        gbl.setConstraints(freeplay, gbc);
        add(freeplay);
        
		gbc.gridy = 2;
        gbl.setConstraints(challenges, gbc);
        add(challenges);
        
		gbc.gridy = 3;
		gbl.setConstraints(options, gbc);
		add(options);

		gbc.gridy = 4;
		gbl.setConstraints(exit, gbc);
		add(exit);

	}
	
	

}
