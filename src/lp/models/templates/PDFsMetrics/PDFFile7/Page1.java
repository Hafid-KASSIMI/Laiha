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

package lp.models.templates.PDFsMetrics.PDFFile7;

public class Page1 extends Page {
    
    private final float[] daysYs = { 121.559f, 196.559f, 272.039f, 347.520f, 423f, 498.480f };
    
    public Page1() {
        super((short) 6);
        for ( short i = 0; i < 6; i++ ) {
            days[i].reset(794.160f, daysYs[i], 26.398f, 74.520f);
            mornsBody[i].reset(372.961f, daysYs[i], 347.758f, 74.520f);
            aftsBody[i].reset(24f, daysYs[i], 346.078f, 74.520f);
        }
        
        days[0].setHeight(74.039f);
        mornsBody[0].setHeight(74.039f);
        aftsBody[0].setHeight(74.039f);
    }
    
}
