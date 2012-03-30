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

import java.io.Serializable;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class Extremity implements Serializable {

    /** Point de controle 1 */
    public Point3D realhint; 
    /** Point de controle 2 */
    public Point3D realhint2;
    /** Borne moins */ 
    public Point3D minus;
    /** Borne plus */
    public Point3D plus;
    /** largeur des bornes, fiches de l'extremite */
    private float largeur;
    /** Angle */
    private double angle;

    /**
     * 
     * @param p
     * @param largeur
     */
    public Extremity(Point3D p, float largeur) {
        this(p, new Point3D(p.x + largeur, p.y), largeur);
    }

    /**
     * 
     * @param plus
     * @param minus
     * @param largeur
     */
    public Extremity(Point3D plus, Point3D minus, float largeur) {
        this.plus = plus;
        this.minus = minus;
        this.largeur = largeur;
        angle = plus.getAngle(minus);
    }

    /**
     * 
     * @param plus
     * @param minus
     * @param hint
     * @param largeur
     */
    public Extremity(Point3D plus, Point3D minus, Point3D hint, float largeur) {
        this(plus, minus, largeur);
        realhint = hint;
    }

    /**
     * 
     * @param plus
     * @param minus
     * @param hint
     * @param hint2
     * @param largeur
     */
    public Extremity(Point3D plus, Point3D minus, Point3D hint, Point3D hint2, float largeur) {
        this(plus, minus, hint, largeur);
        realhint2 = hint2;
    }

    /**
     * 
     * @param g
     */
    public Extremity(Extremity g) {
        this(new Point3D(g.plus), new Point3D(g.minus), g.largeur);
        if (g.realhint != null) {
            realhint = new Point3D(g.realhint);
            if (g.realhint2 != null)
                realhint2 = new Point3D(g.realhint2);
        }
    }

    /**
     * 
     * @param g2
     * @return
     */
    public final boolean isNear(Extremity g2) {
        float nahe_Q = 4F;
        return plus.distanceQ(g2.plus) < nahe_Q && minus.distanceQ(g2.minus) < nahe_Q;
    }

    /**
     * 
     * @param vector
     */
    public final void move(Point3D vector) {
        plus.add(vector);
        minus.add(vector);
        if (realhint != null) {
            realhint.add(vector);
            if (realhint2 != null)
                realhint2.add(vector);
        }
    }

    /**
     * 
     * @param w
     */
    public final void rotate(double w) {
        plus = new Point3D(plus, w);
        minus = new Point3D(minus, w);
        if (realhint != null) {
            realhint = new Point3D(realhint, w);
            if (realhint2 != null)
                realhint2 = new Point3D(realhint2, w);
        }
        angle += w;
    }

    /**
     * Returns the orientation of the extremity in radians
     * @return Angle value in radians
     */
    public double getAngleRadians() {
        return angle;
    }

    /**
     * 
     * @param angle value in radians
     */
    public void setAngleRadians(double angle) {
        this.angle = angle;
    }

    public String toString() {
        return "Extremity [" + this.minus + "|" + this.plus + "|" + (180 * angle) / Math.PI + "(" + realhint + ","
                + realhint2 + ")]";
    }
    
}