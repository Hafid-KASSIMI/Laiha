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
import java.util.Calendar;
import lp.Settings;
import lp.models.Group;
import lp.models.Student;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.LARectangle;
import lp.models.templates.PDFsMetrics.PDFFile10.*;
import lp.models.templates.PDFsMetrics.StudentLARectangle;
import lp.util.ALIGNMENT;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WeekInfos;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFFile10 extends BasePDFFile {
    
    public PDFFile10() {
        super();
        tpl = tempaltesDir + "Template.10.pdf";
        pages[0] = new Page1();
        pages[1] = new Page2();
        pages[2] = new Page3();
        pages[3] = new Page4();
        SHEET1_MAX = 40;
        SHEET2_MAX = 46;
        grayingGap = 7;
    }
    
    @Override
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount, BasePage metrics) {
        boolean bold = Settings.PREF_TEMPORARY.get("TPL10_BOLDER_NAMES").equals("Y");
        Page pgMetrics = (Page) metrics;
        metrics.getStuRect().getSecondName().setIsBold(bold);
        pageHeight = page.getBBox().getHeight();
        try {
            try ( PDPageContentStream pcs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true) ) {
                wrapString(metrics.getSchoolInfos(), pcs, Settings.SCHOOL_DB.getAcademy() + "\n" + Settings.SCHOOL_DB.getDirection() + "\n" + Settings.SCHOOL_DB.getSchool());
                placeString(metrics.getYear(), pcs, Settings.SCHOOL_DB.getYear());
                placeString(metrics.getGroup(), pcs, g.getName());
                placeString(metrics.getTitle(), pcs, Settings.PREF_TEMPORARY.get("TPL10_TITLE"));
                placeString(pgMetrics.getSignature(), pcs, Settings.PREF_TEMPORARY.get("TPL10_SIGNATURE") + ":", ALIGNMENT.LEFT);
                placeString(pgMetrics.getToAdmin(), pcs, Settings.PREF_TEMPORARY.get("TPL10_TO_ADMINISTRATION") + ":", ALIGNMENT.LEFT);
                for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
                    placeStudent(metrics.getStuRect(), pcs, g.getStudent(i), j, getAlignment(Settings.PREF_TEMPORARY.get("TPL10_NAMES_ALIGN")));
                }
                placeTableHeader((Page) metrics, pcs);
            } catch (CloneNotSupportedException ex) {
                
            }
        } catch (IOException | ArabicShapingException ex) { } 
    }
    
    @Override
    protected void placeStudent(StudentLARectangle rectangle, PDPageContentStream pcs, Student stu, int index, short hAlign)  throws IOException, ArabicShapingException {
        float y = rectangle.getRow().getY() + rectangle.getRow().getHeight() * index;
        float h = rectangle.getRow().getHeight();
        LARectangle tmp;
        
        tmp = rectangle.getNumero();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getFormattedNum());
        
        tmp = rectangle.getAdditional();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getFormattedNum());

        tmp = rectangle.getSecondName();
        tmp.setYH(y, h);
        placeString(tmp, pcs, stu.getSecName() + " " + stu.getFirName(), hAlign);
    }
    
    private void placeTableHeader(Page metrics, PDPageContentStream pcs) throws IOException, ArabicShapingException, CloneNotSupportedException {
        WeekInfos wi;
        Integer sw, i, n, day;
        String from = Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " ";
        String to = " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " ";
        DateRange dr;
        try {
            wi = new WeekInfos(Integer.parseInt(Settings.PREF_TEMPORARY.get("TPL10_FIRST_DAY_OF_WEEK")));
        } catch(NumberFormatException nfe) {
            wi = new WeekInfos(Calendar.MONDAY);
        }
        try {
            sw = Integer.parseInt(Settings.PREF_TEMPORARY.get("TPL10_SELECTED_WEEK"));
        } catch(NumberFormatException nfe) {
            sw = 1;
        }
        day = wi.getFirstDay() - 1;
        dr = wi.getWeek(sw);
        i = 1;
        n = 7;
        placeBidirectionalString(metrics.getDate(), pcs, (from + dr.getBeginning() + to + dr.getEnd()));
        for ( int j = 0; i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            placeString(metrics.getDays()[j], pcs, Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            wrapString(metrics.getMorns()[j], pcs, prepareToWrap(metrics.getMorns()[j], Settings.PREF_TEMPORARY.get("ABSTPL_MORNING")), ALIGNMENT.CENTER);
            wrapString(metrics.getAfts()[j], pcs, prepareToWrap(metrics.getAfts()[j], Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON")));
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                gray(pcs, metrics.getMornsBody()[j], true);
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                gray(pcs, metrics.getAftsBody()[j], true);
            }
        }
    }
    
    @Override
    protected void add2PagesGroup(Group g) {
        int stuCount = g.getStudentsCount();
        int lastRow = ( (stuCount > SHEET3_MAX) ? SHEET3_MAX : stuCount );
        add1PageGroup( g, clonePage(doc.getPage(2)), 0, lastRow, pages[2] );
        add1PageGroup( g, clonePage(doc.getPage(3)), SHEET3_MAX, stuCount, pages[3] );
    }
    
    
    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( doc == null || g == null )   return;
        int n;
        n = g.getStudentsCount();
        if ( force2Pages || n >= SHEET2_MAX )
            add2PagesGroup(g);
        else if ( n <= SHEET1_MAX ) {
            add1PageGroup(g, clonePage(doc.getPage(0)), 0, n, pages[0]);
        }
        else if( n <= SHEET2_MAX ) {
            add1PageGroup(g, clonePage(doc.getPage(1)), 0, n, pages[1]);
        }
    }
}
