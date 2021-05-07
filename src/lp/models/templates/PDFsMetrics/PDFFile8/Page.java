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

import lp.models.templates.PDFsMetrics.AvailableFonts;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.PDFFile78.Page78;

public abstract class Page extends Page78 {
            
    public Page(short daysCount) {
        super(daysCount);
        
        customMsg1 = new LARectangle();
        customMsg1.setFormat(12, AvailableFonts.TIMES);
        
        pm.reset(102.238f, 78.12f, 307.801f, 21.313f);
        am.reset(411.960f, 78.12f, 309.238f, 21.48f);
        pm1.reset(334.441f, 99.433f, 75.598f, 20.522f);
        am1.reset(644.039f, 99.599f, 77.159f, 20.522f);
        customMsg1.reset(22.801f, 116.036f, 72.715f, 454.143f);
        
    }
    
}
