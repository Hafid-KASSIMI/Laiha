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

import java.util.Calendar;
import lp.Settings;
import lp.models.Group;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WEEK;
import lp.util.Calendar.WeekInfos;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public abstract class Workbook78 extends BaseWorkbook {
    protected String toAdmin, days, morn, aft;
    protected String[] morns = {"C", "D", "E", "F"};
    protected String[] afts = {"G", "H", "I", "J"};
    protected final int firstDayRow, hoursRow;
    protected String week, tplPfx;
    
    public Workbook78(){
        super();
        schoolInfos = "A1";
        toAdmin = "B4";
        days = "A";
        hoursRow = 5;
        morn = "C4";
        aft = "G4";
        firstDayRow = 6;
        enumerateStudents = false;
        sheetsCount = 2;
    }
    
    public Workbook78(int index){
        this();
        setTemplate(index);
    }
    
    protected final void setTemplate(int index) {
        tplPfx = "TPL" + index + "_";
    }
    
    protected void placeDatesAndFormat(XSSFSheet sht, int page, int pagesNumber) {
        WeekInfos wi;
        Integer sw, i, n, day;
        String from = Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " ";
        String to = " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " ";
        DateRange dr;
        CellReference cr;
        try {
            wi = new WeekInfos(Integer.parseInt(Settings.PREF_TEMPORARY.get(tplPfx + "FIRST_DAY_OF_WEEK")));
        } catch(NumberFormatException nfe) {
            wi = new WeekInfos(Calendar.MONDAY);
        }
        try {
            sw = Integer.parseInt(Settings.PREF_TEMPORARY.get(tplPfx + "SELECTED_WEEK"));
        } catch(NumberFormatException nfe) {
            sw = 1;
        }
        day = wi.getFirstDay() - 1;
        if ( pagesNumber == 1  ) {
            dr = wi.getWeek(sw);
            i = 1;
            n = 7;
        }
        else {
            if ( page == 1 ) {
                i = 1;
                n = 4;
                dr = wi.getWeekHalf(WEEK.FIRST_HALF, sw);
            }
            else {
                i = 4;
                n = 7;
                day = ( day + 3 ) % 7;
                dr = wi.getWeekHalf(WEEK.SECOND_HALF, sw);
            }
        }
        cr = new CellReference(week);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(from + dr.getBeginning() + to + dr.getEnd());
        for ( int j = firstDayRow; i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            cr = new CellReference(days + j);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                grayDayHalf(sht, morns, j);
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                grayDayHalf(sht, afts, j);
            }
        }
        sht.getPrintSetup().setLandscape(true);
        sht.getPrintSetup().setPaperSize(PaperSize.A4_PAPER);
    }
    
    @Override
    protected void grayDayHalf(XSSFSheet sheet, String[] arr, int row) {
        for ( int k = 0; k < 4; k++ ) {
            CellReference cr = new CellReference(arr[k] + row);
            CellStyle grayedStyle;
            XSSFCell cell = sheet.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            grayedStyle = cell.getSheet().getWorkbook().createCellStyle();
            grayedStyle.cloneStyleFrom(cell.getCellStyle());
            grayedStyle.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);
            grayedStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cell.setCellStyle(grayedStyle);
        }
    }

    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( wb == null || g == null )   return;
        String sheetName = g.getName(), tmpName;
        int n;
        tmpName = sheetName + "|";
        if ( takenNames.contains(tmpName) ) {
            int idx = 0;
            int counter = 0;
            while ((idx = takenNames.indexOf(tmpName, idx)) != -1 ){
                counter++;
                idx++;
            }
            sheetName += " (" + counter + ")";
        }
        takenNames += tmpName;
        n = g.getStudentsCount();
        if ( force2Pages )
                add2PagesGroup(g, sheetName);
        else
            add1PageGroup( g, wb.cloneSheet(0, sheetName), 1, 1 );
        
    }

    @Override
    protected void add2PagesGroup(Group g, String sheetName) {
        add1PageGroup(g, wb.cloneSheet(1, sheetName + " | p1"), 1, 2);
        add1PageGroup(g, wb.cloneSheet(1, sheetName + " | p2"), 2, 2);
    }
}
