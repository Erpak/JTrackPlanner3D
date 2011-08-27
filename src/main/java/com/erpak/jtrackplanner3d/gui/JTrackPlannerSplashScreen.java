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
package com.erpak.jtrackplanner3d.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.erpak.jtrackplanner3d.utils.software.ResourcesManager;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * A splash screen to show while the main program is loading. A typical use
 * is:
 * <pre>
 *
 *   public static void main(String[] args) {
 *     JTrackPlannerSplashScreen s = new JTrackPlannerSplashScreen(delay1);
 *     new MainProgram();
 *     s.dispose(delay2);
 *   }  
 *
 * </pre>
 * The first line creates a Splash that will appear until another frame
 * hides it (MainProgram), but at least during "delay1" milliseconds.<br>
 * To distroy the Splash you can either call "s.dispose()" or
 * "s.dispose(delay2)", that will actually show the Splash for "delay2"
 * milliseconds and only then hide it.<br>
 * The picture to show must be specified in the resource file as "SplashScreen".
 */ 
@SuppressWarnings("serial")
public class JTrackPlannerSplashScreen extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1917252226958167381L;


	/**
	 * Creates a Splash that will appear until another frame hides it, but at
	 * least during "delay" milliseconds.
	 * @param delay the delay in milliseconds
	 */
	public JTrackPlannerSplashScreen(int delay) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new SplashPicture());
		p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		getContentPane().add(p);
		setSize(250, 250);
		setLocationRelativeTo(null);
		setVisible(true);
		try {
			Thread.sleep(delay);
		}
		catch (Exception e) {}
	}

	/**
	 * Shows the Splash during the specified time (in milliseconds) and then
	 * hides it.
	 * @param delay the delay in milliseconds
	 */
	public void dispose(int delay) {
		dispose();
		JTrackPlannerSplashScreen s = new JTrackPlannerSplashScreen(delay);
		s.dispose();
	}


	/**
	 * This class loads and shows a picture, that can be either in the same 
	 * jar file than the program or not. If the picture is smaller than the 
	 * available space, it will be centered. If the picture is bigger than
	 * the available space, a zoom will be applied in order to fit exactly 
	 * the space.
	 */ 
	class SplashPicture extends JPanel {
		Image img;      

		public SplashPicture() {
			img = new ImageIcon(ResourcesManager.getResource("SplashScreen")).getImage();
			repaint(); 
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (img == null) return;
			int w = img.getWidth(this);  
			int h = img.getHeight(this);
			boolean zoom = (w > getWidth() || h > getHeight());
			if (zoom) g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			else g.drawImage(img, (getWidth()-w)/2, (getHeight()-h)/2, this);
		}
	}
}