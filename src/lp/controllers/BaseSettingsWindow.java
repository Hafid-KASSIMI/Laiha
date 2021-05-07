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

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.Settings;

public class BaseSettingsWindow {
    private Stage stg;
    private final String resource = "/lp/views/Settings%tpl%.fxml";
    private String template;
    private boolean showing;
    private int index;

    public BaseSettingsWindow() {
        showing = false;
    }
    
    public BaseSettingsWindow(int template) {
        this();
        setTemplate(template);
    }

    public String getTemplate() {
        return template;
    }

    public final void setTemplate(int template) {
        this.template = resource.replace("%tpl%", template + "");
        index = template;
    }
        
    public void show(Stage parent) {
        if ( showing )
            stg.hide();
        try {
            Parent root;
            Scene scene;
            FXMLLoader fl = new FXMLLoader(getClass().getResource(template), Settings.I18N_BUNDLE);
            root = fl.load();
            scene = new Scene(root);
            root.getStylesheets().add("/lp/styles/theme-" + Settings.SELECTED_LANG + ".css");
            scene.setNodeOrientation( ( Settings.SELECTED_LANG.equals("AR") ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT ) );
            stg = new Stage();
            stg.initOwner(parent);
            stg.initModality(Modality.WINDOW_MODAL);
            stg.setTitle(Settings.I18N_BUNDLE.getString("TEMPLATE_SETTINGS") + " [" + index + "]");
            stg.setResizable(false);
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/64.png")));
            stg.setScene(scene);
            stg.show();
            showing = true;
        }
        catch(IOException ex) {}
    }
    
    public void hide() {
        stg.hide();
        showing = false;
    }
    
}
