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
package com.erpak.jtrackplanner3d.utils.libraries;

import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class LibraryLoader {

    private final static String TRACK_LIB_EXTENSION = ".tracklib";
    private final static String TRACK_LIB__ALT_EXTENSION = ".xml";
    private final static String TRACK_LIB_FOLDER = "track_libraries";
    
    /** logger */
    static Logger logger = Logger.getLogger(LibraryLoader.class);
    
    /**
     * Recuperation de la liste des fichiers xml (libraries)
     * Pour chaque fichier, ouverture et recuperation des infos "nom de library, echelle, nom de fichier"
     * @return
     */
    public ArrayList<File> getLibraryList(){
    	return loadLibraries();
    }
    
    /**
     * Get xml files in libraries folder
     * soit un objet library avec nom, echelle, description ... Nécéssité d'ouvrir le fichier lib (avantage, si fichier invalide, n'apparait pas dans la liste)
     * Soit simplement le nom du fichier.
     */
    private ArrayList<File> loadLibraries(){
    	File folder = new File(TRACK_LIB_FOLDER);
    	File[] listOfFiles = folder.listFiles();
    	ArrayList<File> fileLib = new ArrayList<File>();
    	for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.getName().endsWith(TRACK_LIB_EXTENSION) || file.getName().endsWith(TRACK_LIB__ALT_EXTENSION)) {
                    fileLib.add(file);
                    logger.debug("file added : " + file.getName() + "File URI : " + file.getAbsolutePath());
    	        }
    	    }
    	}
    	return fileLib;
    }
    
}
