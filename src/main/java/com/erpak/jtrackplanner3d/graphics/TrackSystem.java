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

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * This class is used to represent a track system
 */
public class TrackSystem {

    /** Track system name */
    private String name;
    /** Track system version */
    private String version;
    /** Track system manufacturer */
    private String manufacturer;
    /** Track system scale */
    private String scale;
    /** Track system comments */
    private String comments;
    /** Track width */
    private float trackWidth;
    /** ballast width */
    private float ballastWidth;
    /** Colors for track symbols */
    private Hashtable colorSet;
    
    /**
     * @param name The track system name
     */
    public TrackSystem(String name, String version){
        this(name, version, "", "", 0, 0);
    }
        
    /**
     * @param name The track system name
     * @param scale The track system scale
     * @param manufacturer The track system manufacturer 
     */
    public TrackSystem(String name, String version, String scale, String manufacturer, float trackWidth, float ballastWidth) {
        this(name, version, scale, manufacturer, "", trackWidth, ballastWidth);
    }

    /**
     * @param name The track system name
     * @param scale The track system scale
     * @param manufacturer The track system manufacturer
     * @param comments The track system comments 
     */
    public TrackSystem(String name, String version, String scale, String manufacturer, String comments, float trackWidth, float ballastWidth){
        this.name = name;
        this.version = version;
        setScale(scale);
        setManufacturer(manufacturer);
        setComments(comments);
        this.trackWidth = trackWidth;
        this.ballastWidth = ballastWidth;
        colorSet = new Hashtable();
    }
    
    /**
     * Returns the track system name
     * @return The track system name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns the track system version
     * @return The track system version
     */
    public String getVersion(){
        return version;
    }
    
    /**
     * Set the track system scale
     * @param scale The track system scale
     */
    public void setScale(String scale){
        if(scale != null){
            this.scale = scale;
        } else {
            this.scale = "";
        }
    }
    
    /**
     * Returns the track system scale
     * @return The track system scale
     */
    public String getScale(){
        return scale;
    }

    /**
     * Set the track system manufacturer
     * @param manufacturer The track system manufacturer
     */
    public void setManufacturer(String manufacturer){
        if(manufacturer != null){
            this.manufacturer = manufacturer;
        } else {
            this.manufacturer = "";
        }        
    }
    
    /**
     * Returns the track system manufacturer
     * @return The track system manufacturer
     */
    public String getManufacturer(){
        return manufacturer;
    }
    
    /**
     * Set the track system comments
     * @param comments The track system comments
     */
    public void setComments(String comments){
         if(comments != null){
            this.comments = comments;
        } else {
            this.comments = "";
        }        
    }
    
    /**
     * Returns the track system comments
     * @return The track system comments
     */
    public String getComments(){
        return comments;
    }
    
    /**
     * Set the track system width
     * @param trackWidth The track system width
     */
    public void setTrackWidth(float trackWidth){
        this.trackWidth = trackWidth;
    }
    
    /**
     * Returns the track system track width
     * @return The track system track width
     */
    public float getTrackWidth(){
        return this.trackWidth;
    }    
    
    /**
     * Set the track system ballast width
     * @param ballastWidth The ballast width
     */
    public void setBallastWidth(float ballastWidth){
        this.ballastWidth = ballastWidth;
    }
    
    /**
     * Returns the track system ballast width
     * @return The track system ballast width
     */
    public float getBallastWidth(){
        return this.ballastWidth;
    }  
    
    /**
     * Add a color to the color set
     * @param colorKey The color name
     * @param color The color to add
     */
    public void addColor(String colorKey, Color color){
        if(colorKey != null && !"".equals(colorKey) && color != null){
            colorSet.put(colorKey, color);
        }
    }
    
    /**
     * Add a color to the color set
     * @param colorKey The color name
     * @param r The red value
     * @param g The green value
     * @param b The blue value
     */
    public void addColor(String colorKey, int r, int g, int b, int alpha){
        if(colorKey != null && !"".equals(colorKey)){
            colorSet.put(colorKey, new Color(r, g, b, alpha));                
        }
    }
    
    /**
     * Add a color to the color set
     * @param colorKey The color name
     * @param r The red value
     * @param g The green value
     * @param b The blue value
     */
    public void addColor(String colorKey, String r, String g, String b){
        if(colorKey != null && !"".equals(colorKey)){
            try {
                colorSet.put(colorKey, new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
            } catch (NumberFormatException ex) {
                //Nothing to do
                System.out.println("WARNING : Unable to add the '" + colorKey + "' color with rgb values = " + r + ", " + g + ", " + b + " !");
            }
        }
    }
    
    /**
     * Returns the specified color
     * @param colorKey The color name
     * @return The color corresponding to the name. If the color is not found, the default light gray color is returned.
     */
    public Color getColor(String colorKey){
        if(colorSet.containsKey(colorKey)){
            return (Color)colorSet.get(colorKey);
        } else {
            return Color.LIGHT_GRAY;
        }
    }
    
    /**
     * Returns the track system informations
     * @return The track system informations 
     */
    public String toString(){
        StringBuffer returnString = new StringBuffer();
        String colorKey;
        Enumeration keys = colorSet.keys();
        returnString.append("Name : " + name);
        returnString.append(", Version : " + version);
        returnString.append(", Manufacturer : " + manufacturer);
        returnString.append(", Scale : " + scale);
        returnString.append(", TrackWidth : " + trackWidth);
        returnString.append(", BallastWidth : " + ballastWidth);
        returnString.append(", Color set [ ");
        while(keys.hasMoreElements()){
            colorKey = (String)keys.nextElement();
            returnString.append( colorKey + " : " + getColor(colorKey));   
            if(keys.hasMoreElements()){
                returnString.append(", ");
            }
        }
        returnString.append(" ]");
        return returnString.toString();
    }

}
