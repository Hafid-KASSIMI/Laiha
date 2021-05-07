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

import java.io.FileOutputStream;
import java.io.IOException;
import lp.models.Group;
import lp.models.Level;
import lp.models.Student;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class BaseWorkbook {
    protected XSSFWorkbook wb;
    protected String takenNames, aca, dir, eta, year, lev, grp, num, mas, sec, fir, gen, dat, pla;
    protected String title, schoolInfos;
    protected int firstRow, fake1stRow;
    protected Student stu;
    protected String tpl;
    protected int sheetsCount;
    protected final String tempaltesDir = "/lp/resources/templates/";
    protected int SHEET1_MAX, SHEET2_MAX, SHEET3_MAX;
    protected Boolean enumerateStudents, enumerateStudentsAbs;
    
    public BaseWorkbook(){
        takenNames = "";
        SHEET1_MAX = 38;
        SHEET2_MAX = 44;
        SHEET3_MAX = 40;
        sheetsCount = 4;
    }
    
    public final void reset(){
        takenNames = "";
        try {
            wb = new XSSFWorkbook(getClass().getResource(tpl).openStream());
        } catch (IOException ex) {
            wb = null;
        }
    }
    
    public abstract void addGroup(Group g, boolean force2Pages);
    abstract protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount);
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount, int page, int pages) {
        // May be overidden
    }
    protected abstract void add2PagesGroup(Group g, String sheetName);

    public void addLevel(Level l, boolean force2Pages) {
        if ( wb == null || l == null )   return;
        for ( int i = 0, n = l.getGroupsCount(); i < n; i++ ) {
            addGroup(l.getGroup(i), force2Pages);
        }
    }
    
    public boolean save(String path) {
        for ( int i = 0; i < sheetsCount; i++ )
            wb.removeSheetAt(0);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            wb.write(fos);
            wb.close();
            return true;
        } catch (IOException ex) { 
            return false;
        }
    }
    
    protected HorizontalAlignment getAlignment(String alignment) {
        switch ( alignment ) {
            case "L":
                return HorizontalAlignment.LEFT;
            case "R":
                return HorizontalAlignment.RIGHT;
            default:
                return HorizontalAlignment.CENTER;
        }
    }
    
    protected void grayDayHalf(XSSFSheet sheet, String[] arr, int column) {
        CellRangeAddress cr;
        XSSFCell cell;
        CellStyle grayedStyle;
        cr = CellRangeAddress.valueOf(arr[column]);
        sheet.addMergedRegion(cr);
        cell = sheet.getRow(cr.getFirstRow()).getCell(cr.getFirstColumn(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        grayedStyle = cell.getSheet().getWorkbook().createCellStyle();
        grayedStyle.cloneStyleFrom(cell.getCellStyle());
        grayedStyle.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);
        grayedStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        grayedStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
        cell.setCellStyle(grayedStyle);
    }
    
}
