package projectgames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Gameplay extends JPanel{
    
    private final ImageIcon imgmap1 = new ImageIcon(this.getClass().getResource("ingame1v3.png"));
    private final ImageIcon imgmap2 = new ImageIcon(this.getClass().getResource("stage2v4.png"));
    public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
    public ArrayList<zombie1> zom1 = new ArrayList<zombie1>();
    public ArrayList<zombie2> zom2 = new ArrayList<zombie2>();
    public ArrayList<zombie3> zom3 = new ArrayList<zombie3>();
    ImageIcon gameover = new ImageIcon(this.getClass().getResource("GameOver1.png"));
    ImageIcon exit = new ImageIcon(this.getClass().getResource("Exitgame.png"));
    public JButton BExit = new JButton(exit);
    private final JLabel point  = new JLabel();
    public int life = 3;
    public int rpoint = 0;
    public int times ;
    public int st1 = 1;
    public int st2 = 2;
    int count = 0;
    boolean timestart = true;
    boolean startzom = false;

    robot r = new robot();
    
	Thread actor = new Thread(new Runnable(){
            public void run(){
		while(true){
                	try{
                            Thread.sleep(1);
			}catch(Exception e){}
                            repaint();
		}
            }
	});
        
        Thread tzom1 = new Thread(new Runnable(){
            public void run() {
                while(true){
                	try{
                            if(startzom == false){
				Thread.sleep(1100);
                            }
			}catch(InterruptedException e){
                            e.printStackTrace();
			}
			if(startzom == false){
                            zom1.add(new zombie1());
			}
		}
            }
	});
        
        Thread tzom2 = new Thread(new Runnable(){
            public void run() {
                while(true){
                	try{
                            if(startzom == false){
				Thread.sleep(2000);
                            }
			}catch(InterruptedException e){
                            e.printStackTrace();
			}
			if(startzom == false){
                            zom2.add(new zombie2());
			}
		}
            }
	});
        
        Thread tzom3 = new Thread(new Runnable(){
            public void run() {
                while(true){
                	try{
                            if(startzom == false){
				Thread.sleep(4000);
                            }
			}catch(InterruptedException e){
                            e.printStackTrace();
			}
			if(startzom == false){
                            zom3.add(new zombie3());
			}
		}
            }
	});
        

        Thread t = new Thread(new Runnable(){
            public void run() {
		while(true){
                	if(timestart == false){
                            times = (times-1) ;
			}
			try{
                            Thread.sleep(1000);
			}catch(InterruptedException e)
			{
                            e.printStackTrace();
			}
		}
            }
	});    
           
    public Gameplay(){
		this.setFocusable(true);
		this.setLayout(null);
                this.addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e){
                        int a = e.getKeyCode();
			if(a==KeyEvent.VK_UP){
			   	r.y-=150;
                                r.count=0;
                            }
                            else if(a == KeyEvent.VK_DOWN){
                               r.y+=150;
                               r.count=0;
			   }
                            else if(a == KeyEvent.VK_Z){
                               r.count=1;
                               bullet.add(new Bullet(230,r.y+140));
			    }
                    }
                    public void keyReleased(KeyEvent e){
			r.count=0;
		    }
		});
                
		r.y = 300;
		actor.start();
		t.start();
                tzom1.start();
                tzom2.start();
                tzom3.start();
	}
	
    public void paintComponent(Graphics g){
         super.paintComponent(g);
         if(times <= 0 || life<=0){
            this.setLayout(null);
            BExit.setBounds(480,400,190,100);
            this.add(BExit);
            g.drawImage(gameover.getImage(),0,0,1200,753,this);
            g.setFont(new Font("Hobo Std",Font.CENTER_BASELINE,30));
            g.setColor(Color.BLACK);
            g.drawString("Your Point is "+rpoint, 470, 300);
         }else if(times <= 25){
            g.drawImage(imgmap2.getImage(),0,0,1200,753,this);
            //robot////////////////////////////////////////////////////////////
            g.drawImage(r.ro[r.count].getImage(), 0, r.y,250,270, this);
                          
            if(r.y<150){
                r.y=this.getHeight()-300;
            }
            if(r.y>this.getHeight()-250){
                r.y=150;
            }
            
            ///////////////////////////////////////////////////////////////////
            
            //bullet///////////////////////////////////////////////////////////
            for(int i=0;i<bullet.size();i++){
                Bullet bu = bullet.get(i);
                g.drawImage(bu.bul[bu.count].getImage(), bu.x-30, bu.y-10,70,25, null);
		bu.move();
                if(bu.y<0){
      		    	bullet.remove(i);
      		    }
            }
            ///////////////////////////////////////////////////////////////////
            
            //zombie1//////////////////////////////////////////////////////////
            for(int i=0 ; i<zom1.size();i++){
                   g.drawImage( zom1.get(i).getImage() ,zom1.get(i).getX(),zom1.get(i).getY(),100,140,this);
                   if(zom1.get(i).getX()<250){
                        zom1.remove(i);
                        life-=2;
                   }
	    }            
       
            for(int i=0 ; i<bullet.size();i++){
                for(int j=0 ; j<zom1.size();j++){
		    if(Intersect(bullet.get(i).getbound(),zom1.get(j).getbound())){
                        zom1.remove(j);
                        bullet.remove(i);
                        rpoint += 10;
                    } 
		}
            }
	    ///////////////////////////////////////////////////////////////////
            
            //zombie2//////////////////////////////////////////////////////////
            for(int i=0 ; i<zom2.size();i++){
                   g.drawImage( zom2.get(i).getImage() ,zom2.get(i).getX(),zom2.get(i).getY(),100,140,this);
                   if(zom2.get(i).getX()<250){
                        zom2.remove(i);
                        life-=4;
                   }
	    }
            
            for(int i=0 ; i<bullet.size();i++){
                for(int j=0 ; j<zom2.size();j++){
		    if(Intersect(bullet.get(i).getbound(),zom2.get(j).getbound())){
                       zom2.remove(j);
                       bullet.remove(i);
                       rpoint += 20;
                    } 
		}
            }
	    ///////////////////////////////////////////////////////////////////
            
            //zombie3//////////////////////////////////////////////////////////
            for(int i=0 ; i<zom3.size();i++){
                   g.drawImage( zom3.get(i).getImage() ,zom3.get(i).getX(),zom3.get(i).getY(),100,140,this);
                   if(zom3.get(i).getX()<250){
                        zom3.remove(i);
                        life-=5;
                   }
	    }
            
            for(int i=0 ; i<bullet.size();i++){
                for(int j=0 ; j<zom3.size();j++){
		    if(Intersect(bullet.get(i).getbound(),zom3.get(j).getbound())){
                        zom3.remove(j);
                        bullet.remove(i);
                        rpoint += 30;
                    } 
                    
		}
            }
	    ///////////////////////////////////////////////////////////////////
           
            g.setFont(new Font("Hobo Std",Font.CENTER_BASELINE,30));
            g.setColor(Color.BLACK);
            g.drawString("Point:  "+rpoint,20, 45);	       
            g.setFont(new Font("Hobo Std",Font.CENTER_BASELINE,30));
            g.drawString("Time: "+times,545,30);
            g.drawString("Stage: "+st2,545,75);
            g.setColor(Color.BLACK);
            g.drawString("Life:  "+life,20,85);
		      
         }else{
            g.drawImage(imgmap1.getImage(),0,0,1200,753,this);
            //robot////////////////////////////////////////////////////////////
            g.drawImage(r.ro[r.count].getImage(), 0, r.y,250,270, this);
                          
            if(r.y<150){
                r.y=this.getHeight()-300;
            }
            if(r.y>this.getHeight()-250){
                r.y=150;
            }
            
            ///////////////////////////////////////////////////////////////////
            
            //bullet///////////////////////////////////////////////////////////
            for(int i=0;i<bullet.size();i++){
                Bullet bu = bullet.get(i);
                g.drawImage(bu.bul[bu.count].getImage(), bu.x-30, bu.y-10,70,25, null);
		bu.move();
                if(bu.y<0){
      		    	bullet.remove(i);
      		    }
            }
            ///////////////////////////////////////////////////////////////////
            
            //zombie1//////////////////////////////////////////////////////////
            for(int i=0 ; i<zom1.size();i++){
                   g.drawImage( zom1.get(i).getImage() ,zom1.get(i).getX(),zom1.get(i).getY(),100,140,this);
                   if(zom1.get(i).getX()<250){
                        zom1.remove(i);
                        life-=1;
                   }
	    }            
       
            for(int i=0 ; i<bullet.size();i++){
                for(int j=0 ; j<zom1.size();j++){
		    if(Intersect(bullet.get(i).getbound(),zom1.get(j).getbound())){
                        zom1.remove(j);
                        bullet.remove(i);
                        rpoint += 10;
                    } 
		}
            }
	    ///////////////////////////////////////////////////////////////////
            
            //zombie2//////////////////////////////////////////////////////////
            for(int i=0 ; i<zom2.size();i++){
                   g.drawImage( zom2.get(i).getImage() ,zom2.get(i).getX(),zom2.get(i).getY(),100,140,this);
                   if(zom2.get(i).getX()<250){
                        zom2.remove(i);
                        life-=2;
                   }
	    }
            
            for(int i=0 ; i<bullet.size();i++){
                for(int j=0 ; j<zom2.size();j++){
		    if(Intersect(bullet.get(i).getbound(),zom2.get(j).getbound())){
                       zom2.remove(j);
                       bullet.remove(i);
                       rpoint += 20;
                    } 
		}
            }
	    ///////////////////////////////////////////////////////////////////
            
            //zombie3//////////////////////////////////////////////////////////
            for(int i=0 ; i<zom3.size();i++){
                   g.drawImage( zom3.get(i).getImage() ,zom3.get(i).getX(),zom3.get(i).getY(),100,140,this);
                   if(zom3.get(i).getX()<250){
                        zom3.remove(i);
                        life-=3;
                   }
	    }
            
            for(int i=0 ; i<bullet.size();i++){
                for(int j=0 ; j<zom3.size();j++){
		    if(Intersect(bullet.get(i).getbound(),zom3.get(j).getbound())){
                        zom3.remove(j);
                        bullet.remove(i);
                        rpoint += 30;
                    } 
                    
		}
            }
	    /////////////////////////////////////////////////////////////////////
                        
            g.setFont(new Font("Hobo Std",Font.CENTER_BASELINE,30));
            g.setColor(Color.BLACK);
            g.drawString("Point:  "+rpoint,20, 45);	       
            g.setFont(new Font("Hobo Std",Font.CENTER_BASELINE,30));
            g.drawString("Time: "+times,545,30);
            g.drawString("Stage: "+st1,545,65);
            g.setColor(Color.BLACK);
            g.drawString("Life:  "+life,20,85);
		      
	    }

	}
    
    public boolean Intersect(Rectangle2D a, Rectangle2D b){
		return (a.intersects(b));
	}
   
}