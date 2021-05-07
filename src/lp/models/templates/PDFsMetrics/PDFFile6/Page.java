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

package lp.models.templates.PDFsMetrics.PDFFile6;

import lp.models.templates.PDFsMetrics.AvailableFonts;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.StudentLARectangle;

public abstract class Page extends BasePage {

    public Page() {
        super();
        
        year = new LARectangle();
        stuRect = new StudentLARectangle();
        customMsg1 = new LARectangle();
        
        stuRect.setNumero(new LARectangle());
        stuRect.setCode(new LARectangle());
        stuRect.setSecondName(new LARectangle());
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TAHOMA);
        year.setFormat(12, AvailableFonts.TIMES);
        customMsg1.setFormat(12, AvailableFonts.TIMES, true);
        stuRect.getNumero().setFormat(12, AvailableFonts.TIMES);
        stuRect.getCode().setFormat(12, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(194.161f, 20.282f, 380.398f, 30.957f);
        title.reset(194.161f, 51.239f, 380.398f, 30.957f);
        year.reset(36.558f, 62.375f, 100.01f, 25f);
        customMsg1.reset(23.282f, 85.921f, 42.719f, 19.316f);
        stuRect.getNumero().setXW(553.559f, 21f);
        stuRect.getCode().setXW(469.8f, 83.758f);
        stuRect.getSecondName().setXW(279.601f, 190.199f);
    }
    
}
