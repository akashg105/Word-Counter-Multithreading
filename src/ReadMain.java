

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ReadMain {
	public static void main(String[] args) throws IOException, InterruptedException { 
		
		//System.out.println("Please enter the path of file");  
		//BufferedReader myPath = new BufferedReader(new InputStreamReader(System.in)); // Enter the path from user
		//String path = myPath.readLine();
		File file = new File("file.txt");
		System.out.println(file.length());
		BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in)); //Enter segments number from user
		System.out.println("Please enter the number of segments");
		String seg = myReader.readLine(); 
		Processor mainThread = new Processor(Integer.parseInt(seg), file,new HashMap<String,Integer>());
		mainThread.start();   //start main thread
		mainThread.join();
		System.out.println(mainThread.getResult());
	}
}
