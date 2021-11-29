/*
 *  Copyright 2017 Administrator of development departament newcontrol.ru .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package gui.pkgfor.some.projects;

/**
 *
 * @author Администратор
 */
public class ZPINcIdxSubStringVariant {
    String strInLowerCase;
    int strInLowerCaseHash;
    String hexForLowerCase;
    int hexForLowerCaseHash;
    String strInUpperCase;
    int strInUpperCaseHash;
    String hexForUpperCase;
    int hexForUpperCaseHash;

    /**
     * Used in
     * <ul>
     * <li>{@link ru.newcontrol.ncfv.NcSrchVariantMaker#getUpperLowerCaseVariant(java.util.ArrayList) }
     * </ul>
     * @param strInLowerCase
     * @param strInUpperCase
     */
    protected ZPINcIdxSubStringVariant(String strInLowerCase, String strInUpperCase) {
        this.strInLowerCase = strInLowerCase;
        this.strInLowerCaseHash = this.strInLowerCase.hashCode();
        this.hexForLowerCase = ZPINcPathToArrListStr.toStrUTFinHEX(this.strInLowerCase);
        this.hexForLowerCaseHash = this.hexForLowerCase.hashCode();
        this.strInUpperCase = strInUpperCase;
        this.strInUpperCaseHash = this.strInUpperCase.hashCode();
        this.hexForUpperCase = ZPINcPathToArrListStr.toStrUTFinHEX(this.strInUpperCase);
        this.hexForUpperCaseHash = this.hexForUpperCase.hashCode();
    }
    
}
