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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lp.Engine;
import lp.Settings;
import lp.models.ListItem;
import org.sicut.fonts.icofont.IcoMoon;

public class MainWindowController implements Initializable {
    @FXML private Button upBtn, downBtn, deleteBtn, clearBtn, addBtn, addAllBtn, generateBtn, outDirBtn;
    @FXML private Button tplBtn1, tplSetBtn1, tplPrvwBtn1;
    @FXML private Button tplBtn2, tplSetBtn2, tplPrvwBtn2;
    @FXML private Button tplBtn3, tplSetBtn3, tplPrvwBtn3;
    @FXML private Button tplBtn4, tplSetBtn4, tplPrvwBtn4;
    @FXML private Button tplBtn5, tplSetBtn5, tplPrvwBtn5;
    @FXML private Button tplBtn6, tplSetBtn6, tplPrvwBtn6;
    @FXML private Button tplBtn7, tplSetBtn7, tplPrvwBtn7;
    @FXML private Button tplBtn8, tplSetBtn8, tplPrvwBtn8;
    @FXML private Button tplBtn9, tplSetBtn9, tplPrvwBtn9;
    @FXML private Button tplBtn10, tplSetBtn10, tplPrvwBtn10;
    @FXML private CheckBox oneShtCB, oneFileCB, exportPdfCB, exportXlCB;
    @FXML private TextField outDirTF, acaTF, dirTF, schoolTF, yearTF;
    @FXML private ProgressBar pdfPB, xlPB;
    @FXML private HBox pdfHB, xlHB;
    @FXML private ListView<ListItem> avaGrpsLV, selGrpsLV;
    @FXML private MenuItem exitMI, changeDbMI, aboutMI;
    @FXML private FlowPane statusBar;
    @FXML private RadioMenuItem arRMI, frRMI, enRMI;
    @FXML private ProgressIndicatorController piController;
    private int usedTpl;
    private final SimpleIntegerProperty waitingJobs;
    private ArrayList<Button> tplBtns, tplSetBtns, tplPrvwBtns;
    private final Engine eng;
    private final BaseSettingsWindow bsw;
    private final Preview prvw;
    private final DirectoryChooser dc = new DirectoryChooser();
    private SuccessMessage sm;

