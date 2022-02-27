/*
 * Copyright 2022 Администратор.
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
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.WindowEvent;

/**
 *
 * @author Администратор
 */
public interface BasedOnSeventinInterface0A {
    
    
public class logWindow extends Frame{ /*Создаем подкласс logWindow класса Frame*/
public logWindow () /*Конструктор класса*/
{
super("Логгер");
/*Вызываем конструктор суперкласса и передаем ему параметр, в
данном случае имя программы*/
setSize (200,200); /*Метод суперкласса для установкиразмеров окна, в пикселях*/
//
/*Создаем объекты*/
Button myButton = new Button ("Мониторинг"); 
/*Создаем кнопку и надпись на ней*/
Label myLabel = new Label ("Данные логгера");
/* Создаем текстовое поле и надпись в нем*/
add (myLabel, BorderLayout.NORTH); 
/* С помощью менеджера размещения, располагаем
текстовое поле в северной части окна*/
add (myButton, BorderLayout.SOUTH); /*Кнопку в южной части*/
myButton.addActionListener (new ActionListener () { /*Для кнопки выбираем событие
слушателя, и создаем новое событие в скобках.*/
public void actionPerformed (ActionEvent e) {
myLabel.setText ("Мониторинг"); /*Выполняется действие, т.е. при нажатии на кнопку
в поле выводится сообщение «Мониторинг» */
}
});
} 
public static void BasedOnSeventinMain (String[] args) { //Точка входа программы
logWindow log = new logWindow (); //Создаем объект класса
log.setVisible (true); //Устанавливаем видимость окна
/*Наше окно запускается и отображается, при нажатии на кнопку меняется надпись в
текстовом поле. Что бы закрыть окно необходимо добавить код обработки события, который
работает следующим образом: мы вызываем для объекта log метод addWindowListener для того,
чтобы назначить слушателя оконных событий. В качестве параметра создаем объект
абстрактного класса WindowAdapter, в котором создаем класс и переопределяем метод для
обработки события закрытия окна - dispose.*/
log.addWindowListener (new WindowAdapter () {
public void windowClosing (WindowEvent e) { // в качестве аргумента передаем событие
e.getWindow ().dispose (); // уничтожает объект Frame
}
});
}
}
    
    public class ExamplesFromNet{
            protected static void exampleCreateGUImain(){
            logWindow log = new logWindow (); //Создаем объект класса
log.setVisible (true); //Устанавливаем видимость окна
/*Наше окно запускается и отображается, при нажатии на кнопку меняется надпись в
текстовом поле. Что бы закрыть окно необходимо добавить код обработки события, который
работает следующим образом: мы вызываем для объекта log метод addWindowListener для того,
чтобы назначить слушателя оконных событий. В качестве параметра создаем объект
абстрактного класса WindowAdapter, в котором создаем класс и переопределяем метод для
обработки события закрытия окна - dispose.*/
log.addWindowListener (new WindowAdapter () {
public void windowClosing (WindowEvent e) { // в качестве аргумента передаем событие
e.getWindow ().dispose (); // уничтожает объект Frame
}
});
    }
    
    public class LearnSventin implements ExecutorService {
        private static int numberOfIteration;

        @Override
        public void shutdown() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isShutdown() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isTerminated() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Future<?> submit(Runnable task) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void execute(Runnable command) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        }
        protected static void someGuiCreator(){
            
            
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    String windowName = GUIinterfaceNamesFA.OldGUIReconstruction.getWindowName("some Gui addition ");
                    JFrame fAnotherThreadframe = GUIinterfaceNamesF.EditedVersionWorkerForWithProviderConsumerPC.editedVersionMainRunProviderConsumer(new JFrame(windowName));
                }
            });
            
            
            
        }

        public void shutdown() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public boolean isShutdown() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public boolean isTerminated() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public <T> Future<T> submit(Callable<T> task) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public <T> Future<T> submit(Runnable task, T result) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public Future<?> submit(Runnable task) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public void execute(Runnable command) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
