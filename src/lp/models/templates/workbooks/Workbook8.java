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
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Workbook8 extends Workbook78 {
    private final String msg;
    
    public Workbook8(){
        super(8);
        schoolInfos = "A1";
        title = "E1";
        grp = "J2";
        year = "J1";
        week = "E2";
        msg = "L6";
        tpl = tempaltesDir + "Template.8.xlsx";
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int page, int pagesNumber) {
        CellReference cr = new CellReference(schoolInfos);
        String separator = Settings.PREF_TEMPORARY.get("TPL8_SCHOOL_INFOS_SEPARATOR");
        separator = separator.equals("NL") ? "\n" : separator;
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.SCHOOL_DB.getAcademy() + separator + Settings.SCHOOL_DB.getDirection() + separator + Settings.SCHOOL_DB.getSchool());
        cr = new CellReference(year);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL8_YEAR") + Settings.SCHOOL_DB.getYear());
        cr = new CellReference(title);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL8_TITLE"));
        cr = new CellReference(grp);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(g.getName());
        cr = new CellReference(toAdmin);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL8_TO_ADMINISTRATION"));
        cr = new CellReference(morn);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
        cr = new CellReference(morns[0] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_1"));
        cr = new CellReference(morns[1] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_2"));
        cr = new CellReference(morns[2] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_3"));
        cr = new CellReference(morns[3] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_4"));
        cr = new CellReference(aft);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
        cr = new CellReference(afts[0] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_1"));
        cr = new CellReference(afts[1] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_2"));
        cr = new CellReference(afts[2] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_3"));
        cr = new CellReference(afts[3] + hoursRow);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_4"));
        cr = new CellReference(msg);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("TPL8_MESSAGE"));
        
        placeDatesAndFormat(sht, page, pagesNumber);
    }
    
}
