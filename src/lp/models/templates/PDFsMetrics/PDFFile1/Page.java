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

package lp.models.templates.PDFsMetrics.PDFFile1;

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
        stuRect.setFirstName(new LARectangle());
        stuRect.setGender(new LARectangle());
        stuRect.setBirthDate(new LARectangle());
        stuRect.setBirthPlace(new LARectangle());
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TAHOMA);
        year.setFormat(12, AvailableFonts.TIMES);
        customMsg1.setFormat(12, AvailableFonts.TIMES, true);
        stuRect.getNumero().setFormat(12, AvailableFonts.TIMES);
        stuRect.getCode().setFormat(12, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        stuRect.getGender().setFormat(12, AvailableFonts.TIMES);
        stuRect.getBirthDate().setFormat(12, AvailableFonts.TIMES);
        stuRect.getBirthPlace().setFormat(8, AvailableFonts.TIMES);
        
        schoolInfos.reset(22.442f, 20.281f, 553.078f, 29.04f);
        title.reset(188.520f, 49.321f, 387f, 29.039f);
        year.reset(22.441f, 49.320f, 166.078f, 29.039f);
        customMsg1.reset(22.441f, 85.078f, 83.039f, 19.301f);
        stuRect.getNumero().setXW(554.519f, 21f);
        stuRect.getCode().setXW(471.48f, 83.038f);
        stuRect.getSecondName().setXW(298.199f, 173.281f);
        stuRect.getGender().setXW(260.281f, 37.917f);
        stuRect.getBirthDate().setXW(188.52f, 71.761f);
        stuRect.getBirthPlace().setXW(105.48f, 83.039f);
    }
    
}
