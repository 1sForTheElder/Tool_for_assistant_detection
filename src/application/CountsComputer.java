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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountsComputer {
	String[] indexFiles;
	String item_part;
	Map<String,File> Map_index_and_Files = new HashMap<String,File>();
	String KeyFile = "";
	static int nums = 0;
	public File paths;
	public static int getNums() {
		return nums;
	}

	public static void setNums(int nums) {
		CountsComputer.nums = nums;
	}
	
	public void setCountsComputer(File filee){
		this.paths = filee;
	}
	public void setZero(){
		this.nums = 0;
	}

	static int totalNums = 0;
    int Counts = 0;
	boolean startToWrite = false;
	String keyWord1 = "Matches sorted by maximum similarity";
	public void running(String result_file_path, String checkFileName) {
        setCountsComputer(new File(result_file_path+"/index.html"));
        List<String> listt = null;
        try {  
            List<String> list = testjiansuo.readFile(paths.getPath());
            listt = list;
            int num_of_exist = 0;
            for( String s : list ){
//            	System.out.println(s);
            	if (s.indexOf(keyWord1) != -1){
            		startToWrite = true;
            	}
            	if (startToWrite == true && s.indexOf(checkFileName)!=-1){
            		System.out.println("zhengjuhua");
            		System.out.println(s);
            	    String des = "<A HREF=";
            	    String de = "</A><BR>";
            	    int cnt = 0;
            	    int offset = 0;
            	    while((s.indexOf(de, offset)) != -1){
            	    	int p = s.indexOf(des,offset);
            	    	int t = s.indexOf(de,offset);
            	    	
            	    	String reco = s.substring(p+9, t);
            	    	reco = reco.replace("\">", " ");
            	    	String[] aa=reco.split(" ");
            	    	this.Map_index_and_Files.put(aa[1], new File(result_file_path+"/"+aa[0]));    	
            	        offset = s.indexOf(de,offset) + de.length();
            	        cnt++;
            	    }
            		num_of_exist=cnt;
            		String[] temp = new String[this.Map_index_and_Files.keySet().size()];
            		int ind = 0;
            		for(String a: this.Map_index_and_Files.keySet()){
            			temp[ind] = a;
            			ind++;
            		}
            		this.indexFiles = temp;
            		break;
            	}
            	

            	
            }
            setNums(num_of_exist);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        startToWrite = false;
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
    
    public static int stringNumbers(String str)  
    {  
        if (str.indexOf(".java")==-1)  
        {  
            return 0;  
        }  
        else if(str.indexOf(".java") != -1)  
        {  
            nums++;  
            stringNumbers(str.substring(str.indexOf(".java")+5));  
            return nums;  
        }  
        return 0;  
    } 
}
