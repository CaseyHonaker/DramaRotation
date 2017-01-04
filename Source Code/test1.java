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
 
 public class test1 extends JFrame {
	static test1 thetest1;
	public static ArrayList<String> quelist;
	public BufferedImage bi;
	public GraphicsDevice gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public int width = this.gd.getDisplayMode().getWidth();
	public int height = this.gd.getDisplayMode().getHeight();	
	public int inputquenumberint;
	public static int lastque = 0;
	public static int totalrotation = 0;
	public static int release;
	public static int port;
	public static int decorated = 1;
	public static int rotationint;
	public static JFrame mainFrame;
	public javax.swing.JMenu menu;
	public JMenuBar menubar;
	public static JPanel pnPanel0;
	public static JPanel pnPanel1;
	public static JPanel pnPanel3;
	public static JTable tbJquetable;
	public static JTable jqueTable;
	public static JTable quemodel;
	public static int quelabel = 0;
	public static Object rotationobject;
	public static Object[] queadd;
	public java.awt.Panel scrollPane;
	public static String rotationstring;
	public static Color color = Color.WHITE;
	public static boolean rotaterflag = true;
	public String path;
	public static GridBagConstraints localGridBagConstraints1;
	public static String ipaddr;
	public static PrintWriter socketout;
	public static Socket echoSocket;
	public static int degree;
	public static int degreepersecond;
	public static Thread rotationthread =  new Thread();
	public static rotater rotater;
	public static int rotations = 0;
	public static ArrayList<String> preferences = new ArrayList<String>();
	public static createMenBar createMenBar;
	public static JPanel overpanel;;
	


   
	public static void main(String[] args) {
		int arguments = 0;
		File preferencefile = new File("./preferences.txt");
		boolean exists = preferencefile.exists();
		Scanner input = new Scanner(System.in);
		if(exists){	
			try {
					input = new Scanner(preferencefile);
					while (input.hasNextLine()) {
						String line = input.nextLine();
						preferences.add(line);
					}
					Object[] ipport = ((String)preferences.get(0)).split(":");
					ipaddr = ipport[0].toString();
					port = Integer.parseInt(ipport[1].toString());
			} catch (FileNotFoundException e) {} catch (IndexOutOfBoundsException p){
				ipaddr = "10.0.0.1"; 
				port = 80;
			}
		} else{
			ipaddr = "10.0.0.1"; 
			port = 80;
			if(args.length == 2) {
				if(args[0].equals("-p")) {
					port = Integer.parseInt(args[1]);
				} else if(args[0].equals("-a")){
					ipaddr = args[1];
				}
			} else if(args.length == 4) {
				if(args[0].equals("-p")) {
					port = Integer.parseInt(args[1]);
					ipaddr = args[3];
				} else if(args[0].equals("-i")){
					ipaddr = args[1];
					port = Integer.parseInt(args[3]);
				}
			} else if (!(args.length == 0)) {
				System.out.println("Invalid System Arguments");
				arguments = 1;
			}
		}
		if(arguments == 0){
			try{
				echoSocket = new Socket(ipaddr, port);
				socketout = new PrintWriter(echoSocket.getOutputStream(), true);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + ipaddr);
			} 
		}
		
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
		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException localClassNotFoundException) {}catch (InstantiationException localInstantiationException) {}catch (IllegalAccessException localIllegalAccessException) {}catch (javax.swing.UnsupportedLookAndFeelException localUnsupportedLookAndFeelException) {}
		*/
		thetest1 = new test1();
	}
   
 
 
   public test1() 	{
	
	//frame definition and layoutmanager definition
	JFrame mainFrame = new JFrame("Que thingy");
	pnPanel0 = new JPanel();
	GridBagLayout localGridBagLayout1 = new GridBagLayout();
	GridBagConstraints localGridBagConstraints1 = new GridBagConstraints();
	pnPanel0.setLayout(localGridBagLayout1);
	pnPanel3 = new JPanel();
	pnPanel3.setLayout(localGridBagLayout1);
     
	//read input from que file
	queinput localqueinput = new queinput();
	quelist = localqueinput.quereporter();
	this.path = ((String)quelist.get(0));
	quelist.remove(0);
    
	 
	 
	 
	 
	 // create image for display
     try {
		final BufferedImage localBufferedImage = ImageIO.read(new File("CircleStagePNG.png"));
		
		
		JPanel pnPanel1 = new JPanel()   {
			public Dimension getPreferredSize() {
				return new Dimension(localBufferedImage.getWidth(), localBufferedImage.getHeight());
			}
         
			protected void paintComponent(Graphics paramAnonymousGraphics) {
				super.paintComponent(paramAnonymousGraphics);
				Graphics2D localGraphics2D = (Graphics2D)paramAnonymousGraphics;
				localGraphics2D.rotate(0.0D, localBufferedImage.getWidth() / 2, localBufferedImage.getHeight() / 2);
				localGraphics2D.drawImage(localBufferedImage, 0, 0, null);
			}
         
		};
		
		//set up graphical properties for image
		GridBagLayout localGridBagLayout2 = new GridBagLayout();
		pnPanel1.setBackground(color);
		pnPanel3.add(pnPanel1);
		pnPanel3.setLayout(localGridBagLayout2);
		localGridBagConstraints1.gridx = 2;
		localGridBagConstraints1.gridy = 0;
		localGridBagConstraints1.gridwidth = 1;
		localGridBagConstraints1.gridheight = 2;
		localGridBagConstraints1.fill = GridBagConstraints.BOTH;
		localGridBagConstraints1.weightx = 1;
		localGridBagConstraints1.weighty = 1;
		localGridBagConstraints1.anchor = GridBagConstraints.NORTHEAST;
		localGridBagLayout1.setConstraints(pnPanel3, localGridBagConstraints1); 
		pnPanel0.add(pnPanel3);
       
 
		//set up table contents
		String[] arrayOfString = { "Que", "rotation", "speed/time"};
		Object[][] arrayOfObject = { { "0", "0", "0" } };
		DefaultTableModel quemodel = new DefaultTableModel(arrayOfObject, arrayOfString);
		JTable tbJquetable = new JTable(quemodel);
		quemodel.removeRow(0);
       
 
 
 
 
		//add keylisteners for table
		InputMap localInputMap1 = tbJquetable.getInputMap(0);
		ActionMap localActionMap1 = tbJquetable.getActionMap();
		localInputMap1.put(javax.swing.KeyStroke.getKeyStroke(32, 0), "space");
		localActionMap1.put("space", new test1.keyaction("space", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
       
 
		InputMap localInputMap2 = tbJquetable.getInputMap(0);
		ActionMap localActionMap2 = tbJquetable.getActionMap();
		localInputMap2.put(javax.swing.KeyStroke.getKeyStroke("released SPACE"), "released");
		localActionMap2.put("released", new test1.keyaction("released", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
	   
		InputMap localInputMap3 = tbJquetable.getInputMap(0);
		ActionMap localActionMap3 = tbJquetable.getActionMap();
		localInputMap3.put(javax.swing.KeyStroke.getKeyStroke("control S"), "controlS");
		localActionMap3.put("controlS", new test1.keyaction("controlS", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
       
	    InputMap localInputMap4 = tbJquetable.getInputMap(0);
		ActionMap localActionMap4 = tbJquetable.getActionMap();
		localInputMap4.put(javax.swing.KeyStroke.getKeyStroke("control shift S"), "controlshiftS");
		localActionMap4.put("controlshiftS", new test1.keyaction("controlshiftS", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
       
	    InputMap localInputMap5 = tbJquetable.getInputMap(0);
		ActionMap localActionMap5 = tbJquetable.getActionMap();
		localInputMap5.put(javax.swing.KeyStroke.getKeyStroke("control R"), "controlR");
		localActionMap5.put("controlR", new test1.keyaction("controlR", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
       
	    InputMap localInputMap6 = tbJquetable.getInputMap(0);
		ActionMap localActionMap6 = tbJquetable.getActionMap();
		localInputMap6.put(javax.swing.KeyStroke.getKeyStroke("control M"), "controlM");
		localActionMap6.put("controlM", new test1.keyaction("controlM", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
       
	   
		InputMap localInputMap7 = tbJquetable.getInputMap(0);
		ActionMap localActionMap7 = tbJquetable.getActionMap();
		localInputMap7.put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
		localActionMap7.put("escape", new test1.keyaction("escape", tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame));
       
 
 
 
		//set table attributes
		tbJquetable.setColumnSelectionAllowed(false);
		tbJquetable.setRowSelectionAllowed(true);
		tbJquetable.setDefaultRenderer(Object.class, new test1.NoBorderTableCellRenderer());
		tbJquetable.setDefaultEditor(Object.class, null);
       
		tbJquetable.getColumnModel().getColumn(0).setPreferredWidth(this.width / 3);
		tbJquetable.getColumnModel().getColumn(1).setPreferredWidth(this.width / 3);
		tbJquetable.getColumnModel().getColumn(2).setPreferredWidth(this.width / 3);
		tbJquetable.setSelectionMode(0);
	
	
	
	
	
		//add data to table
		for (int i = 0; i < quelist.size(); i++) {
			queadd = ((String)quelist.get(i)).split(":");
			addRowToJTable(tbJquetable, (Object[])queadd);
		}
		
		//set up table display attributes
		JScrollPane localJScrollPane = new JScrollPane(tbJquetable);
		localGridBagConstraints1.gridx = 0;
		localGridBagConstraints1.gridy = 2;
		localGridBagConstraints1.gridwidth = 3;
		localGridBagConstraints1.gridheight = 1;
		localGridBagConstraints1.fill = GridBagConstraints.BOTH;
		localGridBagConstraints1.weightx = 1;
		localGridBagConstraints1.weighty = 1;
		localGridBagConstraints1.anchor = GridBagConstraints.NORTH;
		localGridBagLayout1.setConstraints(localJScrollPane, localGridBagConstraints1);
		pnPanel0.add(localJScrollPane);
		
		
		// add menubar
		createMenBar = new createMenBar();
		menubar = createMenBar.createMenuBar(quemodel, tbJquetable, path, quelist, totalrotation, pnPanel0, pnPanel3, pnPanel1, mainFrame, lastque, localJScrollPane);
		
		// set frame attributes
		mainFrame.setDefaultCloseOperation(3);
		mainFrame.setJMenuBar(menubar);
		mainFrame.setExtendedState(6);
		
		
		//uncomment to max screne
		///mainFrame.setUndecorated(true);

		
		pnPanel0.setBackground(Color.RED);
		mainFrame.setContentPane(pnPanel0);
		mainFrame.getContentPane().setBackground(Color.YELLOW);
		mainFrame.pack();
		mainFrame.setVisible(true);
		tbJquetable.requestFocus();
     } catch (IOException localIOException) {}
   }
   
   
   //method to add data to table
   private static void addRowToJTable(JTable paramJTable, Object[] paramArrayOfObject) { JTable tbJquetable = paramJTable;
     DefaultTableModel localDefaultTableModel = (DefaultTableModel)tbJquetable.getModel();
     localDefaultTableModel.addRow(paramArrayOfObject);
     tbJquetable.updateUI();
   }
   
   
   
   
   //keyaction listener
   class keyaction extends javax.swing.AbstractAction {
     private String cmd;
     private JTable table;
     private JPanel panel;
     private JPanel overpanel;
     private JPanel panelactual;
     private JPanel rotatepanel;
     private BufferedImage bi;
     private JFrame frame;
     
     public keyaction(String paramString, JTable paramJTable, JPanel paramJPanel1, JPanel paramJPanel2, JPanel paramJPanel3, JFrame paramJFrame) {
       this.cmd = paramString;
       this.table = paramJTable;
       this.frame = paramJFrame;
       this.panel = paramJPanel2;
       this.overpanel = paramJPanel1;
       this.panelactual = paramJPanel3;
     }
     
 
 
 
	public void actionPerformed(ActionEvent paramActionEvent)	{
		if ((this.cmd.equalsIgnoreCase("space")) && (test1.release == 0) && (this.table.getSelectedRow() != -1)) {
			release = 1;
			rotationobject = table.getModel().getValueAt(table.getSelectedRow(), 1);
			rotationstring = rotationobject.toString();
			if(rotaterflag){
				rotaterflag = false;
				while(rotationthread.isAlive()) {}		
			}
			if(rotationstring.equals("RESET")) {
				if (this.table.getSelectedRow() == test1.quelist.size() - 1) {
				   this.table.scrollRectToVisible(this.table.getCellRect(this.table.getSelectedRow(), 0, true));
				   this.table.setRowSelectionInterval(0, this.table.getSelectedRow());
				 } else {
				   this.table.scrollRectToVisible(this.table.getCellRect(this.table.getSelectedRow() + 2, 0, true));
				   this.table.setRowSelectionInterval(0, this.table.getSelectedRow() + 1);
				}
				if (test1.totalrotation >= 180) {
					rotateboard(360 - totalrotation, this.table, this.overpanel, this.panel, this.panelactual, this.frame, quelabel);
				} else if (test1.totalrotation < 180 && test1.totalrotation > -180) {
					rotateboard(-(totalrotation), this.table, this.overpanel, this.panel, this.panelactual, this.frame, quelabel);
				} else {
					rotateboard(-360 - (totalrotation), this.table, this.overpanel, this.panel, this.panelactual, this.frame, quelabel);
				}
			} else {
				if (this.table.getSelectedRow() == test1.quelist.size() - 1) {
				   this.table.scrollRectToVisible(this.table.getCellRect(this.table.getSelectedRow(), 0, true));
				   this.table.setRowSelectionInterval(0, this.table.getSelectedRow());
				 } else {
				   this.table.scrollRectToVisible(this.table.getCellRect(this.table.getSelectedRow() + 2, 0, true));
				   this.table.setRowSelectionInterval(0, this.table.getSelectedRow() + 1);
				 }
				 int i = Integer.parseInt(test1.this.rotationstring);
				 rotateboard(i, this.table, this.overpanel, this.panel, this.panelactual, this.frame, quelabel);
		 
			}
		} else if ((this.cmd.equalsIgnoreCase("released")) && (test1.release == 1)) {
			 test1.release = 0;
		} else if (this.cmd.equalsIgnoreCase("escape")){
			if (rotationthread.isAlive()) {
				rotaterflag = false;
				sendData(0, 0);
				
			}
		} else if (this.cmd.equalsIgnoreCase("controlS")){
			createMenBar.oversave = null;
			createMenBar.savefile(createMenBar.oversave, table, quelist, path);

		} else if (this.cmd.equalsIgnoreCase("controlshiftS")){
			createMenBar.ShowSaveFileDialog(table, quelist, path);

		} else if (this.cmd.equalsIgnoreCase("controlR")){
				if(rotaterflag){
					rotaterflag = false;
					while(rotationthread.isAlive()) {}		
				}
				if (totalrotation >= 180) {
					rotateboard(360 - (totalrotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 45);
				} else if (totalrotation < 180 && totalrotation > -180) {
					rotateboard(-(totalrotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 45);
				} else {
					rotateboard(-360 - (totalrotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 45);
				}

		} else if (this.cmd.equalsIgnoreCase("controlM")){
			JFrame frame = new JFrame("que rotate thingy");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception p) {
                   p.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JTextField rotatespeed = new JTextField(20);
                rotatespeed.setFont(Font.getFont(Font.SANS_SERIF));
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField rotatenumber = new JTextField(20);
                JButton buttonrotate = new JButton("rotate");
                inputpanel.add(rotatenumber);
                inputpanel.add(rotatespeed);
                inputpanel.add(buttonrotate);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
				buttonrotate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String changerotationstring = rotatenumber.getText();
						int changerotationint = Integer.parseInt(changerotationstring);
						rotateboard(changerotationint, tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, Integer.parseInt(rotatespeed.getText()));
						frame.dispose();
					}
				});
		
		}
     }
   }
   
   
   //removes boarder of table
   class NoBorderTableCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
     private static final long serialVersionUID = 1L;
     
     NoBorderTableCellRenderer() {}
     
     public java.awt.Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2) {
       java.awt.Component localComponent = super.getTableCellRendererComponent(paramJTable, paramObject, paramBoolean1, false, paramInt1, paramInt2);
       return localComponent;
     }
   }
   
   
   //rotates image and then sends data to arduino
	public static void rotateboard(int rotaten, JTable table, JPanel pnPanel0, JPanel pnPanel3, JPanel pnPanel1, JFrame frame, int quelabel) {
		//gets and send data
		try{
			try{
				rotationobject = table.getModel().getValueAt(table.getSelectedRow()-1, 1);
				rotationstring = rotationobject.toString();
			} catch (ArrayIndexOutOfBoundsException a) {
				rotationstring = "String";
			}
			try {
				int rotationint = Integer.parseInt(rotationstring);
			} catch (NumberFormatException e){
				int rotationint = rotaten;
			}
			if (rotaten != 0){	
				if (rotationint == rotaten) {
					if (quelabel == 0){
						sendData(rotaten, Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow()-1, 2).toString()));
					} else{
						sendData(rotaten, quelabel);
					}
				} else {
					sendData(rotaten, 45);
				}
			} else {
				sendData(0, 0);
			}
		} catch (NullPointerException b){}
		//rotates image
		rotater QueRotator = new rotater(rotaten,table,pnPanel0,pnPanel3,pnPanel1,frame,quelabel);
		rotaterflag = true;
		rotationthread = new Thread(QueRotator);
		rotationthread.start();
	} 
	
	
	
	//sets total rotation for other public classes
	public static void settotalrotation(int paramInt) { 
		test1.totalrotation = paramInt; 
	}
   
	

	//changes background will sometimes report nullpointerexception error however has no effect on code
	public static void nightorday(String paramString, JPanel paramJPanel, JScrollPane paramJScrollPane) {
		if (paramString == "day") {
			pnPanel0.setBackground(Color.WHITE);
			color = Color.WHITE;
			pnPanel0.updateUI();
			pnPanel0.revalidate();
			paramJScrollPane.getViewport().setBackground(Color.WHITE);
			rotateboard(0, tbJquetable, pnPanel0, pnPanel3, paramJPanel, mainFrame, quelabel);
		} else if (paramString == "night") {
			pnPanel0.setBackground(Color.GRAY);
			color = Color.GRAY;
			pnPanel0.updateUI();
			pnPanel0.revalidate();
			paramJScrollPane.getViewport().setBackground(Color.GRAY);
			rotateboard(0, tbJquetable, pnPanel0, pnPanel3, paramJPanel, mainFrame, quelabel);
		}
   }
   
   
   
	//sends data to arduino
	public static void sendData(int paramInt, int timeInt) {
			socketout.println(paramInt);
			socketout.println(timeInt);   
	}
}
