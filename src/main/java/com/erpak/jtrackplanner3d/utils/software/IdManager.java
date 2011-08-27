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

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class IdManager {
    
    /** Unique id */
    private int id;
    /** IdManager */
    private static IdManager IdManagerInstance;	
    
    /** 
     * get the instance of IdManager.<p>
     */
    public static IdManager getInstance() {
        if (null == IdManagerInstance) {
            IdManagerInstance = new IdManager();
        }
        return IdManagerInstance;
    }    
    
    /** Creates a new instance of IdManager */
    private IdManager() {
        id = 0;
    }
    
    /**
     * Returns the next unique id
     * @return <code>int</code> value for the unique number
     */
    public int getNextId(){
        id++;
        return id;
    }
    
}
