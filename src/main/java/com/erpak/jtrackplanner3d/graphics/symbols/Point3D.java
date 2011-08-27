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

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * A 3D point
 */
public class Point3D extends java.awt.geom.Point2D.Float implements Comparable {

    public static final Point3D nullPoint = new Point3D();
    public float z;

    /**
     * Constructeur : initialise les valeurs x, y et z a 0.
     */
    public Point3D(){
        this(0.0F, 0.0F, 0.0F);
    }

    /**
     * Constructeur : initialise les valeurs y et z a 0.
     * @param x Coordonee X du point
     */
    public Point3D(float x){
        this(x, 0.0F, 0.0F);
    }

    /**
     * Constructeur : initialise les valeurs y et z a 0.
     * @param x Coordonee X du point
     * @param y Coordonee Y du point
     */
    public Point3D(float x, float y){
        this(x, y, 0.0F);
    }

    /**
     * Constructeur
     * @param x Coordonee X du point
     * @param y Coordonee Y du point
     * @param z Coordonee Z du point
     */
    public Point3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructeur : Calcule les coordonnees X et Y selon les coordonees polaires (r,theta). La valeur z est initialisee a 0.
     * @param hypothenuse Longueur de l'hypotenuse d'un triangle rectangle
     * @param angle Angle theta
     */
    public Point3D(float hypothenuse, double angle){
        this((float)((double)hypothenuse * Math.cos(angle)), (float)((double)hypothenuse * Math.sin(angle)), 0.0F);
    }

    /**
     * Constructeur : Initialise les valeurs X, Y et Z en copiant les valeurs de P
     * @param p Point de reference
     */
    public Point3D(Point3D p){
        this(p.x, p.y, p.z);
    }

    /**
     * Constructeur : La valeur Z est initialisee avec le valeur de p
     * @param p Point de reference
     * @param angle
     */
    public Point3D(Point3D p, double angle){
        this(0.0F, 0.0F, p.z);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        super.x = (float)((double)p.x * cos - (double)p.y * sin);
        super.y = (float)((double)p.x * sin + (double)p.y * cos);
    }

    /**
     * Retourne la distance entre 2 points
     * @param p Le point distant
     * @return La distance entre les 2 points
     */
    public final float distance(Point3D p){
        float c = x - p.x;
        float d = y - p.y;
        float e = z - p.z;
        return (float)Math.sqrt(c * c + d * d + e * e);
    }

    /**
     *
     * @param x2
     * @param y2
     * @param z2
     * @return
     */
    public final float distance(float x2, float y2, float z2){
        x2 -= x;
        y2 -= y;
        z2 -= z;
        return (float)Math.sqrt(x2 * x2 + y2 * y2 + z2 * z2);
    }

    /**
     *
     * @param x2
     * @param y2
     * @return
     */
    public final float distance(float x2, float y2){
        x2 -= x;
        y2 -= y;
        return (float)Math.sqrt(x2 * x2 + y2 * y2);
    }

    /**
     *
     * @param p
     * @return
     */
    public final float distanceQ(Point3D p){
        float c = x - p.x;
        float d = y - p.y;
        float e = z - p.z;
        return c * c + d * d + e * e;
    }

    /**
     *
     * @param x2
     * @param y2
     * @param z2
     * @return
     */
    public final float distanceQ(float x2, float y2, float z2){
        x2 -= x;
        y2 -= y;
        z2 -= z;
        return x2 * x2 + y2 * y2 + z2 * z2;
    }

    /**
     *
     * @param x2
     * @param y2
     * @return
     */
    public final float distanceQ(float x2, float y2){
        x2 -= x;
        y2 -= y;
        return x2 * x2 + y2 * y2;
    }

    /**
     * 
     * @param p
     * @return
     */
    public Point3D add(Point3D p){
        if(p != null){
            x += p.x;
            y += p.y;
            z += p.z;
        }
        return this;
    }

    /**
     * Set coodinates to 0
     */
    public void clear(){
        x = 0.0F;
        y = 0.0F;
        z = 0.0F;
    }

    /**
     *
     * @param o2
     * @return
     */
    public int compareTo(Object o2){
        Point3D p2 = (Point3D)o2;
        if(distanceQ(nullPoint) < p2.distanceQ(nullPoint))
            return -1;
        if(distanceQ(nullPoint) > p2.distanceQ(nullPoint))
            return 1;
        if(getAngle(nullPoint) < p2.getAngle(nullPoint))
            return -1;
        if(getAngle(nullPoint) > p2.getAngle(nullPoint))
            return 1;
        if(hashCode() < p2.hashCode())
            return -1;
        return hashCode() <= p2.hashCode() ? 0 : 1;
    }

    /**
     *
     * @return Un clone du point_3D
     */
    protected Point3D getClone(){
        return new Point3D(x, y, z);
    }

    /**
     *
     * @param f
     * @return
     */
    public Point3D scale(float f){
        x *= f;
        y *= f;
        z *= f;
        return this;
    }

    /**
     *
     * @param f
     * @param ff
     * @return
     */
    public Point3D scaleSpecial(float f, float ff){
        x *= f;
        y *= f;
        z *= ff;
        return this;
    }

    /**
     * Invert all the x, y and z coordinates
     */
    public void setReverse(){
        super.x = -super.x;
        super.y = -super.y;
        z = -z;
    }

    /**
     * Set x,y,z coordinates
     * @param p Point P
     */
    public void setTo(Point3D p){
        x = p.x;
        y = p.y;
        z = p.z;
    }

    /**
     * Set x,y coordinates
     * @param x
     * @param y
     */
    public void setTo(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param p1 Point P1
     * @param p2 Point P2
     */
    public void setToMiddle(Point3D p1, Point3D p2){
        x = (p1.x + p2.x) / 2.0F;
        y = (p1.y + p2.y) / 2.0F;
        z = (p1.z + p2.z) / 2.0F;
    }

    /**
     * Converts rectangular coordinates (x, y) to polar (r, theta). 
     * @param p The point to compare with
     * @return The angle Between 2 Point3D objects (computed only on x,y coordinates).
     */
    public double getAngle(Point3D p){
        if(super.x == p.x && super.y == p.y)
            return 0.0D;
        else
            return Math.atan2(p.y - y, p.x - x);
    }
       
    /**
     * Returns a String representation of the Point3D object
     * @return A <code>String</code> representation : Point3D(X/Y/Z)
     */
    public String toString() {
        return (" Point_3D(" + super.x + "/" + super.y + "/" + z + ")");
    }    

}
