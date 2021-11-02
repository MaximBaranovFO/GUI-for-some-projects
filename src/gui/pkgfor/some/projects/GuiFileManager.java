/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pkgfor.some.projects;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static gui.pkgfor.some.projects.guiReestr;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Администратор
 */
public class GuiFileManager {
        
    private static final String WORK_DIR = "D:/id-kms";
    
    private static final String PDF_DIR = "pdf";
    private static final String PDF_RENAMED_DIR = "pdf-renamed";
    private static final String JPG_DIR = "jpg";
    private static final String TXT_TESS_DIR = "txt-tess";
    private static final String TXT_LINGVO_DIR = "txt-lingvo";
    private static final String XLS_DIR = "xls-tess";
    private static final String XLS_LINGVO_DIR = "xls-lingvo";
    private static final String XLS_VSN_DIR = "xls-vsn";
    private static final String XLS_REPORT_DIR = "report-xls";
    private static final String PDF_REPORT_DIR = "report-pdf";
    private static final String IS_PROCESSED = "process.st";
    private static Path currentStorage;
    private static Path workContainerPath;
    
    private ConcurrentSkipListMap<Integer, Path> idinsystem;

    public GuiFileManager() {
        this.idinsystem = new ConcurrentSkipListMap<>();
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
    
    private void readListInWorkDir() {
        Path workPath = Paths.get(WORK_DIR);
        long countInDir = 0L;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(workPath)) {
        for (Path entry : stream) {
            currentStorage = entry;
            if( !isStorageProcessed(entry) ){
                setProcessStady();
            }
            addPath(entry);
            countInDir++;
        }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Can`t read count files in work directory");
        }
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
    
    private void initAndCheck(){
        Path workPath = Paths.get(WORK_DIR);
        if( !Files.exists(workPath, LinkOption.NOFOLLOW_LINKS)){
            try {
                Files.createDirectory(workPath);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("[ERROR] Can`t create work directory: " + workPath.toString());
            }
        }
        if ( !Files.isDirectory(workPath, LinkOption.NOFOLLOW_LINKS) ){
            System.out.println("[ERROR] File exist and it is not a directory: " + workPath.toString());
            throw new RuntimeException("[ERROR] File exist and it is not a directory: " + workPath.toString());
        }
        workContainerPath = workPath;
    }
    
    
    protected void makeNewStorage(){
        initAndCheck();
        
        readListInWorkDir();
        if( this.idinsystem.isEmpty() ){
            currentStorage = createProcessStorage();
            addPath(currentStorage);
            if( !isStorageProcessed(currentStorage) ){
                setProcessStady();
            }
        }

    }
    /**
     * public static String getNewProcessId(){
        long currentDateTime = System.currentTimeMillis();
      
       //creating Date from millisecond
       Date currentDate = new Date(currentDateTime);
      
       //printing value of Date
       //System.out.println("current Date: " + currentDate);
      
       DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
       
      
       //formatted value of current Date
       return df.format(currentDate);
    }
    */
    private Path createProcessStorage(){
        String newStoragePath = gui.pkgfor.some.projects.GuiReestr.getNewProcessId();
        int testInWorkDirCount = getCountPath();
            newStoragePath = newStoragePath + "-id-" + Integer.toString(testInWorkDirCount);
            Path workPath = Paths.get(WORK_DIR,newStoragePath);
            if( !Files.exists(workPath, LinkOption.NOFOLLOW_LINKS)){
                try {
                    Files.createDirectory(workPath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("[ERROR] Can`t create work directory for Storage: " + workPath.toString());
                }

        }
        return workPath;
    }
    private Boolean isContainerDir(Path innerPath){
        Path wDir = Paths.get(WORK_DIR);
        if( innerPath.compareTo(wDir) == 0 ){
            return true;
        }
        return false;
    }
    private Boolean isStorageProcessed(Path innerPath){
        
        Path isProcessed = Paths.get(innerPath.toString(),IS_PROCESSED);
        return Files.exists(isProcessed, LinkOption.NOFOLLOW_LINKS);
    }
    private Path getCheckProcessStadyPath(){
        Path isProcessed = Paths.get(currentStorage.toString(),IS_PROCESSED);
        if( Files.exists(isProcessed, LinkOption.NOFOLLOW_LINKS) ){
            pathIsNotReadWriteLink(isProcessed);
            if( !Files.isDirectory(isProcessed, LinkOption.NOFOLLOW_LINKS) ){
                return isProcessed;
            }
        }
        throw new IllegalStateException("[ERROR]Not linked file " + isProcessed.toString()
        + " need access for read, write");
    }
    private void setProcessStady(){
        
        Path isProcessed = Paths.get(currentStorage.toString(),IS_PROCESSED);

        try {
            Files.createFile(isProcessed);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t createFile " + isProcessed.toString());
        }
        //JPG_DIR
        getDirForJpg();
        //PDF_DIR
        getDirForPdf();
        //PDF_REPORT_DIR
        getDirForPdfReport();
        //TXT_TESS_DIR
        getDirForTxtTesseract();
        //XLS_DIR
        getDirForXls();
        //PDF_RENAMED_DIR
        getDirForPdfRenamed();
    }
    protected Path getDirForXls(){
       return checkOrCreateSubWorkDir(XLS_DIR);
    }
    protected Path getDirForTxtTesseract(){
       return checkOrCreateSubWorkDir(TXT_TESS_DIR);
    }
    protected Path getDirForPdfReport(){
       return checkOrCreateSubWorkDir(PDF_REPORT_DIR);
    }
    protected Path getDirForPdf(){
       return checkOrCreateSubWorkDir(PDF_DIR);
    }
    protected Path getDirForJpg(){
       return checkOrCreateSubWorkDir(JPG_DIR);
    }
    protected Path getDirForPdfRenamed(){
       return checkOrCreateSubWorkDir(PDF_RENAMED_DIR);
    }
    protected Path getCurrentStorage(){
        return currentStorage;
    }
    private Path checkOrCreateSubWorkDir(String subDirName){
         Path forCheckOrCreateDir = Paths.get(currentStorage.toString(),subDirName);
        if( Files.exists(forCheckOrCreateDir, LinkOption.NOFOLLOW_LINKS) ){
            pathIsNotReadWriteLink(forCheckOrCreateDir);
            if( Files.isDirectory(forCheckOrCreateDir, LinkOption.NOFOLLOW_LINKS) ){
                return forCheckOrCreateDir;
            }
        }
        try {
            Files.createDirectory(forCheckOrCreateDir);
            pathIsNotReadWriteLink(forCheckOrCreateDir);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t createDirectory " + forCheckOrCreateDir.toString());
        }
        return forCheckOrCreateDir;
    }
    protected ArrayList<Path> listFilesInWorkPdfRenamedDir() {
        ArrayList<Path> listToReturn = new ArrayList<>();
        Path workPath = getDirForPdfRenamed();
        System.out.println("Storage contained in PDF-RENAMED directory " + workPath.toString());
        System.out.println("files: ");
        int count = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(workPath,"*.{pdf}")) {
        for (Path entry : stream) {
            System.out.println(entry.toString());
            count++;
            listToReturn.add(entry);
        }
        if( count == 0 ){
            System.out.println("Directory is Empty, put some pdf files into " + workPath.toString());
        }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Can`t read count files in work directory");
        }
        return listToReturn;
    }
    protected ArrayList<Path> listFilesInWorkJpegDir() {
        ArrayList<Path> listToReturn = new ArrayList<>();
        Path workPath = getDirForJpg();
        System.out.println("Storage contained in JPG directory " + workPath.toString());
        System.out.println("files: ");
        int count = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(workPath,"*.{jpg}")) {
        for (Path entry : stream) {
            System.out.println(entry.toString());
            count++;
            listToReturn.add(entry);
        }
        if( count == 0 ){
            System.out.println("Directory is Empty, put some jpeg files into " + workPath.toString());
        }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Can`t read count files in work directory");
        }
        return listToReturn;
    }
    protected ArrayList<Path> listFilesInWorkTxtTesseractDir() {
        ArrayList<Path> listToReturn = new ArrayList<>();
        Path workPath = getDirForTxtTesseract();
        System.out.println("Storage contained in TXT_TESS directory " + workPath.toString());
        System.out.println("files: ");
        int count = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(workPath,"*.{txt}")) {
        for (Path entry : stream) {
            System.out.println(entry.toString());
            count++;
            listToReturn.add(entry);
        }
        if( count == 0 ){
            System.out.println("Directory is Empty, put some txt files into " + workPath.toString());
        }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Can`t read count files in work directory");
        }
        return listToReturn;
    }
    
    protected void listFilesInWorkPdfDir() {
        Path workPath = getDirForPdf();
        System.out.println("Storage contained in PDF directory " + workPath.toString());
        System.out.println("files: ");
        int count = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(workPath,"*.{pdf}")) {
        for (Path entry : stream) {
            System.out.println(entry.toString());
            count++;
            Path dirForRename = getDirForPdfRenamed();
            Path destinationPath = Paths.get(dirForRename.toString(), gui.pkgfor.some.projects.GuiReestr.getNewProcessId() + "-id-" + count + ".pdf");
            copyFileFromSrcToDest(entry, destinationPath);
            writeStady("[STADYPDFRENAME][SRCFILE]" + entry
                    + "[DSTFILE]"
                    + destinationPath
                    + "[COPYFILE][STARTAT]" 
                    + gui.pkgfor.some.projects.GuiReestr.getNewProcessId()
            );
        }
        if( count == 0 ){
            System.out.println("Directory is Empty, put some pdf files into " + workPath.toString());
        }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Can`t read count files in work directory");
        }
    }
    private void writeStady(String strSatdy){
        Path checkProcessStadyPath = getCheckProcessStadyPath();
        List<String> lignes = new ArrayList<>();
        try {
            lignes.addAll(Files.readAllLines(checkProcessStadyPath, Charset.forName("UTF-8")));
        } catch (IOException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        lignes.add(strSatdy);
        try {
            Files.write(checkProcessStadyPath, lignes, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    private void copyFileFromSrcToDest(Path srcPath, Path destPath){
        try {
            Files.copy(srcPath, destPath, REPLACE_EXISTING, COPY_ATTRIBUTES);
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t copy files from "
            + srcPath.toString()
            + " to " + destPath.toString());
        } catch (FileAlreadyExistsException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t copy files from "
            + srcPath.toString()
            + " to " + destPath.toString());
        } catch (DirectoryNotEmptyException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t copy files from "
            + srcPath.toString()
            + " to " + destPath.toString());
        } catch (SecurityException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t copy files from "
            + srcPath.toString()
            + " to " + destPath.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("[ERROR] Can`t copy files from "
            + srcPath.toString()
            + " to " + destPath.toString());
        }
    }
}
