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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Workbook7 extends Workbook78 {
    
    public Workbook7(){
        super(7);
        schoolInfos = "A1";
        title = "E1";
        grp = "I2";
        year = "I1";
        week = "E2";
        tpl = tempaltesDir + "Template.7.xlsx";
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int page, int pagesNumber) {
        CellReference cr = new CellReference(schoolInfos);
        String separator = Settings.PREF_TEMPORARY.get("TPL7_SCHOOL_INFOS_SEPARATOR");
        separator = separator.equals("NL") ? "\n" : separator;
        sht.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(Settings.SCHOOL_DB.getAcademy() + separator + Settings.SCHOOL_DB.getDirection() + separator + Settings.SCHOOL_DB.getSchool());
        cr = new CellReference(year);
        sht.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(Settings.PREF_TEMPORARY.get("TPL7_YEAR") + Settings.SCHOOL_DB.getYear());
        cr = new CellReference(title);
        sht.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(Settings.PREF_TEMPORARY.get("TPL7_TITLE"));
        cr = new CellReference(grp);
        sht.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(g.getName());
        cr = new CellReference(toAdmin);
        sht.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(Settings.PREF_TEMPORARY.get("TPL7_TO_ADMINISTRATION"));
        cr = new CellReference(morn);
        sht.getRow(cr.getRow()).getCell(cr.getCol()).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
        cr = new CellReference(aft);
        sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
        for ( int i = 0; i < 4; i++ ) {
            cr = new CellReference(morns[i] + hoursRow);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_" + ( i + 1 )));
            cr = new CellReference(afts[i] + hoursRow);
            sht.getRow(cr.getRow()).getCell(cr.getCol(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_" + ( i + 1 )));
        }
        placeDatesAndFormat(sht, page, pagesNumber);
    }

}
