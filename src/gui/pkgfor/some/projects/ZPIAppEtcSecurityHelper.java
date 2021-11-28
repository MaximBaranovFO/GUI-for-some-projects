/*
 * Copyright 2018 wladimirowichbiaran.
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

import java.io.FilePermission;
import java.lang.reflect.ReflectPermission;
import java.net.NetPermission;
import java.net.SocketPermission;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Permissions;
import java.security.AllPermission;
import java.security.Policy;
import java.util.PropertyPermission;
import javax.management.MBeanPermission;
import sun.security.util.SecurityConstants;
import sun.reflect.ReflectionFactory;

/**
 *
 * @author wladimirowichbiaran
 */
public class ZPIAppEtcSecurityHelper {
    protected static void createNewSecurity(){
        //java.io.FilePermission
        //java.net.SocketPermission
        //java.net.NetPermission
        //java.security.SecurityPermission
        //java.lang.RuntimePermission
        //java.util.PropertyPermission
        //java.awt.AWTPermission
        //java.lang.reflect.ReflectPermission
        //java.io.SerializablePermission
        //AllPermission
        //BasicPermission
        //FilePermission
        //MBeanPermission
        //PrivateCredentialPermission
        //ServicePermission
        //SocketPermission
        //UnresolvedPermission
        //AudioPermission
        //AuthPermission
        //AWTPermission
        //DelegationPermission
        //JAXBPermission
        //LinkPermission
        //LoggingPermission
        //ManagementPermission
        //MBeanServerPermission
        //MBeanTrustPermission
        //NetPermission
        //PropertyPermission
        //ReflectPermission
        //RuntimePermission
        //SecurityPermission
        //SerializablePermission
        //SQLPermission
        //SSLPermission
        //SubjectDelegationPermission
        //WebServicePermission
        
        Permissions permissions = new Permissions();
        
        Path appPath = ZPIAppFileOperationsSimple.getAppRWEDCheckedPath();
        
        permissions.add(new MBeanPermission("*", "unregisterMBean"));
        
        permissions.add(new SocketPermission("localhost:0-65535", "listen"));
        
        permissions.add(new NetPermission("*", "decline"));
        
        permissions.add(new FilePermission(appPath.toString(), SecurityConstants.PROPERTY_RW_ACTION));
        permissions.add(new FilePermission(appPath.toString() + "/-", SecurityConstants.PROPERTY_RW_ACTION));
        permissions.add(new FilePermission("/-", SecurityConstants.FILE_READ_ACTION));
        permissions.add(new FilePermission("/-", SecurityConstants.FILE_READLINK_ACTION));
        permissions.add(new FilePermission("/-", SecurityConstants.FILE_WRITE_ACTION));
        permissions.add(new FilePermission("/-", SecurityConstants.FILE_EXECUTE_ACTION));
        Path userHomePath = ZPIAppFileOperationsSimple.getUserHomeRWEDCheckedPath();
        Path indexFolder = ZPIAppFileStorageIndex.getIndexFolder();
        permissions.add(new FilePermission(userHomePath.toString(), SecurityConstants.PROPERTY_RW_ACTION));
        permissions.add(new FilePermission(userHomePath.toString() + "/-", SecurityConstants.PROPERTY_RW_ACTION));
        permissions.add(new FilePermission(indexFolder.toString() + "/-", SecurityConstants.FILE_DELETE_ACTION));
        //permissions.add(new FilePermission(userHomePath.toString() + "/zipfstmp*", SecurityConstants.FILE_DELETE_ACTION));
        permissions.add(new ReflectPermission("suppressAccessChecks", "read"));
        
        /*for (String namesKey : System.getProperties().stringPropertyNames()) {
            if( namesKey.isEmpty() ){
                continue;
            }
            permissions.add(new PropertyPermission(namesKey, SecurityConstants.PROPERTY_RW_ACTION));
        }*/
        
        permissions.add(new PropertyPermission("*", SecurityConstants.PROPERTY_RW_ACTION));
        /*permissions.add(new PropertyPermission("line.separator", "read"));
        permissions.add(new PropertyPermission("java.class.path", "read"));*/
        
        permissions.add(new RuntimePermission("getenv.*", "read"));
        
        permissions.add(new RuntimePermission("getStackTrace", "read"));
        permissions.add(new RuntimePermission("modifyThreadGroup", "read"));
        permissions.add(new RuntimePermission("modifyThread", "read"));
        permissions.add(new RuntimePermission("accessDeclaredMembers", "read"));
        permissions.add(new RuntimePermission("fileSystemProvider", "read"));
        permissions.add(new RuntimePermission("fileSystemProvider", "write"));
        
        permissions.add(new RuntimePermission("accessUserInformation", "read"));
        
        
        
        permissions.setReadOnly();
        
        Policy.setPolicy(new ZPIAppEtcSecurityPolicy(permissions));
        System.setSecurityManager(new ZPIAppEtcSecurityManager());
        
        
        
    }
}
