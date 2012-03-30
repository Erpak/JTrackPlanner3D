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

import com.erpak.jtrackplanner3d.graphics.TrackSystem;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * This class is used to represent a straight track symbol
 */
public class StraightTrackSymbol extends TrackSymbol {

    /** Straight track length */
    private float length;
    /** symbol extra properties */
    private String[] properties = {"length"};
    /** */
    private PointsSet pointsSet;
    /** extrmity list */ 
    private ArrayList extremities;
    
    /**
     * @param trackSystem
     * @param reference
     * @param designation
     * @param length (in milimeters)
     */
    public StraightTrackSymbol(TrackSystem trackSystem, String reference, String designation, float length, String colorKey) {
        this(trackSystem, reference, designation, "", length, colorKey);
    }

    /**
     * @param trackSystem
     * @param reference
     * @param designation
     * @param comments
     * @param length (in milimeters)
     */
    public StraightTrackSymbol(TrackSystem trackSystem, String reference, String designation, String comments, float length, String colorKey) {
        super(trackSystem, reference, designation, comments, colorKey);
        this.length = length;
        extremities = new ArrayList(2);
        extremities.add(new Extremity(new Point3D(0.0F, trackSystem.getBallastWidth()+0.001F), new Point3D(), trackSystem.getBallastWidth()+0.001F));
        extremities.add(new Extremity(new Point3D(length, 0), new Point3D(length, trackSystem.getBallastWidth()+0.001F), trackSystem.getBallastWidth()+0.001F));
        pointsSet = new PointsSet(extremities);
    }

    /* (non-Javadoc)
     * @see jTrackPlanner.app.symbols.TrackSymbol#getPropertyValue(java.lang.String)
     */
    public String getPropertyValue(String propertyName) {
        if("length".equals(propertyName)){
            return Float.toString(length);
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see jTrackPlanner.app.symbols.TrackSymbol#getProperties()
     */    
    public String[] getProperties(){
        return this.properties;
    }
    
    /**
     * Returns the symbol length
     * @return The straight track length
     */
    public float getLength(){
        return this.length;
    }
    
    /**
     * Returns the symbol comments
     * @return The symbol comments
     */
    public String getComments(){
        StringBuffer buffer = new StringBuffer("Longueur : ");
        buffer.append(this.length);
        buffer.append(" mm. ");
        buffer.append(this.comments);
        return buffer.toString();
    }
    
    /**
     * 
     * @return A general path object
     */
    public GeneralPath getGeneralPath(){
        return this.pointsSet.toPath();
    }
    
    /**
     * 
     * @return An arraylist object
     */
    public ArrayList getExtremityList(){
        return this.extremities;
    }    

}
