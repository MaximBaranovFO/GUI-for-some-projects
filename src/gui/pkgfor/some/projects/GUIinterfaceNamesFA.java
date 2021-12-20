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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesFA {
    public class OldGUIReconstruction implements ExecutorService {
        private static int numberOfIteration;
        private static void getSystemEnvironment(){
            Map<String, String> env = System.getenv();
        }
        private static void getSystemProperty(){
            Properties properties = System.getProperties();
        }
        private static JTable getEnvArrStr(){
        String[] columnName = {"Property", "Value"};
        
        Map<String, String> sEnv = System.getenv();
        int toRetSize = sEnv.size();
        String[][] toRetStr = new String[toRetSize][2];
        
        int idx = 0;
        for(Map.Entry<String, String> itemEnv : sEnv.entrySet()){
            toRetStr[idx][0] = itemEnv.getKey();
            toRetStr[idx][1] = itemEnv.getValue();
            idx++;
        }
        
        JTable toRetTable = new JTable(toRetStr, columnName);
        return toRetTable;
    }
        private static JComponent getEnvVarTable(){
        JTable toViewTable = getEnvArrStr();
        JScrollPane toRetPane = new JScrollPane(toViewTable);
        toViewTable.setFillsViewportHeight(true);
        return toRetPane;
    }
        protected static void showModalEnvironment(JFrame mainGUI){
        String strTitle = "Environment variables";
        JComponent[] forShow = new JComponent[1];
        forShow[0] = getEnvVarTable();
        
        JOptionPane.showMessageDialog(mainGUI, forShow, strTitle, JOptionPane.INFORMATION_MESSAGE);
    }
        private static JTable getPropArrStr(){
        String[] columnName = {"Property", "Value"};
        
        Properties sProp = System.getProperties();
        Set<String> strPropName = sProp.stringPropertyNames();
        
        int toRetSize = sProp.size();
        String[][] toRetStr = new String[toRetSize][2];
        
        int idx = 0;
        for( String itemPorperties : strPropName ){
            toRetStr[idx][0] =  itemPorperties;
            toRetStr[idx][1] = sProp.getProperty(itemPorperties);
            idx++;
        }
        JTable toRetTable = new JTable(toRetStr, columnName);
        return toRetTable;
    }
        private static JComponent getPropVarTable(){
        JTable toViewTable = getPropArrStr();
        JScrollPane toRetPane = new JScrollPane(toViewTable);
        toViewTable.setFillsViewportHeight(true);
        return toRetPane;
    }
        protected static void showModalProperties(JFrame mainGUI){
        String strTitle = "System properties";
        JComponent[] forShow = new JComponent[1];
        forShow[0] = getPropVarTable();
        JOptionPane.showMessageDialog(mainGUI, forShow, strTitle, JOptionPane.INFORMATION_MESSAGE);
    }
        protected static void someGuiCreator(){
            
            /*if(numberOfIteration < 1)
                numberOfIteration = 0;
            numberOfIteration++;
            long currentTimeMillis = System.currentTimeMillis();
            String valueOf = String.valueOf(currentTimeMillis);
            GUIinterfaceNamesFB.GUIComponentObjectStatus guiComponentObjectStatus = new GUIinterfaceNamesFB.GUIComponentObjectStatus();*/
            String windowName = OldGUIReconstruction.getWindowName("some Gui addition ");
            JFrame fAnotherThreadframe = GUIinterfaceNamesF.EditedVersionWorkerForWithProviderConsumerPC.editedVersionMainRunProviderConsumer(new JFrame(windowName));
            /*guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#001^").concat(valueOf), frame);
            numberOfIteration++;
            JPanel mainPanel = new JPanel();
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#002^").concat(valueOf), mainPanel);
            numberOfIteration++;
            BorderLayout borderLayout = new BorderLayout();
            numberOfIteration++;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#003^").concat(valueOf), borderLayout);
            mainPanel.setLayout(borderLayout);
            numberOfIteration++;
            frame.getContentPane().add(mainPanel);
            Dimension dimension = new Dimension(320, 240);
            numberOfIteration++;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#001^").concat(valueOf), dimension);
            frame.setMinimumSize(dimension);
            Dimension dimension1 = new Dimension(800, 600);
            numberOfIteration++;
            guiComponentObjectStatus.putObject(String.valueOf(numberOfIteration).concat("!MainWindow#001^").concat(valueOf), dimension1);
            numberOfIteration++;
            
            frame.setPreferredSize(dimension1);
            numberOfIteration++;
            frame.repaint();
            frame.revalidate();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);*/
            //GUIinterfaceNamesF.EditedVersionWorkerForWithProviderConsumerPC.editedVersionMainRunProviderConsumer(JFrame windowForRun);
            //showModalProperties(frame);
            ///showModalEnvironment(frame);
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
    protected static String getWindowName(String strSomeNamePre){
        if(strSomeNamePre.length() < 1)
            strSomeNamePre = "|".concat(strSomeNamePre).concat("|");
        strSomeNamePre = "|".concat(strSomeNamePre).concat("|");
        if(numberOfIteration < 1)
                numberOfIteration = 0;
        numberOfIteration++;
        long currentTimeMillis = System.currentTimeMillis();
        String nowFormatedDateTime = FsListOfWorker.getNowFormatedDateTime();
        String concatStrSomeNamePre = strSomeNamePre.concat(nowFormatedDateTime).concat("|");
        String valueOf = String.valueOf(currentTimeMillis);
        return String.valueOf(numberOfIteration).concat(concatStrSomeNamePre.concat(valueOf));
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
