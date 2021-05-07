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

public class Settings5Controller implements Initializable {
    
    @FXML Button saveBtn;
    @FXML ToggleButton leftBtn, rightBtn, centerBtn;
    @FXML CheckBox bolderCB;
    @FXML TextField titleTF;

    public Settings5Controller() {
        super();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleTF.setText(Settings.PREF_BUNDLE.get("TPL5_TITLE"));
        bolderCB.setSelected(Settings.PREF_BUNDLE.get("TPL5_BOLDER_NAMES").equals("Y"));
        leftBtn.setTooltip(new Tooltip(rb.getString("LEFT")));
        rightBtn.setTooltip(new Tooltip(rb.getString("RIGHT")));
        centerBtn.setTooltip(new Tooltip(rb.getString("CENTER")));
        switch(Settings.PREF_BUNDLE.get("TPL5_NAMES_ALIGN")) {
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
            Settings.PREF_BUNDLE.update("TPL5_TITLE", titleTF.getText());
            Settings.PREF_BUNDLE.update("TPL5_BOLDER_NAMES", bolderCB.isSelected() ? "Y" : "N");
            if ( leftBtn.isSelected() ) {
                Settings.PREF_BUNDLE.update("TPL5_NAMES_ALIGN", "L");
            }
            else if ( rightBtn.isSelected() ) {
                Settings.PREF_BUNDLE.update("TPL5_NAMES_ALIGN", "R");
            }
            else {
                Settings.PREF_BUNDLE.update("TPL5_NAMES_ALIGN", "C");
            }
            titleTF.getScene().getWindow().hide();
        });
    }    
    
}
