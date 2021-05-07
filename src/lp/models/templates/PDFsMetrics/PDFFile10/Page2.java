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

package lp.models.templates.PDFsMetrics.PDFFile10;

public class Page2 extends Page {

    private final float bodyH;
    
    public Page2() {
        super();
        
        stuRect.setRow(107.156f, 13.922f);
        stuRect.getNumero().setXW(567.247f, 14.512f);
        stuRect.getSecondName().setXW(417f, 149.762f);
        toAdmin.reset(417f, 787.68f, 164.76f, 33.238f);
        signature.reset(417f, 747.959f, 164.76f, 38.762f);
        
        bodyH = 678.598f;
        for ( int i = 0; i < daysCount; i++ ) {
            mornsBody[i].setHeight(bodyH);
            aftsBody[i].setHeight(bodyH);
        }
    }
    
}
