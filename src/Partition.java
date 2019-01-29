

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Partition {
	int seg;
	File file;
	Partition(File file, int seg){
		this.file=file;
		this.seg=seg;
	}
	
	// partition operation to segment the file
	public void splitOperation() {
		double fileSize= Math.ceil(this.file.length() / (float) seg);  //calculating the individual segment size
		byte[] buffer = new byte[(int) fileSize];
		String fileName = this.file.getName();
		try (FileInputStream Fis = new FileInputStream(this.file);
			BufferedInputStream br = new BufferedInputStream(Fis)){
			int byteamount = 0;
			int j=0;
			
			//problem of adding elements to array when size is not exactly fit.
			//if size is less, it wont be added
			
			
			while((byteamount=br.read(buffer))>0)
			 {
	            String filePartName = fileName.substring(0, fileName.length()-4);
				File newFile = new File(this.file.getParent(),filePartName+j+++".txt");  //generating segments
	            try(FileOutputStream fos = new FileOutputStream(newFile)){
	            	 fos.write(buffer, 0, byteamount);
	             }
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
