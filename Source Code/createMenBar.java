import java.awt.*;        
import java.awt.event.*; 
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.Graphics;
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
import javax.swing.UIManager.*;


public class createMenBar{
	public static String oversave;
	public static int rotation = 0;
	public static JTable tbJquetable;
	public static int lastquerot;
	public static int querotation;
	public static int changerotationint;
	public static String changerotationstring;
	public JMenuBar menuBar;
	public JMenu menu, submenu;
	public JMenuItem save;
	public JMenuItem saveas;
	public JMenuItem newquefile;
	public JMenuItem open;
	public JMenuItem menuItem;
	public JMenuItem addque;
	public JMenuItem delque;
	public JMenuItem resetboard;
	public JMenuItem lastque;
	public JMenuItem quetogo;
	public JMenuItem darkmode;
	public JMenuItem lightmode;
	public JMenuItem rotateboard;
	public JMenuItem stopboard;
	public JMenuItem exit;
	public JPanel overpanel;
	public String rotationstring;
	public Object rotationobject;
	public JRadioButtonMenuItem rbMenuItem;
	public JCheckBoxMenuItem cbMenuItem;
	public ArrayList<String> ques = new ArrayList<String>();
	public static ArrayList<String> quelist;
	public static JFrame mainFrame;
	public static test1 test1;
	public static int quelabel;
	public static JScrollPane scpJquetable;
	
	public JMenuBar createMenuBar(DefaultTableModel quemodel, JTable tbJquetables, String path, ArrayList<String> quelists, int rotations, JPanel pnPanel0, JPanel pnPanel3, JPanel pnPanel1, JFrame mainFrames, int lastquerots, JScrollPane scpJquetables) {
		//Makes  the menuBar look a lot better but there are some inexcusable errors that need to be worked out
		/*
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		} */
		
		quelabel = 0;
		mainFrame = mainFrames;
		JPanel overpanel = pnPanel0;
		JScrollPane scpJquetable = scpJquetables;
		int rotation = rotations;
		int lastquerot = lastquerots;
		ArrayList<String> quelist = quelists;
		JTable tbJquetable = tbJquetables;

        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "File");
        menuBar.add(menu);
 
 
 
