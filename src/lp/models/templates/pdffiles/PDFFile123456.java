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

package lp.models.templates.pdffiles;

import lp.models.Group;

public abstract class PDFFile123456 extends BasePDFFile {
    
    public PDFFile123456(){
        super();
    }

    
    @Override
    protected void add2PagesGroup(Group g) {
        int stuCount = g.getStudentsCount();
        int lastRow = ( (stuCount > SHEET3_MAX) ? SHEET3_MAX : stuCount );
        add1PageGroup(g, clonePage(doc.getPage(2)), 0, lastRow, pages[2]);
        add1PageGroup(g, clonePage(doc.getPage(3)), SHEET3_MAX, stuCount, pages[3]);
    }
    
    
    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( doc == null || g == null )   return;
        int n;        
        n = g.getStudentsCount();
        if ( force2Pages || n >= SHEET2_MAX ) {
            add2PagesGroup(g);
        }
        else if ( n < SHEET1_MAX )
            add1PageGroup( g, clonePage(doc.getPage(0)), 0, n, pages[0] );
        else if( n < SHEET2_MAX ) 
            add1PageGroup( g, clonePage(doc.getPage(1)), 0, n, pages[1] );
    }
    
    

}
