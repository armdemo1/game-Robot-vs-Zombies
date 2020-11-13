package projectgames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ProjectGames extends JFrame implements ActionListener {  
	menugames menugames1 = new menugames();
	Gameplay stage1 = new Gameplay();
	public ProjectGames(){
		this.setSize(1200,800);
		this.add(menugames1);
		menugames1.BExit.addActionListener(this);
                menugames1.BStart.addActionListener(this);
                stage1.BExit.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
            if(e.getSource()== menugames1.BStart){
		this.setLocationRelativeTo(null);
		this.remove(menugames1);
                this.setSize(1200,800);
                this.add(stage1);
                stage1.requestFocusInWindow();
		stage1.rpoint=0;
		stage1.life =20;
		stage1.times = 50;
                stage1.timestart=false;
                stage1.startzom=false;
            }else if(e.getSource() == menugames1.BExit){
		System.exit(0);
            }else if(e.getSource() == stage1.BExit){       
                System.exit(0);
            }
            this.validate();
            this.repaint();
	}
        
     public static void main(String[] args) {
            JFrame jf = new ProjectGames();
            jf.setSize(1200,800);
            jf.setTitle("game Robot vs Zombies");
            jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            String filepath="plz.wav";
            music musicObject =new music();
            musicObject.playMusic(filepath);
    }    
	
}
