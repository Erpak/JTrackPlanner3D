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
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class ResourcesManager {

    private static Locale defaultLocale;

    private static ResourceBundle defaultResourceBoundle;

    private static Locale choosenLocale;

    private static ResourceBundle choosenResourceBundle;

    private static Properties properties = new Properties();

    static {
        // Language resources
        try {
            // default is english
            defaultLocale = new Locale("");
            //defaultResourceBoundle = ResourceBundle.getBundle(JaWEConstants.RESOURCE_PATH, defaultLocale);
            defaultResourceBoundle = ResourceBundle.getBundle("JTrackPlanner3D", defaultLocale, ResourcesManager.class.getClassLoader());
            // chose the default system settings at the start
            //String startingLocale = JaWEConfig.getInstance().getStartingLocale();
            String startingLocale = "";
            if (startingLocale != null && startingLocale.length() > 0) {
                if (!startingLocale.equals("default")) {
                    choosenLocale = new Locale(startingLocale);
                } else {
                    choosenLocale = defaultLocale;
                }
            } else {
                choosenLocale = Locale.getDefault();
            }
            if (!startingLocale.equals("default")) {
                choosenResourceBundle = ResourceBundle.getBundle("JTrackPlanner3D", choosenLocale, ResourcesManager.class.getClassLoader());
            } else {
                choosenResourceBundle = defaultResourceBoundle;
            }
        } catch (MissingResourceException mre) {
            System.err.println("JTrackPlanner3D" + ".properties not found");
            System.exit(1);
        }
        // Pictures resources
        try {
            java.net.URL u = ResourcesManager.class.getClassLoader().getResource("software.res");
            InputStream is = (InputStream) u.getContent();
            properties.load(is);
        } catch (Exception ex) {
            System.err.println("software.res" + " not found");
            System.exit(1);
        }
    }

    /**
     * Gets a language dependent string from the resource bundle.
     * <p>
     * Resource bundle represents the <i>property file</i>. For example, if property file contains something like this:<BR>
     * <CENTER>menubar=file edit help</CENTER> method call getLanguageDependentString("menubar") will give the string
     * <i>file edit help</i> as a result. <BR>
     * This method reads information from property file. If can't find desired resource, returns <b>null</b>.
     * @param nm name of the resource to fetch.
     * @return String value of named resource.
     */
    public static String getLanguageDependentString(String nm) {
        String str;
        try {
            str = choosenResourceBundle.getString(nm);
        } catch (MissingResourceException mre) {
            try {
                str = defaultResourceBoundle.getString(nm);
            } catch (MissingResourceException mre1) {
                str = null;
            }
        }
        return str;
    }

    /**
     * 
     * @param nm
     * @return
     */
    public static String getResourceString(String nm) {
        String str = null;
        try {
            str = properties.getProperty(nm);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return str;
    }

    /**
     * Gets the url from a resource string.
     * 
     * @param key
     *            the string key to the url in the properties.
     * @return the resource location.
     * @see java.lang.Class#getResource
     */
    public static URL getResource(String key) {
        String name = properties.getProperty(key);
        if (name != null) {
            URL url = ResourcesManager.class.getClassLoader().getResource(name);
            return url;
        }
        return null;
    }

    /**
     * 
     * 
     */
    public static void setDefault() {
        choosenResourceBundle = defaultResourceBoundle;
        choosenLocale = defaultLocale;
    }

    /**
     * Returns the default resource bundle.
     */
    public static ResourceBundle getDefaultResourceBundle() {
        return defaultResourceBoundle;
    }

    /**
     * Returns the current locale.
     */
    public static ResourceBundle getChoosenResourceBundle() {
        return choosenResourceBundle;
    }

    /**
     * Returns the default locale.
     */
    public static Locale getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Returns the current locale.
     */
    public static Locale getChoosenLocale() {
        return choosenLocale;
    }

    /**
     * Sets the new resource and locale to be used.
     */
    /*
    public static void setChoosen(Locale loc) throws MissingResourceException {
        Locale previousLocale = choosenLocale;
        try {
            choosenLocale = loc;
            choosenResourceBundle = ResourceBundle.getBundle(JaWEConstants.RESOURCE_PATH, loc);
            //XMLUtil.setChoosenResources(choosenResourceBundle);
        } catch (Exception ex) {
            choosenLocale = previousLocale;
        }
    }
    */

}