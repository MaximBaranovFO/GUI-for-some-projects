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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.constant.ClassDesc;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Администратор
 */
public class FsWriteReadToSubDir implements Serializable {
    
    /** 
     Compiling 15 source files to C:\_bmv\nb-learn\java\GUI for some projects\build\classes
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:90: error: cannot find symbol
        Function<? super ClassDesc, ? extends U> mapper;
  symbol:   class U
  location: class FsWriteReadToSubDir
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:91: error: cannot find symbol
        Optional<? extends U> map = describeConstable.map(mapper);
  symbol:   class U
  location: class FsWriteReadToSubDir
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:156: error: incompatible types: TypeVariable<Class<CAP#1>>[] cannot be converted to TypeVariable<Class<? extends Optional>>[]
        TypeVariable<Class<? extends Optional>>[] typeParameters = aClass2.getTypeParameters();
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Optional from capture of ? extends Optional
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:185: error: incompatible types: Class<CAP#1> cannot be converted to Class<? extends Optional>
        Class<? extends Optional> superclass = aClass2.getSuperclass();
  where CAP#1,CAP#2 are fresh type-variables:
    CAP#1 extends Object super: CAP#2 from capture of ? super CAP#2
    CAP#2 extends Optional from capture of ? extends Optional
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:204: error: cannot find symbol
        extendsOptional<ClassDesc> describeConstable11;
  symbol:   class extendsOptional
  location: class FsWriteReadToSubDir
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:213: error: incompatible types: Supplier<CAP#1> cannot be converted to Supplier<? extends ClassDesc>
        describeConstable.orElseGet(supplier);
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Optional<? extends ClassDesc> from capture of ? extends Optional<? extends ClassDesc>
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:214: error: variable orElseThrow is already defined in method operationOnReadedObjects(Object)
        ClassDesc orElseThrow = describeConstable.orElseThrow();
C:\_bmv\nb-learn\java\GUI for some projects\src\gui\pkgfor\some\projects\FsWriteReadToSubDir.java:215: error: cannot find symbol
        Supplier<? extends X> exceptionSupplier;
  symbol:   class X
  location: class FsWriteReadToSubDir
Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
8 errors
BUILD FAILED (total time: 9 seconds)
     * 
     * 
     */
    
