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
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lp.Settings;

public class SuccessMessage {

    private Stage parent, stg;
    private SuccessMessageController smc;
    
    public SuccessMessage(Stage parent) {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/lp/views/SuccessMessage.fxml"), Settings.I18N_BUNDLE);
            Parent root = fl.load();
            Scene scene = new Scene(root);
            smc = fl.getController();
            this.parent = parent;
            stg = new Stage();
            stg.setScene(scene);
            stg.initModality(Modality.NONE);
            stg.initOwner(parent);
            stg.setResizable(false);
            stg.initStyle(StageStyle.UNDECORATED);
            scene.setNodeOrientation( ( Settings.SELECTED_LANG.equals("AR") ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT ) );
        }
        catch(IOException ex) { }
    }

    public void show() {
        Timeline t = new Timeline();
        KeyValue kv = new KeyValue(stg.opacityProperty(), 1, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        stg.setOpacity(0);
        stg.show();
        stg.setY(parent.getHeight() - stg.getHeight() + parent.getY() - 5);
        stg.setX(parent.getX() + ( Settings.SELECTED_LANG.equals("AR") ? 5 : parent.getWidth() - stg.getWidth() - 5 ));
        smc.updatePath();
        t.getKeyFrames().add(kf);
        t.play();
    }
    
    public void hide() {
        stg.hide();
    }
}
