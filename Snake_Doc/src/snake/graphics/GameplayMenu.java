package snake.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import snake.Game;

public class GameplayMenu extends Displayable {

	private static final long serialVersionUID = -1149506383725208544L;

	private static JLabel score = new JLabel(), highscore = new JLabel();
	private Draw d;
	
	private final static String SCORE_PATTERN = 
			"<html><div style='text-align: center;'>Current Score</div><br><div style='text-align: center;'>%score%</html>";
	private final static String HIGHSCORE_PATTERN = 
			"<html><div style='text-align: center;'>Highscore</div><br><div style='text-align: center;'>%score%</html>";

	public GameplayMenu() {
		super();
		this.d = new Draw(this);
		GridBagLayout gbl = new GridBagLayout(); 
		setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(d, BorderLayout.CENTER);
        
        /**
         * here the score and highscore is created and the position is assigned, the height for the score
         * is calculated with the Gamegrid size
         */
        GamegridCalculator gc = new GamegridCalculator();
        gc.setSquaresY(Game.getRunningGame().getLevel().getSizeY());
        Dimension dim = new Dimension(120, gc.getTotalGridHeight());
        score.setPreferredSize(dim);
        highscore.setPreferredSize(dim);
        
        Font f = new Font("score_disp", Font.BOLD, 16);
        score.setFont(f);
        highscore.setFont(f);
    
        score.setVerticalAlignment(SwingConstants.TOP);
        highscore.setVerticalAlignment(SwingConstants.TOP);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        highscore.setHorizontalAlignment(SwingConstants.CENTER);
        
        /**
		 * the GridBagLayout, which makes it possible to place components in a grid of rows and columns, 
		 * is used to arrange the buttons and the drop-down-menu in the window via the GridBagConstraints
		 */
        
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
        gbl.setConstraints(centerPanel, gbc);
        add(centerPanel);
        
        updateScores();
        
        gbc.gridx = 0;
        gbl.setConstraints(score, gbc);
        add(score);
        
        gbc.gridx = 2;
        gbl.setConstraints(highscore, gbc);
        add(highscore);
        
	}
	
	public Draw getDrawBoard() {
		return d;
	}
	/**
	 * Replace the current score with the passed value
	 * @param value the new score
	 */
	private void setDisplayScore(long value) {
		score.setText(SCORE_PATTERN.replaceAll("%score%", String.valueOf(value)));
	}
	/**
	 * Replace the current highscore with the passed value
	 * @param value the new highscore
	 */
	private void setDisplayHighscore(long value) {
		highscore.setText(HIGHSCORE_PATTERN.replaceAll("%score%", String.valueOf(value)));
	}
	
	/*
	 * Is used to update the current highscore if the current score is higher than the highscore
	 */
	protected void updateScores() {
		long s = Game.getRunningGame().getScore();
		long hs = Game.getConfig().getHighScore();
		if(s > hs) {
			Game.getConfig().setHighScore(s);
			Game.getConfig().save();
			hs = Game.getConfig().getHighScore();
		}
		setDisplayScore(s);
		setDisplayHighscore(hs);
	}
}