    protected Object operationOnReadedObjects(Object fisOutputed){
        Class<? extends Object> aClass = fisOutputed.getClass();
        Optional<ClassDesc> describeConstable = aClass.describeConstable();
        /**
         * Get description of readed class about him
         */
        ClassDesc getFromReadedClassDescription = describeConstable.get();
        Class<? extends Optional> aClass1 = describeConstable.getClass();
        int hashCode = describeConstable.hashCode();
        
        /** 
         
         * public <U> Optional<U> map(Function<? super T,? extends U> mapper)
If a value is present, returns an Optional describing (as if by ofNullable) 
* the result of applying the given mapping function to the value, otherwise 
* returns an empty Optional.
If the mapping function returns a null result then this method returns an 
* empty Optional.
API Note:
This method supports post-processing on Optional values, without the need to 
* explicitly check for a return status. For example, the following code 
* traverses a stream of URIs, selects one that has not yet been processed, 
* and creates a path from that URI, returning an Optional<Path>:
     Optional<Path> p =
         uris.stream().filter(uri -> !isProcessedYet(uri))
                       .findFirst()
                       .map(Paths::get);
 
Here, findFirst returns an Optional<URI>, and then map returns an 
* Optional<Path> for the desired URI if one exists.
Parameters:
mapper - the mapping function to apply to a value, if present 
Type Parameters:
U - The type of the value returned from the mapping function 
Returns:
an Optional describing the result of applying a mapping function 
* to the value of this Optional, if a value is present, otherwise an empty Optional
Throws:
NullPointerException - if the mapping function is null
         
         */
        
        /** >>>***>>>Error in text>>> */ //Function<? super ClassDesc, ? extends U> mapper;
        /** >>>***>>>Error in text>>> */ //Optional<? extends U> map = describeConstable.map(mapper);
        boolean empty = describeConstable.isEmpty();
        boolean present = describeConstable.isPresent();
        /**
         * Now read description about class and extends for it
         * for perspective, need Optional additions in class for
         * or
         * orElse
         * orElseGet
         * ...etc...
         */
        Supplier<? extends Optional<? extends ClassDesc>> supplier;
        //Class<?>[] parameterTypes;
        //parameterTypes = aClass.getClasses();
        Optional<ClassDesc> describeConstable1 = aClass1.describeConstable();
        
        ClassDesc getAlsoClassDescriptionOfReaded = describeConstable1.get();
        /**
         * Number n = 0;
         * Class<? extends Number> c = n.getClass(); 
         */
        Class<? extends Optional> aClass2 = describeConstable1.getClass();
        Class<?> componentType = aClass2.componentType();
        AnnotatedType annotatedSuperclass = aClass2.getAnnotatedSuperclass();
        Field[] declaredFields = aClass2.getDeclaredFields();
        Constructor<?>[] constructors = aClass2.getConstructors();
        Annotation[] annotations = aClass2.getAnnotations();
        AnnotatedType[] annotatedInterfaces = aClass2.getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass1 = aClass2.getAnnotatedSuperclass();
        String canonicalName = aClass2.getCanonicalName();
        ClassLoader classLoader = aClass2.getClassLoader();
        Class<?>[] classes = aClass2.getClasses();
        Class<?> componentType1 = aClass2.getComponentType();
        Constructor<?>[] declaredConstructors = aClass2.getDeclaredConstructors();
        Annotation[] declaredAnnotations = aClass2.getDeclaredAnnotations();
        Class<?>[] declaredClasses = aClass2.getDeclaredClasses();
        Method[] declaredMethods = aClass2.getDeclaredMethods();
        
        AnnotatedType[] annotatedInterfaces1 = aClass2.getAnnotatedInterfaces();
        Annotation[] annotations1 = aClass2.getAnnotations();
        Class<?>[] classes1 = aClass2.getClasses();
        Constructor<?>[] constructors1 = aClass2.getConstructors();
        Annotation[] declaredAnnotations1 = aClass2.getDeclaredAnnotations();
        Class<?>[] declaredClasses1 = aClass2.getDeclaredClasses();
        Constructor<?>[] declaredConstructors1 = aClass2.getDeclaredConstructors();
        Field[] declaredFields1 = aClass2.getDeclaredFields();
        Method[] declaredMethods1 = aClass2.getDeclaredMethods();
        /** 
         public T[] getEnumConstants()
Returns the elements of this enum class or null if this Class object does not represent 
* an enum class.
Returns:
an array containing the values comprising the enum class represented by this Class object 
* in the order 
* they're declared, or null if this Class object does not represent an enum class
         */
        aClass2.getEnumConstants();
        Field[] fields = aClass2.getFields();
        Type[] genericInterfaces = aClass2.getGenericInterfaces();
        Class<?>[] interfaces = aClass2.getInterfaces();
        Method[] methods = aClass2.getMethods();
        Class<?>[] nestMembers = aClass2.getNestMembers();
        Class<?>[] permittedSubclasses = aClass2.getPermittedSubclasses();
        RecordComponent[] recordComponents = aClass2.getRecordComponents();
        Object[] signers = aClass2.getSigners();
        /** >>>***>>>Error in text>>> */ //TypeVariable<Class<? extends Optional>>[] typeParameters = aClass2.getTypeParameters();
        Class<?> arrayType = aClass2.arrayType();
        Class<?> componentType2 = aClass2.componentType();
        
        Class<?>[] parameterTypesAdditional;
        //parameterTypesAdditional = new String.class.getClasses()[5];
        /**
        parameterTypesAdditional[0] = aClass2.getClass();
        parameterTypesAdditional[1] = aClass2.getComponentType();
        parameterTypesAdditional[2] = aClass2.getDeclaringClass();
        parameterTypesAdditional[3] = aClass2.getEnclosingClass();
        parameterTypesAdditional[4] = aClass2.getNestHost();
        */
        parameterTypesAdditional = aClass2.getClasses();
        /** 
         
         * java.​lang.​Class
public Class<? super T> getSuperclass()
Returns the Class representing the direct superclass of the entity 
* (class, interface, primitive type or void) represented by this Class. 
* If this Class represents either the Object class, an interface, 
* a primitive type, or void, then null is returned. If this Class 
* object represents an array class then the Class object representing 
* the Object class is returned.
Returns:
the direct superclass of the class represented by this Class object
         
         */
        //Class<? extends Optional> superclass;
        /** >>>***>>>Error in text>>> */ //Class<? extends Optional> superclass = aClass2.getSuperclass();
        try {
            Constructor<? extends Optional> constructor = aClass2.getConstructor(parameterTypesAdditional);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Class<?>[] parameterTypes;
        parameterTypes = aClass2.getPermittedSubclasses();
        try {
            Constructor<? extends Optional> constructor = aClass2.getConstructor(parameterTypes);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        }
        /** >>>***>>>Error in text>>> */ //extendsOptional<ClassDesc> describeConstable11;
        /** >>>***>>>Error in text>>> */ //describeConstable11 = describeConstable1;
        ClassDesc orElseThrow = describeConstable1.orElseThrow();
        /** >>>***>>>Error in text>>> */ //supplier = describeConstable11;
        /** >>>***>>>Error in text>>> */ //describeConstable.or(supplier);
        ClassDesc other;
        other = getFromReadedClassDescription;
        describeConstable.orElse(other);
        ClassDesc get = describeConstable1.get();
        /** >>>***>>>Error in text>>> */ //describeConstable.orElseGet(supplier);
        /** >>>***>>>Error in text>>> */ //ClassDesc orElseThrow = describeConstable.orElseThrow();
        /** >>>***>>>Error in text>>> */ //Supplier<? extends X> exceptionSupplier;
        /** >>>***>>>Error in text>>> */ //describeConstable.orElseThrow(exceptionSupplier);
        Stream<ClassDesc> stream = describeConstable.stream();
        String toString = describeConstable.toString();
        return fisOutputed;
    }
    /**
     * class PrimeThread extends Thread {
         long minPrime;
         PrimeThread(long minPrime) {
             this.minPrime = minPrime;
         }

         public void run() {
             // compute primes larger than minPrime
              . . .
         }
     }
 
 
The following code would then create a thread and start it running:
     PrimeThread p = new PrimeThread(143);
     p.start();
 
The other way to create a thread is to declare a class that implements the Runnable interface. That class then implements the run method. An instance of the class can then be allocated, passed as an argument when creating Thread, and started. The same example in this other style looks like the following:
 
     class PrimeRun implements Runnable {
         long minPrime;
         PrimeRun(long minPrime) {
             this.minPrime = minPrime;
         }

         public void run() {
             // compute primes larger than minPrime
              . . .
         }
     }
 
 
The following code would then create a thread and start it running:
     PrimeRun p = new PrimeRun(143);
     new Thread(p).start();
 
     * @param oosInputed
     * @return 
     */
    protected ObjectOutputStream operationOnOutputObjects(ObjectOutputStream oosInputed){
        try {
            ObjectOutputStream.PutField putFields = oosInputed.putFields();
            Class<? extends ObjectOutputStream.PutField> aClass = putFields.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            
        } catch (IOException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
            return oosInputed;
        }/* catch (NotActiveException exNotActive){
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return oosInputed;
    }
    protected void writeToSubDir(String fileForWrite, Object ofwito){
        
        try {
            FileOutputStream fos = new FileOutputStream(fileForWrite);

		ObjectOutputStream oos;
            
            
            oos = new ObjectOutputStream(fos);
            ObjectOutputStream operationOnObjects = operationOnOutputObjects(oos);
            
            //SerialTest st = new SerialTest();
	    //operationOnObjects.writeObject(st);
            operationOnObjects.writeObject(ofwito);
		operationOnObjects.flush();
		operationOnObjects.close();
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
    protected void readFromSubDir(String fileForRead){
 
        try {
            FileInputStream fis;
            ObjectInputStream oin;
            fis = new FileInputStream(fileForRead);
            oin = new ObjectInputStream(fis);
            Object readObject = oin.readObject();
            Object operationOnReadedObjects = operationOnReadedObjects(readObject);
            //TestSerial ts;
            //ts = (TestSerial) oin.readObject();
            //System.out.println("version="+ts.version);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        }
        Serializable.class.toGenericString();
        Serializable.class.toString();
	  
        /*try {
            
        } catch (IOException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FsWriteReadToSubDir.class.getName()).log(Level.SEVERE, null, ex);
        }*/
	  
    }
}
