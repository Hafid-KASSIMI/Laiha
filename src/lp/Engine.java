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

package lp;

import java.util.logging.Level;
import java.util.logging.Logger;
import lp.models.templates.pdffiles.*;
import lp.models.templates.workbooks.*;
import lp.models.ListItem;
import lp.models.templates.BaseTemplate;
import lp.util.JobsLogger;
import org.sicut.db.Preferences;

public class Engine {

    private BaseTemplate tpl;

    public Engine(ListItem[] items) {
        this();
        setItems(items);
    }

    public Engine() {
        tpl = new BaseTemplate();
    }

    public final void setItems(ListItem[] items) {
        tpl.setItems(items);
    }
    
    public JobsLogger getPDFJobs() {
        return tpl.getPDFJobs();
    }

    public JobsLogger getXLJobs() {
        return tpl.getXLJobs();
    }
    
    public void vroom() {
        try {
            Settings.PREF_TEMPORARY = (Preferences) Settings.PREF_BUNDLE.clone();
        } catch (CloneNotSupportedException ex) {}
        if (Settings.PREF_TEMPORARY.get("XL_EXPORT").equals("Y")) {
            switch (Settings.PREF_TEMPORARY.get("SELECTED_TEMPLATE")) {
                case "1":
                    tpl.setWb(new Workbook1());
                    break;
                case "2":
                    tpl.setWb(new Workbook2());
                    break;
                case "3":
                    tpl.setWb(new Workbook3());
                    break;
                case "4":
                    tpl.setWb(new Workbook4());
                    break;
                case "5":
                    tpl.setWb(new Workbook5());
                    break;
                case "6":
                    tpl.setWb(new Workbook6());
                    break;
                case "7":
                    tpl.setWb(new Workbook7());
                    break;
                case "8":
                    tpl.setWb(new Workbook8());
                    break;
                case "9":
                    tpl.setWb(new Workbook9());
                    break;
                case "10":
                    tpl.setWb(new Workbook10());
                    break;
            }
            if (tpl.getWb() != null) {
                new Thread(() -> {
                    tpl.exportXLSX();
                }).start();
            }
        }
        if (Settings.PREF_TEMPORARY.get("PDF_EXPORT").equals("Y")) {
            switch (Settings.PREF_TEMPORARY.get("SELECTED_TEMPLATE")) {
                case "1":
                    tpl.setPdf(new PDFFile1());
                    break;
                case "2":
                    tpl.setPdf(new PDFFile2());
                    break;
                case "3":
                    tpl.setPdf(new PDFFile3());
                    break;
                case "4":
                    tpl.setPdf(new PDFFile4());
                    break;
                case "5":
                    tpl.setPdf(new PDFFile5());
                    break;
                case "6":
                    tpl.setPdf(new PDFFile6());
                    break;
                case "7":
                    tpl.setPdf(new PDFFile7());
                    break;
                case "8":
                    tpl.setPdf(new PDFFile8());
                    break;
                case "9":
                    tpl.setPdf(new PDFFile9());
                    break;
                case "10":
                    tpl.setPdf(new PDFFile10());
                    break;
            }
            if (tpl.getPdf() != null) {
                new Thread(() -> {
                    tpl.exportPDF();
                }).start();
            }
        }
    }

}
