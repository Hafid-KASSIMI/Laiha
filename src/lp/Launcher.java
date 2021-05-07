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

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lp.controllers.DBLoader;
import lp.controllers.DBSelector;
import lp.controllers.MainWindow;
import lp.models.SchoolDB;
import org.sicut.db.Configurator;
import org.sicut.db.Preferences;
import org.sicut.db.Translator;

public class Launcher extends Application {
    
    private final SimpleStringProperty command;
    private DBSelector dbs;
    private DBLoader dbl;
    private MainWindow pWindow;
    private Stage stage;
    private Boolean mwShown;

    public Launcher() {
        command = new SimpleStringProperty();
        mwShown = false;
        command.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            switch(newValue) {
                case "DB_LOADED":
                    if ( pWindow == null ) {
                        pWindow = new MainWindow();
                        pWindow.getScene().setUserData(command);
                    }
                    showMainWindow();
                    break;
                case "DB_SELECTED":
                    if ( dbl == null ) {
                        dbl = new DBLoader();
                        dbl.getScene().setUserData(command);
                    }
                    showDBLoader();
                    break;
                case "SELECT_ANOTHER_DB":
                    showDBSelctor();
                    break;
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        (new Configurator()).prepare(Settings.PREF_DB_PATH, Settings.I18N_DB_PATH, Settings.DB_FOLDER_PATH, getClass().getResource("/lp/resources/preferences.sql").openStream(), getClass().getResource("/lp/resources/i18n.sql").openStream());
        Settings.PREF_BUNDLE = new Preferences(Settings.PREF_DB_PATH);
        Settings.SELECTED_LANG = Settings.PREF_BUNDLE.get("LANGUAGE");
        Settings.I18N_BUNDLE = new Translator(Settings.SELECTED_LANG, Settings.I18N_DB_PATH);
        Settings.SCHOOL_DB = new SchoolDB();
        Locale.setDefault(new Locale(Settings.SELECTED_LANG));
        
        dbs = new DBSelector();
        dbs.getScene().setUserData(command);
        stage = primaryStage;
        stage.setTitle(Settings.APP_TITLE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/256.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/128.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/64.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/48.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/32.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/16.png")));
        stage.setOnCloseRequest(evt -> {
            if ( mwShown ) {
                saveMWDimensions();
            }
            Settings.PREF_BUNDLE.commit();
        });
        showDBSelctor();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void showMainWindow() {
        stage.hide();
        stage.setScene(pWindow.getScene());
        stage.setResizable(true);
        pWindow.reinitialize();
        try {
            stage.setX(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_X")));
            stage.setY(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_Y")));
            stage.setWidth(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_W")));
            stage.setHeight(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_H")));
        } catch ( NumberFormatException ex ) {
            stage.setMaximized(true);
        }
        mwShown = true;
        stage.show();
    }
    
    private void showDBSelctor() {
        if ( mwShown ) {
            saveMWDimensions();
            mwShown = false;
        }
        stage.hide();
        stage.setScene(dbs.getScene());
        stage.setResizable(false);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();
    }
    
    private void showDBLoader() {
        stage.hide();
        stage.setScene(dbl.getScene());
        stage.setResizable(false);
        dbl.reInitialize();
        stage.show();
        dbl.start();
    }
    
    private void saveMWDimensions() {
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_X", stage.getX() + "");
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_Y", stage.getY() + "");
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_W", stage.getWidth() + "");
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_H", stage.getHeight() + "");
    }
    
}
