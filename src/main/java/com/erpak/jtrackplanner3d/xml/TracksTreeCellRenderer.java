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
package com.erpak.jtrackplanner3d.xml;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.erpak.jtrackplanner3d.graphics.symbols.CurvedTrackSymbol;
import com.erpak.jtrackplanner3d.graphics.symbols.StraightTrackSymbol;
import com.erpak.jtrackplanner3d.graphics.symbols.StraightTurnoutSymbol;
import com.erpak.jtrackplanner3d.graphics.symbols.TrackSymbol;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * This class is used to represent a node of the track system tree
 */
public class TracksTreeCellRenderer extends DefaultTreeCellRenderer {
    
    /* icons */
    private ImageIcon straigthTrackIcon;
    private ImageIcon curvedTrackIcon;
    private ImageIcon straightTurnoutTrackIcon;
    private ImageIcon crossingTrackIcon;
    
    /**
     * Constructor
     */
    public TracksTreeCellRenderer(){
        straigthTrackIcon = null;
        curvedTrackIcon = null;
        straightTurnoutTrackIcon = null;
        crossingTrackIcon = null;
    }
    
    /**
     * Set icon for straight track element
     * @param icon 
     */
    public void setStraigthTrackIcon(ImageIcon icon){
        this.straigthTrackIcon = icon;
    }
    
    /**
     * Set icon for curved track element
     * @param icon 
     */
    public void setCurvedTrackIcon(ImageIcon icon){
        this.curvedTrackIcon = icon;
    }    
    
    /**
     * Set icon for straight turnout track element
     * @param icon 
     */
    public void setStraightTurnoutTrackIcon(ImageIcon icon){
        this.straightTurnoutTrackIcon = icon;
    }
    
    /**
     * Set icon for crossing track element
     * @param icon 
     */
    public void setCrossingTrackIcon(ImageIcon icon){
        this.crossingTrackIcon = icon;
    }    
    
    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus){
        TrackSymbol symbol;
        super.getTreeCellRendererComponent( tree, value, sel, expanded, leaf, row, hasFocus );
        // Set tooltip text
        if(isStraightTrackSymbol(value)){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            symbol = (TrackSymbol)node.getUserObject();
            this.setToolTipText(symbol.getComments());
        }
        // Set node icon
        if(isStraightTrackSymbol(value)){
            this.setIcon(straigthTrackIcon);
        }
        else if(isCurvedTrackSymbol(value)){
            this.setIcon(curvedTrackIcon);
        }
        else if(isStraightTurnoutSymbol(value)){
            this.setIcon(straightTurnoutTrackIcon);
        }        
        return this;
    }

    /**
     * 
     * @param value  
     * @return 
     */
    protected boolean isTrackSymbol(Object value){
        //TODO secure the cast
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        if( node.getUserObject() instanceof TrackSymbol ){        
            return true;
        }
        return false;        
    }
    
    /**
     * 
     * @param value 
     * @return 
     */
    protected boolean isStraightTrackSymbol(Object value) {
        //TODO secure the cast
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        if( node.getUserObject() instanceof StraightTrackSymbol ){        
            return true;
        }
        return false;
    } 
    
    /**
     * 
     * @param value 
     * @return
     */
    protected boolean isCurvedTrackSymbol(Object value) {
        //TODO secure the cast
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        if( node.getUserObject() instanceof CurvedTrackSymbol ){        
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param value 
     * @return
     */
    protected boolean isStraightTurnoutSymbol(Object value) {
        //TODO secure the cast
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        if( node.getUserObject() instanceof StraightTurnoutSymbol ){        
            return true;
        }
        return false;
    }    

}