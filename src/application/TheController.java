package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextPane;

import javafx.application.Application;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TheController{
	
	public String path_File_name_for_extract = null;
	public String the_last_second_directory_of_source_file = null;
	public String SourceFile_identifier = null;
	public int directory_Hierarchy = 0;
	public int resolution_mode = 0;
	List<String> cannot_Been_Used_Color = new ArrayList<String>();
	public Map<String,String> Map_Directory_Path_To_Name = new HashMap<String,String>();
    public static final String SELECT_TEXT =
            "(function getSelectionText() {\n" +
            "    var text = \"\";\n" +
            "    if (window.getSelection) {\n" +
            "        text = window.getSelection().toString();\n" +
            "    } else if (document.selection && document.selection.type != \"Control\") {\n" +
            "        text = document.selection.createRange().text;\n" +
            "    }\n" +
            "    if (window.getSelection) {\n" +
            "      if (window.getSelection().empty) {  // Chrome\n" +
            "        window.getSelection().empty();\n" +
            "      } else if (window.getSelection().removeAllRanges) {  // Firefox\n" +
            "        window.getSelection().removeAllRanges();\n" +
            "      }\n" +
            "    } else if (document.selection) {  // IE?\n" +
            "      document.selection.empty();\n" +
            "    }" +
            "    return text;\n" +
            "})()";
    public GridPane back_Up_GridPane = new GridPane();
    public GridPane back_Up_Result_GridPane = new GridPane();
    public String Language_Identifier = ".java";
    public boolean WhetherShowUploadFileReminder = false;
    public ColorChooser colorPicker = new ColorChooser();
    public String color_Scheme_Mode = "Auto";
    public String[] Report_Extract_String = new String[]{"-0","-1","-link","-top"};
    public String[] Report_Extract_String1 = new String[]{"back.gif","fields.js","forward.gif","logo.gif","result.xml"};
    public int pointer_for_export = 0;
    public Map<String,AnchorPane> Map_Result_Part_Num_to_its_AnchorPane = new HashMap<String,AnchorPane>();
    public Map<String,MenuButton> Map_Result_Part_Num_to_its_MenuButton = new HashMap<String,MenuButton>();
    public CMD_Executor CMD_executor = new CMD_Executor();
    public CMD_Writer CMD_writer = new CMD_Writer();
    public testjiansuo Check_Java = new testjiansuo();
    public String Selected_Directory;
    public int total_Number_of_Selected_files = 0;
    public Map<String,GridPaneItem> Map_GridPaneItem = new HashMap<String,GridPaneItem>();
    String LastFilesDirectory = System.getProperty("user.home");;
    String SourceFileSelectedPart = null;
    String[] TitledNames = new String[100];
    String[] Files_in_Directory;
    public Map<String,String> Map_color_titlename = new HashMap<String,String>();
    public Map<String,String> Map_Titlename_Color = new HashMap<String,String>();
    public Map<String,SourceFileTitledPane> Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
    public int number_of_parts=0;
    public HTMLEditor a = new HTMLEditor();
	public File SourceFile;
	public String LastSourceFilePath = System.getProperty("user.home");
	final FileChooser fileChooser = new FileChooser();
	public boolean PartsCanBeCreate = true;
	public int color_Pointer=0;
	SourceFileTitledPane Template;
	List<Color> color_LinkList = new LinkedList<Color>(){{add(Color.RED);add(Color.BLUE);add(Color.GREEN);add(Color.CHOCOLATE);add(Color.VIOLET);
	add(Color.AQUA);add(Color.DARKGOLDENROD);add(Color.MEDIUMPURPLE);
	}};
	List<String> color_Link_Names = new LinkedList<String>(){{
		add("RED");add("BLUE");add("GREEN");add("CHOCOLATE");add("VIOLET");add("AQUA");add("DARKGOLDENROD");add("MEDIUMPURPLE");
	}};

	@FXML
	private Button btn_clean_All_Parts;
	
	@FXML
	private Button btn_select_All_Parts;
	
	@FXML
	private AnchorPane the_Second_Largest_AnchorPane;
	
	@FXML
	private AnchorPane the_Largest_AnchorPane;
	
	@FXML
	private MenuItem menuItem_Undo;
	
	@FXML
	private MenuItem menuItem_Redo;
	
	@FXML
	private ToolBar ToolBar_For_Radio_language_Button;
	
	@FXML
	private SplitPane Directory_SplitPane;
	
	@FXML
	private Button btn_Generate_Report_Directory;
	
	@FXML
	private Label TaskName_For_ProgressBar;
	
	@FXML
	private Label Done_For_ProgressBar;
	
	@FXML
	private ProgressBar TheProgressBar;
	
	@FXML
	private AnchorPane ColorPickerContainer;
	
	@FXML
	private MenuButton TheColorPicker;
	
	@FXML
	private MenuItem MenuButton_Color_2;
	
	@FXML
	private MenuItem MenuBar_btn_Source_File;
	
	@FXML
	private MenuItem MenuBar_btn_Directory;
	
	@FXML
	private MenuItem MenuButton_Color_1;
	
    @FXML
    private Label lbl_directory;

    @FXML
    public WebView WebView1;
    
    @FXML
    private WebView WebView2;
    
    @FXML
    private Button btn_Directory_ClearAll;

    @FXML
    private Button btn_CreateNewPart;

    @FXML
    private Button btn_UpLoadDirectory;

    @FXML
    private TitledPane AccMain_Pane_Directory_Files;

    @FXML
    private Accordion Accordion_SourceFileParts;

    @FXML
    private Button btn_Directory_SelectAll;

    @FXML
    private TextField txt_Directory_Search;

    @FXML
    private TitledPane AccMain_Pane_Source_File;

    @FXML
    private Button btn_Directory_Search;

    @FXML
    private GridPane DirectoryGridPane;

    @FXML
    private TitledPane AccMain_Pane_Check_Report;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private Label lbl_SourceFile;

    @FXML
    private Button btn_Addtohighlight;

    @FXML
    private MenuButton Menu_ChooseAPart;

    @FXML
    private Button btn_SourceFileCheck;

    @FXML
    private Accordion Accordion_Main;

    @FXML
    private Button btn_UpLoadSourceFile;

    @FXML
    private Button bnt_TotalCheck;

    @FXML
    private MenuButton MenuButton_Color;

    @FXML
    private Button btn_Directory_Cancel;
    
    @FXML
    private Label lbl_Directory_number_of_files;
    
    @FXML
    private GridPane Report_GridPane;
    
    @FXML
    private AnchorPane AnchorPane_For_reports;
    
    @FXML
    private RadioButton Radio_Java;
    
    @FXML
    private RadioButton Radio_C_Cplus;
    
    @FXML
    private RadioButton Radio_Python;
    
    @FXML
    private Button btn_Clean_Language_Choose;
    
    @FXML
    void Click_menuItem_Undo(ActionEvent event){
    	System.out.println("aaaaa");
    	this.htmlEditor.setHtmlText(this.oldValuesRecorder.getMain_HTMLEditor());
    }
    
    @FXML
    void Click_menuItem_Redo(ActionEvent event){
    	
    }
    
    @FXML
    void Click_btn_clean_All_Parts(ActionEvent event){
    	for(SourceFileTitledPane SFTP:this.Map_SourcePane.values()){
    		SFTP.checkBox.setSelected(false);
    	}
    }
    
    @FXML
    void Click_btn_select_All_Parts(ActionEvent event){
    	for(SourceFileTitledPane SFTP:this.Map_SourcePane.values()){
    		SFTP.checkBox.setSelected(true);
    	}
    }
    
    @FXML 
    void click_btn_Clean_Language_Choose(ActionEvent event){
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	a.setTitle("Reset detecting language");
    	a.setContentText("This will unfreeze the toolbar and give you the"+"\n"+"oppotunity to choose an other language for detection"+"\n"+
    	"Also, the selected source file and parts will be removed"+"\n"+"If you want to continue, please press OK");
    	Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK){
			this.ToolBar_For_Radio_language_Button.setDisable(false);
		} else {
			return;
		}
    }
    
    @FXML
    void click_Radio_Java(ActionEvent event){
    	this.Radio_C_Cplus.setSelected(false);
    	this.Radio_Python.setSelected(false);
    	this.Language_Identifier = ".java";
    }
    
    @FXML
    void click_Radio_C_Cplus(ActionEvent event){
    	this.Radio_Java.setSelected(false);
    	this.Radio_Python.setSelected(false);
    	this.Language_Identifier = ".c";
    }
    
    @FXML 
    void click_Radio_Python(ActionEvent event){
    	this.Radio_C_Cplus.setSelected(false);
    	this.Radio_Java.setSelected(false);
    	this.Language_Identifier = ".py";
    }
    
    @FXML
    void Click_btn_Generate_Report_Directory(ActionEvent event){}
    
    @FXML
    void Click_MenuBar_btn_Source_File(ActionEvent event){
    	try{this.clean_item_files(this.SourceFile.getName());System.out.println(this.SourceFile.getName());}catch(Exception v){System.out.println("no file");}
    	if(this.WhetherShowUploadFileReminder==true){
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Confirmation Dialog");
    		a.setHeaderText("Current source file will be overlapped ");
    		a.setContentText("You are going to upload a new source file \n and this will cause overlapping of current source file,\nIn addition, all selected parts will be deleted and erased. \nDo you want to continue?");
    		Optional<ButtonType> result = a.showAndWait();
    		if (result.get() == ButtonType.OK){
    		} else {
    			return;
    		}
    		
    	}
    	configureFileChooser(fileChooser, "Choose a source file", this.LastSourceFilePath);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            SourceFile = file;
            if(file.getName().indexOf(this.Language_Identifier)==-1){
            	Alert a1 = new Alert(AlertType.CONFIRMATION);
            	a1.setTitle("Confirmation Dialog");
            	a1.setHeaderText("The file you selected does not include "+this.Language_Identifier);
            	a1.setContentText("Do you want to change the kind of language or reselect a file?"+"\n"+"If you want to change the kind of language please press YES"+"\n"+"If you want to reselect a file, please choose cancel");
            	Optional<ButtonType> result = a1.showAndWait();
            	if (result.get() == ButtonType.OK){
            		if(file.getName().indexOf(".java")!=-1){
            			this.Language_Identifier = ".java";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Python.setSelected(false);
            			this.Radio_Java.setSelected(true);
            		}
            		if(file.getName().indexOf(".c")!=-1){
            			this.Language_Identifier = ".c";
            			this.Radio_C_Cplus.setSelected(true);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(false);
            		}
            		if(file.getName().indexOf(".py")!=-1){
            			this.Language_Identifier = ".py";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(true);
            		}
        		} else {
        			return;
        		}
            }
        }else{return;}
        
        
        String TextToInput=null;
        for(String s:this.readTxtFileIntoStringArrList(SourceFile.getPath())){
        	TextToInput+=(s+"\n");
        }
        TextToInput = TextToInput.replaceAll("\n", "<br>");
        TextToInput = TextToInput.replaceAll("<br><br>", "<br>");
        TextToInput = TextToInput.replaceAll(" ", "&nbsp");
        this.htmlEditor.setHtmlText(TextToInput);
        this.LastSourceFilePath = SourceFile.getPath().toString().replace(SourceFile.getName().toString(), "");
        this.lbl_SourceFile.setText(SourceFile.getPath());
        this.Accordion_Main.setExpandedPane(AccMain_Pane_Source_File);
        this.PartsCanBeCreate = true;
        this.Accordion_SourceFileParts.getPanes().clear();
        this.Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
        this.number_of_parts = 0;
        this.ToolBar_For_Radio_language_Button.setDisable(true);
    }
    
    @FXML
    void Click_MenuBar_btn_Directory(ActionEvent event) throws Exception{
    	try{this.clean_total_files(this.SourceFile.getName());}catch(Exception e){};
    	File a = new File("src/uploadFiles/");
    	if(a.exists()!=true){
    		a.mkdirs();
    	}
    	DirectoryChooser dcr = new DirectoryChooser();
    	this.configureDirectoryChooser(dcr, "Chooser a directory for files", this.LastFilesDirectory);
    	File ff = dcr.showDialog(null);
    	
    	String Style_Of_File_For_Language = "*?"+this.Language_Identifier;
    	List<File> File_List = org.codehaus.plexus.util.FileUtils.getFiles(ff, null, this.SourceFile.getName());
    	
    	this.Selected_Directory = ff.getPath()+"/";

        String[] files_in_Directory = new String[File_List.size()];
        System.out.println(Style_Of_File_For_Language);
        int ppt = 0;
        for(int i=0;i<files_in_Directory.length;i++){
        	if(File_List.get(i).getName().indexOf(this.Language_Identifier)!=-1){
        		files_in_Directory[ppt] = File_List.get(ppt).getName();
        		ppt++;
        	} 	
        }
        files_in_Directory = this.removeNullValues(files_in_Directory);
        System.out.println(files_in_Directory.length);
        for(int i = 0;i<files_in_Directory.length;i++){
        	System.out.println(files_in_Directory[i]);
        }

		this.lbl_directory.setText(ff.getPath());
		this.Files_in_Directory = files_in_Directory;
		
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getChildren().clear();
		this.DirectoryGridPane.setGridLinesVisible(true);
		
		for(int i=0;i<this.Files_in_Directory.length;i++){
			try{
				if(this.Files_in_Directory[i].isEmpty()!=true){
					GridPaneItem gpi = new GridPaneItem(this.Files_in_Directory[i]);
					gpi.cb.setOnAction((ActionEvent e) ->
					{this.renewLbl_total_number_of_files();
					});
					this.Map_GridPaneItem.put(this.Files_in_Directory[i], gpi);
				}
			}catch(Exception ee){
				
			}
		}
		int num_Rows = this.Files_in_Directory.length/5+1;
	    for (int i = 0; i < num_Rows; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setMaxHeight(33);
	        row.setMinHeight(33);
	        row.setPrefHeight(33);
	        this.DirectoryGridPane.getRowConstraints().add(row);
	    }
	    int LinshiPointer = 0;
	    this.DirectoryGridPane.setGridLinesVisible(true);
		for(int i=0;i<num_Rows;i++){
			for(int j=0;j<5;j++){
				try{this.DirectoryGridPane.add(this.Map_GridPaneItem.get(this.Files_in_Directory[LinshiPointer]), j, i);}
				catch(Exception e11){break;}
				
				LinshiPointer++;
			}
		}
		this.renewLbl_total_number_of_files();
		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);
    }
    
    @FXML
    void Click_btn_Directory_Search(ActionEvent event) {
		for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
			gpi.cleanSearch();
		}
    	if(this.Map_GridPaneItem.isEmpty()==false){
    		for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    			gpi.ToSearch(this.txt_Directory_Search.getText());
    	
    		}
    	}
    }
    
    @FXML
    void Click_btn_Directory_Cancel(ActionEvent event) {
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
			gpi.cleanSearch();
		}
    }
    
    @FXML
    void Click_btn_Directory_SelectAll(ActionEvent event) {
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		gpi.checkBoxTrue();
    	}
    	this.renewLbl_total_number_of_files();
    }

    @FXML
    void Click_btn_Directory_ClearAll(ActionEvent event) {
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		gpi.checkBoxFalse();
    	}
    	this.renewLbl_total_number_of_files();
    }
    
    
    @FXML
    void Click_btn_UpLoadSourceFile(ActionEvent event) throws IOException, InterruptedException {
    	try{this.clean_item_files(this.SourceFile.getName());System.out.println(this.SourceFile.getName());}catch(Exception v){System.out.println("no file");}
    	if(this.WhetherShowUploadFileReminder==true){
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Confirmation Dialog");
    		a.setHeaderText("Current source file will be overlapped ");
    		a.setContentText("You are going to upload a new source file \n and this will cause overlapping of current source file,\nIn addition, all selected parts will be deleted and erased. \nDo you want to continue?");
    		Optional<ButtonType> result = a.showAndWait();
    		if (result.get() == ButtonType.OK){
    		} else {
    			return;
    		}
    		
    	}
    	configureFileChooser(fileChooser, "Choose a source file", this.LastSourceFilePath);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            SourceFile = file;
            if(file.getName().indexOf(this.Language_Identifier)==-1){
            	Alert a1 = new Alert(AlertType.CONFIRMATION);
            	a1.setTitle("Confirmation Dialog");
            	a1.setHeaderText("The file you selected does not include "+this.Language_Identifier);
            	a1.setContentText("Do you want to change the kind of language or reselect a file?"+"\n"+"If you want to change the kind of language please press YES"+"\n"+"If you want to reselect a file, please choose cancel");
            	Optional<ButtonType> result = a1.showAndWait();
            	if (result.get() == ButtonType.OK){
            		if(file.getName().indexOf(".java")!=-1){
            			this.Language_Identifier = ".java";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Python.setSelected(false);
            			this.Radio_Java.setSelected(true);
            		}
            		if(file.getName().indexOf(".c")!=-1){
            			this.Language_Identifier = ".c";
            			this.Radio_C_Cplus.setSelected(true);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(false);
            		}
            		if(file.getName().indexOf(".py")!=-1){
            			this.Language_Identifier = ".py";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(true);
            		}
        		} else {
        			return;
        		}
            }
        }else{return;}
        
        this.path_File_name_for_extract = null;
        String TextToInput=null;
        for(String s:this.readTxtFileIntoStringArrList(SourceFile.getPath())){
        	TextToInput+=(s+"\n");
        }
        System.out.println(TextToInput);
        TextToInput = TextToInput.replaceAll("\n", "<br>");
        TextToInput = TextToInput.replaceAll("<br><br>", "<br>");
        TextToInput = TextToInput.replaceAll(" ", "&nbsp");
        System.out.println(TextToInput);
        TextToInput = TextToInput.replaceAll("<br><br>", "<br>");
        System.out.println(TextToInput);
        this.htmlEditor.setHtmlText(TextToInput);
        this.LastSourceFilePath = SourceFile.getPath().toString().replace(SourceFile.getName().toString(), "");
        this.lbl_SourceFile.setText(SourceFile.getPath());
        this.Accordion_Main.setExpandedPane(AccMain_Pane_Source_File);
        this.PartsCanBeCreate = true;
        this.Accordion_SourceFileParts.getPanes().clear();
        this.Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
        this.number_of_parts = 0;
        this.ToolBar_For_Radio_language_Button.setDisable(true);
        this.cannot_Been_Used_Color = new ArrayList<String>();
        String tem1 = null;
        String[] tem = null;
        if(System.getProperty("os.name").startsWith("Window")){
    	tem1 = this.SourceFile.getPath().substring(this.SourceFile.getPath().replace("\\"+this.SourceFile.getName(),"").toString().lastIndexOf("\\")+1);
    	tem = new String[]{tem1.substring(0,tem1.indexOf("\\")),this.SourceFile.getName()};
        }
        else{
        	tem1 = this.SourceFile.getPath().substring(this.SourceFile.getPath().replace("/"+this.SourceFile.getName(),"").toString().lastIndexOf("/")+1);
        	tem = new String[]{tem1.substring(0,tem1.indexOf("/")),this.SourceFile.getName()};
        }
    	this.SourceFile_identifier = tem[0]+": "+tem[1];
    }

    @FXML
    void Click_btn_UpLoadDirectory(ActionEvent event) throws Exception {
    	String Directory_Upload_Mode = "pure";
    	try{this.clean_total_files(this.SourceFile.getName());}catch(Exception e){};
    	File a = new File("src/uploadFiles/");
    	if(a.exists()!=true){
    		a.mkdirs();
    	}
    	DirectoryChooser dcr = new DirectoryChooser();
    	this.configureDirectoryChooser(dcr, "Chooser a directory for files", this.LastFilesDirectory);
    	File dir = dcr.showDialog(null);
    	
    	List<String> files = listFiles(dir);
    	this.Selected_Directory = dir.getPath()+"/";

    	for(int i =0;i<files.size();i++){
       		if(files.get(i).equals(this.SourceFile_identifier)){
    			files.remove(i);
    		}
    	}
    	String[] files_in_Directory = new String[files.size()];
    	for(int i=0;i<files.size();i++){
    		
    		files_in_Directory[i] = files.get(i);
    		
    	}
    	
        
		this.lbl_directory.setText(dir.getPath());
		this.Files_in_Directory = files_in_Directory;
		
		this.DirectoryGridPane.getProperties().clear();
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getProperties().putAll(this.back_Up_GridPane.getProperties());
		System.out.println(this.DirectoryGridPane);
		this.DirectoryGridPane.setGridLinesVisible(true);
		if(Files_in_Directory.length==0){
			Alert b = new Alert(AlertType.CONFIRMATION);
    		b.setTitle("Confirmation Dialog");
    		b.setHeaderText("No"+this.Language_Identifier+"files in this directory");
    		b.setContentText("Do you want to reselect a directory? if yes, press OK");
    		Optional<ButtonType> result = b.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    this.Click_btn_UpLoadDirectory(event);
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    			return;
    		}
		}
		this.Map_GridPaneItem = new HashMap<String,GridPaneItem>();
		for(int i=0;i<this.Files_in_Directory.length;i++){
			try{
				if(this.Files_in_Directory[i].isEmpty()!=true){
					GridPaneItem gpi = new GridPaneItem(this.Files_in_Directory[i]);
					gpi.cb.setOnAction((ActionEvent e) ->
					{this.renewLbl_total_number_of_files();
					});
					this.Map_GridPaneItem.put(this.Files_in_Directory[i], gpi);
				}
			}catch(Exception ee){
				
			}
		}
		int num_Rows = this.Files_in_Directory.length/5+1;
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+num_Rows);
	    for (int i = 0; i < num_Rows; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setMaxHeight(33);
	        row.setMinHeight(33);
	        row.setPrefHeight(33);
	        this.DirectoryGridPane.getRowConstraints().add(row);
	    }
	    int LinshiPointer = 0;
	    this.DirectoryGridPane.setGridLinesVisible(true);
		for(int i=0;i<num_Rows;i++){
			for(int j=0;j<5;j++){
				try{this.DirectoryGridPane.add(this.Map_GridPaneItem.get(this.Files_in_Directory[LinshiPointer]), j, i);}
				catch(Exception e11){break;}
				
				LinshiPointer++;
			}
		}
		this.renewLbl_total_number_of_files();
		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);

    }
    
    ////the same as up_load_directory///
    @FXML
    void Click_LblDirectory(MouseEvent event) {
    	this.clean_total_files(this.SourceFile.getName());
    	File a = new File("src/uploadFiles/");
    	if(a.exists()!=true){
    		a.mkdirs();
    	}
    	DirectoryChooser dcr = new DirectoryChooser();
    	this.configureDirectoryChooser(dcr, "Chooser a directory for files", this.LastFilesDirectory);
    	File ff = dcr.showDialog(null);
    	if (ff != null){
    		System.out.println(ff.list());
    	}
    	int StringPointer = 0;
    	this.Selected_Directory = ff.getPath()+"/";
        File[] fileArray = ff.listFiles();
        String[] files_in_Directory = new String[fileArray.length];
        
        ///////////////////
		for(int i=0;i<fileArray.length;i++){
			if(fileArray[i].getName().equals(this.SourceFile.getName())){
				continue;
			}
			files_in_Directory[StringPointer] = fileArray[i].getName();
			StringPointer++;
		}
		this.lbl_directory.setText(ff.getPath());
		this.Files_in_Directory = files_in_Directory;
		
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getChildren().clear();
		this.DirectoryGridPane.setGridLinesVisible(true);
		
		for(int i=0;i<this.Files_in_Directory.length;i++){
			try{
				if(this.Files_in_Directory[i].isEmpty()!=true){
					GridPaneItem gpi = new GridPaneItem(this.Files_in_Directory[i]);
					gpi.cb.setOnAction((ActionEvent e) ->
					{this.renewLbl_total_number_of_files();
					});
					this.Map_GridPaneItem.put(this.Files_in_Directory[i], gpi);
				}
			}catch(Exception ee){
				System.out.println();
			}
		}
		int num_Rows = this.Files_in_Directory.length/5+1;
	    for (int i = 0; i < num_Rows; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setMaxHeight(33);
	        row.setMinHeight(33);
	        row.setPrefHeight(33);
	        this.DirectoryGridPane.getRowConstraints().add(row);
	    }
	    int LinshiPointer = 0;
	    this.DirectoryGridPane.setGridLinesVisible(true);
		for(int i=0;i<num_Rows;i++){
			for(int j=0;j<5;j++){
				try{this.DirectoryGridPane.add(this.Map_GridPaneItem.get(this.Files_in_Directory[LinshiPointer]), j, i);}
				catch(Exception e11){break;}
				
				LinshiPointer++;
			}
		}
		this.renewLbl_total_number_of_files();
		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);
    }

    @FXML
    void Click_bnt_TotalCheck(ActionEvent event) throws IOException, InterruptedException {
    	if(!this.whether_there_are_parts_or_fils()){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Please at least select one part of source file and at least one file for checking");
    		
    		alert.showAndWait();
    		return;
    	}
		this.Report_GridPane.getRowConstraints().clear();
		this.Report_GridPane.getChildren().clear();
		this.Report_GridPane.getProperties().clear();
		this.Report_GridPane.getProperties().putAll(this.back_Up_Result_GridPane.getProperties());
		this.Report_GridPane.setGridLinesVisible(true);
    	this.Map_Result_Part_Num_to_its_AnchorPane = new HashMap<String,AnchorPane>();
    	this.process_whole_files_when_checking();
    	double progress_part = this.Map_SourcePane.size();
    	progress_part = 1/progress_part;
    	
    	int part_flag = 0;
    	for(SourceFileTitledPane tfp:this.Map_SourcePane.values()){
    		if(tfp.checkBox.isSelected()){
    			this.write_Part_file(tfp.Editor.getHtmlText());
    			System.out.println("kankankanknakankan");
    			System.out.println(tfp.title_name);
    			this.check_One_Source_file_and_Export(tfp.title_name,part_flag);
    			part_flag++;
    			this.pointer_for_export++;

    		}
    		this.TheProgressBar.setProgress(progress_part);
    		progress_part+=progress_part;
    	}

//		this.renewLbl_total_number_of_files();
//		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);

    	int num_of_Row = (int) (Math.floor(this.pointer_for_export/2)+2);
    	for(int i=1;i<num_of_Row;i++){
    		RowConstraints row = new RowConstraints();
   	        row.setMaxHeight(30);
   	        row.setMinHeight(30);
   	        row.setPrefHeight(30);
   	        this.Report_GridPane.getRowConstraints().add(row);
   	    }
   	    int LinshiPointer = 0;
   	    whole_Probability_AnchorPane WPA = new whole_Probability_AnchorPane();
   	    WPA.generate.setOnAction((ActionEvent evt) -> {
   	    	float probbb = 1;
   	    	int num_of_countss = 0;
   	    for(AnchorPane ap: Map_Result_Part_Num_to_its_AnchorPane.values()){
   	    	Result_AnchorPane_Item RA = (Result_AnchorPane_Item)ap;
   	    	if(RA.similarity_prob!=0){
   	    		probbb*=RA.similarity_prob;
   	    		num_of_countss+=1;
   	    	}
   	    }

   	    if(num_of_countss==0){
   	    	probbb = 0;
   	    }else{
   	    	WPA.information.setText("Whole_Probability: "+probbb);
   	    }
   	    });
   	 try{this.Report_GridPane.add(WPA, 0, 0);
   	 	}
		catch(Exception e11){}
   	 
   		for(int i=1;i<num_of_Row;i++){
   			for(int j=0;j<2;j++){
   				try{this.Report_GridPane.add(this.Map_Result_Part_Num_to_its_AnchorPane.get(LinshiPointer+""), j, i);}
   				catch(Exception e11){break;}
   				
   				LinshiPointer++;
   			}
    	}
    	
    	this.pointer_for_export = 0;
    	this.Accordion_Main.setExpandedPane(AccMain_Pane_Check_Report);
    }

    @FXML
    void Click_btn_Addtohighlight(ActionEvent event) throws IOException {

    	if(this.Menu_ChooseAPart.getText().equals("Choose a part")){
    		this.showTanChuang(new Stage(), "Please select a part before highlighting");
    		return;
    	}

    	String Color_Name_For_HTML = null;
    	if(this.color_Scheme_Mode == "Auto"){
    		int colorIndex = Integer.parseInt(this.Map_color_titlename.get(this.SourceFileSelectedPart));
    		Color_Name_For_HTML = this.color_Link_Names.get(colorIndex).toLowerCase();
    	}else{
    		Color_Name_For_HTML = this.Map_Titlename_Color.get(this.SourceFileSelectedPart);
    	}
    	
    	WebView webView = (WebView) this.htmlEditor.lookup("WebView");
    	
    	
        if (webView != null) {
            WebEngine engine = webView.getEngine();
            Object selection = engine.executeScript(SELECT_TEXT);
            if (selection instanceof String && ((String) selection).length() != 0) {
                String add = (String) selection;
                add = this.process_prob_HTML(add);
                
                String ppp = this.htmlEditor.getHtmlText();
                ppp = ppp.replaceAll("&lt;", "<");
                ppp = ppp.replaceAll("&gt;", ">");
                
                if(ppp.indexOf(add)==-1){
                	Alert alert = new Alert(AlertType.INFORMATION);
                	alert.setTitle("Information Dialog");
                	alert.setHeaderText(null);
                	alert.setContentText("Please do not repeat selecting the codes");
                	alert.showAndWait();
                	return;
                }
                try{
                String preCheck = ppp.substring(ppp.indexOf(add));
                if(preCheck.indexOf("</font>")<preCheck.indexOf("<font color=") || (preCheck.indexOf("<font color=")==-1&& preCheck.indexOf("</font>")!=-1)){
                	Alert alert = new Alert(AlertType.INFORMATION);
                	alert.setTitle("Information Dialog");
                	alert.setHeaderText(null);
                	alert.setContentText("Please do not repeat selecting the codes");
                	alert.showAndWait();
                	return;
                }
                }catch(Exception e){System.out.println("error");}
                
                String deal = add;
                add = "<font color='"+Color_Name_For_HTML+"'>"+add+"</font>";
                

                ppp = ppp.replace(deal,add);
                StringBuffer buf = new StringBuffer(this.Map_SourcePane.get(this.SourceFileSelectedPart).Editor.getHtmlText());
                buf.insert(buf.length()-21,"<br>"+deal);
                String addto = buf.toString();
                this.Map_SourcePane.get(this.SourceFileSelectedPart).Editor.setHtmlText(addto);
                this.htmlEditor.setHtmlText(ppp);
               
            }
        }

    }

    @FXML
    void click_btn_CreateNewPart(ActionEvent event) throws IOException {
    	String color_Name_For_HTML = null;
    	Color color_For_HTML = null;
    	if(this.color_Scheme_Mode == "Auto"){
    		 color_Name_For_HTML = this.color_Link_Names.get(number_of_parts).toLowerCase(); 
    		 color_For_HTML = this.color_LinkList.get(this.number_of_parts);
    	}else{
    		try{
    		color_Name_For_HTML = this.colorPicker.getChosenColorName();
    		color_For_HTML = this.colorPicker.getChosenColor();}
    		catch(Exception e){this.showTanChuang(new Stage(), "Please choose a color");}
    	}
    	try{File fff = new File(this.SourceFile.getPath());}
    	catch(Exception v){this.showTanChuang(new Stage(), "Please upload a source file before creating a part");return;}
    	
    	if(this.PartsCanBeCreate == true){

    		WebView webView = (WebView) this.htmlEditor.lookup("WebView");

            if (webView != null) {
                WebEngine engine = webView.getEngine();
                Object selection = engine.executeScript(SELECT_TEXT);
                if (selection instanceof String && ((String) selection).length() != 0) {
                	
                    String add = (String) selection;
                    
                    add = this.process_prob_HTML(add);
                    
                    String ppp = this.htmlEditor.getHtmlText();
                    ppp = ppp.replaceAll("&lt;", "<");
                    ppp = ppp.replaceAll("&gt;", ">");
                    ppp = ppp.replaceAll("&amp;", "&");

                    if(ppp.indexOf(add)==-1){
                    	System.out.println("==-1");
                    	System.out.println(ppp);
                    	System.out.println(add);
                    	Alert alert = new Alert(AlertType.INFORMATION);
                    	alert.setTitle("Information Dialog");
                    	alert.setHeaderText(null);
                    	alert.setContentText("Please do not repeat selecting the codes");
                    	alert.showAndWait();
                    	return;
                    }
                    try{
                    String preCheck = ppp.substring(ppp.indexOf(add));
                    if(preCheck.indexOf("</font>")<preCheck.indexOf("<font color=") || (preCheck.indexOf("<font color=")==-1&& preCheck.indexOf("</font>")!=-1)){
                    	System.out.println("font");
                    	System.out.println(ppp);
                    	System.out.println(add);
                    	System.out.println(preCheck);
                    	Alert alert = new Alert(AlertType.INFORMATION);
                    	alert.setTitle("Information Dialog");
                    	alert.setHeaderText(null);
                    	alert.setContentText("Please do not repeat selecting the codes");
                    	alert.showAndWait();
                    	return;
                    }
                    }catch(Exception e){System.out.println("error");}
                    
                    if(cannot_Been_Used_Color.contains(color_Name_For_HTML)){
                    	Alert alert = new Alert(AlertType.INFORMATION);
                    	alert.setTitle("Information Dialog");
                    	alert.setHeaderText(null);
                    	alert.setContentText("Please do not repeat selecting the colour");
                    	alert.showAndWait();
                    	return;
                    }
                    
                    String deal = add;
                    String title_Name = "Part"+(this.number_of_parts+1)+": "+color_Name_For_HTML;
                	MenuItem MI = new MenuItem(title_Name);
                	MI.setText(title_Name);
                	MI.setId(title_Name);
                	MI.setOnAction((ActionEvent et) -> {this.SourceFileSelectedPart = title_Name;this.Menu_ChooseAPart.setText(this.SourceFileSelectedPart);});
                	this.Menu_ChooseAPart.getItems().add(MI);
                	
                    add = "<font color=\""+color_Name_For_HTML+"\">"+add+"</font>";
                    

                    ppp = ppp.replace(deal,add);
                                        
            		SourceFileTitledPane TP1 = new SourceFileTitledPane(title_Name,color_For_HTML,color_Name_For_HTML,resolution_mode);
            		TP1.btn_delete.setOnAction(evt -> {
            			WebView webView1 = (WebView) TP1.Editor.lookup("WebView");
            			WebEngine engine1 = webView1.getEngine();
                        Object selection1 = engine1.executeScript(SELECT_TEXT);
                        if (selection1 instanceof String && ((String) selection1).length() != 0) {
                        	String selected = (String) selection1;
                        	try {
								selected = this.process_prob_HTML(selected);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	String tempEditor = TP1.Editor.getHtmlText();
                        	String color_HTML_flag = "<font color=\""+TP1.inner_color_name+"\">";
                        	if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+7).equals("</font>")){
                        		if(tempEditor.substring(tempEditor.indexOf(selected)-4,tempEditor.indexOf(selected)).equals("<br>")){
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace("<br>"+selected, ""));
                            	}else{
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected, ""));
                            	}
                            	this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace((selected+"</font>"),"</font>"+selected));
                        	}
                      
                        	else if(tempEditor.substring(tempEditor.indexOf(selected)-color_HTML_flag.length(),tempEditor.indexOf(selected)).equals(color_HTML_flag)){
                        		if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+4).equals("<br>")){
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected+"<br>", ""));
                            	}else{
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected, ""));
                            	}
                            	this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace((color_HTML_flag+selected),selected+color_HTML_flag));
                        	}
                        	else{
                        		String match_flag1 = "&nbsp;";
                        		
                        		if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+4).equals("<br>")
                        				&&tempEditor.substring(tempEditor.indexOf(selected)-4,tempEditor.indexOf(selected)).equals("<br>")){

                        			TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected+"<br>", ""));
                            	}else if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+4).equals("<br>")
                            			&&!tempEditor.substring(tempEditor.indexOf(selected)-4,tempEditor.indexOf(selected)).equals("<br>")){
                            		int selectedIndex = tempEditor.indexOf(selected);
                            		String tempEditor1 = tempEditor.substring(0,selectedIndex);
                            		int tempIndex = tempEditor1.lastIndexOf("<br>");
                            		String tempEditor1_Left = tempEditor1.substring(tempIndex);
                            		System.out.println(tempEditor1);
                            		System.out.println(tempEditor1_Left);
                            		if(tempEditor1_Left.replaceAll(match_flag1, "").equals("<br>")){
                            			String tempReplace = tempEditor.substring(tempIndex,selectedIndex+selected.length());
                            			TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(tempReplace, ""));
                            			String tempReplace_MainEditor = "</font>"+tempReplace+"<font color=\""+TP1.inner_color_name+"\">";
                            			System.out.println(tempReplace_MainEditor);
                            			System.out.println(this.htmlEditor.getHtmlText());
                            			this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace(tempReplace,tempReplace_MainEditor));
                            			System.out.println(this.htmlEditor.getHtmlText());
                            		}else{
                                		Alert alert4 = new Alert(AlertType.INFORMATION);
                                		alert4.setTitle("Warning");
                                		alert4.setHeaderText("Illegal choice, please choose a complete sentence");
                                		alert4.setContentText("Press OK to rehighlight");
                                		alert4.showAndWait();
                            		};
                            		
                            	}else{
                            		Alert alert5 = new Alert(AlertType.INFORMATION);
                            		alert5.setTitle("Warning");
                            		alert5.setHeaderText("Illegal choice, please choose a complete sentence");
                            		alert5.setContentText("Press OK to rehighlight");
                            		alert5.showAndWait();
                            	}
                            	this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace((color_HTML_flag+selected),selected+color_HTML_flag));
                        	}
                        	
                        }
            			
            		});
            		this.cannot_Been_Used_Color.add(color_Name_For_HTML);
            		
                	this.Accordion_SourceFileParts.getPanes().add(TP1.innerPane);
                	this.Accordion_SourceFileParts.setExpandedPane(TP1.innerPane);
                    TP1.Editor.setHtmlText(add);
                    this.htmlEditor.setHtmlText(ppp);
                    this.Map_SourcePane.put(title_Name, TP1);
                    this.Map_color_titlename.put(title_Name, this.number_of_parts+"");
                    this.Map_Titlename_Color.put(title_Name, this.colorPicker.getChosenColorName());
                    this.number_of_parts+=1;
                    this.WhetherShowUploadFileReminder = true;
                    this.MenuButton_Color.setDisable(true);
                }
            }       
    	}
    }


    @FXML
    void initialize() {
        assert lbl_directory != null : "fx:id=\"lbl_directory\" was not injected: check your FXML file 'tes.fxml'.";
        assert the_Second_Largest_AnchorPane != null : "fx:id=\"the_Second_Largest_AnchorPane\" was not injected: check your FXML file 'tes.fxml'.";
        assert Accordion_SourceFileParts != null : "fx:id=\"Accordion_SourceFileParts\" was not injected: check your FXML file 'tes.fxml'.";
        assert txt_Directory_Search != null : "fx:id=\"txt_Directory_Search\" was not injected: check your FXML file 'tes.fxml'.";
        assert Radio_C_Cplus != null : "fx:id=\"Radio_C_Cplus\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Clean_Language_Choose != null : "fx:id=\"btn_Clean_Language_Choose\" was not injected: check your FXML file 'tes.fxml'.";
        assert MenuBar_btn_Source_File != null : "fx:id=\"MenuBar_btn_Source_File\" was not injected: check your FXML file 'tes.fxml'.";
        assert Report_GridPane != null : "fx:id=\"Report_GridPane\" was not injected: check your FXML file 'tes.fxml'.";
        assert TheColorPicker != null : "fx:id=\"TheColorPicker\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Directory_Search != null : "fx:id=\"btn_Directory_Search\" was not injected: check your FXML file 'tes.fxml'.";
        assert AccMain_Pane_Check_Report != null : "fx:id=\"AccMain_Pane_Check_Report\" was not injected: check your FXML file 'tes.fxml'.";
        assert menuItem_Redo != null : "fx:id=\"menuItem_Redo\" was not injected: check your FXML file 'tes.fxml'.";
        assert lbl_SourceFile != null : "fx:id=\"lbl_SourceFile\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Addtohighlight != null : "fx:id=\"btn_Addtohighlight\" was not injected: check your FXML file 'tes.fxml'.";
        assert Accordion_Main != null : "fx:id=\"Accordion_Main\" was not injected: check your FXML file 'tes.fxml'.";
        assert WebView1 != null : "fx:id=\"WebView1\" was not injected: check your FXML file 'tes.fxml'.";
        assert MenuButton_Color != null : "fx:id=\"MenuButton_Color\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Directory_ClearAll != null : "fx:id=\"btn_Directory_ClearAll\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_CreateNewPart != null : "fx:id=\"btn_CreateNewPart\" was not injected: check your FXML file 'tes.fxml'.";
        assert Directory_SplitPane != null : "fx:id=\"Directory_SplitPane\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_UpLoadDirectory != null : "fx:id=\"btn_UpLoadDirectory\" was not injected: check your FXML file 'tes.fxml'.";
        assert AccMain_Pane_Directory_Files != null : "fx:id=\"AccMain_Pane_Directory_Files\" was not injected: check your FXML file 'tes.fxml'.";
        assert MenuButton_Color_2 != null : "fx:id=\"MenuButton_Color_2\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Generate_Report_Directory != null : "fx:id=\"btn_Generate_Report_Directory\" was not injected: check your FXML file 'tes.fxml'.";
        assert lbl_Directory_number_of_files != null : "fx:id=\"lbl_Directory_number_of_files\" was not injected: check your FXML file 'tes.fxml'.";
        assert Radio_Python != null : "fx:id=\"Radio_Python\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_select_All_Parts != null : "fx:id=\"btn_select_All_Part\" was not injected: check your FXML file 'tes.fxml'.";
        assert TheProgressBar != null : "fx:id=\"TheProgressBar\" was not injected: check your FXML file 'tes.fxml'.";
        assert ColorPickerContainer != null : "fx:id=\"ColorPickerContainer\" was not injected: check your FXML file 'tes.fxml'.";
        assert menuItem_Undo != null : "fx:id=\"menuItem_Undo\" was not injected: check your FXML file 'tes.fxml'.";
        assert AccMain_Pane_Source_File != null : "fx:id=\"AccMain_Pane_Source_File\" was not injected: check your FXML file 'tes.fxml'.";
        assert AnchorPane_For_reports != null : "fx:id=\"AnchorPane_For_reports\" was not injected: check your FXML file 'tes.fxml'.";
        assert MenuButton_Color_1 != null : "fx:id=\"MenuButton_Color_1\" was not injected: check your FXML file 'tes.fxml'.";
        assert DirectoryGridPane != null : "fx:id=\"DirectoryGridPane\" was not injected: check your FXML file 'tes.fxml'.";
        assert MenuBar_btn_Directory != null : "fx:id=\"MenuBar_btn_Directory\" was not injected: check your FXML file 'tes.fxml'.";
        assert Done_For_ProgressBar != null : "fx:id=\"Done_For_ProgressBar\" was not injected: check your FXML file 'tes.fxml'.";
        assert htmlEditor != null : "fx:id=\"htmlEditor\" was not injected: check your FXML file 'tes.fxml'.";
        assert ToolBar_For_Radio_language_Button != null : "fx:id=\"ToolBar_For_Radio_language_Button\" was not injected: check your FXML file 'tes.fxml'.";
        assert Menu_ChooseAPart != null : "fx:id=\"Menu_ChooseAPart\" was not injected: check your FXML file 'tes.fxml'.";
        assert Radio_Java != null : "fx:id=\"Radio_Java\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Directory_Cancel != null : "fx:id=\"btn_Directory_Cancel\" was not injected: check your FXML file 'tes.fxml'.";
        assert TaskName_For_ProgressBar != null : "fx:id=\"TaskName_For_ProgressBar\" was not injected: check your FXML file 'tes.fxml'.";
        assert the_Largest_AnchorPane != null : "fx:id=\"the_Largest_AnchorPane\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_UpLoadSourceFile != null : "fx:id=\"btn_UpLoadSourceFile\" was not injected: check your FXML file 'tes.fxml'.";
        assert bnt_TotalCheck != null : "fx:id=\"bnt_TotalCheck\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_Directory_SelectAll != null : "fx:id=\"btn_Directory_SelectAll\" was not injected: check your FXML file 'tes.fxml'.";
        assert btn_clean_All_Parts != null : "fx:id=\"btn_clean_All_Parts\" was not injected: check your FXML file 'tes.fxml'.";

        Tooltip tp = new Tooltip("Upload a new source file");
        Tooltip tp1 = new Tooltip("Upload a new file directory");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();

        if(height<1000){
        	this.the_Largest_AnchorPane.setPrefHeight(820);
        	this.the_Second_Largest_AnchorPane.setPrefHeight(820);
        	this.Accordion_Main.setPrefHeight(600);
        	this.bnt_TotalCheck.setLayoutY(785);
        	this.Accordion_SourceFileParts.setPrefHeight(340);
        	this.btn_select_All_Parts.setLayoutY(500);
        	this.btn_clean_All_Parts.setLayoutY(500);
        }
        if(height<850){
        	this.resolution_mode = 1;
        	this.the_Largest_AnchorPane.setPrefHeight(720);
        	this.the_Second_Largest_AnchorPane.setPrefHeight(720);
        	this.Accordion_Main.setPrefHeight(500);
        	this.bnt_TotalCheck.setLayoutY(693);
        	this.Accordion_SourceFileParts.setPrefHeight(100);
        	this.btn_select_All_Parts.setLayoutY(400);
        	this.btn_clean_All_Parts.setLayoutY(400);
        }
//        this.Accordion_SourceFileParts
        this.btn_Generate_Report_Directory.onActionProperty().bindBidirectional(this.bnt_TotalCheck.onActionProperty());
        this.Radio_Java.setSelected(true);
        this.btn_UpLoadSourceFile.setTooltip(tp);
        this.btn_UpLoadDirectory.setTooltip(tp1);
        this.ColorPickerContainer.getChildren().add(colorPicker);
        this.colorPicker.setOnMouseExited(evt -> {this.change_ColorPicker_Rectangle(this.colorPicker.getChosenColor(), this.colorPicker.getChosenColorName());});
        this.TheColorPicker.getStyleClass().add("column-filter");
        this.TheColorPicker.setText("None");
        this.TheColorPicker.setGraphic(new Rectangle(12,12));
        this.MenuButton_Color.setText("Automatic");
        this.TheColorPicker.setDisable(true);
        this.MenuButton_Color_1.setOnAction(evt -> {this.color_Scheme_Mode = "Auto";this.MenuButton_Color.setText(this.MenuButton_Color_1.getText());this.TheColorPicker.setDisable(true);});
        this.MenuButton_Color_2.setOnAction(evt -> {this.color_Scheme_Mode = "Hand";this.MenuButton_Color.setText(this.MenuButton_Color_2.getText());this.TheColorPicker.setDisable(false);});
        this.deleteBars(htmlEditor);
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getChildren().clear();
        this.back_Up_GridPane.getProperties().clear();
        this.back_Up_GridPane.getProperties().putAll(this.DirectoryGridPane.getProperties());
		this.Report_GridPane.getRowConstraints().clear();
		this.Report_GridPane.getChildren().clear();
		this.back_Up_Result_GridPane.getProperties().clear();
		this.back_Up_Result_GridPane.getProperties().putAll(this.Report_GridPane.getProperties());
        
    }

    public void add_one_part(String Color_of_Part, String Content_of_Part){
    	
    }


    
    private static void configureFileChooser(final FileChooser fileChooser, String setTitle, String lastFilePath){                           
        fileChooser.setTitle(setTitle);
        fileChooser.setInitialDirectory(
            new File(lastFilePath)
        ); 
    }
    
    private static void configureDirectoryChooser( DirectoryChooser fileChooser, String setTitle, String lastFilePath){                           
        fileChooser.setTitle(setTitle);
        fileChooser.setInitialDirectory(
            new File(lastFilePath)
        ); 
    }
    
    ///////////////read a file by line////////////////////////
    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { 
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                	lineTxt = lineTxt+"\n";
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println(" ");
            }
        }
        catch (Exception e)
        {
            System.out.println(" ");
            e.printStackTrace();
        }

        return list;
    }
    
    /////to delete the redundant bars/////
    public void deleteBars(HTMLEditor ThehtmlEditor){
        for(Node node: ThehtmlEditor.lookupAll(".tool-bar")){   	    		  
            node.setManaged(false);
            node.setDisable(true);
            node.setVisible(false);       	     	    	  
        };
    }
    
    ////create a new anchor and return////
    public TitledPane create_titledPane_for_parts(String pane_Name){
    	AnchorPane AP = new AnchorPane(); 	
    	HTMLEditor ED = new HTMLEditor();  	
    	ED.setId("html"+this.number_of_parts);
    	this.deleteBars(ED);
    	ED.setPrefSize(200, 250);
    	ED.setLayoutX(10);
    	ED.setLayoutY(20);
    	AP.getChildren().add(ED);
    	TitledPane TP = new TitledPane(pane_Name,AP);

    	return TP;
    }
    
    ///get RGB
    
    public int[] getRGB( Color col) 
    {
        int r = ((int)col.RED.getRed()*255);
        int g = ((int)col.RED.getGreen() * 255);
        int b = ((int)col.RED.getBlue() * 255);
//        int rgb = (r << 16) + (g << 8) + b;
        int[] rgb = new int[3];
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;

        return rgb;
    }
    
    ///deal with problematic HTML/////
    public String process_prob_HTML(String s) throws UnsupportedEncodingException{
    	byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
        String UTFSpace = new String(bytes,"utf-8");

        s = s.replaceAll("\n", "<br>");
        s = s.replaceAll("<br><br>", "<br>");
        s = s.replaceAll(" ", "&nbsp");
        s = s.replaceAll(UTFSpace, "&nbsp;");
        return s;
    }
    
    ///check whether////
    public String[] checkString(String[] checkSt){
    	int p = 0;
    	for(int i=checkSt.length;i>1;i--){
    		if(p==0){
    			if(checkSt[i].isEmpty()){

    			}
    		}
    	}
    	return checkSt;
    
    }
    
    public void renewLbl_total_number_of_files(){
    	int numsss = 0;
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		if(gpi.cb.isSelected()){
    			numsss++;
    		}
    	}
    	this.lbl_Directory_number_of_files.setText("Number of files included: "+numsss);
    	this.total_Number_of_Selected_files = numsss;
    }
    
    
    ////write part compare file////
    public void write_Part_file(String s) throws IOException{

    	String[] temp_string = this.SourceFile_identifier.split(": ");
    	
        s = this.delHTMLTag(s);
        FileOutputStream fos=null;
        OutputStreamWriter osw=null;  
        BufferedWriter bw=null;  
        String path = "src/uploadFiles/"+temp_string[0]+"_"+temp_string[1];
        this.path_File_name_for_extract = temp_string[0]+"_"+temp_string[1];
	    File newFile=new File(path);
	    if(!newFile.exists()){
	        newFile.createNewFile();
	    }
	    fos=new FileOutputStream(newFile);  
	    osw=new OutputStreamWriter(fos);  
	    bw=new BufferedWriter(osw);  
//	    bw.write(ss+"\n");
	    bw.write(s);
	    bw.close();
	    if(this.Language_Identifier==".java"){
	    this.Check_Java.running(newFile);}
    }
    
    /////do whole files job when checking///////
    public void process_whole_files_when_checking(){
    	
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		if(gpi.cb.isSelected()){

    			String[] temp_file_names = gpi.file_Name.split(": ");

    			String directory_flag = "/"+temp_file_names[0];


    			File source = new File("");
    			File dest = new File("");
    			if(this.Selected_Directory.indexOf(directory_flag)!=-1){

    				source = new File(this.Selected_Directory+temp_file_names[1]);
        			dest = new File("src/uploadFiles/"+temp_file_names[1]);
    			}else{

    				source = new File(this.Selected_Directory+temp_file_names[0]+"/"+temp_file_names[1]);
        			dest = new File("src/uploadFiles/"+temp_file_names[0]+"_"+temp_file_names[1]);
    			}
    	
    			try {
    				System.out.println(source);
    				System.out.println(dest);
    				copyFileUsingJava7Files(source,dest);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
    	
    }
    
    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>";  
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>";  
        String regEx_html="<[^>]+>";  
        htmlStr = htmlStr.replaceAll("<br>","\n");
        htmlStr = htmlStr.replaceAll("&nbsp;"," ");
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); 
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll("");  
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll("");  

       return htmlStr.trim();  
    }
    /////copy files////////
	private static void copyFileUsingJava7Files1(File source, File dest)
	        throws IOException {
	        Files.copy(source.toPath(), dest.toPath());
	}
	
	private static void copyFileUsingJava7Files(File source, File dest)
	        throws IOException {    
	    InputStream input = null;    
	    OutputStream output = null;    
	    try {
	           input = new FileInputStream(source);
	           output = new FileOutputStream(dest);        
	           byte[] buf = new byte[1024];        
	           int bytesRead;        
	           while ((bytesRead = input.read(buf)) > 0) {
	               output.write(buf, 0, bytesRead);
	           }
	    } finally {
	        input.close();
	        output.close();
	    }
	}
	
	////check one source file and export results/////////
	public void check_One_Source_file_and_Export(String name,int part_flag) throws IOException, InterruptedException{
		CountsComputer countsComputer = new CountsComputer();
		countsComputer.item_part = "item"+part_flag;
		
		System.out.println(System.getProperty("user.dir")+"/items/item"+part_flag);
		String result_file = System.getProperty("user.dir")+"/items/item"+part_flag;

		String language_type = "";
		if(this.Language_Identifier==".py"){language_type="python3";}else if(this.Language_Identifier==".java"){language_type="java17";}else if(this.Language_Identifier==".c"){language_type="c/c++";}
		if(System.getProperty("os.name").startsWith("Windows"))
		{
			this.CMD_writer.writee(result_file,language_type);
			this.CMD_executor.cmd();
		}else if(System.getProperty("os.name").startsWith("Mac")){

			  Process p = Runtime.getRuntime().exec("java -jar "+System.getProperty("user.dir")+"/jplag.jar -l "+language_type+" -r "+result_file+" -s "+System.getProperty("user.dir")+"/src/uploadFiles");
			  InputStream is = p.getInputStream();
			  BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			  String line;
			  while((line = reader.readLine())!= null){
			   System.out.println(line);
			  }
			  p.waitFor();
			  is.close();
			  reader.close();
			  p.destroy();
			
		}else{
		  Process p = Runtime.getRuntime().exec("java -jar "+System.getProperty("user.dir")+"/jplag.jar -l "+language_type+" -r "+result_file+" -s "+System.getProperty("user.dir")+"/src/uploadFiles");
		  InputStream is = p.getInputStream();
		  BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		  String line;
		  while((line = reader.readLine())!= null){
		   System.out.println(line);
		  }
		  p.waitFor();
		  is.close();
		  reader.close();
		  p.destroy();
		}
		

		File check_file_name = new File(result_file+"/index.html");
		File another_file = new File(result_file+"/match53.html");
		double progress_part = this.Map_SourcePane.size()*50;
    	progress_part = 1/progress_part;
		while(!check_file_name.exists() || !another_file.exists() ){
			try {
		    	
		    
		    	this.TheProgressBar.setProgress(progress_part);

				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(progress_part<1){
			progress_part+=progress_part;}
			else{
				progress_part=1;
			}
		}
		
		countsComputer.running(result_file,path_File_name_for_extract);

		System.out.println("flagg");
		System.out.println((float)(Math.round((float)countsComputer.getNums()/this.total_Number_of_Selected_files*10000))/100);
		Result_AnchorPane_Item RAI = new Result_AnchorPane_Item(countsComputer.getNums(),this.total_Number_of_Selected_files,name,countsComputer.Map_index_and_Files);
		this.Map_Result_Part_Num_to_its_AnchorPane.put(this.pointer_for_export+"", RAI);
		
		for(String mp:countsComputer.Map_index_and_Files.keySet()){
			Report_Menu_Item RMI = new Report_Menu_Item(mp,countsComputer.Map_index_and_Files.get(mp));
			String subpath = "/items/"+countsComputer.item_part+"/"+countsComputer.Map_index_and_Files.get(mp).getName();
			System.out.println(subpath);
			String item_name_to_show = mp;
			System.out.println("memmeda");
			System.out.println("file:/"+System.getProperty("user.dir")+subpath);
			System.out.println("aaaaaaaaaaaaaaaa");
			
			URL url1 = new URL("file://"+System.getProperty("user.dir")+subpath);
			URL url2 = new URL("file:/"+System.getProperty("user.dir")+subpath);
			if(System.getProperty("os.name").startsWith("Window")){
				RMI.setOnAction(evt -> {System.out.println(url1);this.WebView1.getEngine().load(url2.toExternalForm());RAI.detail.setText(item_name_to_show);
				int counttt = 0;
				for(MenuItem RMIi:RAI.detail.getItems()){
					Report_Menu_Item report = (Report_Menu_Item)RMIi;
					if(report.checkBox.isSelected()){
						counttt+=1;
					};
				}
				RAI.Recount(counttt);
				System.out.println("memememeda");
				});
			}
			else{
			RMI.setOnAction(evt -> {System.out.println(url1);this.WebView1.getEngine().load(url1.toExternalForm());RAI.detail.setText(item_name_to_show);
			int counttt = 0;
			for(MenuItem RMIi:RAI.detail.getItems()){
				Report_Menu_Item report = (Report_Menu_Item)RMIi;
				if(report.checkBox.isSelected()){
					counttt+=1;
				};
			}
			RAI.Recount(counttt);
			System.out.println("memememeda");
			});
			}
			RAI.detail.getItems().add(RMI);
		}
	}
	
	/////read a file and return//////
	public String read_a_file_return_html(String file_path){
		int flag = 0;
		String text = "";
        for(String s:this.readTxtFileIntoStringArrList(file_path)){
        	if(flag==9||flag==10||flag==11||flag==12){
        	System.out.println(flag);
        	text+=s;
        	}
        	flag++;
        }
        return text;
	}
	/////delete files/////////////
	public void invokedelete(String path){
	    File f=new File(path);
	    if(f.isDirectory()){
	        String[] list=f.list();
	        for(int i=0;i<list.length;i++){
	            invokedelete(path+"//"+list[i]);
	        }
	    }       
	    f.delete();
	}
	//////clean item file////
	public void clean_item_files(String f){
		File a = new File("src/uploadFiles/"+f);
		System.out.println(a.getPath());
		if(a.exists()){
			this.invokedelete(a.getPath());
		}
		File b = new File(System.getProperty("user.dir")+"/items/");
		if(b.exists()){
			this.invokedelete(b.getPath());
		}
	}
	///////clean total files/////
	public void clean_total_files(String f){
		File a = new File("src/uploadFiles/");
		if(a.exists() && a.getName()!=f){
			this.invokedelete(a.getPath());
		}
		File b = new File(System.getProperty("user.dir")+"/items/");
		if(b.exists()){
			this.invokedelete(b.getPath());
		}
	}
	
	////show TanChuang/////
	public void showTanChuang(Stage primaryStage,String wrongInformation) throws IOException{
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TanChuang.fxml"));
	    Parent root = fxmlLoader.load();

	    Scene scene = new Scene(root);
	    TanChuangController controller = fxmlLoader.getController();  
	    controller.ErrorInformation(wrongInformation);
	    primaryStage.setTitle("Error");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public void showTanChuang1(Stage primaryStage,String wrongInformation) throws IOException, InterruptedException{
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TanChuang1.fxml"));
	    Parent root = fxmlLoader.load();

	    Scene scene = new Scene(root);
	    TanChuangController1 controller = fxmlLoader.getController(); 
	    controller.ErrorInformation(wrongInformation);

	    primaryStage.setTitle("Error");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    primaryStage.setOnCloseRequest(evt -> System.out.println("gggg"));

	}
	/////return string[]/////
    public static String[] removeNullValues(String[] firstArray) {

	    List<String> list = new ArrayList<String>();

	    for (String s : firstArray) {
	        if (s != null && s.length() > 0) {
	            list.add(s);
	        }
	    }

	    firstArray = list.toArray(new String[list.size()]);
	    return firstArray;
	}	
    ////////change ColorPicker Rectangle////////////
    public void change_ColorPicker_Rectangle(Color c, String name){
    	this.TheColorPicker.setText(name);
    	Rectangle r = new Rectangle(12,12);
    	r.setFill(c);
    	this.TheColorPicker.setGraphic(r);
    }
    
    ////////check whether there are parts ///////
    public boolean whether_there_are_parts_or_fils(){
    	boolean flag_file = false;
    	boolean flag_directory = false;
    	for(SourceFileTitledPane sftp: this.Map_SourcePane.values())
    	{
    		if(sftp.checkBox.isSelected()){
    			flag_file = true;
    		}
    	}
    	if(this.total_Number_of_Selected_files!=0){
    		flag_directory =true;
    	}
    	return flag_file&&flag_directory;
    }
    
    
    /////for upload directory functions///
	public List<String> listFiles(File root){
	    List<String> files = new ArrayList<String>();
	    listFiles(files, root);
	    return files;
	}
	 
	public void listFiles(List<String> files, File dir){
	    File[] listFiles = dir.listFiles();
	    this.directory_Hierarchy+=1;
	    for(File f: listFiles){
	        if(f.isFile()&&f.getName().indexOf(this.Language_Identifier)!=-1){
	        	String pathh = f.getPath();
	        	
	        	if(f.getPath().indexOf("\\")!=-1){
	        		int point_flag = pathh.subSequence(0, pathh.lastIndexOf("\\",pathh.length()-f.getName().length()-1)).toString().lastIndexOf("\\")+1;
	        		String front_String = pathh.substring(point_flag);
	        		String father_Directory_name = front_String.replace("\\"+f.getName(), "");
	        		this.Map_Directory_Path_To_Name.put(father_Directory_name, f.getName());
	        		files.add(father_Directory_name+": "+f.getName());
	        	} else if(f.getPath().indexOf("/")!=-1){
	        		int point_flag = pathh.subSequence(0, pathh.lastIndexOf("/",pathh.length()-f.getName().length()-1)).toString().lastIndexOf("/")+1;
	        		String front_String = pathh.substring(point_flag);
	        		String father_Directory_name = front_String.replace("/"+f.getName(), "");
	        		this.Map_Directory_Path_To_Name.put(father_Directory_name, f.getName());
	        		files.add(father_Directory_name+": "+f.getName());
	        	};

	        }else if(f.isDirectory()){
	            listFiles(files, f);
	        }
	    }
	}
    
    
}
