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

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.erpak.jtrackplanner3d.graphics.GraphicArea;
import com.erpak.jtrackplanner3d.graphics.symbols.TrackSymbol;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * This class is used to represent a curved track symbol
 */
public class TracksTreeSelectionListener implements TreeSelectionListener {

    private GraphicArea graphicArea;
    
    /**
     * 
     * @param graphicArea
     */
    public TracksTreeSelectionListener(GraphicArea graphicArea){
        this.graphicArea = graphicArea;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
     */
    public void valueChanged(TreeSelectionEvent arg0) {
        // TODO Auto-generated method stub
        //System.out.println(arg0.getNewLeadSelectionPath().toString());
        JTree tree = (JTree)arg0.getSource();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if (node != null){
            Object nodeInfo = node.getUserObject();
            if (node.isLeaf()) {
                TrackSymbol symbol = (TrackSymbol)nodeInfo;
                //graphicArea.setSelectedSymbol(symbol);
                //System.out.println(graphicArea.printTrackElementList());
            }
        }
    }

}
