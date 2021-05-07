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

package lp.models.templates.PDFsMetrics;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDFont;

public abstract class BasePage {
    protected float vGap;
    
    protected LARectangle schoolInfos, title, group, year, customMsg1, customMsg2;
    protected StudentLARectangle stuRect;
    
    public BasePage() {
        schoolInfos =  new LARectangle();
        title =  new LARectangle();
        group =  new LARectangle();
    }

    public LARectangle getSchoolInfos() {
        return schoolInfos;
    }

    public void setSchoolInfos(LARectangle schoolInfos) {
        this.schoolInfos = schoolInfos;
    }

    public LARectangle getTitle() {
        return title;
    }

    public void setTitle(LARectangle title) {
        this.title = title;
    }

    public LARectangle getGroup() {
        return group;
    }

    public void setGroup(LARectangle group) {
        this.group = group;
    }

    public LARectangle getYear() {
        return year;
    }

    public void setYear(LARectangle year) {
        this.year = year;
    }

    public LARectangle getCustomMsg1() {
        return customMsg1;
    }

    public void setCustomMsg1(LARectangle customMsg1) {
        this.customMsg1 = customMsg1;
    }

    public LARectangle getCustomMsg2() {
        return customMsg2;
    }

    public void setCustomMsg2(LARectangle customMsg2) {
        this.customMsg2 = customMsg2;
    }

    public StudentLARectangle getStuRect() {
        return stuRect;
    }

    public void setStuRect(StudentLARectangle stuRect) {
        this.stuRect = stuRect;
    }
    
    public float getStudentY(int i, PDFont fnt, int fntSz) throws IOException {
        return stuRect.getRow().getY() - vGap * i - fntSz / 3f;
    }
    
    public String normalize1stNameW(String fName, PDFont fnt, int fntSz) throws IOException {
        float coef = fntSz / 1000f;
        fName = fName.replaceAll("\\p{M}", "");
        try {
            if ( fnt.getStringWidth(fName) * coef < stuRect.getFirstName().getWidth() ) return fName;
            do {
                fName = fName.substring(0, fName.length() - 3) + "..";
            } while ( fnt.getStringWidth(fName) * coef > stuRect.getFirstName().getWidth() );
            return fName;
        }
        catch ( IllegalArgumentException ex ) {
            return "";
        }
    }
    
    public String normalize2ndNameW(String lName, PDFont fnt, int fntSz) throws IOException {
        float coef = fntSz / 1000f;
        lName = lName.replaceAll("\\p{M}", "");
        try {
            if ( fnt.getStringWidth(lName) * coef < stuRect.getSecondName().getWidth() ) return lName;
            do {
                lName = lName.substring(0, lName.length() - 3) + "..";
            } while ( fnt.getStringWidth(lName) * coef > stuRect.getSecondName().getWidth() );
            return lName;
        }
        catch ( IllegalArgumentException ex ) {
            return "";
        }
    }
    
    public String normalizeBirthPlaceW(String bPlc, PDFont fnt, int fntSz) throws IOException {
        float coef = fntSz / 1000f;
        bPlc = bPlc.replaceAll("\\p{M}", "");
        try {
            if ( fnt.getStringWidth(bPlc) * coef < stuRect.getBirthPlace().getWidth() ) return bPlc;
            do {
                bPlc = bPlc.substring(0, bPlc.length() - 3) + "..";
            } while ( fnt.getStringWidth(bPlc) * coef > stuRect.getBirthPlace().getWidth() );
            return bPlc;
        }
        catch ( IllegalArgumentException ex ) {
            return "";
        }
    }
    
    
}
