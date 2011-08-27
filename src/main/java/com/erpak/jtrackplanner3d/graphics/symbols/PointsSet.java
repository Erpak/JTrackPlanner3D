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

import java.awt.geom.GeneralPath;
import java.util.ArrayList;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * This class is used to store symbol reference points
 */
public class PointsSet {

    /** Tableau de points */
    private Point3D points[];

    /** Tableau de r�gles pour les droites. Si listePointsDeControle1[x] est different de
     *  <code>null</code>, une droite est tracee.
     *  Si listePointsDeControle1[x] est <code>null</code>, une courbe quadratique ou cubique (Bezier)
     *  est tracee.
     *  listePointsDeControle1[x] est utilis� comme point de controle pour le trace des courbes quadratiques et de Bezier */
    private Point3D listePointsDeControle1[];

    /** Tableau de r�gles pour les courbes. listePointsDeControle2[x] est utilis� comme point de
     *  controle pour le trac� des courbes de Bezier  */
    private Point3D listePointsDeControle2[];

    /**
     *
     * @param nombrePoints
     */
    public PointsSet(int nombrePoints){
        points = new Point3D[nombrePoints];
        listePointsDeControle1 = new Point3D[nombrePoints];
        listePointsDeControle2 = new Point3D[nombrePoints];
    }

    /**
     * Initialise l'ensemble de point a 1
     * @param point Le premier point de l'ensemble
     */
    public PointsSet(Point3D point){
        this(1);
        this.points[0] = point;
    }

    /**
     *
     * @param point
     * @param pointDeControle
     */
    public PointsSet(Point3D point, Point3D pointDeControle){
        this(point);
        this.listePointsDeControle1[0] = pointDeControle;
    }

    /**
     *
     * @param point
     * @param pointDeControle1
     * @param pointDeControle2
     */
    public PointsSet(Point3D point, Point3D pointDeControle1, Point3D pointDeControle2){
        this(point, pointDeControle1);
        this.listePointsDeControle2[0] = pointDeControle2;
    }


    /**
     * Initialise l'ensmble de point avec un tableau de <code>Point3D</code>
     * 
     * @param p
     */
    public PointsSet(Point3D p[]){
        this(p.length);
        points = p;
    }

    /**
     * Construit un ensemble de points � partir d'une liste d'extremit�s
     * @param listeExtremites
     */
    public PointsSet(ArrayList listeExtremites){
        this(listeExtremites.size() * 2); // Chaque extremit� comporte 2 points
        for(int i = 0; i < listeExtremites.size(); i++){
            Extremity g = (Extremity)listeExtremites.get(i);
            points[i * 2] = g.plus;
            points[i * 2 + 1] = g.minus;
            listePointsDeControle1[i * 2] = null;
            listePointsDeControle1[i * 2 + 1] = g.realhint;
            listePointsDeControle2[i * 2] = null;
            listePointsDeControle2[i * 2 + 1] = g.realhint2;
        }
    }

    /**
     *
     */
    public PointsSet(){
        this(new PointsSet(new Point3D()), new PointsSet(new Point3D()));
    }

    /**
     *
     * @param w
     */
    public PointsSet(PointsSet w[]){
        int wl = 0;
        for(int i = 0; i < w.length; i++)
            wl += w[i].points.length;

        points = new Point3D[wl];
        listePointsDeControle1 = new Point3D[wl];
        listePointsDeControle2 = new Point3D[wl];
        int wp = 0;
        for(int j = 0; j < w.length; j++){
            System.arraycopy(w[j].points, 0, points, wp, w[j].points.length);
            System.arraycopy(w[j].listePointsDeControle1, 0, listePointsDeControle1, wp, w[j].points.length);
            System.arraycopy(w[j].listePointsDeControle2, 0, listePointsDeControle2, wp, w[j].points.length);
            wp += w[j].points.length;
        }

    }

    /**
     *
     * @param weg1
     * @param weg2
     */
    public PointsSet(PointsSet weg1, PointsSet weg2){
        this(weg1.points.length + weg2.points.length);
        System.arraycopy(weg1.points, 0, points, 0, weg1.points.length);
        System.arraycopy(weg2.points, 0, points, weg1.points.length, weg2.points.length);
        System.arraycopy(weg1.listePointsDeControle1, 0, listePointsDeControle1, 0, weg1.listePointsDeControle1.length);
        System.arraycopy(weg2.listePointsDeControle1, 0, listePointsDeControle1, weg1.listePointsDeControle1.length, weg2.listePointsDeControle1.length);
        System.arraycopy(weg1.listePointsDeControle2, 0, listePointsDeControle2, 0, weg1.listePointsDeControle2.length);
        System.arraycopy(weg2.listePointsDeControle2, 0, listePointsDeControle2, weg1.listePointsDeControle2.length, weg2.listePointsDeControle2.length);
    }

    /**
     *
     * @param x Coordonnee X du point
     * @param y Coordonnee Y du point
     */
    public final void addPoint(int x, int y){
        PointsSet w = new PointsSet(this, new PointsSet(new Point3D(x, y)));
        points = w.points;
        listePointsDeControle1 = w.listePointsDeControle1;
        listePointsDeControle2 = w.listePointsDeControle2;
    }

    /**
     *
     * @param x
     * @param y
     * @param a
     * @param b
     */
    public final void addPoint(int x, int y, int a, int b){
        PointsSet w = new PointsSet(this, new PointsSet(new Point3D(x, y), new Point3D(a, b)));
        points = w.points;
        listePointsDeControle1 = w.listePointsDeControle1;
        listePointsDeControle2 = w.listePointsDeControle2;
    }

    /**
     *
     * @param x
     * @param y
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public final void addPoint(int x, int y, int a, int b, int c, int d){
        PointsSet w = new PointsSet(this, new PointsSet(new Point3D(x, y), new Point3D(a, b), new Point3D(c, d)));
        points = w.points;
        listePointsDeControle1 = w.listePointsDeControle1;
        listePointsDeControle2 = w.listePointsDeControle2;
    }

    /**
     * Forme un objet <code>GeneralPath</code> en fonction d'un tableau de points et de r�gles.
     * Les points sont relies par des lignes, des courbes quadratiques ou des courbes de Bezier.
     * @return
     */
    public final GeneralPath toPath(){
        GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        int i, j; // Compteurs
        if(points.length > 1){
            p.moveTo(points[0].x, points[0].y);
            for(i = 0; i < points.length; i++){
                if(i == points.length - 1)
                    j = 0;
                else
                    j = i + 1;
                if(listePointsDeControle1[i] == null){
                    // Trace une ligne
                    p.lineTo(points[j].x, points[j].y);
                    continue;
                }
                if(listePointsDeControle2[i] == null)
                    // Trace une courbe quadratique
                    p.quadTo(listePointsDeControle1[i].x, listePointsDeControle1[i].y, points[j].x, points[j].y);
                else
                    // Trace une courbe de Bezier
                    p.curveTo(listePointsDeControle1[i].x, listePointsDeControle1[i].y, listePointsDeControle2[i].x, listePointsDeControle2[i].y, points[j].x, points[j].y);
            }
        }
        p.closePath();
        //System.out.println(toString());
        return p;
    }
    
    public String toString(){
     String s = "PointSet {";
     for(int i=0; i<points.length; i++){
         s= s + "[" + points[i] + "|" + listePointsDeControle1[i] + "|" + listePointsDeControle2[i] + "]";
     }
     s = s + "}";
     return s;   
    }
    
}
