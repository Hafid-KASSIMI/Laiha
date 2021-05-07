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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lp.Settings;

public class SuccessMessageController implements Initializable {

    @FXML private Button openBtn, closeBtn;
    @FXML private Label counterLbl, message;
    private String msgTpl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeBtn.setOnAction(evt -> {
            closeBtn.getScene().getWindow().hide();
        });
        openBtn.setOnAction(evt -> {
            File selectedDest = new File(Settings.PREF_TEMPORARY.get("OUTPUT_DIR"));
            if ( !selectedDest.exists() )
                selectedDest = new File(Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR"));
            try {
                Desktop.getDesktop().open(selectedDest);
            } catch(IOException ioe) { }
            closeBtn.getScene().getWindow().hide();
        });
        msgTpl = rb.getString("SUCCESS_MESSAGE");
    }
    
    public void updatePath() {
        String tmp = Settings.PREF_TEMPORARY.get("OUTPUT_DIR");
        int counter = 1;
        if ( !(new File(tmp)).exists() )
            tmp = Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR");
        message.setText(msgTpl.replace("%PATH%", tmp));
        try {
            counter = Integer.parseInt(Settings.PREF_TEMPORARY.get("GENERATIONS_COUNTER")) + 1;
        } catch ( NumberFormatException e ) {}
        tmp = counter > 999 ? String.format("%.1f", counter / 1000.0) + "k" : counter + "";
        Settings.PREF_TEMPORARY.update("GENERATIONS_COUNTER", tmp);
        counterLbl.setText(tmp);
    }
}
