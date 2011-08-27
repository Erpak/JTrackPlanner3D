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
public class SymetricThreeWayTurnoutSymbol extends TrackSymbol {

    /** Straight turnout length */
    private float length;
    /** Straight turnout radius */
    private float radius;
    /** Straight turnout angle */
    private float angle;
    /** symbol extra properties */
    private String[] properties = {"length", "radius", "angle"};
    /** Point set */
    private PointsSet pointsSet;
    /** Extremity list */
    private ArrayList listeExtremites;
    
    /**
     * 
     * @param reference TrackSymbol reference
     * @param designation
     * @param length
     * @param radius
     * @param angle
     * @param direction
     */
    public SymetricThreeWayTurnoutSymbol(TrackSystem trackSystem, String reference, String designation, float length, float radius, float angle, String direction, String colorKey) {
        this(trackSystem, reference, designation, "", length, radius, angle, colorKey);
    }

    /**
     * 
     * @param reference
     * @param designation
     * @param comments
     * @param length
     * @param radius
     * @param angle
     * @param direction
     */
    public SymetricThreeWayTurnoutSymbol(TrackSystem trackSystem, String reference, String designation, String comments, float length, float radius, float angle, String colorKey) {
        super(trackSystem, reference, designation, comments, colorKey);
        this.length = length;
        this.radius = radius;
        this.angle = angle;
        listeExtremites = new ArrayList(3);
        // Calculs des rayons
        float angleRad = (float)(Math.PI*angle)/180;
        float rayonExterne = radius + trackSystem.getBallastWidth() / 2F;
        float rayonInterne = radius - trackSystem.getBallastWidth() / 2F;
        // Calcul des coordonnees extremites 1
        float x2 = (float)((double)rayonExterne * Math.sin(angleRad));
        float y2 = (float)((double)rayonExterne * (1D - Math.cos(angleRad)));
        // Calcul des coordonnees extremites 2
        float x3 = (float)((double)rayonInterne * Math.sin(angleRad));
        float y3 = (float)((double)rayonInterne * (1D - Math.cos(angleRad)) + (double)trackSystem.getBallastWidth());
        // Calcul des coordonn�es centrales de l'element
        float xha = (float)((double)rayonExterne * Math.sin(angleRad / 2D));
        float xhi = (float)((double)rayonInterne * Math.sin(angleRad / 2D));
        // La 1er extremite de la partie droite
        listeExtremites.add(new Extremity(new Point3D(0.0F, trackSystem.getBallastWidth()+0.001F), new Point3D(), trackSystem.getBallastWidth()+0.001F));
        // L'extremite de la partie courbe a gauche
        listeExtremites.add(new Extremity(new Point3D(x2, -y2+y3), new Point3D(x3, -y3+y3), new Point3D(xhi, -trackSystem.getBallastWidth()+y3), trackSystem.getBallastWidth()));            
        // La 2nd extremite de la partie droite
        listeExtremites.add(new Extremity(new Point3D(length, 0), new Point3D(length, trackSystem.getBallastWidth()+0.001F), trackSystem.getBallastWidth()+0.001F));
        // L'extremite de la partie courbe a droite
        listeExtremites.add(new Extremity(new Point3D(x2, y2), new Point3D(x3, y3), new Point3D(xhi, trackSystem.getBallastWidth()), trackSystem.getBallastWidth()));
        pointsSet = new PointsSet(listeExtremites);
    }

    /* (non-Javadoc)
     * @see jTrackPlanner.app.symbols.TrackSymbol#getPropertyValue(java.lang.String)
     */
    public String getPropertyValue(String propertyName) {
        if("length".equals(propertyName)){
            return Float.toString(length);
        }
        if("radius".equals(propertyName)){
            return Float.toString(radius);
        }
        if("angle".equals(propertyName)){
            return Float.toString(angle);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see jTrackPlanner.app.symbols.TrackSymbol#getProperties()
     */
    public String[] getProperties() {
        return properties;
    }

    /**
     * Returns the symbol comments
     * @return The symbol comments
     */
    public String getComments(){
        StringBuffer buffer = new StringBuffer("Rayon : ");
        buffer.append(this.radius);
        buffer.append(" mm, Angle : ");
        buffer.append(this.angle);
        buffer.append(" �. ");
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
        return this.listeExtremites;
    }    
    
}
