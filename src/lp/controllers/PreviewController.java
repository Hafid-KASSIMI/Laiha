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
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp.Settings;

public class PreviewController implements Initializable {
    
    @FXML private ImageView img;
    @FXML private Label categoryLbl, infosLbl, titleLbl;

    private final String url = "/lp/resources/images/";
    private ResourceBundle rb;
    
    public PreviewController() {
    }
    
    public PreviewController(int template) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }    
    
    public void preview(int template) {
        String[] infosTab = Settings.PREF_BUNDLE.get("TPL" + template + "_INFOS").split("\\|");
        String tmp = rb.getString(Settings.PREF_BUNDLE.get("TPL" + template + "_CATEGORY"));
        img.setImage(new Image(url + template + ".jpeg"));
        categoryLbl.setText( tmp + "." );
        tmp = "";
        for ( int i = 0, n = infosTab.length; i < n; i++ ) {
            try {
                tmp += rb.getString(infosTab[i]);
            }
            catch( MissingResourceException mre ) {
                tmp += infosTab[i];
            }
        }
        infosLbl.setText(tmp);
        tmp = Settings.PREF_BUNDLE.get("TPL" + template + "_TITLE");
        titleLbl.setText( tmp + "." );
    }
    
}
