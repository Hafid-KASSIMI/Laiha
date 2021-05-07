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

package lp.models.templates.PDFsMetrics.PDFFile4;

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
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TAHOMA);
        group.setFormat(18, AvailableFonts.TAHOMA, true);
        year.setFormat(12, AvailableFonts.TIMES);
        customMsg1.setFormat(12, AvailableFonts.TIMES, true);
        stuRect.getNumero().setFormat(12, AvailableFonts.TIMES);
        stuRect.getCode().setFormat(12, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        stuRect.getFirstName().setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(369.085f, 20.231f, 207.181f, 61.977f);
        title.reset(192.487f, 20.232f, 176.599f, 30.988f);
        group.reset(192.487f, 51.22f, 176.599f, 30.988f);
        year.reset(38.509f, 61.757f, 100.114f, 25f);
        customMsg1.reset(21.718f, 85.918f, 42.728f, 19.301f);
        stuRect.getNumero().setXW(555.266f, 21f);
        stuRect.getCode().setXW(471.508f, 83.758f);
        stuRect.getFirstName().setXW(278.064f, 91.082f);
        stuRect.getSecondName().setXW(369.146f, 102.362f);
    }
    
}
