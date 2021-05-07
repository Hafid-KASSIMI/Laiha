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

package lp.models.templates;

import lp.models.templates.pdffiles.BasePDFFile;
import lp.models.templates.workbooks.BaseWorkbook;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lp.Settings;
import lp.models.Group;
import lp.models.Level;
import lp.models.ListItem;
import lp.util.JobsLogger;

public class BaseTemplate {
    protected BasePDFFile pdf;
    protected BaseWorkbook wb;
    protected JobsLogger pdfJobs, xlJobs;
    protected ListItem[] items;
    private int itemsCount;

    public BaseTemplate(ListItem[] items) {
        this();
        setItems(items);
    }

    public BaseTemplate() {
        pdf = null;
        wb = null;
        xlJobs = new JobsLogger();
        pdfJobs = new JobsLogger();
    }
    
    public JobsLogger getPDFJobs() {
        return pdfJobs;
    }

    public JobsLogger getXLJobs() {
        return xlJobs;
    }

    public BasePDFFile getPdf() {
        return pdf;
    }

    public void setPdf(BasePDFFile pdf) {
        this.pdf = pdf;
    }

    public BaseWorkbook getWb() {
        return wb;
    }

    public void setWb(BaseWorkbook wb) {
        this.wb = wb;
    }

    public ListItem[] getItems() {
        return items;
    }

    public final void setItems(ListItem[] items) {
        this.items = items;
        itemsCount = 0;
        for ( ListItem item : items ) {
            itemsCount += item.getIslevel() ? item.getLevel().getGroupsCount() : 1;
        }
    }
    
