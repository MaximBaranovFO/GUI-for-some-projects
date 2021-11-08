/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pkgfor.some.projects;

import static gui.pkgfor.some.projects.GuiReestr.RunTesseract;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public class GuiConcurrentInput {
    
    private static final String PDF_IMAGES = "C:/ioop/forPdfToJpeg/pdfimages.exe";
    private static final String PARAM_SPLITTER = " ";
    private static final String PDF_IMAGES_OPTION = "-j";
    private static final String TESSERACT_FILE = "\"C:/Program Files (x86)/Tesseract-OCR/tesseract.exe\"";
    private static final String TESSERACT_FILE_OPTION = "-l rus";
    
    private ConcurrentSkipListMap<Integer, Object> idnewwindow;
    
    private ConcurrentSkipListMap<Integer, Path> idinsystem;
    
    private final GuiFileManager idFM;
    GuiConcurrentInput(){
        this.idinsystem = new ConcurrentSkipListMap<>();
        this.idFM = new GuiFileManager();
        createGuiFileManager();
    }
    protected Boolean createGuiFileManager(){
        this.addNewWindow(this.idFM);
        return Boolean.TRUE;
    }
    protected void getParmWindow(){
        Integer thisClass = java.util.Objects.hashCode(this);
        Integer thisClassForThatWindow = java.util.Objects.hashCode(this.idFM);
        String thisClassWriteToStringResult = java.util.Objects.toString(this);
        Class<? extends GuiConcurrentInput> cThisClassName = this.getClass();
        
        String thisClassForThatWindowWriteToStringResult = java.util.Objects.toString(this.idFM);
        Class<? extends GuiFileManager> cParam = this.idFM.getClass();
        
        ClassLoader platformClassLoader = java.lang.ClassLoader.getPlatformClassLoader();
        ClassLoader systemClassLoader = java.lang.ClassLoader.getSystemClassLoader();
        
        try {
            java.lang.ClassLoader.getSystemResources(this.idFM.getClass().getName());
        } catch (IOException ex) {
            Logger.getLogger(GuiConcurrentInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void addNewWindow(Object windowForAdd){
        this.idnewwindow.put(windowForAdd.hashCode(), windowForAdd);
    }
    protected Integer countInListOfWindow(){
        return this.idnewwindow.size();
    }
    
    private void addPath(Path innerPath){
        pathIsNotReadWriteLink(innerPath);
        this.idinsystem.put(innerPath.hashCode(), innerPath);
    }
    
    private Set getPathEntrySet(){
        return this.idinsystem.entrySet();
    }
    
    private Integer getCountPath(){
        return this.idinsystem.size();
    }
    
     private static void pathIsNotReadWriteLink(Path innerWorkPath){
        if ( !Files.isReadable(innerWorkPath) ){
            System.out.println("[ERROR] File exist and it is not a Readable: " + innerWorkPath.toString());
            throw new RuntimeException("[ERROR] File exist and it is not a Readable: " + innerWorkPath.toString());
        }
        if ( !Files.isWritable(innerWorkPath) ){
            System.out.println("[ERROR] File exist and it is not a Writable: " + innerWorkPath.toString());
            throw new RuntimeException("[ERROR] File exist and it is not a Writable: " + innerWorkPath.toString());
        }
        if ( Files.isSymbolicLink(innerWorkPath) ){
            System.out.println("[ERROR] File exist and it is not a SymbolicLink: " + innerWorkPath.toString());
            throw new RuntimeException("[ERROR] File exist and it is a SymbolicLink: " + innerWorkPath.toString());
        }
    }
    
    private void inputedDirForWork(){
        GuiConcurrentInput idReestrIteration = new GuiConcurrentInput();
        ArrayList<Path> listFilesInWorkJpegDir = idReestrIteration.idFM.listFilesInWorkJpegDir();
        ArrayList<Path> filesInWorkTxtTesseractDir = idReestrIteration.idFM.listFilesInWorkTxtTesseractDir();
        if( filesInWorkTxtTesseractDir.isEmpty() ){
            if( !listFilesInWorkJpegDir.isEmpty() ){
                for (Path pathJpegName : listFilesInWorkJpegDir) {

                Path dstPath = Paths.get(idReestrIteration.idFM.getDirForTxtTesseract().toString(), 
                        pathJpegName.getFileName().toString());
                String cmdToRun = TESSERACT_FILE
                        + PARAM_SPLITTER
                        + pathJpegName
                        + PARAM_SPLITTER
                        + dstPath.toString()
                        + PARAM_SPLITTER
                        + TESSERACT_FILE_OPTION
                        ;
                RunTesseract(cmdToRun);
                }
            }
        }
    }
    public static String getNewProcessId(){
        long currentDateTime = System.currentTimeMillis();
      
       //creating Date from millisecond
       Date currentDate = new Date(currentDateTime);
      
       //printing value of Date
       //System.out.println("current Date: " + currentDate);
      
       DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
       
      
       //formatted value of current Date
       return df.format(currentDate);
    }
    public static void RunTesseract(String strCmd){
        try {
            Process p = Runtime.getRuntime().exec(strCmd);
            int waitFor = 0;
            try {
                waitFor = p.waitFor();
            } catch (InterruptedException ex) {
               System.out.println(ex.getMessage());
               ex.printStackTrace();
            }
            OutputStream outputStream = p.getOutputStream();
            
            System.out.println("Command " + strCmd + " end with " + Integer.toString(waitFor));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(IdReestr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
