package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Pattern;  
  
public class testjiansuo {  
	/** 
     * @param args 
     */  
    public void running(File paths) {
    	FileInputStream fis=null;  
        InputStreamReader isr=null;  
        BufferedReader br=null;  
        FileOutputStream fos=null;  
        OutputStreamWriter osw=null;  
        BufferedWriter bw=null;  
          
        FileReader fr=null;  
        FileWriter fw=null;
        
    	String refill1 = "public class test{";
    	String refill2 = "public static void main(String[] args){";
        String needle1 = "public class";
        String needle2 = "public static void main(String";
        String needle3 = "for£¨";
        String needle4 = "while(";
        String needle5 = "if (";
        boolean index01 = false;
        boolean index02 = false;
        boolean whetherRunable = false;
        List<String> listt = null;
        try {  
            List<String> list = testjiansuo.readFile(paths.getPath());
            listt = list;
            for( String s : list ){
            	
            	int index1 = s.indexOf(needle1);
            	int index2 = s.indexOf(needle2);
            	int index3 = s.indexOf(needle3);
            	int index4 = s.indexOf(needle4);
            	int index5 = s.indexOf(needle5);
            	if(index3 != -1 || index4 != -1 || index5 != -1){
            		whetherRunable = true;
            	}
            	if(index1 != -1){
            		index01 = true;
            		System.out.println(new String(s.getBytes(), "UTF-8").trim());
            	}
            	if(index2 != -1){
            		index02 = true;
            		System.out.println(new String(s.getBytes(), "UTF-8").trim());
            	}
            	
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        
        if(index01 == false && index02 == false){
        	
    		try {

    		    File newFile=new File(paths.getPath());
    		    if(!newFile.exists()){
    		        newFile.createNewFile();
    		    }
    		    fos=new FileOutputStream(newFile);  
    		    osw=new OutputStreamWriter(fos);  
    		    bw=new BufferedWriter(osw);  
    		    fw = new FileWriter(newFile); 
//    		    bw.write(ss+"\n");
    		    bw.write(refill1+"\n");
    		    bw.write(refill2+"\n");
    		    for(String s : listt){
    		    	bw.write(s+"\n");
    		    	bw.flush();  
    		    }
    		    
    		    
//    		    }

    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        }
        
        if(index01 == false && index02 == true){
        	try {
    		    File newFile=new File(paths.getPath());
    		    if(!newFile.exists()){
    		        newFile.createNewFile();
    		    }
    		    fos=new FileOutputStream(newFile);  
    		    osw=new OutputStreamWriter(fos);  
    		    bw=new BufferedWriter(osw);  
    		    fw = new FileWriter(newFile); 
//    		    bw.write(ss+"\n");
    		    bw.write(refill1+"\n");
    		    for(String s : listt){
    		    	bw.write(s+"\n");
    		    	bw.flush();  
    		    }

    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        }
        if(index01 == true && index02 == false){
        	boolean needwrite = true;
        	try {
    		    File newFile=new File(paths.getPath());
    		    if(!newFile.exists()){
    		        newFile.createNewFile();
    		    }
    		    fos=new FileOutputStream(newFile);  
    		    osw=new OutputStreamWriter(fos);  
    		    bw=new BufferedWriter(osw);  
    		    fw = new FileWriter(newFile); 
    		    if(whetherRunable == true){
	    		    bw.write(refill1+"\n");
	    		    for(String s : listt){
	    		    	if(s.indexOf("public class ")!=-1 && needwrite == true){
	    		    		bw.write(refill2+"\n");
	    		    		bw.write(s+"\n");
	    		    		needwrite = false;
	    		    	}else{
	    		    	bw.write(s+"\n");
	    		    	bw.flush();  
	    		    	}
	    		    }
    		    }else{
    		    	for(String s : listt){
    		    		bw.write(s+"\n");
    		    		bw.flush();
    		    	}
    		    }

    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        }
        
  
    }  
      
    public static List<String> readFile(final String filename) throws IOException {     
        List<String> filecon = new ArrayList<String>();  
        String m = "";  
        BufferedReader file = new BufferedReader(new FileReader(filename));     
    
        while ((m = file.readLine()) != null)     
        {     
    
            if (!m.equals("0")) 
            {     
                if (!m.equals("")) 
                {     
                    filecon.add(m);
                }     
    
            }     
    
        }     
        file.close();     
        return filecon;     
    }  
  
}  