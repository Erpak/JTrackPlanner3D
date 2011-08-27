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
package com.erpak.jtrackplanner3d.utils.software;

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class SoftwareInformations {
    
    /** */
    private static ResourceBundle versionResource;
    /** */
    private static String version = "";
    /** */
    private static String comments = "";
    /** */
    private static String copyright = "";
    /** */
    private static String product = "";
    /** */
    static {
        try {
            String vp = "software.properties";
            InputStream is = SoftwareInformations.class.getClassLoader().getResourceAsStream(vp);
            if (is != null) {
                versionResource = new PropertyResourceBundle(is);
                version = versionResource.getString("software.version.number.major")
                + "." + versionResource.getString("software.version.number.minor");
                product = versionResource.getString("software.product");
                comments = versionResource.getString("software.comments");
                copyright = versionResource.getString("software.copyright");
            }
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
            version = "{File version.properties not available.}";
        }
    }
    
    /**
     * Returns the current version number
     * @return The app version
     */
    public static String getVersion() {
        return "Version " + version;
    }
    
    /**
     * Returns the product name
     * @return The app product name
     */
    public static String getProduct() {
        return product;
    }
    
    /**
     * Returns the comments
     * @return The app comments
     */
    public static String getComments() {
        return comments;
    }
    
    /**
     * Returns the copyright
     * @return The app copyrigth
     */
    public static String getCopyRight() {
        return copyright;
    }
    
    /**
     * Returns if the application runs on a Mac
     * @return true if the application runs on a Mac system, false otherwise
     */
    public static boolean isMacintoshSystem(){
        if(System.getProperty("mrj.version") != null){
            return true;
        } else {
            return false;
        }
        
    }
    
}