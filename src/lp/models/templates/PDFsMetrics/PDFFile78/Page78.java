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

package lp.models.templates.PDFsMetrics.PDFFile78;

import lp.models.templates.PDFsMetrics.AvailableFonts;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;

public abstract class Page78 extends BasePage {
    protected LARectangle pm, am, pm1, am1, date, toAdmin;
    protected LARectangle[] days, mornsBody, aftsBody;
            
    public Page78(short daysCount) {
        super();
        year = new LARectangle();
        pm = new LARectangle();
        am = new LARectangle();
        pm1 = new LARectangle();
        am1 = new LARectangle();
        date = new LARectangle();
        toAdmin = new LARectangle();
        days = new LARectangle[daysCount];
        mornsBody = new LARectangle[daysCount];
        aftsBody = new LARectangle[daysCount];
        for ( int i = 0; i < daysCount; i++ ) {
            days[i] = new LARectangle();
            days[i].setFormat(16, AvailableFonts.TIMES, true);
            mornsBody[i] = new LARectangle();
            aftsBody[i] = new LARectangle();
        }
        schoolInfos.setFormat(11, AvailableFonts.TAHOMA);
        title.setFormat(16, AvailableFonts.TAHOMA);
        group.setFormat(20, AvailableFonts.TAHOMA, true);
        year.setFormat(11, AvailableFonts.TAHOMA);
        pm.setFormat(12, AvailableFonts.TIMES, true);
        am.setFormat(12, AvailableFonts.TIMES, true);
        pm1.setFormat(12, AvailableFonts.TIMES);
        am1.setFormat(12, AvailableFonts.TIMES);
        date.setFormat(14, AvailableFonts.TAHOMA);
        toAdmin.setFormat(12, AvailableFonts.TIMES);
        
        schoolInfos.reset(547.199f, 20.28f, 273.84f, 48.242f);
        title.reset(197.52f, 20.28f, 349.68f, 24.122f);
        group.reset(23.519f, 44.401f, 174f, 24.122f);
        year.reset(23.519f, 20.28f, 174f, 24.122f);
        date.reset(197.52f, 44.401f, 349.68f, 24.12f);
        toAdmin.reset(721.199f, 78.12f, 72.481f, 42.001f);
        
    }

    public LARectangle getPm() {
        return pm;
    }

    public void setPm(LARectangle pm) {
        this.pm = pm;
    }

    public LARectangle getAm() {
        return am;
    }

    public void setAm(LARectangle am) {
        this.am = am;
    }

    public LARectangle getPm1() {
        return pm1;
    }

    public void setPm1(LARectangle pm1) {
        this.pm1 = pm1;
    }

    public LARectangle getAm1() {
        return am1;
    }

    public void setAm1(LARectangle am1) {
        this.am1 = am1;
    }

    public LARectangle getDate() {
        return date;
    }

    public void setDate(LARectangle date) {
        this.date = date;
    }

    public LARectangle getToAdmin() {
        return toAdmin;
    }

    public void setToAdmin(LARectangle toAdmin) {
        this.toAdmin = toAdmin;
    }

    public LARectangle[] getDays() {
        return days;
    }

    public LARectangle[] getMornsBody() {
        return mornsBody;
    }

    public LARectangle[] getAftsBody() {
        return aftsBody;
    }
    
}
