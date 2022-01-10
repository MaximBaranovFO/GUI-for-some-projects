/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package concurrency;
package gui.pkgfor.some.projects;

import static gui.pkgfor.some.projects.ZPINcSwingIndexManagerApp.createGui;
import java.awt.EventQueue;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.nio.file.FileSystem;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class GUIForSomeProjects {
    public GUIForSomeProjects(){
        super();
    }
    public static void main(String[] args) {
        try {
            /**
             * Runnable r = () ->
            {
                GUIinterfaceNamesA.CreatorForGUINeedChanges creatorForGUINeedChanges = new GUIinterfaceNamesA.CreatorForGUINeedChanges();
                creatorForGUINeedChanges.InterfaceNamesAmainOfCreatorForGUINeedChanges();
            };
            EventQueue.invokeLater(r);
            **/
            final ExecutorService executor = Executors.newFixedThreadPool(1);
            Runnable r = () ->
            {
                GUIinterfaceNamesA.CreatorForGUINeedChanges creatorForGUINeedChanges = new GUIinterfaceNamesA.CreatorForGUINeedChanges();
                creatorForGUINeedChanges.InterfaceNamesAmainOfCreatorForGUINeedChanges();
            };
            executor.execute(r);
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
}
