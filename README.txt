This is the readme for the que_jar_6_3.jar 

TABLE OF CONTENTS:
	-FEATURES
	-SYSTEM REQUIREMENTS
	-HOW TO USE
		-Key Shortcuts
	-KNOWN BUGS

FEATURES:
-Can send data across TCP to a listening host
-Rotates image at current speed of the stage (see bugs)
-Can rotate stage with out need of creating a new que (for rehersals)
-Can go to Certain que number and will calculate the position it would have been in if all of the previous ques wre run
-Can rotate the stage back to zero and will calculate the quickest route i.e. if the stage has rotaten 181 degrees with fill finish the turn but if it has rotaten on 179 clockwise than it will counterclockwise
-Can be switiched to night mode to darken screen (see bugs)
-Can remove boarders of screen (commented out of code for debugging purposes)
-Most commands have key shortcuts associated with them for a full list look bellow

SYSTEM REQUIREMENTS:
This jar has been tested with java SE Runtime Enviroment build 1.8.0_91-b15
This jar should be compatable with 1.8.0_x or 1.7.x 
The most updated version of java can be found at:
https://java.com/en/
This jar does not require but is recomend to have an active connection to a target machine i.e. an Arduino

The Source code can be found in the folder named "Source Code"
CircleStagePNG.png must be in the same folder as the que_jar_6_3.jar to successfully run the .jar
CircleStagePNGSTOP.png must be in the same folder as the que_jar_6_3.jar to successfully run the .jar


HOW TO USE:
To use from cmd enter command:
java -jar que_jar_6_3.jar <-a ipaddress> <-p port>

To use from gui just double click the que_jar_6_3.jar file
If used from gui or no ip address is assigned the default ip is 10.0.0.1 on port 80.
This can easily be changed and can be set in the preference.txt 
To see preference.txt example rename preference1.txt to preference.txt
The preference file will overide any command line arguments

Once launched you must select a file to load ques
A test que file was included with this download and was named QueTestFile.txt
Que files are in the format:

que number:rotation in degrees:speed

once slected to run a que select one from the table and hit the spacebar


SHORTCUTS:
-Escape: stops current que
-Control S: Saves file
-Control Shift S: Save As command
-Control M: Manuel Rotates stage
-Control R: Reset Stage


KNOWN BUGS:
-A major bug is that when the second thread is rotating it will output multiple errors. This is from the implementation of Nimbus but has no impact on the networking function and thus is not a major issue.
-If you try to open a file from the "file" menubar and then proceed to subsequently open another the first file will "ghost" and be visible but not selectable
-If an invalid IP was entered than the ques will take a bit longer to run due to the code attempting to connect to a host that is not present
-More of an inconvience the Night mode will not change the whole screen to gray only certain panels

If any more bugs are found then please report  them to techsupport@rekanoh.com

TWFkZSBieSBNaWNoYWVsIEhvbmFrZXI=
