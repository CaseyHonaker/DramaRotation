import java.awt.*;        
import java.awt.event.*; 
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.Graphics;
import java.net.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;
import javax.swing.UIManager.*;

public class rotater implements Runnable {

    public static int rotaten;
	public JTable table;
	public JPanel pnPanel0;
	public JPanel pnPanel3;
	public JPanel pnPanel1;
	public JFrame frame;
	public int quelabel;
	public static test1 test1;
	public int rotation;
	public static int rotationint;
	public static BufferedImage bi;
	public static int rotaterrotation;

    public rotater(int rotaten, JTable table, JPanel pnPanel0, JPanel pnPanel3, JPanel pnPanel1, JFrame frame, int quelabel) {
        this.rotaten = rotaten;
		this.table = table;
		this.pnPanel0 = pnPanel0;
		this.pnPanel3 = pnPanel3;
		this.pnPanel1 = pnPanel1;
		this.frame = frame;
		this.quelabel = quelabel;
		
		//Makes the whole program look a lot better but there are some inexcusable errors that need to be worked out
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		} 
    }

    public void run() {
		test1.rotations = 1;
		try{
			if (quelabel == 0){
				try{
					Object rotationobject = table.getModel().getValueAt(table.getSelectedRow()-1, 1);
					String rotationstring = rotationobject.toString();
					int rotationint = Integer.parseInt(rotationstring);
				} catch (NullPointerException a){
					int rotationint = 0;
				} catch (ArrayIndexOutOfBoundsException c){
					int rotationint = 0; 
				} catch (NumberFormatException b){
					int rotationint = rotaten; 
				} 
			} else {
				int rotationint = quelabel;
			}
			bi = ImageIO.read(new File("CircleStagePNG.png"));			
			if (rotaten < 0) {
				for(int i=test1.totalrotation;i > (rotaten + test1.totalrotation) - 1;i--) {
					test1.pnPanel3.removeAll();
					rotation = i;
					if (i == (rotaten + test1.totalrotation)){
						bi = ImageIO.read(new File("CircleStagePNGSTOP.png"));
					}
					JPanel rotatepanel = new JPanel() {
						@Override
						public Dimension getPreferredSize() {
							return new Dimension(bi.getWidth(), bi.getHeight());
						}
						
						@Override
						protected void paintComponent(Graphics g) {
							super.paintComponent(g);
							Graphics2D g2 = (Graphics2D) g;
							g2.rotate(Math.toRadians(rotation), bi.getWidth() / 2, bi.getHeight() / 2);
							g2.drawImage(bi, 0, 0, null);
						}
					};
					
				
					rotatepanel.setBackground(test1.color);
					test1.pnPanel3.add(rotatepanel);
					test1.pnPanel0.setBackground(test1.color);
					test1.pnPanel0.updateUI();
					test1.pnPanel0.revalidate();
					if (!test1.rotaterflag) {
						test1.totalrotation = test1.totalrotation + rotaten;
						test1.rotaterflag = false;
						test1.rotations = 0;
						break;
					}
					try {
						
						if (rotationint == rotaten) {
							TimeUnit.MILLISECONDS.sleep(Math.abs(Math.round((float)((((float)rotaten/(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow()-1, 2).toString())))/(float)rotaten)*1000))));
						} else {
							TimeUnit.MILLISECONDS.sleep(Math.abs(Math.round((float)((((float)5/(float)rotaten)*1000)))));
						}
					} catch(InterruptedException p) {
						System.out.println("ERROR!");
					}
					if (i == (rotaten + test1.totalrotation)) {
						test1.rotaterflag = false;
						test1.totalrotation = test1.totalrotation + rotaten;
						test1.lastque = rotaten;
						test1.rotations = 0;
						break;
					}
				}
			} else {
				
				for(int i=test1.totalrotation;i < (rotaten + test1.totalrotation) + 1;i++) {
					test1.pnPanel3.removeAll();
					rotation = i;
					if (i == (rotaten + test1.totalrotation)){
						bi = ImageIO.read(new File("CircleStagePNGSTOP.png"));
					}
					JPanel rotatepanel = new JPanel() {
						@Override
						public Dimension getPreferredSize() {
							return new Dimension(bi.getWidth(), bi.getHeight());
						}
						
						@Override
						protected void paintComponent(Graphics g) {
							super.paintComponent(g);
							Graphics2D g2 = (Graphics2D) g;
							g2.rotate(Math.toRadians(rotation), bi.getWidth() / 2, bi.getHeight() / 2);
							g2.drawImage(bi, 0, 0, null);
						}
					};
					
				
					rotatepanel.setBackground(test1.color);
					test1.pnPanel3.add(rotatepanel);
					test1.pnPanel0.setBackground(test1.color);
					test1.pnPanel0.updateUI();
					test1.pnPanel0.revalidate();
					if (!test1.rotaterflag) {
						test1.totalrotation = rotation;
						test1.rotaterflag = false;
						test1.rotations = 0;
						break;
					}
					try {
						if (rotationint == rotaten) {
							TimeUnit.MILLISECONDS.sleep(Math.abs(Math.round((float)((((float)rotaten/(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow()-1, 2).toString())))/(float)rotaten)*1000))));
						} else {
							TimeUnit.MILLISECONDS.sleep(Math.abs(Math.round((float)((((float)5/(float)rotaten)*1000)))));
						}
					} catch(InterruptedException p) {
						System.out.println("Interrupt!");
					} catch (NullPointerException n) {}
					if (i == (rotaten + test1.totalrotation)) {
						test1.totalrotation = test1.totalrotation + rotaten;
						test1.lastque = rotaten;
						test1.rotaterflag = false;
						test1.rotations = 0;
						break;
					}
				}
			}
			
		} catch(IOException p){
			p.printStackTrace();
		} catch(NullPointerException n){}
		
    }

}