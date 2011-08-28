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
package com.erpak.jtrackplanner3d.graphics;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import com.erpak.jtrackplanner3d.graphics.symbols.TrackSymbolTransfertHandler;
import com.erpak.jtrackplanner3d.xml.LibraryReader;
import java.io.File;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class Project extends JSplitPane {

    /** Project name */
    private String name = "";
    /** Project author */
    private String author;
    /** Scroll Pane for Symbol tree display */
    private JScrollPane libraryScrollPane;
    /** JTree for Symbol display */
    private JTree libraryTree;
    /** Area for graphical display */
    private GraphicArea graphicArea = new GraphicArea();
    /** blanck JPanel */
    private JPanel blankPanel = new JPanel();

    /**
     * Creates a blank project
     */
    public Project (){
        blankPanel.setBackground(Color.WHITE);
        this.setLeftComponent(blankPanel);
        this.setRightComponent(graphicArea);
        this.setContinuousLayout(true);
        this.setOneTouchExpandable(true);
        this.setDividerLocation(300);
        updateUI();        
    };
    
    /**
     * Creates a new project with the specified name and opens the library in the library tree
     * @param name Project name
     * @param libraryName Library Name
     * @param author Author name
     */
    // Not used !! To remove
    /*public Project (String name, String libraryName, String author){
        init(name, libraryName, author);
    }*/

    /**
     * Initialize the project
     * @param name Project name
     * @param libraryFileName Library file name
     * @param author Author name
     */
    public void init(String name, File libraryFile, String author){
        this.name = name;
        this.author = author;
        // load the library tree
        LibraryReader libraryReader = new LibraryReader(libraryFile);
        libraryTree = libraryReader.getTree();
        // Drag & drop
        TrackSymbolTransfertHandler trackSymbolTransfertHandler = new TrackSymbolTransfertHandler();
        libraryTree.setDragEnabled(true);
        libraryTree.setTransferHandler(trackSymbolTransfertHandler);
        graphicArea.setTransferHandler(trackSymbolTransfertHandler);
        // Display library tree and graphic area
        libraryScrollPane = new JScrollPane(libraryTree);
        libraryTree.setBackground(Color.WHITE);
        this.setLeftComponent(libraryScrollPane);
        this.setRightComponent(graphicArea);
        this.setContinuousLayout(true);
        this.setOneTouchExpandable(true);
        this.setDividerLocation(300);
        updateUI();
    }
    
    /**
     * Load the project from a project file
     * @param name
     */
    public void load(String name){
        
    }
    
    /**
     * Save the project
     */
    public void save(){   
    
    }
    
    /**
     * Save the project with the specified name
     * @param name
     */
    public void saveAs(String name){
        
    }
    
    /**
     * Close the project
     *
     */
    public void close(){
        name = "";
        this.setLeftComponent(blankPanel);        
        graphicArea.deleteAllElements();
        updateUI();
    }
    
    /**
     * 
     */
    public String getName(){
        return this.name;
    }
}
