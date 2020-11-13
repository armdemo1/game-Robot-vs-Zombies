package projectgames;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bullet extends JPanel{
    public ImageIcon[] bul = new ImageIcon[1];
    public int y;
    public int x;
    public int count=0;
    Bullet(int x,int y){
        for(int i=0;i<bul.length;i++){
            bul[i] = new ImageIcon(this.getClass().getResource("bullet1.png"));
	}
            this.x=x;
            this.y=y;
    }
	
    public void move(){
	this.x+=10;
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,70,25));
    }
}
