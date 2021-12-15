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
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author Администратор
 */
public interface GUIinterfaceNamesFC {
        public class someAdditionsForInterface{
        protected static void functForDoAdditions(){
              Runnable r = () ->
      {
         final JFrame frame = new JFrame("View Page");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JPanel panel = new JPanel();
         panel.add(new JLabel("Enter URL"));
         final JTextField txtURL = new JTextField(40);
         panel.add(txtURL);
         frame.getContentPane().add(panel, BorderLayout.NORTH);
         final JTextArea txtHTML = new JTextArea(10, 40);
         frame.getContentPane().add(new JScrollPane (txtHTML), 
                                    BorderLayout.CENTER);
         ActionListener al = (ae) ->
         {
            txtURL.setEnabled(false);
            class GetHTML extends SwingWorker<StringBuilder, Void>
            {
               private final String url;

               GetHTML(String url)
               {
                  this.url = url;
               }

               @Override
               public StringBuilder doInBackground()
               {
                  StringBuilder sb = new StringBuilder();
                  InputStream is = null;
                  try
                  {
                     URL url = new URL(this.url);
                     is = url.openStream();
                     int b;
                     while ((b = is.read()) != -1)
                        sb.append((char) b);
                     return sb;
                  }
                  catch (IOException ioe)
                  {
                     sb.setLength(0);
                     sb.append(ioe.getMessage());
                     return sb;
                  }
                  finally
                  {
                     if (is != null)
                        try
                        {
                           is.close();
                        }
                        catch (IOException ioe)
                        {
                        }
                  }
               }

               @Override
               public void done()
               {
                  try
                  {
                     StringBuilder sb = get();
                     txtHTML.setText(sb.toString());
                     txtHTML.setCaretPosition(0);
                  }
                  catch (ExecutionException ee)
                  {
                     txtHTML.setText(ee.getMessage());
                  }
                  catch (InterruptedException ie)
                  {
                     txtHTML.setText("Interrupted");
                  }
                  txtURL.setEnabled(true);
               }
            }
            new GetHTML(txtURL.getText()).execute();
         };
         txtURL.addActionListener(al);
         GUIinterfaceNamesE.GuiFromWindowAttr.correctTitleOfGUIWindow(frame);
         frame.pack();
         frame.setVisible(true);
      };
      EventQueue.invokeLater(r);
        }
    }
}
