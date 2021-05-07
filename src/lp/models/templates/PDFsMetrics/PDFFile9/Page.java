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

package lp.models.templates.PDFsMetrics.PDFFile9;

import lp.models.templates.PDFsMetrics.AvailableFonts;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.StudentLARectangle;

public abstract class Page extends BasePage {
    
    protected LARectangle date, signature, toAdmin;
    protected LARectangle[] days, morns, afts, mornsBody, aftsBody;
    
    public Page() {
        super();
        
        year = new LARectangle();
        date = new LARectangle();
        signature = new LARectangle();
        toAdmin = new LARectangle();
        days = new LARectangle[3];
        morns = new LARectangle[3];
        afts = new LARectangle[3];
        mornsBody = new LARectangle[3];
        aftsBody = new LARectangle[3];
        
        stuRect = new StudentLARectangle();
        
        stuRect.setNumero(new LARectangle());
        stuRect.setSecondName(new LARectangle());
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TIMES);
        group.setFormat(13, AvailableFonts.TIMES, true);
        year.setFormat(12, AvailableFonts.TIMES);
        date.setFormat(12, AvailableFonts.TIMES);
        signature.setFormat(12, AvailableFonts.TIMES);
        toAdmin.setFormat(12, AvailableFonts.TIMES);
        for ( int i = 0; i < 3; i++ ) {
            days[i] = new LARectangle();
            days[i].setFormat(12, AvailableFonts.TIMES, true);
            morns[i] = new LARectangle();
            morns[i].setFormat(9, AvailableFonts.TIMES);
            afts[i] = new LARectangle();
            afts[i].setFormat(9, AvailableFonts.TIMES);
            mornsBody[i] = new LARectangle();
            aftsBody[i] = new LARectangle();
        }
        stuRect.getNumero().setFormat(10, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(402.961f, 19.801f, 178.32f, 45.359f);
        title.reset(145.68f, 19.801f, 257.281f, 22.68f);
        date.reset(145.68f, 42.481f, 257.281f, 22.68f);
        year.reset(16.078f, 19.801f, 129.602f, 22.68f);
        group.reset(16.078f, 42.481f, 129.602f, 22.68f);
        date.reset(145.68f, 42.481f, 257.281f, 22.68f);
        days[0].reset(273.961f, 67.559f, 128.064f, 15.961f);
        days[1].reset(145.32f, 67.559f, 126.69f, 15.961f);
        days[2].reset(17.039f, 67.559f, 126.359f, 15.961f);
        morns[0].reset(338.762f, 84.481f, 63.263f, 21.739f);
        morns[1].reset(210.121f, 84.481f, 61.918f, 21.739f);
        morns[2].reset(81.48f, 84.481f, 61.918f, 21.739f);
        afts[0].reset(273.961f, 84.481f, 61.918f, 21.739f);
        afts[1].reset(145.32f, 84.481f, 61.918f, 21.739f);
        afts[2].reset(16.684f, 84.481f, 61.918f, 21.739f);
        mornsBody[0].reset(338.762f, 108.121f, 63.238f, 0);
        mornsBody[1].reset(210.121f, 108.121f, 62.028f, 0);
        mornsBody[2].reset(81.48f, 108.121f, 62.028f, 0);
        aftsBody[0].reset(273.837f, 108.121f, 62.042f, 0);
        aftsBody[1].reset(145.196f, 108.121f, 62.042f, 0);
        aftsBody[2].reset(16.559f, 108.121f, 61.594f, 0);
        
        stuRect.getNumero().setXW(561.961f, 19.32f);
        stuRect.getSecondName().setXW(402.612f, 159.348f);
        signature.reset(402.961f, 0, 178.052f, 38.762f);
        toAdmin.setXW(402.961f, 178.052f);
    }

    public LARectangle getDate() {
        return date;
    }

    public LARectangle getSignature() {
        return signature;
    }

    public LARectangle getToAdmin() {
        return toAdmin;
    }

    public LARectangle[] getDays() {
        return days;
    }

    public LARectangle[] getMorns() {
        return morns;
    }

    public LARectangle[] getAfts() {
        return afts;
    }

    public LARectangle[] getMornsBody() {
        return mornsBody;
    }

    public LARectangle[] getAftsBody() {
        return aftsBody;
    }
    
}
