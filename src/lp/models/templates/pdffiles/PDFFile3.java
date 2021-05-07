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

package lp.models.templates.pdffiles;

import com.ibm.icu.text.ArabicShapingException;
import java.io.IOException;
import lp.Settings;
import lp.models.Group;
import lp.models.Student;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.PDFFile3.*;
import lp.models.templates.PDFsMetrics.StudentLARectangle;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFFile3 extends PDFFile123456 {
    
    public PDFFile3() {
        super();
        tpl = tempaltesDir + "Template.3.pdf";
        pages[0] = new Page1();
        pages[1] = new Page2();
        pages[2] = new Page3();
        pages[3] = new Page4();
    }

    @Override
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount, BasePage metrics) {
        boolean bold = Settings.PREF_TEMPORARY.get("TPL3_BOLDER_NAMES").equals("Y");
        metrics.getStuRect().getSecondName().setIsBold(bold);
        metrics.getStuRect().getFirstName().setIsBold(bold);
        pageHeight = page.getBBox().getHeight();
        try {
            try (PDPageContentStream pcs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                wrapString(metrics.getSchoolInfos(), pcs, Settings.SCHOOL_DB.getAcademy() + "\n" + Settings.SCHOOL_DB.getDirection() + "\n" + Settings.SCHOOL_DB.getSchool());
                placeString(metrics.getYear(), pcs, Settings.SCHOOL_DB.getYear());
                placeString(metrics.getGroup(), pcs, g.getName());
                placeString(metrics.getTitle(), pcs, Settings.PREF_TEMPORARY.get("TPL3_TITLE"));
                placeString(metrics.getCustomMsg1(), pcs, Settings.PREF_TEMPORARY.get("TPL3_CUSTOM_STRING"));
                for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
                    placeStudent(metrics.getStuRect(), pcs, g.getStudent(i), j, getAlignment(Settings.PREF_TEMPORARY.get("TPL3_NAMES_ALIGN")));
                }
            } 
        } catch (IOException | ArabicShapingException | CloneNotSupportedException ex) { } 
    }
    
    @Override
    protected void placeStudent(StudentLARectangle rectangle, PDPageContentStream pcs, Student stu, int index, short hAlign)  throws IOException, ArabicShapingException {
        float y = rectangle.getRow().getY() + rectangle.getRow().getHeight() * index;
        float h = rectangle.getRow().getHeight();
        LARectangle tmp;
        
        tmp = rectangle.getNumero();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getFormattedNum());
        
        tmp = rectangle.getCode();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getCode());
        
        tmp = rectangle.getFirstName();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getFirName(), hAlign);

        tmp = rectangle.getSecondName();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getSecName(), hAlign);

        tmp = rectangle.getGender();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getGender());

        tmp = rectangle.getBirthDate();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getBirthDate());

        tmp = rectangle.getBirthPlace();
        tmp.setYH(y, h);
        try {
            wrapString(tmp, pcs, prepareToWrap(tmp, stu.getAddress()));
        } catch (CloneNotSupportedException ex) { }
    }
    
}
