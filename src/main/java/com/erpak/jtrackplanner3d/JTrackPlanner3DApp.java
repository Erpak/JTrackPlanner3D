/**
* Copyright © 2011, Erpak 
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

package com.erpak.jtrackplanner3d;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.UIManager;
import org.apache.log4j.Logger;
import com.erpak.jtrackplanner3d.gui.JTrackPlannerMainFrame;
import com.erpak.jtrackplanner3d.gui.JTrackPlannerSplashScreen;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class JTrackPlanner3DApp {
   
   /** */
   boolean packFrame = false;
   /** */
   static Logger logger = Logger.getLogger(JTrackPlanner3DApp.class);
   
   /**
    * Main method
    * @param args Command line arguments
    */
   public static void main(String[] args) {
       try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       } catch(Exception e) {
           e.printStackTrace();
       }
       JTrackPlannerSplashScreen s = new JTrackPlannerSplashScreen(1250);
       new JTrackPlanner3DApp();
       s.dispose(1250);
   }
   
   /**
    * Construct the application
    */
   public JTrackPlanner3DApp() {
       logger.info("JTrackPlanner3D Application starting !");
       JTrackPlannerMainFrame frame = new JTrackPlannerMainFrame();
       //Validate frames that have preset sizes
       //Pack frames that have useful preferred size info, e.g. from their layout
       if (packFrame) {
           frame.pack();
       } else {
           frame.validate();
       }
       //Center the window
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Dimension frameSize = frame.getSize();
       if (frameSize.height > screenSize.height) {
           frameSize.height = screenSize.height;
       }
       if (frameSize.width > screenSize.width) {
           frameSize.width = screenSize.width;
       }
       frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
       frame.setVisible(true);
   }
   
}