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

package lp.models.templates.PDFsMetrics.PDFFile2;

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
        customMsg2 = new LARectangle();
        
        stuRect.setNumero(new LARectangle());
        stuRect.setCode(new LARectangle());
        stuRect.setSecondName(new LARectangle());
        stuRect.setFirstName(new LARectangle());
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TAHOMA);
        group.setFormat(18, AvailableFonts.TAHOMA, true);
        year.setFormat(12, AvailableFonts.TIMES);
        customMsg1.setFormat(12, AvailableFonts.TIMES);
        customMsg2.setFormat(12, AvailableFonts.TIMES, true);
        stuRect.getNumero().setFormat(12, AvailableFonts.TIMES);
        stuRect.getCode().setFormat(12, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        stuRect.getFirstName().setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(464.162f, 20.279f, 111.962f, 61.918f);
        title.reset(129.320f, 20.279f, 334.841f, 30.959f);
        group.reset(129.320f, 51.238f, 334.842f, 30.959f);
        year.reset(22.149f, 51.238f, 107.171f, 30.959f);
        customMsg1.reset(22.149f, 20.279f, 107.171f, 30.959f);
        customMsg2.reset(21.708f, 85.894f, 107.171f, 19.344f);
        stuRect.getNumero().setXW(554.400f, 21.479f);
        stuRect.getCode().setXW(464.160f, 90.24f);
        stuRect.getFirstName().setXW(128.878f, 167.641f);
        stuRect.getSecondName().setXW(296.52f, 167.641f);
    }
    
}
