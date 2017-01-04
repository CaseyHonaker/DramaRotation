This is the readme for the que_jar_4.jar 

TABLE OF CONTENTS:
	-SYSTEM REQUIREMENTS
	-HOW TO USE
	-KNOWN BUGS

SYSTEM REQUIREMENTS:
This jar has been tested with java SE Runtime Enviroment build 1.8.0_91-b15
This jar should be compatable with 1.8.0_x or 1.7.x 
The most updated version of java can be found at:
https://java.com/en/
This jar does not require but is recomend to have an active connection to a target machine i.e. an Arduino

HOW TO USE:
To use from cmd enter command:
java -jar que_jar_4.jar <ip address>

To use from gui just double click the que_jar_4.jar file
If used from gui or no ip address is assigned the default ip is 10.0.0.1 
This can easily be changed and will be upon request

Once launched you must select a file to load ques
A test que file was included with this download and was named QueTestFile.txt
Que files are in the format:
que number:rotation in degrees:speed

once slected to run a que select one from the table and hit the spacebar



KNOWN BUGS:
-If you try to open a file from the "file" menubar and then proceed to subsequently open another the first file will "ghost" and be visible but not selectable
-When selecting night mode or day mode from the "view" screen a NullPointerException will occur. This has no effect on the program and is just an inconvience at this point in time
-If an invalid IP was entered than the ques will take a bit longer to run due to the code attempting to connect to a host that is not present
-More of an inconvience the Nightmode will not change the whole screen to gray only certain panels