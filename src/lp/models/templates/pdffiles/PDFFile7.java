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
import lp.models.templates.PDFsMetrics.PDFFile78.Page78;
import lp.models.templates.PDFsMetrics.BasePage;
import lp.models.templates.PDFsMetrics.PDFFile7.*;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFFile7 extends PDFFile78 {
    
    public PDFFile7() {
        super(7);
        pages[0] = new Page1();
        pages[1] = new Page2();
    }

    @Override
    protected void add1PageGroup(Group g, PDPage page, int pageIndex, int pagesNumber, BasePage metrics) {
        pageHeight = page.getBBox().getHeight();
        try {
            try (PDPageContentStream pcs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                wrapString(metrics.getSchoolInfos(), pcs, Settings.SCHOOL_DB.getAcademy() + "\n" + Settings.SCHOOL_DB.getDirection() + "\n" + Settings.SCHOOL_DB.getSchool());
                placeBidirectionalString(metrics.getYear(), pcs, Settings.SCHOOL_DB.getYear(), Settings.PREF_TEMPORARY.get("TPL7_YEAR"));
                placeBidirectionalString(metrics.getTitle(), pcs, Settings.PREF_TEMPORARY.get("TPL7_TITLE"));
                placeString(metrics.getGroup(), pcs, g.getName());
                placeTableHeader(page, pageIndex, pagesNumber, "TPL7_", (Page78) metrics, pcs);
            } catch (CloneNotSupportedException ex) {
            }
        } catch (IOException | ArabicShapingException ex) { } 
    }
}
