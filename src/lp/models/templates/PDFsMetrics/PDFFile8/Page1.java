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

package lp.models.templates.PDFsMetrics.PDFFile8;

public class Page1 extends Page {

    private final float[] daysYs = { 121.320f, 196.320f, 271.801f, 347.281f, 422.758f, 498.238f };

    public Page1() {
        super((short) 6);
        for ( short i = 0; i < 6; i++ ) {
            days[i].reset(794.398f, daysYs[i], 26.402f, 74.520f);
            mornsBody[i].reset(412.441f, daysYs[i], 308.520f, 74.520f);
            aftsBody[i].reset(102.719f, daysYs[i], 306.840f, 74.520f);
        }
        
        days[0].setHeight(74.039f);
        mornsBody[0].setHeight(74.039f);
        aftsBody[0].setHeight(74.039f);
    }
    
}
