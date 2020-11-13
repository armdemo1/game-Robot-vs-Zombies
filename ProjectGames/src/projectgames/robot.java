package projectgames;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class robot{
    public ImageIcon[] ro = new ImageIcon[2];
    public int y;
    public int count = 0;
    robot(){
        for(int i=0;i<ro.length;i++){
            String imageLocation = "Robot8bitgun0"+(i+1)+".png";
            ro[i] = new ImageIcon(this.getClass().getResource((imageLocation)));
	}
    }
}
