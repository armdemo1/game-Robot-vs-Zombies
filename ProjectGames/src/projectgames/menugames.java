package projectgames;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class menugames extends JPanel{
        private ImageIcon background = new ImageIcon(this.getClass().getResource("Startmenu1.png"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("Exitgame.png"));
	private ImageIcon Start = new ImageIcon(this.getClass().getResource("Startgame.png"));
	public JButton BStart = new JButton(Start);
	public JButton BExit  = new JButton(exit);
	menugames(){
            setLayout(null);
            BExit.setBounds(480,500,190,100);
            add(BExit);
            BStart.setBounds(480,370,190,100);
            add(BStart);
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(background.getImage(),0,0,1200,753,this);
	}
}