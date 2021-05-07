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

package lp.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lp.Settings;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WeekInfos;

public class Settings78Controller implements Initializable {

    @FXML protected TextField shtTitleTF, toAdminTF, mornTF, aftTF, m1TF, m2TF, m3TF, m4TF, a1TF, a2TF, a3TF, a4TF;
    @FXML protected CheckBox d1mCB, d1aCB, d2mCB, d2aCB, d3mCB, d3aCB, d4mCB, d4aCB, d5mCB, d5aCB, d6mCB, d6aCB;
    @FXML protected ComboBox firstDayCB, selWeekCB;
    @FXML protected Label day1L, day2L, day3L, day4L, day5L, day6L, curWeekL, nextWeekL, otherWeekL;
    @FXML protected RadioButton nextWeekRB, curWeekRB, othWeekRB;
    @FXML Button saveBtn;
    private String tpl, dtRng;
    private final WeekInfos wi;
    private final ArrayList<CheckBox> holidays;
    private final ArrayList<TextField> hours;
    private final ArrayList<Label> days;

    public Settings78Controller(int index) {
        this();
        setTemplate(index);
    }

    public Settings78Controller() {
        holidays = new ArrayList();
        hours = new ArrayList();
        days = new ArrayList();
        wi = new WeekInfos();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    protected final void setTemplate(int index) {
        tpl = "TPL" + index + "_";
    }
    
    protected void init(ResourceBundle rb) {
        int tmp;
        shtTitleTF.setText(Settings.PREF_BUNDLE.get(tpl + "TITLE"));
        toAdminTF.setText(Settings.PREF_BUNDLE.get(tpl + "TO_ADMINISTRATION"));
        mornTF.setText(Settings.PREF_BUNDLE.get("ABSTPL_MORNING"));
        aftTF.setText(Settings.PREF_BUNDLE.get("ABSTPL_AFTERNOON"));
        holidays.addAll(Arrays.asList(d1mCB, d1aCB, d2mCB, d2aCB, d3mCB, d3aCB, d4mCB, d4aCB, d5mCB, d5aCB, d6mCB, d6aCB));
        hours.addAll(Arrays.asList(m1TF, a1TF, m2TF, a2TF, m3TF, a3TF, m4TF, a4TF));
        days.addAll(Arrays.asList(day1L, day2L, day3L, day4L, day5L, day6L));
        try {
            tmp = Integer.parseInt(Settings.PREF_BUNDLE.get("WEEKS_RANGE_SIZE"));
        }
        catch( NumberFormatException nfe) {
            tmp = 0;
        }
        for ( int i = 0; i < tmp; i++ ) {
            selWeekCB.getItems().add(rb.getString("WEEKS_RANGE_" + i));
        }
        try {
            tmp = Integer.parseInt(Settings.PREF_BUNDLE.get(tpl + "SELECTED_WEEK"));
        }
        catch( NumberFormatException nfe) {
            tmp = 0;
        }
        switch (tmp) {
            case 0:
                curWeekRB.setSelected(true);
                break;
            case 1:
                nextWeekRB.setSelected(true);
                break;
            default:
                othWeekRB.setSelected(true);
                break;
        }
        selWeekCB.getSelectionModel().select(tmp + 4);
        selWeekCB.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            DateRange dr;
            dr = wi.getWeek(selWeekCB.getSelectionModel().getSelectedIndex() - 4);
            otherWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        });
        for ( int i = 0, n = hours.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            hours.get(i).setText(Settings.PREF_BUNDLE.get("ABSTPL_" + ( i % 2 == 0 ? "MORNING_" : "AFTERNOON_" ) + j));
        }
        for ( int i = 0, n = holidays.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            holidays.get(i).setSelected(Settings.PREF_BUNDLE.get("ABSTPL_DAY_" + j + ( i % 2 == 0 ? "_MORNING" : "_AFTERNOON" ) + "_GRAYED").equals("Y"));
        }
        for ( int i = 0; i < 7; i++ ) {
            firstDayCB.getItems().add(rb.getString("WEEK_DAY_" + i));
        }
        try {
            tmp = Integer.parseInt(Settings.PREF_BUNDLE.get(tpl + "FIRST_DAY_OF_WEEK"));
        }
        catch( NumberFormatException nfe) {
            tmp = 0;
        }
        dtRng = rb.getString("FROM") + " %FROM% " + rb.getString("TO") + " %TO%";
        firstDayCB.getSelectionModel().select(tmp);
        generateDates(tmp + 1);
        firstDayCB.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            generateDates(newValue.intValue() + 1);
        });
        saveBtn.setOnAction(evt -> {
            save();
            saveBtn.getScene().getWindow().hide();
        });
    }
    
    protected void save() {
        int w;
        if ( nextWeekRB.isSelected() )
            w = 1;
        else if ( curWeekRB.isSelected() )
            w = 0;
        else {
            w = selWeekCB.getSelectionModel().getSelectedIndex() - 4;
        }
        Settings.PREF_BUNDLE.update(tpl + "SELECTED_WEEK", w + "");
        Settings.PREF_BUNDLE.update(tpl + "TITLE", shtTitleTF.getText());
        Settings.PREF_BUNDLE.update(tpl + "TO_ADMINISTRATION", toAdminTF.getText());
        Settings.PREF_BUNDLE.update("ABSTPL_MORNING", mornTF.getText());
        Settings.PREF_BUNDLE.update("ABSTPL_AFTERNOON", aftTF.getText());
        Settings.PREF_BUNDLE.update(tpl + "FIRST_DAY_OF_WEEK", firstDayCB.getSelectionModel().getSelectedIndex() + "");
        for( int i = 0, n = hours.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            Settings.PREF_BUNDLE.update("ABSTPL_" + ( i % 2 == 0 ? "MORNING_" : "AFTERNOON_" ) + j, hours.get(i).getText());
        }
        for( int i = 0, n = holidays.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            Settings.PREF_BUNDLE.update("ABSTPL_DAY_" + j + ( i % 2 == 0 ? "_MORNING_GRAYED" : "_AFTERNOON_GRAYED"), holidays.get(i).isSelected() ? "Y" : "N");
        }
        
    }
    
    private void generateDates(int firstDay) {
        DateRange dr;
        wi.setFirstDay(firstDay);
        dr = wi.getCurrentWeek();
        curWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        dr = wi.getNextWeek();
        nextWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        dr = wi.getWeek(selWeekCB.getSelectionModel().getSelectedIndex() - 4);
        otherWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        for ( int i = 0; i < 6; i++ ) {
            days.get(i).setText(firstDayCB.getItems().get((firstDay - 1 + i) % 7).toString());
        }
    }
    
}
