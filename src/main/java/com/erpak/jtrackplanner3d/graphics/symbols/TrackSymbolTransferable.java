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
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class TrackSymbolTransferable implements Transferable {
    
    /** The tracksymbol to transfert */
    private TrackSymbol trackSymbol;
    /** Logger */
    static Logger logger = Logger.getLogger(TrackSymbolTransferable.class);    
    
    /**
     * 
     * @param trackSymbol 
     */
    public TrackSymbolTransferable(TrackSymbol trackSymbol){
        this.trackSymbol = trackSymbol;
    }

    /**
     * 
     * @return 
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor[] result= new DataFlavor[2]; 
        result[0]= TrackSymbolFlavorFactory.getTrackSymbolFlavor(); 
        result[1]= DataFlavor.stringFlavor; 
        return result; 
    } 
    
    /**
     * 
     * @param flavor
     * @return 
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) { 
        return TrackSymbolFlavorFactory.getTrackSymbolFlavor().equals(flavor) || DataFlavor.stringFlavor.equals(flavor); 
    } 
    
    /**
     * 
     * @param flavor
     * @return
     * @throws UnsupportedFlavorException
     * @throws IOException 
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (TrackSymbolFlavorFactory.getTrackSymbolFlavor().equals(flavor)) 
            return trackSymbol;
        else if (DataFlavor.stringFlavor.equals(flavor)) {
            return trackSymbol.getReference();
        } else throw new UnsupportedFlavorException(flavor); 
    }

}