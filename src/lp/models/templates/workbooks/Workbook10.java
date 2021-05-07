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
import lp.util.Calendar.WeekInfos;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Workbook10 extends BaseWorkbook {
    private final String week, halfDaysRow, signature, toAdmin;
    private final String[] days_, mornsPeriods, aftsPeriods, nums;
    protected String[] morns;
    protected String[] afts;
    
    public Workbook10(){
        super();
        schoolInfos = "A1";
        title = "C1";
        week = "C2";
        grp = "AM2";
        year = "AM1";
        morns = "C K S AB AJ AR".split(" ");
        afts = "G O W AF AN AV".split(" ");
        halfDaysRow = "5";
        days_ = "C4 K4 S4 AB4 AJ4 AR4".split(" ");
        mornsPeriods = "C6:F52 K6:N52 S6:V52 AB6:AE52 AJ6:AM52 AR6:AU52".split(" ");
        aftsPeriods = "G6:J52 O6:R52 W6:Z52 AF6:AI52 AN6:AQ52 AV6:AY52".split(" ");
        nums = "A AA".split(" ");
        signature = "B52";
        toAdmin = "B53";
        sec = "B";
        firstRow = 6;
        SHEET1_MAX = 40;
        SHEET2_MAX = 46;
        tpl = tempaltesDir + "Template.10.xlsx";
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount) {
        CellReference cr = new CellReference(schoolInfos);
        boolean bold = Settings.PREF_TEMPORARY.get("TPL10_BOLDER_NAMES").equals("Y");
        XSSFCellStyle cs;
        XSSFCell xc;
        HorizontalAlignment ha = getAlignment(Settings.PREF_TEMPORARY.get("TPL10_NAMES_ALIGN"));
        
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.SCHOOL_DB.getAcademy() + "\n" + Settings.SCHOOL_DB.getDirection() + "\n" + Settings.SCHOOL_DB.getSchool());
        cr = new CellReference(year);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.SCHOOL_DB.getYear());
        cr = new CellReference(grp);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(g.getName());
        cr = new CellReference(title);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL10_TITLE"));

        for ( int i = start, j = firstRow; i < stuCount; i++, j++ ) {
            stu = g.getStudent(i);
            
            for ( int k = 0; k < 2; k++ ) {
                cr = new CellReference(nums[k] + j);
                xc = sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                xc.setCellValue(stu.getNum());
            }
            
            cr = new CellReference(sec + j);
            xc = sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            xc.setCellValue(stu.getSecName() + " " + stu.getFirName());
            cs = sht.getWorkbook().createCellStyle();
            cs.cloneStyleFrom(xc.getCellStyle());
            cs.getFont().setBold(bold);
            cs.setAlignment(ha);
            xc.setCellStyle(cs);
        }
        
        for ( int i = 0, n = morns.length; i < n; i++ ) {
            cr = new CellReference(morns[i] + halfDaysRow);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
            cr = new CellReference(afts[i] + halfDaysRow);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
        }

        cr = new CellReference(signature);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL10_SIGNATURE") + ": ");
        cr = new CellReference(toAdmin);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL10_TO_ADMINISTRATION") + ": ");

        placeDatesAndFormat(sht);
    }
    
    
    protected void placeDatesAndFormat(XSSFSheet sht) {
        WeekInfos wi;
        Integer sw, i, n, day;
        String from = Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " ";
        String to = " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " ";
        DateRange dr;
        CellReference cr;
        try {
            wi = new WeekInfos(Integer.parseInt(Settings.PREF_TEMPORARY.get("TPL10_FIRST_DAY_OF_WEEK")));
        } catch(NumberFormatException nfe) {
            wi = new WeekInfos(Calendar.MONDAY);
        }
        try {
            sw = Integer.parseInt(Settings.PREF_TEMPORARY.get("TPL10_SELECTED_WEEK"));
        } catch(NumberFormatException nfe) {
            sw = 1;
        }
        day = wi.getFirstDay() - 1;
        dr = wi.getWeek(sw);
        i = 1;
        n = 7;
        cr = new CellReference(week);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(from + dr.getBeginning() + to + dr.getEnd());
        for ( int j = 0; i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            cr = new CellReference(days_[j]);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                grayDayHalf(sht, mornsPeriods, j);
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                grayDayHalf(sht, aftsPeriods, j);
            }
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
        if ( force2Pages || n >= SHEET2_MAX )
            add2PagesGroup(g, sheetName);
        else if ( n <= SHEET1_MAX ) {
            add1PageGroup(g, wb.cloneSheet(0, sheetName), 0, n);
        }
        else if( n <= SHEET2_MAX ) {
            add1PageGroup(g, wb.cloneSheet(1, sheetName), 0, n);
        }
    }

    @Override
    protected void add2PagesGroup(Group g, String sheetName) {
        int stuCount = g.getStudentsCount();
        int lastRow = ( (stuCount > SHEET3_MAX) ? SHEET3_MAX : stuCount );
        add1PageGroup( g, wb.cloneSheet(2, sheetName + " | p1"), 0, lastRow );
        add1PageGroup( g, wb.cloneSheet(3, sheetName + " | p2"), SHEET3_MAX, stuCount );
    }

}
