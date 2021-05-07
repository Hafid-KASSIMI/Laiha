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

package lp.models.templates.PDFsMetrics.PDFFile9;

public class Page2 extends Page {

    private final float bodyH;
    
    public Page2() {
        super();
        
        stuRect.setRow(107.168f, 13.797f);
        signature.setY(742.437f);
        toAdmin.setYH(782.16f, 33.84f);
        bodyH = 673.128f;
        for ( int i = 0; i < 3; i++ ) {
            mornsBody[i].setHeight(bodyH);
            aftsBody[i].setHeight(bodyH);
        }
    }
    
}
