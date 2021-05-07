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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import lp.Settings;
import lp.models.Group;
import lp.models.templates.PDFsMetrics.PDFFile78.Page78;
import lp.models.templates.PDFsMetrics.LARectangle;
import static lp.util.ALIGNMENT.*;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WEEK;
import lp.util.Calendar.WeekInfos;
import lp.util.Misc;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.Matrix;

public abstract class PDFFile78 extends BasePDFFile {
    
    public PDFFile78(int index) {
        super();
        tpl = tempaltesDir + "Template." + index + ".pdf";
        pagesCount = 2;
    }

    @Override
    protected void add2PagesGroup(Group g) {
        add1PageGroup(g, clonePage(doc.getPage(1)), 1, 2, pages[1]);
        add1PageGroup(g, clonePage(doc.getPage(1)), 2, 2, pages[1]);
    }

    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( doc == null || g == null )   return;
        if ( force2Pages )
            add2PagesGroup( g );
        else
            add1PageGroup(g, clonePage(doc.getPage(0)), 1, 1, pages[0]);
    }
    
    protected void placeTableHeader(PDPage page, int pageIndex, int pagesNumber, String tplPfx, Page78 metrics, PDPageContentStream pcs) throws IOException, ArabicShapingException, CloneNotSupportedException {
        WeekInfos wi;
        Integer sw, i, n, day;
        String from = Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " ";
        String to = " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " ";
        DateRange dr;
        float xAm1, xPm1;
        xAm1 = metrics.getAm1().getX();
        xPm1 = metrics.getPm1().getX();
        try {
            wi = new WeekInfos(Integer.parseInt(Settings.PREF_TEMPORARY.get(tplPfx + "FIRST_DAY_OF_WEEK")));
        } catch(NumberFormatException nfe) {
            wi = new WeekInfos(Calendar.MONDAY);
        }
        try {
            sw = Integer.parseInt(Settings.PREF_TEMPORARY.get(tplPfx + "SELECTED_WEEK"));
        } catch(NumberFormatException nfe) {
            sw = 1;
        }
        day = wi.getFirstDay() - 1;
        if ( pagesNumber == 1  ) {
            dr = wi.getWeek(sw);
            i = 1;
            n = 7;
        }
        else {
            if ( pageIndex == 1 ) {
                i = 1;
                n = 4;
                dr = wi.getWeekHalf(WEEK.FIRST_HALF, sw);
            }
            else {
                i = 4;
                n = 7;
                day = ( day + 3 ) % 7;
                dr = wi.getWeekHalf(WEEK.SECOND_HALF, sw);
            }
        }
        placeBidirectionalString(metrics.getDate(), pcs, (from + dr.getBeginning() + to + dr.getEnd()));
        for ( int j = 0; i < n; i++, day = ( day + 1 ) % 7, j++ ) {
            placeAndMinusPiRotateString(metrics.getDays()[j], pcs, Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                gray(pcs, metrics.getMornsBody()[j], false);
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                gray(pcs, metrics.getAftsBody()[j], false);
            }
        }
        wrapString(metrics.getToAdmin(), pcs, prepareToWrap(metrics.getToAdmin(), Settings.PREF_TEMPORARY.get(tplPfx + "TO_ADMINISTRATION")));
        placeBidirectionalString(metrics.getAm(), pcs, Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
        placeBidirectionalString(metrics.getPm(), pcs, Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
        for ( i = 1; i < 5; i++ ) {
            placeBidirectionalString(metrics.getAm1(), pcs, Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_" + i));
            placeBidirectionalString(metrics.getPm1(), pcs, Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_" + i));
            metrics.getAm1().setX(metrics.getAm1().getX() - metrics.getAm1().getWidth());
            metrics.getPm1().setX(metrics.getPm1().getX() - metrics.getPm1().getWidth());
        }
        metrics.getAm1().setX(xAm1);
        metrics.getPm1().setX(xPm1);
    }
    
    protected void placeDays(LARectangle rectangle, PDPageContentStream pcs, String string) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        Matrix mx;
        float x, y;
        String str = "";
        String parts[] = string.split(" ");
        for ( String part : parts ) {
            str = ( ( Misc.isArabic(part) ) ? new StringBuilder(AR_SHAPING.shape(part)).reverse().toString() : part ) + " " + str;
        }
        str = str.trim();
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        coef /= 2;
        x = rectangle.getTransformedX();
        y = rectangle.getTransformedY(pageHeight);
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        mx = new Matrix();
        mx.translate(x, y);
        mx.rotate(-Math.PI / 2);
        mx.translate(rectangle.getHeight() / 2 - fnt.getStringWidth(str) * coef, rectangle.getWidth() / 2 - (fnt.getFontDescriptor().getCapHeight() - fnt.getFontDescriptor().getDescent()) * coef);
        pcs.setTextMatrix(mx);
        pcs.showText(str);
        pcs.endText();
    }
    
    protected void placeMessage(LARectangle rectangle, PDPageContentStream pcs, String string, short hAlign) throws IOException, ArabicShapingException, CloneNotSupportedException, IllegalArgumentException {
        float fntSize, coef;
        PDFont fnt;
        Matrix mx;
        float w, h, fnCap, x, a, b;
        int n;
        String[] lines;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        mx = new Matrix();
        mx.translate(rectangle.getTransformedX(), rectangle.getTransformedY(pageHeight) - rectangle.getHeight());
        mx.rotate(Math.PI / 2);
        w = rectangle.getHeight();
        rectangle.transpose();
        lines = prepareMessage(fnt, coef, string, w);
        n = lines.length;
        pcs.setTextMatrix(mx);
        fnCap = fnt.getFontDescriptor().getCapHeight() * coef;
        h = ( rectangle.getHeight() - fnCap * n ) / ( n * 2 );
        b = 0;
        for ( int i = 0; i < n; i++ ) {
            if ( Misc.isArabic(lines[i]) )
                lines[i] = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(lines[i])).reverse().toString());
            switch( hAlign ) {
                case CENTER:
                    a = (w - fnt.getStringWidth(lines[i]) * coef) / 2;
                    break;
                case RIGHT:
                    a = w - fnt.getStringWidth(lines[i]) * coef;
                    break;
                default:
                    a = 0;
            }
            x = a - b;
            b = a;
            pcs.newLineAtOffset(x, -h * (Math.signum(i) + 1) - fnCap);
            pcs.showText( lines[i] );
        }
        pcs.endText();
        rectangle.transpose();
    }
    
    private String[] prepareMessage(PDFont fnt, float coef, String message, float width) throws IOException, IllegalArgumentException {
        ArrayList<String> lines = new ArrayList(Arrays.asList(message.split("\n")));
        for ( int i = 0, li; i < lines.size(); i++ ) {
            if( fnt.getStringWidth(lines.get(i)) * coef > width ) {
                String part = lines.get(i), last = "";
                while ( (li = part.lastIndexOf(" ")) != -1 ) {
                    last = part.substring(li) + last;
                    part = part.substring(0, li);
                    if( fnt.getStringWidth(part) * coef <= width ) {
                        lines.set(i, part);
                        if ( i < lines.size() - 1 ) {
                            lines.add(i + 1, last);
                        }
                        else {
                            lines.add(last);
                        }
                        break;
                    }
                }
            }
        }
        return lines.toArray(new String[lines.size()]);
    }
    
}