    protected String generateFileName(String extension) {
        return "/Listes " + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now()) + "." + extension;
    }
    
    protected String generateFileName(String grpName, String extension) {
        return "/" + grpName + " " + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now()) + "." + extension;
    }
    
    protected String generateDirectoryName(String extension) {
        String s = ( ( extension.equals("pdf") ) ? "Listes PDF " : "Classeurs Excel " );
        return "/" + s + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now());
    }
    
    public void exportXLSX() {
        Boolean force2pgs = Settings.PREF_TEMPORARY.get("TWO_SHEETS_PER_GROUP").equals("Y");
        Boolean oneFilePerGrp = Settings.PREF_TEMPORARY.get("ONE_FILE_PER_GROUP").equals("Y");
        String selectedDest = Settings.PREF_TEMPORARY.get("OUTPUT_DIR");
        if ( !(new File(selectedDest)).exists() )
            selectedDest = Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR");
        xlJobs.setTotalJobs(itemsCount);
        xlJobs.start();
        if ( oneFilePerGrp ) {
            String dirPath = selectedDest + generateDirectoryName("xlsx");
            String takenNames = "";
            String grpName, tmpName;
            if ( !(new File(dirPath)).mkdir() )
                dirPath = selectedDest;
            for ( ListItem item : items ) {
                if ( item.getIslevel() ) {
                    for ( Group g : item.getLevel().getGroups() ) {
                        wb.reset();
                        xlJobs.setCurrentJob(g.getName());
                        wb.addGroup(g, force2pgs);
                        xlJobs.increment();
                        tmpName = g.getName() + "|";
                        grpName = g.getName();
                        if ( takenNames.contains(tmpName) ) {
                            int idx = 0;
                            int counter = 0;
                            while ((idx = takenNames.indexOf(tmpName, idx)) != -1 ){
                                counter++;
                                idx++;
                            }
                            grpName += " (" + counter + ")";
                        }
                        takenNames += grpName + "|";
                        wb.save(dirPath + generateFileName(grpName, "xlsx"));
                    }
                }
                else {
                    wb.reset();
                    xlJobs.setCurrentJob(item.getGroup().getName());
                    wb.addGroup(item.getGroup(), force2pgs);
                    xlJobs.increment();
                    grpName = item.getGroup().getName();
                    tmpName = grpName + "|";
                    if ( takenNames.contains(tmpName) ) {
                        int idx = 0;
                        int counter = 0;
                        while ((idx = takenNames.indexOf(tmpName, idx)) != -1 ){
                            counter++;
                            idx++;
                        }
                        grpName += " (" + counter + ")";
                    }
                    takenNames += tmpName;
                    wb.save(dirPath + generateFileName(grpName, "xlsx"));
                }
            }
        }
        else {
            wb.reset();
            for ( ListItem item : items ) {
                if ( item.getIslevel() ) {
                    Level lev = item.getLevel();
                    for ( int i = 0, n = lev.getGroupsCount(); i < n; i++ ) {
                        xlJobs.setCurrentJob(lev.getGroup(i).getName());
                        wb.addGroup(lev.getGroup(i), force2pgs);
                        xlJobs.increment();
                    }
                }
                else {
                    xlJobs.setCurrentJob(item.getGroup().getName());
                    wb.addGroup(item.getGroup(), force2pgs);
                    xlJobs.increment();
                }
            }
            wb.save(selectedDest + generateFileName("xlsx"));
        }
        xlJobs.stop();
    }
    
    public void exportPDF() {
        Boolean force2pgs = Settings.PREF_TEMPORARY.get("TWO_SHEETS_PER_GROUP").equals("Y");
        Boolean oneFilePerGrp = Settings.PREF_TEMPORARY.get("ONE_FILE_PER_GROUP").equals("Y");
        String selectedDest = Settings.PREF_TEMPORARY.get("OUTPUT_DIR");
        if ( !(new File(selectedDest)).exists() )
            selectedDest = Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR");
        pdfJobs.setTotalJobs(itemsCount);
        pdfJobs.start();
        if ( oneFilePerGrp ) {
            String dirPath = selectedDest + generateDirectoryName("pdf");
            String takenNames = "";
            String grpName, tmpName;
            if ( !(new File(dirPath)).mkdir() )
                dirPath = selectedDest;
            for ( ListItem item : items ) {
                if ( item.getIslevel() ) {
                    for ( Group g : item.getLevel().getGroups() ) {
                        pdf.reset();
                        pdfJobs.setCurrentJob(g.getName());
                        pdf.addGroup(g, force2pgs);
                        pdfJobs.increment();
                        tmpName = g.getName() + "|";
                        grpName = g.getName();
                        if ( takenNames.contains(tmpName) ) {
                            int idx = 0;
                            int counter = 0;
                            while ((idx = takenNames.indexOf(tmpName, idx)) != -1 ){
                                counter++;
                                idx++;
                            }
                            grpName += " (" + counter + ")";
                        }
                        takenNames += grpName + "|";
                        pdf.save(dirPath + generateFileName(grpName, "pdf"));
                    }
                }
                else {
                    pdf.reset();
                    pdfJobs.setCurrentJob(item.getGroup().getName());
                    pdf.addGroup(item.getGroup(), force2pgs);
                    pdfJobs.increment();
                    grpName = item.getGroup().getName();
                    tmpName = grpName + "|";
                    if ( takenNames.contains(tmpName) ) {
                        int idx = 0;
                        int counter = 0;
                        while ((idx = takenNames.indexOf(tmpName, idx)) != -1 ){
                            counter++;
                            idx++;
                        }
                        grpName += " (" + counter + ")";
                    }
                    takenNames += tmpName;
                    pdf.save(dirPath + generateFileName(grpName, "pdf"));
                }
            }
        }
        else {
            pdf.reset();
            for ( ListItem item : items ) {
                if ( item.getIslevel() ) {
                    Level lev = item.getLevel();
                    for ( int i = 0, n = lev.getGroupsCount(); i < n; i++ ) {
                        pdfJobs.setCurrentJob(lev.getGroup(i).getName());
                        pdf.addGroup(lev.getGroup(i), force2pgs);
                        pdfJobs.increment();
                    }
                }
                else {
                    pdfJobs.setCurrentJob(item.getGroup().getName());
                    pdf.addGroup(item.getGroup(), force2pgs);
                    pdfJobs.increment();
                }
            }
            pdf.save(selectedDest + generateFileName("pdf"));
        }
        pdfJobs.stop();
    }
}
