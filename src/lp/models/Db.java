/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/

package lp.models;


import java.io.*;
import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lp.Settings;
import lp.util.Misc;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

public class Db {
    private int firstRow;
    private int sheetsNumber;
    private String classRef, dirRef, acadRef, schoolRef, yearRef, levRef;
    private String numCol, codeCol, fNameCol, sNameCol, genderCol, bDateCol, addressCol;
    private ArrayList<Integer> validSheets;
    private HSSFWorkbook wb;
    private HSSFSheet sht;
    private SimpleBooleanProperty status;
    private SimpleDoubleProperty processedSheets;
            
    public Db(){
        firstRow = 16;
        codeCol = "X";
        numCol = "AA";
        fNameCol = "M";
        sNameCol = "Q";
        genderCol = "L";
        bDateCol = "F";
        addressCol = "C";
        classRef = "I11";
        dirRef = "U8";
        acadRef = "T6";
        schoolRef = "H8";
        yearRef = "C6";
        levRef = "T10";
        validSheets = new ArrayList();
        status = new SimpleBooleanProperty(false);
        processedSheets = new SimpleDoubleProperty(0);
    }
    
    public Db(File f){
        this();
        try {
            wb = new HSSFWorkbook(new FileInputStream(f));
        } catch (IOException ex) {
            wb = null;
        }
    }
    
    public String getStringValue(HSSFSheet sht, String cellRef){
        CellReference cr;
        DataFormatter df;
        cr = new CellReference(cellRef);
        df = new DataFormatter();
        try {
            return df.formatCellValue(sht.getRow(cr.getRow()).getCell(cr.getCol()));
        }
        catch (NullPointerException ex) {
            return "";
        }
    }
    
    public final boolean setWorkbook(File f){
        validSheets.clear();
        try {
            wb = new HSSFWorkbook(new FileInputStream(f));
        } catch (IOException ex) {
            wb = null;
            return false;
        }
        return isItFromMassar();
    }
    
    private void loadValidSheets(){
        sheetsNumber = wb.getNumberOfSheets();
        for ( int i = 0; i < sheetsNumber; i++ ) {
            if ( isFromMassar( wb.getSheetAt(i) ) )
                validSheets.add(i);
        }  
    }
    
    private boolean isItFromMassar(){
        loadValidSheets();
        return ( validSheets.size() > 0 );
    }
    
    private boolean isFromMassar(HSSFSheet sh){
        return (sh == null)?false:(
                    getStringValue(sh, "E6").contains("السنة الدراسية") &&
                    getStringValue(sh, "K3").contains("لائحة التلاميذ ")
                );
    }    
    
    public void loadDB(){
        if ( validSheets.isEmpty() )  return;
        sht = wb.getSheetAt(validSheets.get(0));
        Settings.SCHOOL_DB.reset();
        Settings.SCHOOL_DB.setAcademy(getStringValue(sht, acadRef));
        Settings.SCHOOL_DB.setDirection(getStringValue(sht, dirRef));
        Settings.SCHOOL_DB.setSchool(getStringValue(sht, schoolRef));
        Settings.SCHOOL_DB.setYear(getStringValue(sht, yearRef));
        new Thread(() -> {
            String code;
            int num, current;
            status.set(false);
            current = 0;
            processedSheets.set(0);
            for( int i : validSheets ) {
                Group grp;
                Level lev;
                int currentRow = firstRow;
                String cls, levName;
                sht = wb.getSheetAt(i);
                cls = getStringValue(sht, classRef);
                levName = getStringValue(sht, levRef);
                lev = Settings.SCHOOL_DB.addLevel(levName);
                grp = lev.addGroup(cls);
                num = 0;
                while( !(code = getStringValue(sht, codeCol + currentRow)).isEmpty()) {
                    Student stu = new Student();
                    try {
                        stu.setNum(Integer.parseInt(getStringValue(sht, numCol + currentRow)));
                    }
                    catch(NumberFormatException e) {
                    }
                    stu.setCode(code);
                    stu.setAddress(getStringValue(sht, addressCol + currentRow));
                    stu.setSecName(Misc.removeShakl(getStringValue(sht, sNameCol + currentRow)));
                    stu.setFirName(Misc.removeShakl(getStringValue(sht, fNameCol + currentRow)));
                    stu.setGender(getStringValue(sht, genderCol + currentRow));
                    stu.setBirthDate(getStringValue(sht, bDateCol + currentRow));
                    stu.setGroup(cls);
                    grp.addStudent(stu);
                    currentRow++;
                }
                processedSheets.set((double) ++current / sheetsNumber);
            }
            status.set(true);
        }).start();
    }
    
    public SimpleBooleanProperty getStatus() {
        return status;
    }

    public SimpleDoubleProperty getProcessedSheets() {
        return processedSheets;
    }
    
    public int getSheetsNumber() {
        return sheetsNumber;
    }
    
    public boolean isNull(){
        return (wb == null);
    }
}
