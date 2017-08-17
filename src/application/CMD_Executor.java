package application;
public class CMD_Executor{  
  
    public void execCommand(String[] arstringCommand) {  
        for (int i = 0; i < arstringCommand.length; i++) {  
        }  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    public void execCommand(String arstringCommand) {  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
  
    public void cmd(){   
        String cmd = "cmd /k start call running.bat";  
        execCommand(cmd);  
    }  
      
}  