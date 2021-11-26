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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Developed based on the publications found on the Internet at
 * http://www.skipy.ru/technics/gui_sync.html
 * Thanks and best regards to author of publishing
 * 
 * @author wladimirowichbiaran
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZPINcThProcTypeDetectInterface {
    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcThProcInvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]) }
     * </ul>
     * Process types defined in the
     * {@link ru.newcontrol.ncfv.NcThProcType}
     * @return type of process 
     */
    ZPINcThProcType value() default ZPINcThProcType.ASYNC;
}
