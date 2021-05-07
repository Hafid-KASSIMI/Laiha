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

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.Group;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Workbook6 extends Workbook123456 {
    private final String customStr;
    private String gap;
    
    public Workbook6(){
        super();
        schoolInfos = "A1";
        title = "A2";
        year = "G1";
        gap = "..............................\n";
        gap += gap + gap;
        num = "A";
        mas = "B";
        sec = "C";
        customStr = "I4";
        tpl = tempaltesDir + "Template.6.xlsx";
        firstRow = 5;
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount) {
        CellReference cr = new CellReference(schoolInfos);
        boolean bold = Settings.PREF_TEMPORARY.get("TPL6_BOLDER_NAMES").equals("Y");
        XSSFCellStyle cs;
        XSSFCell xc;
        HorizontalAlignment ha = getAlignment(Settings.PREF_TEMPORARY.get("TPL6_NAMES_ALIGN"));
        String separator = Settings.PREF_TEMPORARY.get("TPL6_SCHOOL_INFOS_SEPARATOR");
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.SCHOOL_DB.getAcademy() + separator + Settings.SCHOOL_DB.getDirection() + separator + Settings.SCHOOL_DB.getSchool());
        cr = new CellReference(year);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(gap + Settings.SCHOOL_DB.getYear());
        cr = new CellReference(title);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL6_TITLE") + " " + g.getName());
        cr = new CellReference(customStr);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL6_CUSTOM_STRING"));
        
        for ( int i = start, j = firstRow; i < stuCount; i++, j++ ) {
            stu = g.getStudent(i);
            cr = new CellReference(num + j);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(stu.getNum());
            cr = new CellReference(mas + j);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(stu.getCode());
            cr = new CellReference(sec + j);
            xc = sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            xc.setCellValue(stu.getFullName());
            cs = sht.getWorkbook().createCellStyle();
            cs.cloneStyleFrom(xc.getCellStyle());
            cs.getFont().setBold(bold);
            cs.setAlignment(ha);
            xc.setCellStyle(cs);
        }
    }
    
}
