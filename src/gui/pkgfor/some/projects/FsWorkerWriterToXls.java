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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Администратор
 */
public class FsWorkerWriterToXls {
    private static HSSFCellStyle getSampleStyle(HSSFWorkbook workbook) {
        // Font
        HSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setItalic(false);
 
        // Font Height
        font.setFontHeightInPoints((short) 12);
 
        // Font Color
        font.setColor(IndexedColors.BLACK.index);
 
        // Style
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
 
        return style;
    }
    public static void writeFromTesseractTxtToXls(List<String> innerLines, Path xlsFile){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Style Demo");
 
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        int rowNum = 0;
        for (String innerLine : innerLines) {
            rowNum++;
            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(innerLine);
            HSSFCellStyle style = getSampleStyle(workbook);
            cell.setCellStyle(style);
        }
        //
        
        
 
        
        String newFilePath = xlsFile.toString() + ".xls";
        Path pathNewFile = Paths.get(newFilePath);
        File file = new File(newFilePath);
        try {
            Files.createFile(pathNewFile);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        FileOutputStream outFile;
        try {
            outFile = new FileOutputStream(file);
            try {
                workbook.write(outFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
