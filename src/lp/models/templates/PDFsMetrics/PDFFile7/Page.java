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

import lp.models.templates.PDFsMetrics.PDFFile78.Page78;

public abstract class Page extends Page78 {
    
    public Page(short daysCount) {
        super(daysCount);
        
        pm.reset(23.52f, 78.12f, 347.999f, 21.48f);
        am.reset(371.519f, 78.12f, 349.68f, 21.48f);
        pm1.reset(284.519f, 99.84f, 87f, 20.522f);
        am1.reset(634.199f, 99.599f, 87f, 20.522f);
        
    }
    
}
