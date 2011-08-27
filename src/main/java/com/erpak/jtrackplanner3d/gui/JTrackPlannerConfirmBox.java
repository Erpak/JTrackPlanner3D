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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.erpak.jtrackplanner3d.utils.software.ResourcesManager;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * Confirm dialog box displayed with an icon, a message, a OK button and a Cancel button 
  */
@SuppressWarnings("serial")
public class JTrackPlannerConfirmBox extends JDialog implements ActionListener{

    // Panels
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel insetsPanel1 = new JPanel();
    JPanel insetsPanel2 = new JPanel();
    JPanel insetsPanel3 = new JPanel();
    // Buttons
    JButton okButton = new JButton();
    JButton cancelButton = new JButton();
    // Picture
    JLabel imageLabel = new JLabel();
    // Labels
    JLabel projectNameLabel = new JLabel();
    // Layouts
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    FlowLayout flowLayout1 = new FlowLayout();
    GridLayout gridLayout1 = new GridLayout();
    // Boolean
    boolean ok = false;

    /**
     * Constructor
     * @param parent The parent frame
     * @param message The message to display in the box
     */
    public JTrackPlannerConfirmBox(Frame parent, String title, String message) {
      super(parent);
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {
        jbInit(title, message);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      pack();
    }

    /**
     * Component initialization
     * @param message The message to display in the box 
     * @throws Exception
     */
    private void jbInit(String title, String message) throws Exception  {
      imageLabel.setIcon(new ImageIcon(ResourcesManager.getResource("QuestionIcon")));
      this.setTitle(title);
      setResizable(false);
      panel1.setLayout(borderLayout1);
      panel2.setLayout(borderLayout2);
      insetsPanel1.setLayout(flowLayout1);
      insetsPanel2.setLayout(flowLayout1);
      insetsPanel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      gridLayout1.setRows(4);
      gridLayout1.setColumns(1);
      projectNameLabel.setText(message);
      insetsPanel3.setLayout(gridLayout1);
      insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
      okButton.setText("Ok");
      okButton.addActionListener(this);
      cancelButton.setText(ResourcesManager.getLanguageDependentString("CancelButton"));
      cancelButton.addActionListener(this);      
      insetsPanel2.add(imageLabel, null);
      panel2.add(insetsPanel2, BorderLayout.WEST);
      this.getContentPane().add(panel1, null);
      insetsPanel3.add(projectNameLabel, null);
      panel2.add(insetsPanel3, BorderLayout.CENTER);
      insetsPanel1.add(okButton, null);
      insetsPanel1.add(cancelButton, null);      
      panel1.add(insetsPanel1, BorderLayout.SOUTH);
      panel1.add(panel2, BorderLayout.NORTH);
    }

    /**
     * Overridden so we can exit when window is closed
     * @param e The window event
     */
    protected void processWindowEvent(WindowEvent e) {
      if (e.getID() == WindowEvent.WINDOW_CLOSING) {
        cancel();
      }
      super.processWindowEvent(e);
    }

    /**
     * Close the dialog
     */
    void cancel() {
      dispose();
    }
    
   /**
    * Close the dialog on a button event
    * @param e
    */
   public void actionPerformed(ActionEvent e) {
     if (e.getSource() == cancelButton) {
       cancel();
     }
     if (e.getSource() == okButton) {
       ok = true;  
       this.setVisible(false);
     }     
   }
   
   /**
    * Returns if the user has confirmed the action
    * @return true if the OK button is clicked, false otherwise
    */
   public boolean isValidated(){
       return ok;
   }
     
}