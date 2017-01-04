import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
public class queinput{

	public ArrayList<String> quereporter()    {
	ArrayList<String> ques = new ArrayList<String>();
	try {
		Scanner input = new Scanner(System.in);
		//System.out.print("Enter the file name with extention : ");
		//File file = new File(input.nextLine());

		
		
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		//chooser.setFileSelectionMode(JFileChooser.Files);
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
		
		//for debugging perposes
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		  System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
		  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
		} else {
		  System.out.println("No Selection ");
		  return(null);
		}
		
		
		ques.add(chooser.getSelectedFile().getAbsolutePath());
		input = new Scanner(chooser.getSelectedFile());
		while (input.hasNextLine()) {
			String line = input.nextLine();
			ques.add(line);
		}
		input.close();

	} catch (Exception ex) {
		ex.printStackTrace();
	} 

	return(ques);
	}
}