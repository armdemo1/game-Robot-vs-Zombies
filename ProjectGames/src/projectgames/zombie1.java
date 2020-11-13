package projectgames;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.Random;
public class zombie1 {
        public static int getRandom(int[] array) {
             int rnd = new Random().nextInt(array.length);
             return array[rnd];
        }
    
	Image img;
	public int x = 1100;
        public int[] array = new int[]{230,370,520};
        public int y =  getRandom(array);
	public int count = 0;
	zombie1(){
                String imageLocation = "Zombie1.png";
                URL imageURL = this.getClass().getResource(imageLocation);
		img = Toolkit.getDefaultToolkit().getImage(imageURL);
		runner.start();
	}
	Thread runner = new Thread(new Runnable() {
            public void run() {
		while(true){
                    x -= 6;
                    if(x <= 0){
			img = null;
			runner = null;
                    }
                    try{
			runner.sleep(10);
                    }catch(InterruptedException e){}
                }
            }
	});
	
	public Image getImage(){
            return img;
	}
	
	public int getX(){
            return x;
	}
	public int getY(){
            return y;
	}
	public Rectangle2D getbound(){
    	    return (new Rectangle2D.Double(x,y,100,140));
	}

}