    public MainWindowController() {
        usedTpl = 0;
        bsw = new BaseSettingsWindow();
        prvw = new Preview();
        eng = new Engine();
        waitingJobs = new SimpleIntegerProperty();
        sm = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Boolean tmp;
        File output_dir = new File(Settings.PREF_BUNDLE.get("OUTPUT_DIR"));
        if ( output_dir.exists() ) {
            outDirTF.setText(Settings.PREF_BUNDLE.get("OUTPUT_DIR"));
            dc.setInitialDirectory(output_dir);
        }
        addAllBtn.setText(Settings.SELECTED_LANG.equals("AR") ? IcoMoon.backward : IcoMoon.forward2);
        addAllBtn.setTooltip(new Tooltip(rb.getString("ADD_ALL_TO_LIST")));
        addBtn.setText(Settings.SELECTED_LANG.equals("AR") ? IcoMoon.left5 : IcoMoon.right5);
        addBtn.setTooltip(new Tooltip(rb.getString("ADD_TO_LIST")));
        
        oneShtCB.setSelected(Settings.PREF_BUNDLE.get("TWO_SHEETS_PER_GROUP").equals("Y"));
        oneFileCB.setSelected(Settings.PREF_BUNDLE.get("ONE_FILE_PER_GROUP").equals("Y"));
        avaGrpsLV.setCellFactory(cf -> new ListItemFactory());
        selGrpsLV.setCellFactory(cf -> new ListItemFactoryNoSpace());
        
        tmp = Settings.PREF_BUNDLE.get("PDF_EXPORT").equals("Y");
        exportPdfCB.setSelected(tmp);
        waitingJobs.set( tmp ? 1 : 0 );
        
        tmp = Settings.PREF_BUNDLE.get("XL_EXPORT").equals("Y");
        exportXlCB.setSelected(tmp);
        waitingJobs.set( waitingJobs.get() + ( tmp ? 1 : 0 ) );
        
        generateBtn.setDisable(true);
            
        addBtn.setOnAction(evt -> {
            selGrpsLV.getItems().addAll(avaGrpsLV.getSelectionModel().getSelectedItems());
        });
        
        addAllBtn.setOnAction(evt -> {
            selGrpsLV.getItems().addAll(avaGrpsLV.getItems().filtered(item -> item.getIslevel()));
        });
        
        tplBtns = new ArrayList(Arrays.asList(tplBtn1, tplBtn2, tplBtn3, tplBtn4, tplBtn5, tplBtn6, tplBtn7, tplBtn8, tplBtn9, tplBtn10));
        try {
            usedTpl = Integer.parseInt(Settings.PREF_BUNDLE.get("SELECTED_TEMPLATE")) - 1;
        } catch ( NumberFormatException e ) {}
        tplBtns.get(usedTpl).setText(IcoMoon.tick);
        tplBtns.forEach( btn -> {
            btn.setOnAction(evt -> {
                tplBtns.get(usedTpl).setText("");
                usedTpl = tplBtns.indexOf(btn);
                tplBtns.get(usedTpl).setText(IcoMoon.tick);
                Settings.PREF_BUNDLE.update("SELECTED_TEMPLATE", "" + (usedTpl + 1));
            });
            btn.setTooltip(new Tooltip(rb.getString("CHOOSE_TEMPLATE")));
        } );
        
        tplSetBtns = new ArrayList(Arrays.asList(tplSetBtn1, tplSetBtn2, tplSetBtn3, tplSetBtn4, tplSetBtn5, tplSetBtn6, tplSetBtn7, tplSetBtn8, tplSetBtn9, tplSetBtn10));
        tplSetBtns.forEach( btn -> {
            btn.setOnAction(evt -> {
                bsw.setTemplate(tplSetBtns.indexOf(btn) + 1);
                bsw.show((Stage) btn.getScene().getWindow());
            });
            btn.setText(IcoMoon.gears);
            btn.setTooltip(new Tooltip(rb.getString("CHANGE_PARAMETERS")));
        } );
        
        tplPrvwBtns = new ArrayList(Arrays.asList(tplPrvwBtn1, tplPrvwBtn2, tplPrvwBtn3, tplPrvwBtn4, tplPrvwBtn5, tplPrvwBtn6, tplPrvwBtn7, tplPrvwBtn8, tplPrvwBtn9, tplPrvwBtn10));
        tplPrvwBtns.forEach( btn -> {
            btn.setOnAction(evt -> {
                prvw.show(tplPrvwBtns.indexOf(btn) + 1, (Stage) btn.getScene().getWindow());
            });
            btn.setText(IcoMoon.eye);
            btn.setTooltip(new Tooltip(rb.getString("PREVIEW_TEMPLATE")));
        } );
        
        generateBtn.setOnAction(evt -> {
            if ( sm != null )
                    sm.hide();
            eng.setItems(selGrpsLV.getItems().toArray(new ListItem[selGrpsLV.getItems().size()]));
            piController.setProgress(0);
            pdfPB.setProgress(0);
            xlPB.setProgress(0);
            piController.setVisible(true);
            pdfHB.setVisible(exportPdfCB.isSelected());
            xlHB.setVisible(exportXlCB.isSelected());
            exportXlCB.setDisable(true);
            exportPdfCB.setDisable(true);
            eng.getPDFJobs().getProcessedJobsProperty().addListener((ChangeListener<Number>) (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                Platform.runLater(() -> {
                    pdfPB.setProgress(eng.getPDFJobs().isDone() ? 1 : eng.getPDFJobs().getPercentage());
                    piController.setProgress((pdfPB.getProgress() + xlPB.getProgress()) / waitingJobs.get());
                });
            });
            eng.getXLJobs().getProcessedJobsProperty().addListener((ChangeListener<Number>) (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                Platform.runLater(() -> {
                    xlPB.setProgress(eng.getXLJobs().isDone() ? 1 : eng.getXLJobs().getPercentage());
                    piController.setProgress((pdfPB.getProgress() + xlPB.getProgress()) / waitingJobs.get());
                });
            });
            eng.vroom();
        });
        
        piController.doneProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if ( newValue ) {
                if ( sm == null )
                    sm = new SuccessMessage((Stage) generateBtn.getScene().getWindow());
                sm.show();
                exportXlCB.setDisable(false);
                exportPdfCB.setDisable(false);
            }
        });
        
        oneShtCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("TWO_SHEETS_PER_GROUP", newValue ? "Y" : "N");
        });
        
        oneFileCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("ONE_FILE_PER_GROUP", newValue ? "Y" : "N");
        });
        
        exportPdfCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("PDF_EXPORT", newValue ? "Y" : "N");
            waitingJobs.set( waitingJobs.get() + ( newValue ? 1 : -1 ) );
        });
        
        exportXlCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("XL_EXPORT", newValue ? "Y" : "N");
            waitingJobs.set( waitingJobs.get() + ( newValue ? 1 : -1 ) );
        });
        
        waitingJobs.addListener((ChangeListener<Number>) (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            generateBtn.setDisable(newValue.intValue() == 0  || selGrpsLV.getItems().isEmpty());
        });
        
        clearBtn.setOnAction(e -> {
            selGrpsLV.getItems().clear();
        });
        clearBtn.setTooltip(new Tooltip(rb.getString("DELETE_ALL")));
        
        deleteBtn.setOnAction(e -> {
            int selected = selGrpsLV.getSelectionModel().getSelectedIndex();
            selGrpsLV.getItems().remove( selGrpsLV.getSelectionModel().getSelectedIndex() );
            if ( selected == selGrpsLV.getItems().size() )
                selGrpsLV.getSelectionModel().select(selected - 1);
        });
        deleteBtn.setTooltip(new Tooltip(rb.getString("DELETE")));
        
        upBtn.setOnAction(e -> {
            int selected = selGrpsLV.getSelectionModel().getSelectedIndex();
            ListItem li = selGrpsLV.getItems().get(selected - 1);
            selGrpsLV.getItems().set(selected - 1, selGrpsLV.getItems().get(selected));
            selGrpsLV.getItems().set(selected, li);
            selGrpsLV.getSelectionModel().select(selected - 1);
        });
        upBtn.setTooltip(new Tooltip(rb.getString("MOVE_UP")));
        
        downBtn.setOnAction(e -> {
            int selected = (int) selGrpsLV.getSelectionModel().getSelectedIndices().get(0);
            ListItem li = selGrpsLV.getItems().get(selected + 1);
            selGrpsLV.getItems().set(selected + 1, selGrpsLV.getItems().get(selected));
            selGrpsLV.getItems().set(selected, li);
            selGrpsLV.getSelectionModel().select(selected + 1);
        });
        downBtn.setTooltip(new Tooltip(rb.getString("MOVE_DOWN")));
        
        avaGrpsLV.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) o -> {
            addBtn.setDisable( o.getList().isEmpty() );
        });
        
        selGrpsLV.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) o -> {
            if ( o.getList().isEmpty() ) {
                upBtn.setDisable(true);
                downBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
            else {
                int selected = (int) o.getList().get(0);
                upBtn.setDisable( selected == 0 );
                downBtn.setDisable( selected == ( selGrpsLV.getItems().size() - 1 ) );
                deleteBtn.setDisable(false);
            }
        });
        
        selGrpsLV.getItems().addListener((ListChangeListener) o -> {
            Boolean empty = selGrpsLV.getItems().isEmpty();
            clearBtn.setDisable(empty);
            generateBtn.setDisable(empty || waitingJobs.get() == 0);
        });
        
        exitMI.setOnAction(evt -> {
            generateBtn.getScene().getWindow().hide();
            Settings.PREF_BUNDLE.commit();
        });
        
        arRMI.setUserData("AR");
        frRMI.setUserData("FR");
        enRMI.setUserData("EN");
        switch( Settings.SELECTED_LANG ) {
            case "AR":
                arRMI.setSelected(true);
                break;
            case "FR":
                frRMI.setSelected(true);
                break;
            case "EN":
                enRMI.setSelected(true);
                break;
        }
        
        arRMI.getToggleGroup().selectedToggleProperty().addListener((ChangeListener<Toggle>) (ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if ( newValue != null ) {
                Settings.PREF_BUNDLE.update("LANGUAGE", newValue.getUserData().toString());
                statusBar.setVisible(!newValue.getUserData().toString().equals(Settings.SELECTED_LANG));
            }
        });
        
        statusBar.visibleProperty().addListener((ChangeListener<Boolean>) (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            statusBar.setManaged(newValue);
        });
        
        statusBar.setVisible(false);
        
        changeDbMI.setOnAction(evt -> {
            Settings.SCHOOL_DB.reset();
            ((SimpleStringProperty) clearBtn.getScene().getUserData()).set("SELECT_ANOTHER_DB");
        });
        
        aboutMI.setOnAction(evt -> {
            (new About((Stage) clearBtn.getScene().getWindow())).show();
        });
        
        outDirBtn.setOnAction(e -> {
            File f = dc.showDialog(outDirBtn.getScene().getWindow());
            if ( f != null ) {
                outDirTF.setText(f.getPath());
                Settings.PREF_BUNDLE.update("OUTPUT_DIR", f.getPath());
                dc.setInitialDirectory(f);
            }
        });
        outDirBtn.setTooltip(new Tooltip(rb.getString("BROWSE")));
        
        refillList();
    }
    
    public void refillList() {
        new Thread(() -> {
            selGrpsLV.getItems().clear();
            avaGrpsLV.getItems().clear();
            Settings.SCHOOL_DB.getLevels().stream().sorted((l1, l2) -> l1.getWeight().compareTo(l2.getWeight())).forEach(level -> {
                avaGrpsLV.getItems().add(new ListItem(true, level));
                level.getGroups().stream().sorted((g1, g2) -> g1.getIndex().compareTo(g2.getIndex())).forEach(group -> {
                    avaGrpsLV.getItems().add(new ListItem(false, group));
                });
            });
        }).start();
        acaTF.setText(Settings.SCHOOL_DB.getAcademy());
        dirTF.setText(Settings.SCHOOL_DB.getDirection());
        schoolTF.setText(Settings.SCHOOL_DB.getSchool());
        yearTF.setText(Settings.SCHOOL_DB.getYear());
        piController.setVisible(false);
        pdfHB.setVisible(false);
        xlHB.setVisible(false);
    }
    
}
