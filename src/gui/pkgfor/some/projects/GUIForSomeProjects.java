/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package concurrency;
package gui.pkgfor.some.projects;

import static gui.pkgfor.some.projects.ZPINcSwingIndexManagerApp.createGui;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.nio.file.FileSystem;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
//import javax.swing.*;
/**
 *
 * @author Администратор
 */
public class GUIForSomeProjects extends JFrame 
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


    public GUIForSomeProjects() {
        super("GUIForSomeProjects");
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
    public static void main(String[] args) {
        GUIinterfaceNamesFC.someAdditionsForInterface.functForDoAdditions();
        runPreIterations();
        runSwingUtilitesIterations();
        //ZPINcSwingIndexManagerApp.ZPINcRunSIMA();
        GUIManagerInterface.builderSoft();
        
    }
    protected static void runPreIterations(){
        //Create GUI
        GUIinterfaceNamesFA.OldGUIReconstruction.doWorkForSingleWithResult();
        GUIinterfaceNamesFA.OldGUIReconstruction.someGuiCreator();
        //Add console workers
        GUIsomenamesB frockedFieldWorkers = new GUIsomenamesB();
        frockedFieldWorkers.doCreationTaskHowMain();
        
        try {
            GUIinterfaceNamesB.MultithreadClient.mainFromPoolWorker();
        } catch (ExecutionException ex) {
            Logger.getLogger(GUIForSomeProjects.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUIForSomeProjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GUIinterfaceNamesB.SingleThreadClient.mainFromSingleThreadClient();
        
        GUIinterfaceNamesC.MotivatedByNetWorkInformation.singleAboutAuthors();
        GUIinterfaceNamesC.MotivatedByNetWorkInformation.doWorkForSingleWithResult();
        GUIinterfaceNamesC.MotivatedByNetWorkInformation.doWorkForSingleWithContol();
        
        GUIinterfaceNamesD.MainDoWorkerByAuthorsFromNet.mainDoWorkerByAuthorsFromNet();
        GUIinterfaceNamesD.mainDoWorkerByAuthorsFromNetWithForkPool();
        GUIinterfaceNamesD.mainDoWorkerByAuthorsFromNetWithFork();
        //Create GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIinterfaceNamesFA.OldGUIReconstruction.someGuiCreator();
            }
        });
        GUIinterfaceNamesD.MainDoWorkerByAuthorsFromNet.doGUIForWorkerControl();
    }
    protected static void runSwingUtilitesIterations(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIForSomeProjects guiForSomeProjects = new GUIForSomeProjects();
                guiFromWindowAttrCurrent.correctTitleOfGUIWindow(guiForSomeProjects);
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GuiGridBagAndHelper guiGridBagAndHelper = new GuiGridBagAndHelper();
                guiFromWindowAttrCurrent.correctTitleOfGUIWindow(guiGridBagAndHelper.getWindowOneByExtended());
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GuiTableTree guiTableTree = new GuiTableTree();
                guiTableTree.openAndShow();
                guiFromWindowAttrCurrent.correctTitleOfGUIWindow(guiTableTree.getWindowCreated());
            }
        });
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
