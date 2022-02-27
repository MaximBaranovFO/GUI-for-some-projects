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

import java.io.Console;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public class GUIsomenamesC {
    
    protected static void methodForRun(System inputedSys){
        System inputedSys1 = inputedSys;
        inputedSys1.getClass().descriptorString();
        Object[] signers = inputedSys1.getClass().getSigners();
        Class<? extends Object[]> aClass = signers.getClass();
        aClass.getSimpleName();
        Method[] methods = aClass.getMethods();
        Method method;
        for(int idxrm = 0; idxrm < methods.length; idxrm++){
            method = methods[idxrm];
            method.getDefaultValue();
            method.getName();
            method.getGenericExceptionTypes();
            method.getGenericParameterTypes();
            method.getGenericReturnType();
            method.getReturnType();
            method.getParameters();
            method.getAnnotatedExceptionTypes();
            method.getAnnotatedParameterTypes();
            method.getAnnotatedReceiverType();
            method.getAnnotatedReturnType();
            method.getAnnotations();
            method.getDeclaredAnnotations();
            method.getDeclaringClass();
            method.getDefaultValue();
            method.getExceptionTypes();
            method.getGenericExceptionTypes();
            method.getGenericParameterTypes();
            method.getGenericReturnType();
            method.getModifiers();
            method.getName();
            method.getParameterAnnotations();
            method.getParameterCount();
            method.getParameterTypes();
            method.getParameters();
            method.getReturnType();
            method.getTypeParameters();
            method.isBridge();
            method.isDefault();
            method.isSynthetic();
            method.isVarArgs();
            Runnable rFourFive = () ->
            {
                try {
                    System.gc();
                    Console console = System.console();
                    Field[] declaredFields = signers.getClass().getDeclaredFields();
                    //how used method at his name, get his inputed params names
                    //int aInt = declaredFields[1].getInt(idxrm);
                    //method.invoke(signers, inputedSys);
                    //Object invoke = method.invoke(idxrm, signers);
                    
                } catch (IllegalAccessError ex) {
                    Logger.getLogger(GUIsomenamesC.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(GUIsomenamesC.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InternalError ex) {
                    Logger.getLogger(GUIsomenamesC.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            };
            Executors.newFixedThreadPool(1).execute(rFourFive);
        }
    }
        protected static void runInExecutorServiceOneThree(System inputedSys){
        try {
            
            final ExecutorService executor = Executors.newFixedThreadPool(4);
            Runnable rFour = () ->
            {
                System inputedSys1 = inputedSys;
                
                Class<?>[] classes = inputedSys1.getClass().getClasses();
                
                inputedSys1.getClass().getConstructors();
                
                inputedSys1.getClass().getDeclaredConstructors();
                
                inputedSys1.getClass().getAnnotations();
                
                inputedSys1.getClass().getDeclaredAnnotations();
                
                inputedSys1.getClass().getClasses();
                
                inputedSys1.getClass().getDeclaredClasses();
                
                inputedSys1.getClass().getFields();
                
                inputedSys1.getClass().getDeclaredFields();
                
                inputedSys1.getClass().getMethods();
                
                inputedSys1.getClass().getDeclaredMethods();
                
                inputedSys1.getClass().getModule();
                
                inputedSys1.getClass().getPackageName();
                
                inputedSys1.getClass().getPackage();
                
                inputedSys1.getClass().getSuperclass();
                
                inputedSys1.getClass().getName();
                
                inputedSys1.getClass().getTypeName();
                
                inputedSys1.getClass().getSigners();
                
                inputedSys1.getClass().getTypeParameters();
                
                ClassLoader classLoader = inputedSys1.getClass().getClassLoader();
                
                ClassLoader parent = classLoader.getParent();
                
                inputedSys.getProperties();
                inputedSys.getenv();
            };
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
}
