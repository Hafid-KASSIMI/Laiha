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

package lp.models.templates.PDFsMetrics.PDFFile3;

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
        group.setFormat(18, AvailableFonts.TAHOMA, true);
        year.setFormat(12, AvailableFonts.TIMES);
        customMsg1.setFormat(12, AvailableFonts.TIMES, true);
        stuRect.getNumero().setFormat(12, AvailableFonts.TIMES);
        stuRect.getCode().setFormat(12, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        stuRect.getFirstName().setFormat(12, AvailableFonts.TIMES);
        stuRect.getGender().setFormat(12, AvailableFonts.TIMES);
        stuRect.getBirthDate().setFormat(12, AvailableFonts.TIMES);
        stuRect.getBirthPlace().setFormat(8, AvailableFonts.TIMES);
        
        schoolInfos.reset(384.84f, 20.281f, 194.282f, 58.078f);
        title.reset(184.912f, 20.281f, 199.928f, 29.039f);
        group.reset(184.912f, 49.32f, 199.928f, 29.039f);
        year.reset(18.359f, 49.32f, 166.552f, 29.039f);
        customMsg1.reset(18.839f, 85.081f, 83.04f, 19.337f);
        stuRect.getNumero().setXW(558.122f, 21f);
        stuRect.getCode().setXW(475.081f, 83.041f);
        stuRect.getSecondName().setXW(384.84f, 90.241f);
        stuRect.getFirstName().setXW(294.599f, 90.24f);
        stuRect.getGender().setXW(256.68f, 37.92f);
        stuRect.getBirthDate().setXW(184.919f, 71.76f);
        stuRect.getBirthPlace().setXW(101.879f, 83.041f);
    }
    
}
