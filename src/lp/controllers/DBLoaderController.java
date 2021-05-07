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
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lp.Settings;
import lp.models.Db;

public class DBLoaderController implements Initializable {
    
    @FXML private TextField acaTF, dirTF, schoolTF, yearTF;
    @FXML private ProgressIndicatorController piController;
    @FXML private Button startBtn;
    @FXML private Label progressLbl;
    private final Db mw;
    private ResourceBundle rb;

    public DBLoaderController() {
        mw = new Db();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        startBtn.setOnAction(evt -> {
            Settings.SCHOOL_DB.setAcademy(acaTF.getText());
            Settings.SCHOOL_DB.setDirection(dirTF.getText());
            Settings.SCHOOL_DB.setSchool(schoolTF.getText());
            Settings.SCHOOL_DB.setYear(yearTF.getText());
            ((SimpleStringProperty) startBtn.getScene().getUserData()).set("DB_LOADED");
        });
        reInitialize();
    }
    
    public void start() {
        if ( mw.setWorkbook(new File(Settings.PREF_BUNDLE.get("SELECTED_DB"))) ) {
            new Thread(() -> {
                mw.loadDB();
                acaTF.setText(Settings.SCHOOL_DB.getAcademy());
                dirTF.setText(Settings.SCHOOL_DB.getDirection());
                schoolTF.setText(Settings.SCHOOL_DB.getSchool());
                yearTF.setText(Settings.SCHOOL_DB.getYear());
                mw.getStatus().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if ( newValue ) {
                        Platform.runLater(() -> {
                            startBtn.setVisible(true);
                            progressLbl.setText(rb.getString("LOAD_DONE"));
                        });
                    }
                });
                mw.getProcessedSheets().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    Platform.runLater(() -> {
                        piController.setProgress((double) newValue);
                    });
                });
            }).start();
        }
    }

    public void reInitialize() {
        startBtn.setVisible(false);
        piController.reset();
        progressLbl.setText(rb.getString("LOAD_PERCENTAGE"));
    }
    
}
