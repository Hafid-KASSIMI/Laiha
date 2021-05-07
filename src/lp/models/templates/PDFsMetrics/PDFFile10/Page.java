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

package lp.models.templates.PDFsMetrics.PDFFile10;

import lp.models.templates.PDFsMetrics.AvailableFonts;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.StudentLARectangle;

public abstract class Page extends BasePage {
    
    protected LARectangle date, signature, toAdmin;
    protected LARectangle[] days, morns, afts, mornsBody, aftsBody;
    protected final short daysCount = 6;
    private final float daysY, daysH, daysHalfsY, daysHalfsH;
    
    public Page() {
        super();
        
        year = new LARectangle();
        date = new LARectangle();
        signature = new LARectangle();
        toAdmin = new LARectangle();
        days = new LARectangle[daysCount];
        morns = new LARectangle[daysCount];
        afts = new LARectangle[daysCount];
        mornsBody = new LARectangle[daysCount];
        aftsBody = new LARectangle[daysCount];
        
        stuRect = new StudentLARectangle();
        
        stuRect.setNumero(new LARectangle());
        stuRect.setAdditional(new LARectangle());
        stuRect.setSecondName(new LARectangle());
        
        schoolInfos.setFormat(12, AvailableFonts.TIMES);
        title.setFormat(18, AvailableFonts.TIMES);
        group.setFormat(13, AvailableFonts.TIMES, true);
        year.setFormat(12, AvailableFonts.TIMES);
        date.setFormat(12, AvailableFonts.TIMES);
        signature.setFormat(12, AvailableFonts.TIMES);
        toAdmin.setFormat(12, AvailableFonts.TIMES);
        for ( int i = 0; i < daysCount; i++ ) {
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
        stuRect.getAdditional().setFormat(10, AvailableFonts.TIMES);
        stuRect.getSecondName().setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(416.668f, 19.801f, 164.614f, 45.359f);
        title.reset(120.593f, 19.801f, 296.074f, 22.68f);
        date.reset(120.593f, 42.481f, 296.074f, 22.68f);
        year.reset(16.078f, 19.801f, 104.515f, 22.68f);
        group.reset(16.078f, 42.481f, 104.515f, 22.68f);
        daysY = 68.039f;
        daysH = 15.48f;
        days[0].reset(352.32f, daysY, 63.719f, daysH);
        days[1].reset(288f, daysY, 62.398f, daysH);
        days[2].reset(223.68f, daysY, 62.398f, daysH);
        days[3].reset(144.84f, daysY, 62.398f, daysH);
        days[4].reset(80.52f, daysY, 62.402f, daysH);
        days[5].reset(16.199f, daysY, 62.402f, daysH);
        daysHalfsY = 84.48f;
        daysHalfsH = 21.719f;
        morns[0].reset(384.961f, daysHalfsY, 31.078f, daysHalfsH);
        morns[1].reset(320.641f, daysHalfsY, 29.758f, daysHalfsH);
        morns[2].reset(256.32f, daysHalfsY, 29.84f, daysHalfsH);
        morns[3].reset(177.48f, daysHalfsY, 29.758f, daysHalfsH);
        morns[4].reset(113.16f, daysHalfsY, 29.762f, daysHalfsH);
        morns[5].reset(48.84f, daysHalfsY, 29.762f, daysHalfsH);
        afts[0].reset(352.32f, daysHalfsY, 29.758f, daysHalfsH);
        afts[1].reset(288f, daysHalfsY, 29.762f, daysHalfsH);
        afts[2].reset(223.68f, daysHalfsY, 29.762f, daysHalfsH);
        afts[3].reset(144.84f, daysHalfsY, 29.762f, daysHalfsH);
        afts[4].reset(80.52f, daysHalfsY, 29.762f, daysHalfsH);
        afts[5].reset(16.559f, daysHalfsY, 29.402f, daysHalfsH);
        for ( int i = 0; i < daysCount; i++ ) {
            mornsBody[i].reset(morns[i].getX(), 108.121f, morns[i].getWidth(), 0);
            aftsBody[i].reset(afts[i].getX(), 108.121f, afts[i].getWidth(), 0);
        }
        stuRect.getAdditional().setXW(209.16f, 12.602f);
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
