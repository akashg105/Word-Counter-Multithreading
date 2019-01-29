

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ReadThread extends Thread{
	String ans;
	HashMap<String,Integer> result;
	int fin;
	ReadThread( String file,HashMap<String,Integer> result){
		this.ans=file;
		this.result = result;
	}
	
	//setters and getters
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
		//			FileInputStream miniFile = new FileInputStream(file);
		//			BufferedInputStream br = new BufferedInputStream(miniFile);
		//			DataInputStream ds = new DataInputStream(br);
		//			String output="";
		//			while (ds.available() != 0) {
		//				output+=(char)ds.read();
		//				output = output.toLowerCase();
		//			}
		//			ds.close();
					String[] ans = this.ans.split("\\s+");
					this.setFin(ans.length);
					for(int i=0;i<ans.length;++i) {				//populating individual thread map
						if(this.result.containsKey(ans[i])) {
							int val = this.result.get(ans[i]);
							this.result.put(ans[i], ++val);
						}
						else {
							this.result.put(ans[i], 1);
						}
					}
	}
	
}
