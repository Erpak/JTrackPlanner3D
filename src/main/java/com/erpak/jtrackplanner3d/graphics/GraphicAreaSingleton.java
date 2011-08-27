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

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class GraphicAreaSingleton {

    private static GraphicAreaSingleton graphicAreaSingletonInstance;	
    
    private GraphicArea graphicArea;

    /** 
     * Recupere l'instance unique de la class Singleton.<p>
     */
    public static GraphicAreaSingleton getInstance() {
        if (null == graphicAreaSingletonInstance) { // Premier appel
            graphicAreaSingletonInstance = new GraphicAreaSingleton();
        }
        return graphicAreaSingletonInstance;
    }

    /** 
     * Constructeur est rendu inaccessible
     */
    private GraphicAreaSingleton() {}

    /**
     * 
     * @param graphicArea 
     */
    public void setGraphicArea(GraphicArea graphicArea){
        this.graphicArea = graphicArea;
    }

    
    /**
     * Comment
     */
    public void deleteSelectedElements(){
        if(graphicArea != null){
            graphicArea.deleteSelectedElements();
        }
    }
    
    /**
     * Comment
     */
    public void moveSelectedElements(){
        if(graphicArea != null){
            graphicArea.moveSelectedElements();
        }        
    }

    /**
     * Comment
     */
    public void rotateSelectedElements(){
        if(graphicArea != null){
            graphicArea.rotateSelectedElements();
        }        
    }
    
}
