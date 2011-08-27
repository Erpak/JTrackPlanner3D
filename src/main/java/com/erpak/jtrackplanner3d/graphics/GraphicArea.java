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
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.apache.log4j.Logger;

import com.erpak.jtrackplanner3d.graphics.symbols.TrackSymbol;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * GraphicArea : The area for graphical display
 */
public class GraphicArea extends JPanel {
    
    /** Border */
    private Border blackline;
    /** ArrayList to store the track elements */
    private Hashtable elementTable; 
    /** Mode to use the graphic area */
    private String mode;
    /** logger */
    static Logger logger = Logger.getLogger(GraphicArea.class);    
    
    /**
     *
     */
    public GraphicArea() {
        blackline = BorderFactory.createLineBorder(Color.black);
        setBackground(Color.WHITE);
        // Set XY layout
        setLayout(null);
        // Set mouse listener
        GraphicAreaSingleton.getInstance().setGraphicArea(this);
        // Enable component for drag & drop actions
        this.setEnabled(true);
        //this.setTransferHandler();
        this.elementTable = new Hashtable();         
    }
    
    /**
     * 
     * @param symbol 
     * @param x 
     * @param y 
     */
    public void addTrackElement(TrackSymbol symbol, int x, int y){
        if(symbol != null){
            TrackElement element = new TrackElement(symbol, x, y);
            elementTable.put(Integer.valueOf(element.getId()),element);
            this.add(element);
            this.updateUI();
        } else {
            logger.info("No symbol to add");
        }
    }
    
    /**
     * Delete all the elements in the graphic area
     */
    public void deleteAllElements(){
        elementTable = new Hashtable();
        this.removeAll();
        this.updateUI();
    }
    
    /**
     * Delete the selected elements only
     */
    public void deleteSelectedElements(){
        Enumeration enumeration = elementTable.keys();
        while(enumeration.hasMoreElements()){
            TrackElement element = (TrackElement)elementTable.get(enumeration.nextElement());
            if(element.isSelected()){
                logger.debug("Removing : " + element.toString());
                // Suppression de la liste des elements
                elementTable.remove(Integer.valueOf(element.getId()));
                // Suppression de l'affichage
                this.remove(element);
            }
        }
        this.updateUI();
    }
    
    /**
     * Comment
     */
    public void moveSelectedElements(){
        Enumeration enumeration = elementTable.keys();
        while(enumeration.hasMoreElements()){
            TrackElement element = (TrackElement)elementTable.get(enumeration.nextElement());
            if(element.isSelected()){
                logger.debug("Moving : " + element.toString());
                // Suppression de la liste des elements
                //elementTable.remove(Integer.valueOf(element.getId()));
                // Suppression de l'affichage
                //this.remove(element);
            }
        }
        this.updateUI();        
    }
    
    /**
     * Comment
     */
    public void rotateSelectedElements(){
        Enumeration enumeration = elementTable.keys();
        while(enumeration.hasMoreElements()){
            TrackElement element = (TrackElement)elementTable.get(enumeration.nextElement());
            if(element.isSelected()){
                logger.debug("Rotating : " + element.toString());
                // Suppression de la liste des elements
                //elementTable.remove(Integer.valueOf(element.getId()));
                // Suppression de l'affichage
                //this.remove(element);
            }
        }
        this.updateUI();
    }
    
    /**
     * 
     * @return
     */
    public String printTrackElementList(){
        StringBuffer s = new StringBuffer("Track Element List : {\n");
        Enumeration enumeration = elementTable.keys();
        while(enumeration.hasMoreElements()){
            s.append(((TrackElement)elementTable.get(enumeration.nextElement())).toString() + "\n");
        }
        s.append("\n}");
        return s.toString();
    }
    
}
