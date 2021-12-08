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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesFA {
    public class OldGUIReconstruction implements ExecutorService {
        protected static void someGuiCreator(){
            JFrame frame = new JFrame("some Gui addition");
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            frame.getContentPane().add(mainPanel);
            frame.setMinimumSize(new Dimension(320, 240));
            frame.setPreferredSize(new Dimension(800, 600));
            frame.repaint();
            frame.revalidate();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        protected static void ZPINcRunSIMAchanged(){
            ZPINcRunSIMA();
            createGui();
            someGuiCreator();
        }
        protected static void ZPINcRunSIMA(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Significantly improves the look of the output in
                    // terms of the file names returned by FileSystemView!
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception weTried) {
                    weTried.getMessage();
                    weTried.getStackTrace();
                }
                createGui();
                
            }
        });
    }

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.ZPINcSwingIndexManagerApp#ZPINcRunSIMA() }
     * </ul>
     */
    protected static void createGui(){
        ZPINcSwGUIComponentStatus listComponents = new ZPINcSwGUIComponentStatus();
        
        JFrame frame = new JFrame(ZPINcStrGUILabel.TITLE_APP.getStr());
        String componentPath = ZPINcSwGUIComponentRouter.pathMainFrame();
        listComponents.putComponents(componentPath, frame);  
            
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        toLALRcreateGui();
        
        JPanel mainPanel = new JPanel();
        componentPath = ZPINcSwGUIComponentRouter.pathMainFramePanel();
        listComponents.putComponents(componentPath, mainPanel);
        
        mainPanel.setLayout(new BorderLayout());
        
        frame.setJMenuBar(ZPINcSwMainMenu.getMainMenu(listComponents));
        toLALRcreateGuiPanel();
        
        mainPanel.add(ZPINcSwPanelPageEnd.getPanel(listComponents), BorderLayout.PAGE_END);
        mainPanel.add(ZPINcSwPanelCenter.getPanel(listComponents), BorderLayout.CENTER);
        mainPanel.add(ZPINcSwPanelPageStart.getPanel(listComponents), BorderLayout.PAGE_START);
        
        mainPanel.add(ZPINcSwPanelLineStart.getPanel(listComponents), BorderLayout.LINE_START);
        
        
        
        JPanel panelLineEnd = ZPINcSwPanelLineEnd.getPanel(listComponents);
        
        mainPanel.add(panelLineEnd, BorderLayout.LINE_END);


        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(320, 240));
        frame.setPreferredSize(new Dimension(800, 600));
        frame.repaint();
        frame.revalidate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        toLALRendOfCreateGUI();
    }
    private static void toLALRcreateGui(){
        if( ZPINcfvRunVariables.isLALRNcSwIdxMngAppCreateGui() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogMsgText.APP_GUI_START.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogMsgText.GUI_CREATE_JPANEL_FOR_MAIN_FRAME.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
    private static void toLALRcreateGuiPanel(){
        if( ZPINcfvRunVariables.isLALRNcSwIdxMngAppCreateGui() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogMsgText.GUI_CREATE_JPANEL_FOR_MAIN_FRAME.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_JPANEL_CENTER.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
    private static void toLALRendOfCreateGUI(){
        if( ZPINcfvRunVariables.isLALRNcSwPanelPageStartgetPanel() ){
            String strLogMsg = ZPINcStrLogMsgField.INFO.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NOW.getStr()
                + ZPINcStrLogLogicVar.LA_SET_VISIBLE_GUI.getStr()
                + ZPINcStrLogMsgField.APP_LOGIC_NEXT_WAY_VAR.getStr()
                + ZPINcStrLogLogicVar.LA_GUI_WAIT_FOR_USER_INPUT.getStr();
            ZPINcAppHelper.outMessage(strLogMsg);
        }
    }
    
    public static void doWorkForSingleWithResult(){
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future future = service.submit(new Callable(){
                public Object call() throws Exception {
                    ZPINcRunSIMAchanged();
                    System.out.println("Make GUI, with Another thread was executed");
                    return "result";
                }
            });

            try {
                System.out.println("Result: " + future.get());
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIinterfaceNamesC.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(GUIinterfaceNamesC.class.getName()).log(Level.SEVERE, null, ex);
            }
            service.shutdown();
        }

        @Override
        public void shutdown() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isShutdown() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isTerminated() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> Future<T> submit(Runnable r, T t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Future<?> submit(Runnable r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> clctn) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> clctn, long l, TimeUnit tu) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> clctn) throws InterruptedException, ExecutionException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> clctn, long l, TimeUnit tu) throws InterruptedException, ExecutionException, TimeoutException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void execute(Runnable r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
}
