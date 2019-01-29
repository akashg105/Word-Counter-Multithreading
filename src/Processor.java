


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Processor extends Thread {
	int seg;
	File file;
	HashMap<String,Integer> result;
	int fin;

	// Constructor for the Segment	
	Processor(int seg, File file, HashMap<String,Integer> result){
		this.seg=seg;
		this.file=file;
		this.result=result;
	}
	
	//getters and setters
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	public HashMap<String, Integer> getResult() {
		return result;
	}
	public void setResult(HashMap<String, Integer> result) {
		this.result = result;
	}

	public void run() {
		
		try {
//			BufferedReader segments = new BufferedReader(new InputStreamReader(System.in)); 
//			System.out.println("enter segments");
//			String seg = segments.readLine();
//			String line="";
			int nlines=0;
			//File file = new File("D:\\eclipse\\Read\\file.txt");
			//System.out.println(file.length());
			DataInputStream in =new DataInputStream(new FileInputStream(file));
			
		//			FileInputStream fs = new FileInputStream(file);
		//			DataInputStream in = new DataInputStream(new BufferedInputStream(fs));
			int i = 0;
		    char c;
		    String[] stringList = new String[this.seg];
			int index = 0;
			ArrayList<HashMap<String,Integer>> ret = new ArrayList<HashMap<String,Integer>>();  //HashMap for main thread
			ReadThread[] threads= new ReadThread[this.seg];
		    
			double fileSize= Math.ceil(this.file.length() / (float) seg); 
			while((i = in.read())!=-1) {
		    	if(nlines==fileSize) {
		    		threads[index]=new ReadThread(stringList[index],new HashMap<String,Integer>());
					threads[index].start();
					this.setResult(threads[index].getResult());
					ret.add(threads[index].getResult());
					index++;
					nlines=0;
				}
				 
				 
		        // converts integer to character
		        c = (char)i;
		        stringList[index]+=c;
		        nlines++;
		       // System.out.println(i);
		        // prints
		        //System.out.println("Character read: "+c);
		         }
		    //
		    in.close();
		    System.out.println(nlines);
		    System.out.println(threads.length);
		    for(int j=0;j<seg;++j) {
				try {
					threads[j].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(i=0;i<ret.size();++i) {
				Iterator it = ret.get(i).entrySet().iterator();
				while (it.hasNext()) {
					 Map.Entry pair = (Map.Entry)it.next();
				     if(this.result.containsKey(pair.getKey())) {
				    	 int val = (Integer)pair.getValue();
				    	 this.result.put(pair.getKey().toString(), ++val);
				     }
				     else {
				    	 this.result.put(pair.getKey().toString(), 1);
				     }
				}
				
			}
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		//System.out.println(" DISBISTest: exception:" + e );
		}
		
		
		
		
		
		
		
//		Partition mySplit = new Partition(file,seg);     //partitioning the file into required segments
//		mySplit.splitOperation();
//		
//		
//		String fileName = this.file.getName();
//		ReadThread[] threads= new ReadThread[seg];
//		ArrayList<HashMap<String,Integer>> ret = new ArrayList<HashMap<String,Integer>>();  //HashMap for main thread
//		
//		// Main thread calling helper threads
//		for(int i=0;i<seg;++i) {
//			String filePartName = fileName.substring(0, fileName.length()-4);
//			File file = new File("D:\\eclipse\\Read\\"+filePartName+ i +".txt");
//			threads[i]=new ReadThread(file,new HashMap<String,Integer>());
//			threads[i].start();
//			this.setResult(threads[i].getResult());
//			ret.add(threads[i].getResult());
//			
//		}
//		
		
		
		// Consolidating individual frequency word count
	
	}
		
		
}
	
