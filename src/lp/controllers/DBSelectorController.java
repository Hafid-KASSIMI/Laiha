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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lp.Settings;
import lp.models.Db;

public class DBSelectorController implements Initializable {

    @FXML Button prevDBBtn, dBBtn;
    @FXML ImageView logo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File ini_db = new File(Settings.PREF_BUNDLE.get("SELECTED_DB"));
        File ini_db_dir = new File(Settings.PREF_BUNDLE.get("SELECTED_DB_DIR"));
        Db mw = new Db();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel 2003", "*.xls"));
        if ( !ini_db.exists() )
            prevDBBtn.setVisible(false);
        else {
            prevDBBtn.setTooltip(new Tooltip(ini_db.getPath()));
            prevDBBtn.setOnAction(evt -> {
                mw.setWorkbook(ini_db);
                ((SimpleStringProperty) dBBtn.getScene().getUserData()).set("DB_SELECTED");
            });
        }
        fc.setTitle(Settings.I18N_BUNDLE.getString("CHOOSE_DB_TITLE"));
        dBBtn.setOnAction(evt -> {
            if ( ini_db_dir.exists() && ini_db_dir.isDirectory() )
                fc.setInitialDirectory(ini_db_dir);
            File f = fc.showOpenDialog(dBBtn.getScene().getWindow());
            if ( f == null )
                return;
            if ( f.getParentFile() != ini_db_dir )
                Settings.PREF_BUNDLE.update("SELECTED_DB_DIR", f.getParent());
            if ( mw.setWorkbook(f) ) {
                if ( f != ini_db )
                    Settings.PREF_BUNDLE.update("SELECTED_DB", f.getPath());
                ((SimpleStringProperty) dBBtn.getScene().getUserData()).set("DB_SELECTED");
            }
        });
    }
    
}
