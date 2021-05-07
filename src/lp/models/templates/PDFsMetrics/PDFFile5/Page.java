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

package lp.models.templates.PDFsMetrics.PDFFile5;

import lp.models.templates.PDFsMetrics.AvailableFonts;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.StudentLARectangle;

public abstract class Page extends BasePage {

    public Page() {
        super();
        
        year = new LARectangle();
        stuRect = new StudentLARectangle();
        stuRect.setNumero(new LARectangle());
        stuRect.setCode(new LARectangle());
        stuRect.setSecondName(new LARectangle());
        stuRect.setFirstName(new LARectangle());
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TAHOMA);
        group.setFormat(18, AvailableFonts.TAHOMA, true);
        year.setFormat(12, AvailableFonts.TIMES);
        stuRect.getNumero().setFormat(12, AvailableFonts.TIMES);
        stuRect.getCode().setFormat(12, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        stuRect.getFirstName().setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(368.238f, 20.279f, 207.162f, 61.918f);
        title.reset(174.888f, 20.279f, 193.350f, 30.848f);
        group.reset(174.888f, 51.127f, 193.350f, 30.848f);
        year.reset(32.131f, 61.122f, 88.355f, 26f);
        stuRect.getNumero().setXW(554.400f, 20.492f);
        stuRect.getCode().setXW(470.642f, 83.758f);
        stuRect.getFirstName().setXW(277.199f, 91.082f);
        stuRect.getSecondName().setXW(368.281f, 102.362f);
    }
    
}
