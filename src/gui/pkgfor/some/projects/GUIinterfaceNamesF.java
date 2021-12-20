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

import gui.pkgfor.some.projects.GUIinterfaceNamesFA.OldGUIReconstruction;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesF {


public class WorkerForWithProviderConsumerPC
{
   public static void mainRunProviderConsumer()
   {
      final BlockingQueue<Character> bq;
      bq = new ArrayBlockingQueue<Character>(26);
      final ExecutorService executor = Executors.newFixedThreadPool(2);
      Runnable producer = () ->
                          {
                             for (char ch = 'A'; ch <= 'Z'; ch++)
                             {
                                try
                                {
                                   bq.put(ch);
                                   System.out.printf("%c produced by " +
                                                     "producer.%n at %s", ch, Thread.currentThread().getName(), System.currentTimeMillis());
                                }
                                catch (InterruptedException ie)
                                {
                                }
                             }
                          };
      executor.execute(producer);
      Runnable consumer = () ->
                          {
                             char ch = '\0';
                             do
                             {
                                try
                                {
                                   ch = bq.take();
                                   System.out.printf("%c consumed by " +
                                                     "consumer.%n at %s", ch, Thread.currentThread().getName(), System.currentTimeMillis());
                                }
                                catch (InterruptedException ie)
                                {
                                }
                             }
                             while (ch != 'Z');
                             executor.shutdownNow();
                          };
      executor.execute(consumer);
   }
 }
//GUIinterfaceNamesF.EditedVersionWorkerForWithProviderConsumerPC.editedVersionMainRunProviderConsumer(JFrame windowForRun);
public class EditedVersionWorkerForWithProviderConsumerPC
{
   public static JFrame editedVersionMainRunProviderConsumer(JFrame windowForRun)
   {
      final BlockingQueue<Character> bq;
      bq = new ArrayBlockingQueue<Character>(26);
      final ExecutorService executor = Executors.newFixedThreadPool(2);
      final JFrame valueForThreadedWorkerRun;
      valueForThreadedWorkerRun = windowForRun;
      Runnable producer = () ->
                          {
                             for (char ch = 'A'; ch <= 'Z'; ch++)
                             {
                                try
                                {
                                   bq.put(ch);
                                   Class<? extends JFrame> aClass = valueForThreadedWorkerRun.getClass();
                                   System.out.printf("%c produced by " +
                                                     "producer.%n at %s", ch, Thread.currentThread().getName(), System.currentTimeMillis());
                                }
                                catch (InterruptedException ie)
                                {
                                }
                             }
                          };
                              executor.execute(producer);
                              Runnable consumer = () ->
                          {
                             char ch = '\0';
                             do
                             {
                                try
                                {
                                   ch = bq.take();
                                    int eVnumberOfIteration = 0;
                                   
                                   if(eVnumberOfIteration < 1)
                                        eVnumberOfIteration = 0;
                                   eVnumberOfIteration++;
                                   long eVcurrentTimeMillis = System.currentTimeMillis();
                                    String eVvalueOf = String.valueOf(eVcurrentTimeMillis);
                                    GUIinterfaceNamesFB.GUIComponentObjectStatus eVguiComponentObjectStatus = new GUIinterfaceNamesFB.GUIComponentObjectStatus();
                                    String eVwindowName = OldGUIReconstruction.getWindowName("some Gui addition ");
                                   
                                   JFrame eVframe = valueForThreadedWorkerRun;
                                   
                                   Class<? extends JFrame> aClass = valueForThreadedWorkerRun.getClass();
                                   eVguiComponentObjectStatus.putObject(String.valueOf(eVnumberOfIteration).concat("!MainWindow#001^").concat(eVvalueOf), eVframe);
                                    eVnumberOfIteration++;
                                    JPanel eVmainPanel = new JPanel();
                                    eVguiComponentObjectStatus.putObject(String.valueOf(eVnumberOfIteration).concat("!MainWindow#002^").concat(eVvalueOf), eVmainPanel);
                                    eVnumberOfIteration++;
                                    BorderLayout eVborderLayout = new BorderLayout();
                                    eVnumberOfIteration++;
                                    eVguiComponentObjectStatus.putObject(String.valueOf(eVnumberOfIteration).concat("!MainWindow#003^").concat(eVvalueOf), eVborderLayout);
                                    eVmainPanel.setLayout(eVborderLayout);
                                    eVnumberOfIteration++;
                                    eVframe.getContentPane().add(eVmainPanel);
                                    Dimension eVdimension = new Dimension(320, 240);
                                    eVnumberOfIteration++;
                                    eVguiComponentObjectStatus.putObject(String.valueOf(eVnumberOfIteration).concat("!MainWindow#001^").concat(eVvalueOf), eVdimension);
                                    eVframe.setMinimumSize(eVdimension);
                                    Dimension eVdimension1 = new Dimension(800, 600);
                                    eVnumberOfIteration++;
                                    eVguiComponentObjectStatus.putObject(String.valueOf(eVnumberOfIteration).concat("!MainWindow#001^").concat(eVvalueOf), eVdimension1);
                                    eVnumberOfIteration++;
                                    
                                    eVframe.setPreferredSize(eVdimension1);
                                    eVnumberOfIteration++;
                                    eVframe.repaint();
                                    eVframe.revalidate();
                                    eVframe.pack();
                                    eVframe.setLocationRelativeTo(null);
                                    eVframe.setVisible(true);
            
                                    try
                                    {
                                          final ExecutorService executorWorkerSun = Executors.newFixedThreadPool(2);
                                          
                                          Runnable consumerSunOne = () ->
                                            {
                                                showModalProperties(eVframe);
                                            };
                                          executorWorkerSun.execute(consumerSunOne);
                                          Runnable consumerSunTwo = () ->
                                            {
                                                showModalEnvironment(eVframe);
                                            };
                                          executorWorkerSun.execute(consumerSunTwo);
                                          executorWorkerSun.shutdownNow();
            }
                                      catch (Exception ie)
                                      {
                                      }
                                         System.out.printf("%c consumed by " +
                                                     "consumer.%n at %s", ch, Thread.currentThread().getName(), System.currentTimeMillis());
                                }
                                catch (InterruptedException ie)
                                {
                                }
                             }
                             while (ch != 'Z');
                             executor.shutdownNow();
                          };
      executor.execute(consumer);
      return valueForThreadedWorkerRun;
   }
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
        String eVwindowNameSunOfWorker = OldGUIReconstruction.getWindowName(strTitle);
        JComponent[] forShow = new JComponent[1];
        forShow[0] = getEnvVarTable();
        
        JOptionPane.showMessageDialog(mainGUI, forShow, eVwindowNameSunOfWorker, JOptionPane.INFORMATION_MESSAGE);
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
            String eVwindowNameSunOfWorker = OldGUIReconstruction.getWindowName(strTitle);
            JComponent[] forShow = new JComponent[1];
            forShow[0] = getPropVarTable();
            JOptionPane.showMessageDialog(mainGUI, forShow, eVwindowNameSunOfWorker, JOptionPane.INFORMATION_MESSAGE);
        }
 }

}
