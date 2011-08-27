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
 * This class is used to represent a curved track symbol
 */
public class CurvedTrackSymbol extends TrackSymbol {

    /** Straight track radius (in milimeters) */
    private float radius;
    /** Straight track angle (in degrees) */
    private float angle;    
    /** symbol extra properties */
    private String[] properties = {"radius", "angle"};
    /** */
    private PointsSet pointsSet;
    /** Liste des extremites */
    private ArrayList listeExtremites;
    
    /**
     * @param reference
     * @param designation
     * @param radius (in milimeters)
     * @param angle (in degrees)
     */
    public CurvedTrackSymbol(TrackSystem trackSystem, String reference, String designation, float radius, float angle, String colorKey) {
        this(trackSystem, reference, designation, "", radius, angle, colorKey);
    }

    /**
     * @param reference
     * @param designation
     * @param comments
     * @param radius (in milimeters)
     * @param angle (in degrees)
     */
    public CurvedTrackSymbol(TrackSystem trackSystem, String reference, String designation, String comments, float radius, float angle, String colorKey) {
        super(trackSystem, reference, designation, comments, colorKey);
        this.radius = radius;
        this.angle = angle;
        float angleRad = (float)(Math.PI*angle)/180;
        listeExtremites = new ArrayList(2);
        /****** Ajout code CPLAN ******/
        // Calculs des rayons
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
        // Ajout des extremites
        listeExtremites.add(new Extremity(new Point3D(0.0F, trackSystem.getBallastWidth()), new Point3D(), new Point3D(xha), trackSystem.getBallastWidth()));
        listeExtremites.add(new Extremity(new Point3D(x2, y2), new Point3D(x3, y3), new Point3D(xhi, trackSystem.getBallastWidth()), trackSystem.getBallastWidth()));
        //sucherealMitte();
        /***** FIN Ajout code CPLAN *****/
        pointsSet = new PointsSet(listeExtremites);        
    }

    /* (non-Javadoc)
     * @see jTrackPlanner.app.symbols.TrackSymbol#getPropertyValue(java.lang.String)
     */
    public String getPropertyValue(String propertyName) {
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
        return "Rayon : " + this.radius + " mm, Angle : " + this.angle + " �. " + this.comments;
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
