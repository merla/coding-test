Name score OCC coding project

STEPS FOR EXECUTION:

Import the project as Maven project into any IDE like IntelliJ 
Run the mvn clean install (jar file is generated)
Run the main application class file with the input arguments as  "src/main/resources/OCC Take Home Coding names.txt"    "src/main/resources/OCC Take Home Coding names short.txt"
Junits are executed automatically, otherwise you could individually run the test file

Using command line
Execute the Jar file directly from command line using java -jar coding-test-1.0-SNAPSHOT.jar by passing the arguments like
java -jar target/coding-test-1.0-SNAPSHOT.jar <Input file path/name> (e.g.   ./src/main/resources/OCC Take Home Coding names short.txt)


Manifest
./pom.xml is the manifest of dependent libraries.
