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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

/**
 *
 * @author Администратор
 */
//GUIinterfaceNamesA.CreatorForGUINeedChanges();
public interface GUIinterfaceNamesA {
    public class CreatorForGUINeedChanges  extends JFrame 
                  implements ActionListener  {
    private static final GUIinterfaceNamesE.GuiFromWindowAttr guiFromWindowAttrCurrent = new GUIinterfaceNamesE.GuiFromWindowAttr();
    /**
     * Some editing and add code to app (Seaacta)
     * AFSO - A(Add) F(From) S(Siberia) O(Omsk)
     * Seaacta000000
     */
    private final GridBagLayout layofelementsAFSO;
    //private final javax.swing.JTabbedPane tabbedPaneAFSO;
    //private final javax.swing.JPanel panelAFSO;
    //***   ***   ***   000000   ***   ***   ***
    /**
     * Code from examples by oracle
     */
    private final GridBagConstraints constraints;
    private final JTextField headsText, totalText, devText;
    private final Border border =
        BorderFactory.createLoweredBevelBorder();
    private final JButton startButton, stopButton;
    /**
     * GUIForSomeProjectsTask gfsp - g(GUI)f(For)s(Some)p(Projects)
    */
    private GUIForSomeProjectsTask gfspTask;

    private JTextField makeText() {
        JTextField t = new JTextField(20);
        t.setEditable(false);
        t.setHorizontalAlignment(JTextField.RIGHT);
        t.setBorder(border);
        getContentPane().add(t, constraints);
        return t;
    }

    private JButton makeButton(String caption) {
        JButton b = new JButton(caption);
        b.setActionCommand(caption);
        b.addActionListener(this);
        getContentPane().add(b, constraints);
        return b;
    }


    public CreatorForGUINeedChanges() {
        super("CreatorForGUINeedChanges");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * Seaacta000001
         */
        JButton forAdditionsButton = new JButton("Seaacta000001");
        layofelementsAFSO = new GridBagLayout();
        layofelementsAFSO.addLayoutComponent("Seaacta000001", forAdditionsButton);
        //***   ***   ***   000001   ***   ***   ***
        //Make text boxes
        getContentPane().setLayout(layofelementsAFSO);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(3, 10, 3, 10);
        headsText = makeText();
        totalText = makeText();
        devText = makeText();

        //Make buttons
        startButton = makeButton("Start");
        stopButton = makeButton("Stop");
        stopButton.setEnabled(false);

        /**
         * Seaacta000002
         */
        this.addImpl(forAdditionsButton, constraints, constraints.gridwidth);
        //***   ***   ***   000002   ***   ***   ***
        //Display the window.
        pack();
        setVisible(true);
    }

    private static class GUIForSomeProjectsPair {
        private final long heads, total;
        GUIForSomeProjectsPair(long heads, long total) {
            this.heads = heads;
            this.total = total;
        }
    }

    private class GUIForSomeProjectsTask extends SwingWorker<Void, GUIForSomeProjectsPair> {
        @Override
        protected Void doInBackground() {
            long heads = 0;
            long total = 0;
            Random random = new Random();
            while (!isCancelled()) {
                total++;
                if (random.nextBoolean()) {
                    heads++;
                }
                publish(new GUIForSomeProjectsPair(heads, total));
            }
            return null;
        }

        @Override
        protected void process(List<GUIForSomeProjectsPair> pairs) {
            GUIForSomeProjectsPair pair = pairs.get(pairs.size() - 1);
            headsText.setText(String.format("%d", pair.heads));
            totalText.setText(String.format("%d", pair.total));
            devText.setText(String.format("%.10g", 
                    ((double) pair.heads)/((double) pair.total) - 0.5));
        }
    }



    public void actionPerformed(ActionEvent e) {
        if ("Start" == e.getActionCommand()) {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            (gfspTask = new GUIForSomeProjectsTask()).execute();
        } else if ("Stop" == e.getActionCommand()) {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            gfspTask.cancel(true);
            gfspTask = null;
        }

    }
    /**
     * @param args the command line arguments
     */
    public static void InterfaceNamesAmainOfCreatorForGUINeedChanges() {
        GUIinterfaceNamesFC.someAdditionsForInterface.functForDoAdditions();
        runPreIterations();
        runSwingUtilitesIterations();
        //ZPINcSwingIndexManagerApp.ZPINcRunSIMA();
        GUIManagerInterface.builderSoft();
        
    }
    protected static void runPreIterations(){
        
        
        runModifiedWithThreadsFive();
        
        try {
            GUIinterfaceNamesB.MultithreadClient.mainFromPoolWorker();
        } catch (ExecutionException ex) {
            Logger.getLogger(GUIForSomeProjects.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUIForSomeProjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GUIinterfaceNamesB.SingleThreadClient.mainFromSingleThreadClient();
        
        runModifiedWithThreadsFour();
        
        
        
        runModifiedWithThreadsTwo();
        
        //Create GUI
        runModifiedWithThreads();
        
        runModifiedWithThreadsOne();
    }
    
    protected static void runModifiedWithThreadsOne(){
        try {
            
            final ExecutorService executorForSomeTwo = Executors.newFixedThreadPool(1);
            Runnable runSomeTwo = () ->
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUIinterfaceNamesD.MainDoWorkerByAuthorsFromNet.doGUIForWorkerControl();
                    }
                });
            };
            runThreadPoolWithExceptions(executorForSomeTwo,runSomeTwo);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    protected static void runModifiedWithThreadsTwo(){
        try {
            
            final ExecutorService executorForSomeThree = Executors.newFixedThreadPool(1);
            Runnable runSomeThree = () ->
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUIinterfaceNamesD.MainDoWorkerByAuthorsFromNet.mainDoWorkerByAuthorsFromNet();
                        GUIinterfaceNamesD.mainDoWorkerByAuthorsFromNetWithForkPool();
                        GUIinterfaceNamesD.mainDoWorkerByAuthorsFromNetWithFork();
                    }
                });
            };
            runThreadPoolWithExceptions(executorForSomeThree,runSomeThree);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    protected static void runModifiedWithThreadsFour(){
        try {
            
            final ExecutorService executorForSomeFour = Executors.newFixedThreadPool(1);
            Runnable runSomeFour = () ->
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        
                        GUIinterfaceNamesC.MotivatedByNetWorkInformation.singleAboutAuthors();
                        GUIinterfaceNamesC.MotivatedByNetWorkInformation.doWorkForSingleWithResult();
                        GUIinterfaceNamesC.MotivatedByNetWorkInformation.doWorkForSingleWithContol();
                    }
                });
            };
            runThreadPoolWithExceptions(executorForSomeFour,runSomeFour);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    protected static void runModifiedWithThreadsFive(){
        try {
            
            final ExecutorService executorForSomeFive = Executors.newFixedThreadPool(1);
            Runnable runSomeFive = () ->
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        
                        //Create GUI
                        GUIinterfaceNamesFA.OldGUIReconstruction.doWorkForSingleWithResult();
                        GUIinterfaceNamesFA.OldGUIReconstruction.someGuiCreator();
                        //Add console workers
                        GUIsomenamesB frockedFieldWorkers = new GUIsomenamesB();
                        frockedFieldWorkers.doCreationTaskHowMain();
                        
                    }
                });
            };
            runThreadPoolWithExceptions(executorForSomeFive,runSomeFive);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    protected static void runModifiedWithThreads(){
        try {
            
            final ExecutorService executorForSomeOne = Executors.newFixedThreadPool(1);
            Runnable runSomeOne = () ->
            {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GUIinterfaceNamesFA.OldGUIReconstruction.someGuiCreator();
                    }
                });
            };
            runThreadPoolWithExceptions(executorForSomeOne,runSomeOne);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    protected static void runSwingUtilitesIterations(){
        runInEcecutorServiceOneThree();
        
    }
    protected static void runInEcecutorServiceOneThree(){
        try {
            
            final ExecutorService executor = Executors.newFixedThreadPool(4);
            Runnable rOne = () ->
            {
                runIntefaceACreatorGUINeedChanges();
            };
            //executor.execute(rOne);
            runThreadPoolWithExceptions(executor,rOne);
            Runnable rTwo = () ->
            {
                
                runGuiGridBagAndHelper();
        
            };
            //executor.execute(rTwo);
            runThreadPoolWithExceptions(executor,rTwo);
            Runnable rThree = () ->
            {
                
                runGuiTableTree();
        
            };
            //executor.execute(rThree);
            runThreadPoolWithExceptions(executor,rThree);
            Runnable rFour = () ->
            {
                
                runThSimpleCR();
            };
            //executor.execute(rFour);
            runThreadPoolWithExceptions(executor,rFour);
        } catch (ClassCastException exKeyCanNotWithKeysList) {
                System.out.println(exKeyCanNotWithKeysList.getMessage());
                exKeyCanNotWithKeysList.printStackTrace();
                
        } catch (NullPointerException exNullValInKeyOrVal) {
                System.out.println(exNullValInKeyOrVal.getMessage());
                exNullValInKeyOrVal.printStackTrace();
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    protected static void runThreadPoolWithExceptions(ExecutorService executorGlobal, Runnable runTwoElement){
        try {
            executorGlobal.execute(runTwoElement);
        } catch (ThreadDeath exThreadExcept){
            System.out.println(exThreadExcept.getMessage());
            exThreadExcept.printStackTrace();
        } catch (VirtualMachineError exVMWare){
            System.out.println(exVMWare.getMessage());
            exVMWare.printStackTrace();
        } catch (Error exError){
            System.out.println(exError.getMessage());
            exError.printStackTrace();
        }
    }
    protected static void runIntefaceACreatorGUINeedChanges(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIinterfaceNamesA.CreatorForGUINeedChanges guiForSomeProjects = new GUIinterfaceNamesA.CreatorForGUINeedChanges();
                guiFromWindowAttrCurrent.correctTitleOfGUIWindow(guiForSomeProjects);
            }
        });
    }
    protected static void runGuiGridBagAndHelper(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GuiGridBagAndHelper guiGridBagAndHelper = new GuiGridBagAndHelper();
                guiFromWindowAttrCurrent.correctTitleOfGUIWindow(guiGridBagAndHelper.getWindowOneByExtended());
            }
        });
    }
    protected static void runGuiTableTree(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GuiTableTree guiTableTree = new GuiTableTree();
                guiTableTree.openAndShow();
                guiFromWindowAttrCurrent.correctTitleOfGUIWindow(guiTableTree.getWindowCreated());
            }
        });
    }
    protected static void runThSimpleCR(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Simple code has thread name and not have JFrame
                ZPIThSimpleCR threadSimpleCodeRunForEnd = new ZPIThSimpleCR();
                threadSimpleCodeRunForEnd.runSimpleClassRunnableStudy();
                //guiFromWindowAttrCurrent.correctTitleOfGUIWindow(threadSimpleCodeRunForEnd);
            }
        });
    }
}
}
