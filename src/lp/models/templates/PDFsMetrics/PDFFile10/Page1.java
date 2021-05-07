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

public class Page1 extends Page {
    
    private final float bodyH;
    
    public Page1() {
        super();
        
        stuRect.setRow(107.161f, 15.961f);
        stuRect.getNumero().setXW(567.24f, 14.519f);
        stuRect.getSecondName().setXW(416.519f, 150.721f);
        toAdmin.reset(417f, 785.762f, 164.76f, 34.797f);
        signature.reset(417f, 746.039f, 164.76f, 38.762f);
        
        bodyH = 676.68f;
        for ( int i = 0; i < daysCount; i++ ) {
            mornsBody[i].setHeight(bodyH);
            aftsBody[i].setHeight(bodyH);
        }
    }
    
}
