/*
 * Copyright 2021 Администратор.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gui.pkgfor.some.projects;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Администратор
 */
public class FsWorkerDirFiles {
    private static Integer COUNT_USED_ITERATIONS;
    private static final String WORK_DIR = "C:\\_bmv\\id-jao";
    private static final String XLS_DIR = "xls-tess";
    
    private static final String IS_PROCESSED = "process.st";
    private ConcurrentSkipListMap<Integer, Path> idinsystem;
    
    private static Path currentStorage;
    private static Path workContainerPath;
            
    private static FsListOfWorker fsListOfWorker;
    
    public FsWorkerDirFiles() {
        COUNT_USED_ITERATIONS = 0;
        this.idinsystem = new ConcurrentSkipListMap<>();
        initAndCheck();
        this.currentStorage = this.workContainerPath;
        fsListOfWorker = new FsListOfWorker();
        fsListOfWorker.initOfListWatcher();
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
    
    private Boolean isStorageProcessed(Path innerPath){
        
        Path isProcessed = Paths.get(innerPath.toString(),IS_PROCESSED);
        return Files.exists(isProcessed, LinkOption.NOFOLLOW_LINKS);
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
    protected Path checkOrCreateFileInSubWorkDirWithObject(String subDirName, Object writeObjectIntoDir){
        String propertyClassPath = System.getProperty("java.class.path");
        Path getPropertyClassPath = Paths.get(propertyClassPath);
        Path parentPropertyClassPath = getPropertyClassPath.getParent();
        if(subDirName == null)
            subDirName = parentPropertyClassPath.toString();
        Path forCheckOrCreateDir = Paths.get(subDirName);
        if( Files.exists(forCheckOrCreateDir, LinkOption.NOFOLLOW_LINKS) ){
            pathIsNotReadWriteLink(forCheckOrCreateDir);
            if( Files.isDirectory(forCheckOrCreateDir, LinkOption.NOFOLLOW_LINKS) ){
                
                COUNT_USED_ITERATIONS++;
                
                Properties properties = System.getProperties();
                Map<String, String> env = System.getenv();
                
                long nanoTime = System.nanoTime();
                String stringNanoTime = new String();
                long currentTimeMillis = System.currentTimeMillis();
                
                DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
                
                String tfgnowFormatedDateTime = fsListOfWorker.getNowFormatedDateTime();
                
                String intoDirVsFileName = subDirName.concat("\\").concat(
                        String.valueOf(tfgnowFormatedDateTime.concat("_")
                                .concat(String.valueOf(COUNT_USED_ITERATIONS))
                        ));
                
                if(intoDirVsFileName == null)
                    intoDirVsFileName = subDirName;
                FsWriteReadToSubDir tmpOperationsOnObjects = new FsWriteReadToSubDir();
                tmpOperationsOnObjects.writeToSubDir(intoDirVsFileName, writeObjectIntoDir);
                
                Runtime.getRuntime().gc();
                System.gc();
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
    protected Path getDirForXls(){
        ArrayList<String> somePervousDir = new ArrayList();
        somePervousDir.add(XLS_DIR);
        somePervousDir.add("asis_object");
        somePervousDir.add("asis_serialized");
        somePervousDir.add("asis_string");
        for(String item : somePervousDir){
            Path checkOrCreateSubWorkDirNowElement = checkOrCreateSubWorkDir(item);
        }
       return checkOrCreateSubWorkDir(dirFromSomeVar());
    }
    private String dirFromSomeVar(){
        return XLS_DIR;
    }
    private void addPath(Path innerPath){
        pathIsNotReadWriteLink(innerPath);
        this.idinsystem.put(innerPath.hashCode(), innerPath);
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
        /*getDirForJpg();*/
        //PDF_DIR
        /*getDirForPdf();*/
        //PDF_REPORT_DIR
        /*getDirForPdfReport();*/
        //TXT_TESS_DIR
        /*getDirForTxtTesseract();*/
        //XLS_DIR
        getDirForXls();
        //PDF_RENAMED_DIR
        /*getDirForPdfRenamed();*/
    }
    /**
     C:\_bmv>java -jar "C:\_bmv\nb-learn\java\GUI for some projects\dist\GUI_for_some_projects.jar"
эю с. 07, 2021 2:16:39 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:48)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:16:40 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
                at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)


C:\_bmv>java -jar "C:\_bmv\nb-learn\java\GUI for some projects\dist\GUI_for_some_projects.jar"
эю с. 07, 2021 2:00:48 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:137)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:48)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:00:48 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:137)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:00:48 PM gui.pkgfor.some.projects.FsWriteReadToSubDir writeToSubDir
SEVERE: null
java.io.NotSerializableException: gui.pkgfor.some.projects.GuiGridBagTwo
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1197)
        at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:354)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:66)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:137)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)


C:\_bmv>java -jar "C:\_bmv\nb-learn\java\GUI for some projects\dist\GUI_for_some_projects.jar"
эю с. 07, 2021 2:03:11 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:138)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:48)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:03:12 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:138)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:03:12 PM gui.pkgfor.some.projects.FsWriteReadToSubDir writeToSubDir
SEVERE: null
java.io.NotSerializableException: gui.pkgfor.some.projects.GuiGridBagTwo
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1197)
        at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:354)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:66)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:138)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)


C:\_bmv>java -jar "C:\_bmv\nb-learn\java\GUI for some projects\dist\GUI_for_some_projects.jar"
эю с. 07, 2021 2:16:39 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:48)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:16:40 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:16:40 PM gui.pkgfor.some.projects.FsWriteReadToSubDir writeToSubDir
SEVERE: null
java.io.NotSerializableException: gui.pkgfor.some.projects.GuiGridBagTwo
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1197)
        at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:354)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:66)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

     
     
     */
    
    
    /*
    
C:\_bmv>java -jar "C:\_bmv\nb-learn\java\GUI for some projects\dist\GUI_for_some_projects.jar"
эю с. 07, 2021 2:16:39 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:48)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:16:40 PM gui.pkgfor.some.projects.FsWriteReadToSubDir operationOnOutputObjects
SEVERE: null
java.io.NotActiveException: not in call to writeObject
        at java.base/java.io.ObjectOutputStream.putFields(ObjectOutputStream.java:465)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.operationOnOutputObjects(FsWriteReadToSubDir
.java:44)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:62)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)

эю с. 07, 2021 2:16:40 PM gui.pkgfor.some.projects.FsWriteReadToSubDir writeToSubDir
SEVERE: null
java.io.NotSerializableException: gui.pkgfor.some.projects.GuiGridBagTwo
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1197)
        at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:354)
        at gui.pkgfor.some.projects.FsWriteReadToSubDir.writeToSubDir(FsWriteReadToSubDir.java:66)
        at gui.pkgfor.some.projects.FsWorkerDirFiles.checkOrCreateFileInSubWorkDirWithObject(FsWorke
rDirFiles.java:144)
        at gui.pkgfor.some.projects.GuiGridBagAndHelper.writeObjectToFs(GuiGridBagAndHelper.java:49)

        at gui.pkgfor.some.projects.GuiGridBagAndHelper.<init>(GuiGridBagAndHelper.java:41)
        at gui.pkgfor.some.projects.GUIForSomeProjects$2.run(GUIForSomeProjects.java:164)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:771)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:722)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:716)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(P
rotectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:741)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java
:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:12
4)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java
:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)


    */
}
