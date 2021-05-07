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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import lp.Settings;

public class Settings16Controller implements Initializable {

    @FXML Button saveBtn;
    @FXML TextField titleTF, customStrTF, separatorTF;
    @FXML ToggleButton leftBtn, rightBtn, centerBtn;
    @FXML CheckBox bolderCB;
    private String tpl;
    
    public Settings16Controller() {
    
    }
    
    public void setTpl(int index) {
        tpl = "TPL" + index;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leftBtn.setTooltip(new Tooltip(rb.getString("LEFT")));
        rightBtn.setTooltip(new Tooltip(rb.getString("RIGHT")));
        centerBtn.setTooltip(new Tooltip(rb.getString("CENTER")));
    }
    
    public void init() {
        titleTF.setText(Settings.PREF_BUNDLE.get(tpl + "_TITLE"));
        customStrTF.setText(Settings.PREF_BUNDLE.get(tpl + "_CUSTOM_STRING"));
        separatorTF.setText(Settings.PREF_BUNDLE.get(tpl + "_SCHOOL_INFOS_SEPARATOR"));
        bolderCB.setSelected(Settings.PREF_BUNDLE.get(tpl + "_BOLDER_NAMES").equals("Y"));
        switch(Settings.PREF_BUNDLE.get(tpl + "_NAMES_ALIGN")) {
            case "L":
                leftBtn.setSelected(true);
                break;
            case "R":
                rightBtn.setSelected(true);
                break;
            default:
                centerBtn.setSelected(true);
                break;
        }
        saveBtn.setOnAction(evt -> {
            Settings.PREF_BUNDLE.update(tpl + "_TITLE", titleTF.getText());
            Settings.PREF_BUNDLE.update(tpl + "_CUSTOM_STRING", customStrTF.getText());
            Settings.PREF_BUNDLE.update(tpl + "_SCHOOL_INFOS_SEPARATOR", separatorTF.getText());
            Settings.PREF_BUNDLE.update(tpl + "_BOLDER_NAMES", bolderCB.isSelected() ? "Y" : "N");
            if ( leftBtn.isSelected() ) {
                Settings.PREF_BUNDLE.update(tpl + "_NAMES_ALIGN", "L");
            }
            else if ( rightBtn.isSelected() ) {
                Settings.PREF_BUNDLE.update(tpl + "_NAMES_ALIGN", "R");
            }
            else {
                Settings.PREF_BUNDLE.update(tpl + "_NAMES_ALIGN", "C");
            }
            titleTF.getScene().getWindow().hide();
        });
    }    
    
}
