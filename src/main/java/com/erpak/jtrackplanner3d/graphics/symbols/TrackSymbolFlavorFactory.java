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

import java.awt.datatransfer.DataFlavor;
import org.apache.log4j.Logger;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class TrackSymbolFlavorFactory {
    
    /** Logger */
    static Logger logger = Logger.getLogger(TrackSymbolFlavorFactory.class);      
    
    /** Creates a new instance of TrackSymbolFlavorFactory */
    public TrackSymbolFlavorFactory() {
    }
    
    public final static DataFlavor getTrackSymbolFlavor(){
        /** Custom flavor */
        DataFlavor trackSymbolFlavor;
        try{
            String MIME= DataFlavor.javaJVMLocalObjectMimeType + ";class=" + TrackSymbol.class.getName();
            trackSymbolFlavor = new DataFlavor(MIME);
        }
        catch(Exception ex){
            logger.error("Error during initialization of trackSymbolFlavor, default Dataflavor used in remplacement");
            trackSymbolFlavor = new DataFlavor();
        }        
        return trackSymbolFlavor;
    }
    
}
