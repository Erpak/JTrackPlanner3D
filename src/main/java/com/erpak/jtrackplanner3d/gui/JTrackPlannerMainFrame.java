/**
** Copyright © 2011, Erpak 
* 
* This file is part of JTrackPlanner3D.
*
* JTrackPlanner3D is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* JTrackPlanner3D is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with JTrackPlanner3D. If not, see <http://www.gnu.org/licenses/>.
*/
package com.erpak.jtrackplanner3d.gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.apache.log4j.Logger;

import com.erpak.jtrackplanner3d.graphics.GraphicAreaSingleton;
import com.erpak.jtrackplanner3d.graphics.Project;
import com.erpak.jtrackplanner3d.utils.software.ResourcesManager;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class JTrackPlannerMainFrame extends JFrame {

    /** Logger */
    static Logger logger = Logger.getLogger(JTrackPlannerMainFrame.class);      
    
    JPanel contentPane;
    JMenuBar jMenuBar = new JMenuBar();
    
    // JTrackPlanner menu
    JMenu jMenuJTrackPlanner = new JMenu();
    JMenuItem jMenuJTrackPlannerAbout = new JMenuItem();
    JMenuItem jMenuJTrackPlannerPref = new JMenuItem();
    JMenuItem jMenuJTrackPlannerExit = new JMenuItem();
    
    // Project menu
    JMenu jMenuProject = new JMenu();
    JMenuItem jMenuProjectNew = new JMenuItem();
    JMenuItem jMenuProjectOpen = new JMenuItem();
    JMenuItem jMenuProjectSave = new JMenuItem();
    JMenuItem jMenuProjectSaveAs = new JMenuItem();
    JMenuItem jMenuProjectClose = new JMenuItem();
    JMenuItem jMenuProjectProperties = new JMenuItem();
    
    // Edit menu
    JMenu jMenuEdit = new JMenu();
    JMenuItem jMenuEditPaste = new JMenuItem();
    JMenuItem jMenuEditCut = new JMenuItem();
    JMenuItem jMenuEditCopy = new JMenuItem();
    JMenuItem jMenuEditDelete = new JMenuItem();
    JMenuItem jMenuEditUndo = new JMenuItem();
    JMenuItem jMenuEditRedo = new JMenuItem();
    
    // Help menu
    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpManual = new JMenuItem();
    JMenuItem jMenuHelpTutorial = new JMenuItem();
    
    // Toolbar
    JToolBar jToolBar = new JToolBar();
    JButton newProjectButton = new JButton();
    JButton openProjectButton = new JButton();
    JButton closeProjectButton = new JButton();
    JButton saveProjectButton = new JButton();
    JButton deleteElementButton = new JButton();
    JButton moveElementButton = new JButton();
    JButton rotateElementButton = new JButton();
    
    // Icons for the toolbar
    ImageIcon newProjectImage;
    ImageIcon openProjectImage;
    ImageIcon closeProjectImage;
    ImageIcon saveProjectImage;
    ImageIcon deleteElementImage;
    ImageIcon moveElementImage;
    ImageIcon rotateElementImage;
    
    // Statusbar
    JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();
    
    // Graphic Area
    Project project;
    
    /**
     * Construct the frame
     */
    public JTrackPlannerMainFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Component initialization
     *
     * @throws Exception
     */
    private void jbInit() throws Exception {
        openProjectImage = new ImageIcon(ResourcesManager.getResource("OpenProjectIcon"));
        closeProjectImage = new ImageIcon(ResourcesManager.getResource("CloseProjectIcon"));
        newProjectImage = new ImageIcon(ResourcesManager.getResource("NewProjectIcon"));
        saveProjectImage = new ImageIcon(ResourcesManager.getResource("SaveProjectIcon"));
        deleteElementImage = new ImageIcon(ResourcesManager.getResource("DeleteElementIcon"));
        moveElementImage = new ImageIcon(ResourcesManager.getResource("MoveElementIcon"));
        rotateElementImage = new ImageIcon(ResourcesManager.getResource("RotateElementIcon"));
        
        setIconImage(Toolkit.getDefaultToolkit().createImage(ResourcesManager.getResource("ApplicationIcon")));
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(800, 600));
        this.setTitle(ResourcesManager.getLanguageDependentString("MainWindowTitleLabel"));
        statusBar.setText(ResourcesManager.getLanguageDependentString("ReadyStatusMsg"));
        // JTrackPlanner Menu
        jMenuJTrackPlanner.setText(ResourcesManager.getLanguageDependentString("JTrackPlannerMenuLabel"));
        jMenuJTrackPlannerAbout.setText(ResourcesManager.getLanguageDependentString("JTrackPlannerAboutLabel"));
        jMenuJTrackPlannerAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTrackPlannerAbout_actionPerformed(e);
            }
        });
        jMenuJTrackPlannerPref.setText(ResourcesManager.getLanguageDependentString("JTrackPlannerPrefLabel"));
        jMenuJTrackPlannerExit.setText(ResourcesManager.getLanguageDependentString("JTrackPlannerExitLabel"));
        jMenuJTrackPlannerExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuJTrackPlannerExit_actionPerformed(e);
            }
        });
        // Project Menu
        jMenuProject.setText(ResourcesManager
                .getLanguageDependentString("ProjectMenuLabel"));
        jMenuProjectNew.setText(ResourcesManager
                .getLanguageDependentString("ProjectNewLabel"));
        jMenuProjectNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuProjectNew_actionPerformed(e);
            }
        });
        jMenuProjectOpen.setText(ResourcesManager
                .getLanguageDependentString("ProjectOpenLabel"));
        jMenuProjectSave.setText(ResourcesManager
                .getLanguageDependentString("ProjectSaveLabel"));
        jMenuProjectSaveAs.setText(ResourcesManager
                .getLanguageDependentString("ProjectSaveAsLabel"));
        jMenuProjectClose.setText(ResourcesManager
                .getLanguageDependentString("ProjectCloseLabel"));
        jMenuProjectClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuProjectClose_actionPerformed(e);
            }
        });
        jMenuProjectProperties.setText(ResourcesManager
                .getLanguageDependentString("ProjectPropertiesLabel"));
        // Edit Menu
        jMenuEdit.setText(ResourcesManager
                .getLanguageDependentString("EditMenuLabel"));
        jMenuEditPaste.setText(ResourcesManager
                .getLanguageDependentString("EditPasteLabel"));
        jMenuEditCut.setText(ResourcesManager
                .getLanguageDependentString("EditCutLabel"));
        jMenuEditCopy.setText(ResourcesManager
                .getLanguageDependentString("EditCopyLabel"));
        jMenuEditDelete.setText(ResourcesManager
                .getLanguageDependentString("EditDeleteLabel"));
        jMenuEditUndo.setText(ResourcesManager
                .getLanguageDependentString("EditUndoLabel"));
        jMenuEditRedo.setText(ResourcesManager
                .getLanguageDependentString("EditRedoLabel"));
        // Help Menu
        jMenuHelp.setText(ResourcesManager
                .getLanguageDependentString("HelpMenuLabel"));
        jMenuHelpManual.setText(ResourcesManager
                .getLanguageDependentString("HelpManualLabel"));
        jMenuHelpTutorial.setText(ResourcesManager
                .getLanguageDependentString("HelpTutorialLabel"));
        // Toolbar buttons
        newProjectButton.setIcon(newProjectImage);
        newProjectButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("NewProjectTooltip"));
        newProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuProjectNew_actionPerformed(e);
            }
        });
        openProjectButton.setIcon(openProjectImage);
        openProjectButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("OpenProjectTooltip"));
        openProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuProjectOpen_actionPerformed(e);
            }
        });
        closeProjectButton.setIcon(closeProjectImage);
        closeProjectButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("CloseProjectToolTip"));
        closeProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuProjectClose_actionPerformed(e);
            }
        });
        saveProjectButton.setIcon(saveProjectImage);
        saveProjectButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("SaveProjectToolTip"));
        saveProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuProjectSave_actionPerformed(e);
            }
        });
        deleteElementButton.setIcon(deleteElementImage);
        deleteElementButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("DeleteElementToolTip"));
        deleteElementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuDeleteElement_actionPerformed(e);
            }
        });
        moveElementButton.setIcon(moveElementImage);
        moveElementButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("MoveElementToolTip"));
        moveElementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuMoveElement_actionPerformed(e);
            }
        });
        rotateElementButton.setIcon(rotateElementImage);
        rotateElementButton.setToolTipText(ResourcesManager
                .getLanguageDependentString("RotateElementToolTip"));
        rotateElementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuRotateElement_actionPerformed(e);
            }
        });
        
        jToolBar.add(newProjectButton);
        jToolBar.add(openProjectButton);
        jToolBar.add(closeProjectButton);
        jToolBar.add(saveProjectButton);
        jToolBar.addSeparator();
        jToolBar.add(deleteElementButton);
        jToolBar.add(moveElementButton);
        jToolBar.add(rotateElementButton);
        
        //
        jMenuJTrackPlanner.add(jMenuJTrackPlannerAbout);
        jMenuJTrackPlanner.addSeparator();
        jMenuJTrackPlanner.add(jMenuJTrackPlannerPref);
        jMenuJTrackPlanner.addSeparator();
        jMenuJTrackPlanner.add(jMenuJTrackPlannerExit);
        //
        jMenuProject.add(jMenuProjectNew);
        jMenuProject.add(jMenuProjectOpen);
        jMenuProject.addSeparator();
        jMenuProject.add(jMenuProjectClose);
        jMenuProject.addSeparator();
        jMenuProject.add(jMenuProjectSave);
        jMenuProject.add(jMenuProjectSaveAs);
        jMenuProject.addSeparator();
        jMenuProject.add(jMenuProjectProperties);
        //
        jMenuEdit.add(jMenuEditUndo);
        jMenuEdit.add(jMenuEditRedo);
        jMenuEdit.addSeparator();
        jMenuEdit.add(jMenuEditCopy);
        jMenuEdit.add(jMenuEditCut);
        jMenuEdit.add(jMenuEditPaste);
        jMenuEdit.addSeparator();
        jMenuEdit.add(jMenuEditDelete);
        //
        jMenuHelp.add(jMenuHelpManual);
        jMenuHelp.add(jMenuHelpTutorial);
        //
        jMenuBar.add(jMenuJTrackPlanner);
        jMenuBar.add(jMenuProject);
        jMenuBar.add(jMenuEdit);
        jMenuBar.add(jMenuHelp);
        this.setJMenuBar(jMenuBar);
        contentPane.add(jToolBar, BorderLayout.NORTH);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        project = new Project();
        contentPane.add(project, BorderLayout.CENTER);
    }
    
    /**
     * Project | New action performed
     * @param e ActionEvent
     */
    public void jMenuProjectNew_actionPerformed(ActionEvent e) {
        if (project.getName().equals("")) {
            JTrackPlannerNewProjectBox dlg = new JTrackPlannerNewProjectBox(
                    this);
            Dimension dlgSize = dlg.getPreferredSize();
            Dimension frmSize = getSize();
            Point loc = getLocation();
            dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
            dlg.setModal(true);
            dlg.show();
            if (dlg != null && dlg.isValidated()) {
                project.init(dlg.getProjectName(), dlg.getLibraryName(), "");
                this.validate();
                this.setTitle(ResourcesManager
                        .getLanguageDependentString("MainWindowTitleLabel")
                        + " - " + dlg.getProjectName());
                dlg.dispose();
            }
        }
    }
    
    /**
     * Project | Close action performed
     * @param e ActionEvent
     */
    public void jMenuProjectClose_actionPerformed(ActionEvent e) {
        if (project.getName() != null) {
            JTrackPlannerConfirmBox dlg = new JTrackPlannerConfirmBox(
                    this,
                    ResourcesManager
                    .getLanguageDependentString("CloseProjectFrameTitle"),
                    ResourcesManager
                    .getLanguageDependentString("CloseProjectMessageLabel")
                    + " '" + project.getName() + "' ?");
            Dimension dlgSize = dlg.getPreferredSize();
            Dimension frmSize = getSize();
            Point loc = getLocation();
            dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
            dlg.setModal(true);
            dlg.show();
            if (dlg != null && dlg.isValidated()) {
                this.setTitle(ResourcesManager
                        .getLanguageDependentString("MainWindowTitleLabel")
                        + " - untitled");
                project.close();
                this.validate();
                dlg.dispose();
            }
        }
    }
    
    /**
     * Project | Open action performed
     * @param e ActionEvent
     */
    public void jMenuProjectOpen_actionPerformed(ActionEvent e) {
        // Code to add to open a project
    }
    
    /**
     * Project | Save action performed
     * @param e ActionEvent
     */
    public void jMenuProjectSave_actionPerformed(ActionEvent e) {
        // Code to add to save a project
    }
    
    /**
     * Project | Exit action performed
     * @param e ActionEvent
     */
    public void jMenuJTrackPlannerExit_actionPerformed(ActionEvent e) {
        logger.info("JTrackPlanner Application stopped !");
        System.exit(0);
    }
    
    /**
     * Help | About action performed
     * @param e ActionEvent
     */
    public void jTrackPlannerAbout_actionPerformed(ActionEvent e) {
        JTrackPlannerAboutBox dlg = new JTrackPlannerAboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.show();
    }
    
    /**
     * Display | Track set box action performed
     * @param e ActionEvent
     */
    /*
     * public void jMenuDisplayTrackSetBox_actionPerformed(ActionEvent e) {
     * JTrackPlannerTrackSetBox dlg = new JTrackPlannerTrackSetBox(this);
     * Dimension dlgSize = dlg.getPreferredSize(); Dimension frmSize =
     * getSize(); Point loc = getLocation(); dlg.setLocation((frmSize.width -
     * dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 +
     * loc.y); dlg.setModal(false); dlg.show(); }
     */
    
    /**
     * delete action performed
     * @param e ActionEvent
     */
    public void jMenuDeleteElement_actionPerformed(ActionEvent e) {
        GraphicAreaSingleton.getInstance().deleteSelectedElements();
    }
    
    /**
     * move action performed
     * @param e ActionEvent
     */
    public void jMenuMoveElement_actionPerformed(ActionEvent e) {
        GraphicAreaSingleton.getInstance().moveSelectedElements();
    }
    
    /**
     * rotate action performed
     * @param e ActionEvent
     */
    public void jMenuRotateElement_actionPerformed(ActionEvent e) {
        GraphicAreaSingleton.getInstance().rotateSelectedElements();
    }
    
    /**
     * Overridden so we can exit when window is closed
     * @param e WindowsEvent
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            jMenuJTrackPlannerExit_actionPerformed(null);
        }
    }
}