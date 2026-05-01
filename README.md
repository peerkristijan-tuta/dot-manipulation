1. Execution instructions

Compile with javac, then run with java. The program outputs a dot cluster representing alphabetical keys on a keyboard with each corresponding dot either invisible or visible, but with all dots visible at the start of program execution. When the user inputs alphabets and clicks enter, the program will output a new dot cluster to replace the previous one with the corresponding dots for the inputed alphabets either appearing as a ' ' if the dot was previously visible or a '.' if not. At the forth program run, the program will display all outputs during the previous three runs before executing normally and erasing the output history. 

2. Program information

The converter method in the classes ExtravagantDisplay and Coordinator encode and decode alphabetical keys into and from numbers in the same way. Program I/O is executed by two instantiations of the IO thread class representing input and output respectively, and each instance runs a synchronized method called method which ensures that only one of the two can carry out operations at a time. ExtravagantDisplay is executed when the program detects that it is run for the third time. Output history is stored as the encoded numbers whilst the numbers are decoded to a specific dot location in its cluster.