		newquefile = new JMenuItem(new AbstractAction("new") {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("que add thingy");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception r) {
					// If Nimbus is not available, you can set the GUI to another look and feel.
				} 
				/*
				try {
				   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception p) {
				   p.printStackTrace();
				}
				*/
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setOpaque(true);
				JLabel label = new JLabel("Do you want to save?");
				panel.add(label);
				
				JButton buttonyes = new JButton("Yes");
				
				JButton buttonno = new JButton("No");
				
				panel.add(buttonyes);
				panel.add(buttonno);
				frame.getContentPane().add(BorderLayout.CENTER, panel);
				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				frame.setResizable(false);
				buttonyes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (buttonyes.isEnabled()) {
							frame.setVisible(false);
							frame.dispose();
							savefile(null, tbJquetable, quelist, path);
							quemodel.setRowCount(0);
						}
					}
				});
				buttonno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (buttonno.isEnabled()) {
							frame.setVisible(false);
							frame.dispose();
						}
					}
				});
            }
		});
		
		open = new JMenuItem(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				quemodel.setRowCount(0);

				try {
					Scanner input = new Scanner(System.in);
					JFileChooser chooser = new JFileChooser();
					chooser.setSelectedFile(new File(""));
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("choosertitle");
					chooser.setFileFilter(new FileFilter() {

						public String getDescription() {
							return "text files (*.txt)";
						}	
						public boolean accept(File f) {
							if (f.isDirectory()) {
								return true;
							} else {
								String filename = f.getName().toLowerCase();
								return filename.endsWith(".txt");
							}
						}
					});
					
					chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
					  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
					} else {
					  System.out.println("No Selection ");
					}
					input = new Scanner(chooser.getSelectedFile());
					while (input.hasNextLine()) {
						String line = input.nextLine();
						ques.add(line);
					}
					input.close();
					for (int i = 0; i < ques.size(); i++) {
						Object[] values = ques.get(i).split(":");
						addRowToJTable(tbJquetable, values);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} 
			}
		});
    	
		
        save = new JMenuItem(new AbstractAction("save") {
			public void actionPerformed(ActionEvent e) {
				oversave = null;
				savefile(oversave, tbJquetable, quelist, path);
			}
		});
		exit = new JMenuItem(new AbstractAction("exit") {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
       
		
		saveas = new JMenuItem(new AbstractAction("save as") {
			public void actionPerformed(ActionEvent arg0) {
				ShowSaveFileDialog(tbJquetable, quelist, path);
			}
			
		});
		
		
		
		menu.add(newquefile);
		menu.add(open);
		menu.add(save);
        menu.add(saveas);
		menu.addSeparator();
		menu.add(exit);
		
        //Build second menu in the menu bar.
        menu = new JMenu("Edit");
		stopboard = new JMenuItem(new AbstractAction("Stop") { 
			public void actionPerformed(ActionEvent e) {
				test1.rotaterflag = false;
			}
		});
		resetboard = new JMenuItem(new AbstractAction("Reset") { 
			public void actionPerformed(ActionEvent e) {
				if(test1.rotaterflag){
					test1.rotaterflag = false;
					while(test1.rotationthread.isAlive()) {}		
				}
				if (test1.totalrotation >= 180) {
					test1.rotateboard(360 - (test1.totalrotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 45);
					rotationtozero(rotation);
				} else if (test1.totalrotation < 180 && test1.totalrotation > -180) {
					test1.rotateboard(-(test1.totalrotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 45);
					rotationtozero(rotation);
				} else {
					test1.rotateboard(-360 - (test1.totalrotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 45);
					rotationtozero(rotation);
				}
	
			}
		});
		rotateboard = new JMenuItem(new AbstractAction("Rotate") { 
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("que rotate thingy");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception p) {
					// If Nimbus is not available, you can set the GUI to another look and feel.
				} 
				/*
                try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception p) {
                   p.printStackTrace();
                }
				*/
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
				JPanel inputpanel = new JPanel();
				inputpanel.setLayout(new FlowLayout());
                JTextField rotatespeed = new JTextField(20);
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
						int changerotationint = Integer.parseInt(rotatenumber.getText());
						test1.rotateboard(changerotationint, tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, Integer.parseInt(rotatespeed.getText()));
						frame.dispose();
					}
				});
			}
		});
		quetogo = new JMenuItem(new AbstractAction("Change Que") {
			public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("que change thingy");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception w) {
					// If Nimbus is not available, you can set the GUI to another look and feel.
				} 
				/*
                try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception p) {
                   p.printStackTrace();
                }
				*/
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JTextArea textArea = new JTextArea(15, 50);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField inputquenumber = new JTextField(20);
                JButton buttonqueadd = new JButton("Go");
                inputpanel.add(inputquenumber);
                inputpanel.add(buttonqueadd);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
				buttonqueadd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int quetogoint = Integer.parseInt(inputquenumber.getText());
						if (quetogoint <= test1.quelist.size()) {
							if (quetogoint == 1) { 
								test1.rotateboard(-(rotation), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, quelabel);
							} else {
								querotation = 0;					
								for (int i = 0; i < quetogoint-1; i++) {
									rotationobject = tbJquetable.getModel().getValueAt(i, 1);
									if(rotationobject.equals("RESET")){
										if (test1.totalrotation >= 180) {
											querotation = (360 - (test1.totalrotation));
										} else if (test1.totalrotation < 180 && test1.totalrotation > -180) {
											querotation = (-(test1.totalrotation));
										} else {
											querotation = (-360 -(test1.totalrotation));
										}
									} else {
										querotation = querotation + Integer.parseInt(rotationobject.toString());
									}
								}
								querotation = querotation - test1.totalrotation; 

								test1.rotateboard(querotation, tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, 1);
							}
							tbJquetable.setRowSelectionInterval(0, quetogoint-1);	
							tbJquetable.requestFocus();
							frame.dispose();
						}
					}
				});
			}
		});
		
		
		
		lastque = new JMenuItem(new AbstractAction("Go Back One Que") {
			public void actionPerformed(ActionEvent e) {
				if (tbJquetable.getSelectedRow() != 0) {
					rotationobject = tbJquetable.getModel().getValueAt(tbJquetable.getSelectedRow()-1, 1);	
					String timestring = (tbJquetable.getModel().getValueAt(tbJquetable.getSelectedRow()-1, 2)).toString();	
					rotationstring = rotationobject.toString();
					if (rotationstring.equals("RESET")){
						test1.rotateboard(-(test1.lastque), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, Integer.parseInt(timestring));
					} else{
						test1.rotateboard(-(Integer.parseInt(rotationstring)), tbJquetable, pnPanel0, pnPanel3, pnPanel1, mainFrame, Integer.parseInt(timestring));
					}
					tbJquetable.setRowSelectionInterval(0, tbJquetable.getSelectedRow()-1);
					tbJquetable.scrollRectToVisible(tbJquetable.getCellRect(tbJquetable.getSelectedRow(),0, true));
				}
			
			}
		});
		
      	menu.add(stopboard);
		menu.add(resetboard);
		menu.add(rotateboard);
		menu.add(quetogo);
		menu.add(lastque);
		
		
		menu.addSeparator();
		addque = new JMenuItem(new AbstractAction("Add Que") {
			public void actionPerformed(ActionEvent e) {
				addframe(quemodel, tbJquetable, quelist);
            }
		});
		
		
		delque = new JMenuItem(new AbstractAction("Delte Que") {
			public void actionPerformed(ActionEvent e) {
				if (tbJquetable.getSelectedRow() != -1) {
					quelist.trimToSize();
					for (int i = Integer.parseInt((tbJquetable.getModel().getValueAt(tbJquetable.getSelectedRow(), 0)).toString()); i < quelist.size(); i++) {
						tbJquetable.setValueAt(i,i,0);
					}
					quemodel.removeRow(tbJquetable.getSelectedRow());
					quelist.remove(tbJquetable.getSelectedRow()+1);
				}
            }
		});
		
		
		menu.add(addque);
        menu.add(delque);
		menuBar.add(menu);
		
		menu = new JMenu("View");
		
		darkmode = new JMenuItem(new AbstractAction("Night Mode") {
			public void actionPerformed(ActionEvent e) {
				nightordaymenbar("night", pnPanel1, scpJquetable);
            }
		});
		lightmode = new JMenuItem(new AbstractAction("Light Mode") {
			public void actionPerformed(ActionEvent e) {
				nightordaymenbar("day", pnPanel1, scpJquetable);
            }
		});

		menu.add(darkmode);
        menu.add(lightmode);
		menuBar.add(menu);
		
		
		
		
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Edits table");
 
        return menuBar;
	}
	public void ShowSaveFileDialog(JTable tbJquetable, ArrayList<String> quelist, String path) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "text files (*.txt)";
			}	
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String filename = f.getName().toLowerCase();
					return filename.endsWith(".txt");
				}
			}
		});
		int userSelection = fileChooser.showSaveDialog(fileChooser);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			savefile(fileToSave.getAbsolutePath(), tbJquetable, quelist, path);
			path = fileToSave.getAbsolutePath();
		}
	}
	public void savefile(String oversave, JTable tbJquetable, ArrayList<String> quelist, String path) {
		try {
			if (oversave == null) {
				
				PrintWriter out = new PrintWriter(path);
				for (int i = 0; i < quelist.size(); i++) {
					if (i == quelist.size()-1){
						out.println(tbJquetable.getModel().getValueAt(i, 0)+ ":" +tbJquetable.getModel().getValueAt(i, 1) + ":" +tbJquetable.getModel().getValueAt(i, 2));
					} else {
						out.println(tbJquetable.getModel().getValueAt(i, 0)+ ":" +tbJquetable.getModel().getValueAt(i, 1) + ":" +tbJquetable.getModel().getValueAt(i, 2));
					}
				}
				out.flush();
				out.close();
			} else {
				PrintWriter out = new PrintWriter(oversave);
				for (int i = 0; i < quelist.size(); i++) {
					if (i == quelist.size()-1){
						out.println(tbJquetable.getModel().getValueAt(i, 0)+ ":" +tbJquetable.getModel().getValueAt(i, 1) + ":" +tbJquetable.getModel().getValueAt(i, 2));
					} else {
						out.println(tbJquetable.getModel().getValueAt(i, 0)+ ":" +tbJquetable.getModel().getValueAt(i, 1) + ":" +tbJquetable.getModel().getValueAt(i, 2));
					}
				}
				out.flush();
				out.close();
			}
		} catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	private static void addRowToJTable( JTable theTable, Object[] rowString) {
			JTable tbl = (JTable) theTable;
			DefaultTableModel model = (DefaultTableModel) tbl.getModel();
			model.addRow(rowString);
			tbl.updateUI();
	}
	public void rotationtozero(int rotation){} 
	
	public static void nightordaymenbar(String time, JPanel pnPanel1, JScrollPane scpJquetable) {
		if (time == "day") {
			test1.nightorday("day", pnPanel1, scpJquetable);
		} else if (time == "night"){
			test1.nightorday("night", pnPanel1, scpJquetable);
		}
	}
	
	public static void addframe(DefaultTableModel quemodel, JTable tbJquetable, ArrayList<String> quelist) {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()	{
                JFrame frame = new JFrame("que add thingy");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception q) {
					// If Nimbus is not available, you can set the GUI to another look and feel.
				} 
				/*
                try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                   e.printStackTrace();
                }
				*/
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JTextArea textArea = new JTextArea(15, 50);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField inputquenumber = new JTextField(20);
				JTextField inputquerotation = new JTextField(20);
				JTextField timeinput = new JTextField(20);
                JButton buttonqueadd = new JButton("Submit");
                inputpanel.add(inputquenumber);
                inputpanel.add(inputquerotation);
                inputpanel.add(timeinput);
                inputpanel.add(buttonqueadd);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
				buttonqueadd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (buttonqueadd.isEnabled()) {
							String inputquenumberstring = inputquenumber.getText();
							int inputquenumberint = Integer.parseInt(inputquenumberstring);
							String inputquerotationstring = inputquerotation.getText();
							String timeinputstring = timeinput.getText();
							int timeinputint = Integer.parseInt(inputquerotationstring);
							Object[] queadd = {inputquenumberstring, inputquerotationstring, timeinputstring};
							quemodel.insertRow(inputquenumberint-1, queadd);
							frame.dispose();
							quelist.add(inputquenumberstring + ":" + inputquerotationstring +":"+ timeinputstring);
							quelist.trimToSize();
							for (int i = Integer.parseInt((tbJquetable.getModel().getValueAt(inputquenumberint, 0)).toString())-1; i < quelist.size()+1; i++) {
								if (i == 0) {
									tbJquetable.setValueAt(i,0,0);
								}else {
									tbJquetable.setValueAt(i,i-1,0);
								}
							}
						}
					}
				});
            }
        });
    }
	
}