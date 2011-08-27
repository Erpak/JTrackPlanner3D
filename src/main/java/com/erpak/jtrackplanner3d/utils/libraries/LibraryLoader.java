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
import java.util.Vector;
import org.apache.log4j.Logger;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class LibraryLoader {

    /** logger */
    static Logger logger = Logger.getLogger(LibraryLoader.class);
    
    /**
     * Recuperation de la liste des fichiers xml (libraries)
     * Pour chaque fichier, ouverture et recuperation des infos "nom de library, echelle, nom de fichier"
     * @return
     */
    public Vector<String> getLibraryList(){
    	//Vector<Library> libList = new Vector<Library>();
    	Vector<String> libList = new Vector<String>();
    	//libList.add(new Library());
    	libList.add("HO - Marklin Voie C");
    	//libList.add(new Library());
    	libList.add("HO - Roco Rocoline");
    	//libList.add(new Library());
    	libList.add("HO - Trix Voie C");
    	//libList.add(new Library());
    	libList.add("N - Trix");
    	//return libList;
    	return loadLibraries();
    }
    
    /**
     * Get xml files in libraries folder
     * soit un objet library avec nom, echelle, description ... Nécéssité d'ouvrir le fichier lib (avantage, si fichier invalide, n'apparait pas dans la liste)
     * Soit simplement le nom du fichier.
     * 
     */
    private Vector<String> loadLibraries(){
        // Directory path here
    	String path = "track_libraries"; 
    	String file;
    	File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();
    	Vector<String> fileLib = new Vector<String>();
    	for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
    	    file = listOfFiles[i].getName();
                if (file.endsWith(".tracklib")) {
                    fileLib.add(file); 
    	        }
    	    }
    	}
    	return fileLib;
    }
    
}
