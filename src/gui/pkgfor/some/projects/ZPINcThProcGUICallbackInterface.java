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

/**
 * Developed based on the publications found on the Internet at
 * http://www.skipy.ru/technics/gui_sync.html
 * Thanks and best regards to author of publishing
 * 
 * @author wladimirowichbiaran
 */
public interface ZPINcThProcGUICallbackInterface {
    /**
     * Not used
     */
    @ZPINcThProcTypeDetectInterface
    void appendSrchResult();
    /**
     * Not used
     */
    @ZPINcThProcTypeDetectInterface
    void setSrcResult();
    /**
     * Not used
     */
    @ZPINcThProcTypeDetectInterface
    void showProgressSwitch();
    /**
     * Not used
     */
    @ZPINcThProcTypeDetectInterface
    void startSrch();
    /**
     * Not used
     */
    @ZPINcThProcTypeDetectInterface
    void stopSrch();
    /**
     * Not used
     * @param strMessage 
     */
    @ZPINcThProcTypeDetectInterface(ZPINcThProcType.SYNC)
    void showError(String strMessage);
}
