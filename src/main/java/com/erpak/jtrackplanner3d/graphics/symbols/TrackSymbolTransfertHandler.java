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

import com.erpak.jtrackplanner3d.graphics.GraphicArea;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.log4j.Logger;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class TrackSymbolTransfertHandler extends TransferHandler {

    /** Logger */
    static Logger logger = Logger.getLogger(TrackSymbolTransfertHandler.class);     
    
    /** Creates a new instance of TrackSymbolTransfertHandler */
    public TrackSymbolTransfertHandler() {
    }
    
    /**
     * Cree un objet transferable adapte, donc un PersonneTransferable.
     * Recoit comme parametre la source du drag and drop
     * (ce qui permet de partager des PersonneTransferHandler)
     */
    protected Transferable createTransferable(JComponent component) {
        logger.debug("Export from : " + component.getClass().getName());
        TrackSymbol trackSymbol = null;
        JTree trackSystemTree = (JTree) component;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)trackSystemTree.getLastSelectedPathComponent();
        if (node != null){
            Object nodeInfo = node.getUserObject();
            if (node.isLeaf()) {
                trackSymbol = (TrackSymbol)nodeInfo;
                logger.debug("trackSymbol : " + trackSymbol);
            }
        }
        return new TrackSymbolTransferable(trackSymbol);                
    }
    
    /**
     * Permet de dire si le drag est possible. Dans notre cas, il l'est toujours.
     * cette methode peut renvoyer COPY, NONE, MOVE, COPY_OR_MOVE.
     * Si NONE est renvoye, le drag and drop sera impossible.
     */
    public int getSourceActions(JComponent c) {
        return COPY;
    }
    
    /**
     * Teste si les donnees proposee comportent une PersonneFlavor.
     * On pourrait aussi accepter des donnees de mode texte, par exemple.
     */
    public boolean canImport(JComponent component, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            if (TrackSymbolFlavorFactory.getTrackSymbolFlavor().equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * L'importation de donnees proprement dite.
     * @param component : la cible du transfert.
     * @param transferable : donnees a transferer
     */
    public boolean importData(JComponent component, Transferable transferable) {
        if (canImport(component, transferable.getTransferDataFlavors())) {
            try {
            	// On extrait l'objet TrackSymbol du transferable
                TrackSymbol trackSymbol = (TrackSymbol)transferable.getTransferData(TrackSymbolFlavorFactory.getTrackSymbolFlavor());
                // On le range dans la cible.
                logger.debug("Import to : " + component.getClass().getName());
                GraphicArea gArea = (GraphicArea)component;
                gArea.addTrackElement(trackSymbol, (int)(gArea.getMousePosition().getX()), (int)(gArea.getMousePosition().getY()));
                return true;
            } catch (UnsupportedFlavorException ufe) {
                ufe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return false;
    }    
    
}
