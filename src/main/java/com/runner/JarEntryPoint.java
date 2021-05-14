package main.java.com.runner;

public class JarEntryPoint {

	/*
	 * When using the "create-jar" maven profile to create a self-contained .jar
	 * file with everything needed to execute your tests, this file will be used as
	 * the entry point for the jar. It is called out in the maven profile as the
	 * "main class", and will be where execution of the .jar starts
	 */
	public static void main(String[] args) {
		String[] testSuiteFile = { "testng.xml" };
		org.testng.TestNG.main(testSuiteFile);
	}

}
