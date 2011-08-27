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
package com.erpak.jtrackplanner3d.graphics.symbols;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import com.erpak.jtrackplanner3d.graphics.TrackSystem;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * L'interface (ou classe abstraite) TrackSymbol est l'objet utilise dans l'arbo pour afficher les elements presents dans un track_system
 * Cette interface defini des methodes communes qui seront utilisees par d'autres objets : 
 * - Par exemple clone ou getNewElement qui seront utilisees lors d'un click souris sur la zone graphique pour deposer un nouvel element.
 * - toString qu'il faut surcharger pour obtenir la designation de l'element
 * - getProperties pour afficher les proprietes d'un element
 * .....
 */
public abstract class TrackSymbol {

    /** Reference */
    String reference;
    /** Designation */
    String designation;
    /** Comments */
    String comments;
    /** ColorKey */
    String colorKey;
    /** TrackSystem */
    TrackSystem trackSystem;
    
    /**
     * Constructor
     * @param trackSystem The track system
     * @param reference The symbol reference
     * @param designation The symbol designation
     */
    protected TrackSymbol(TrackSystem trackSystem, String reference, String designation, String colorKey){
        this(trackSystem, reference, designation, "", colorKey);
    }
    
    /**
     * Constructor
     * @param trackSystem The track system
     * @param reference The symbol reference
     * @param designation The symbol designation
     * @param comments The symbol comments
     */
    protected TrackSymbol(TrackSystem trackSystem, String reference, String designation, String comments, String colorKey){
        this.trackSystem = trackSystem;
        this.reference = reference;
        this.designation = designation;
        this.comments = comments;
        this.colorKey = colorKey;
    }

    /**
     * Returns the symbol reference
     * @return The symbol reference
     */
    public String getReference(){
        return this.reference;
    }

    /**
     * Returns the symbol designation
     * @return The symbol designation
     */
    public String getDesignation(){
        return this.designation;
    }    

    /**
     * Returns the symbol comments
     * @return The symbol comments
     */
    public String getComments(){
        return this.comments;
    }    
    
    /**
     * Returns the symbol colorKey
     * @return The symbol colorKey
     */
    public String getColorKey(){
        return this.colorKey;
    }
    
    /**
     * Returns the symbol color
     * @return The symbol color
     */
    public Color getColor(){
        return trackSystem.getColor(this.colorKey);
    }
    
    /**
     * Returns the TrackSymbol Track System
     * @return The symbol TrackSystem
     */
    public TrackSystem getTrackSystem(){
        return this.trackSystem;
    } 
    
    /**
     * Returns the symbol designation
     * @return The symbol designation
     */
    public String toString(){
        return this.designation;
    }
    
    /**
     * Returns the value corresponding to the property
     * @param propertyName The property name
     * @return The property value
     */
    public abstract String getPropertyValue(String propertyName);
    
    /**
     * Returns the symbol extra properties
     * @return A String array with the symbol extra properties
     */
    public abstract String[] getProperties();

    /**
     * Returns a general path for the symbol graphic representation
     * @return A <code>GeneralPath</code> object
     */
    public abstract GeneralPath getGeneralPath();
    
    /**
     * Returns aa arraylist filled with the symbol extremities 
     * @return A <code>ArrayList</code> object filled with <code>Extremity</code>
     */
    public abstract ArrayList getExtremityList();    
    
}
