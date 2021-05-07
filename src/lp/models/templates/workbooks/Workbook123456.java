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

import lp.models.Group;

public abstract class Workbook123456 extends BaseWorkbook {

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
        if ( force2Pages || n >= SHEET2_MAX ) {
            add2PagesGroup(g, sheetName);
        }
        else if ( n < SHEET1_MAX )
            add1PageGroup( g, wb.cloneSheet(0, sheetName), 0, n );
        else if( n < SHEET2_MAX ) 
            add1PageGroup( g, wb.cloneSheet(1, sheetName), 0, n );
    }

    @Override
    protected void add2PagesGroup(Group g, String sheetName) {
        int stuCount = g.getStudentsCount();
        int lastRow = ( (stuCount > SHEET3_MAX) ? SHEET3_MAX : stuCount );
        add1PageGroup(g, wb.cloneSheet(2, sheetName + " | p1"), 0, lastRow);
        add1PageGroup(g, wb.cloneSheet(3, sheetName + " | p2"), SHEET3_MAX, stuCount);
    }
    
}
