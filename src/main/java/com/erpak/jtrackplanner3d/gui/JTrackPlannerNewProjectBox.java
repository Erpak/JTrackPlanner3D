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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.erpak.jtrackplanner3d.utils.libraries.LibraryLoader;
import com.erpak.jtrackplanner3d.utils.software.ResourcesManager;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
@SuppressWarnings("serial")
public class JTrackPlannerNewProjectBox extends JDialog implements ActionListener {

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
    JLabel projectAuthorLabel = new JLabel();
    JLabel projectLibraryLabel = new JLabel();
    // TextFields
    JTextField projectNameTextField = new JTextField(20);
    JTextField projectAuthorTextField = new JTextField(20);
    // ComboBox
    JComboBox projectLibrary;
    // Layouts
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    FlowLayout flowLayout1 = new FlowLayout();
    GridLayout gridLayout1 = new GridLayout();
    // Boolean
    boolean ok = false;
    // Library loader
    LibraryLoader libLoader;

    /**
     *
     * @param parent
     */
    public JTrackPlannerNewProjectBox(Frame parent) {
      super(parent);
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {
        jbInit();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      pack();
    }

    /**
     * Component initialization
     * @throws Exception
     */
    private void jbInit() throws Exception  {
      
      imageLabel.setIcon(new ImageIcon(ResourcesManager.getResource("NewProjectIcon")));
      this.setTitle(ResourcesManager.getLanguageDependentString("NewProjectFrameTitle"));
      setResizable(false);
      panel1.setLayout(borderLayout1);
      panel2.setLayout(borderLayout2);
      insetsPanel1.setLayout(flowLayout1);
      insetsPanel2.setLayout(flowLayout1);
      insetsPanel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      gridLayout1.setRows(4);
      gridLayout1.setColumns(1);
      projectNameLabel.setText(ResourcesManager.getLanguageDependentString("NewProjectNameLabel") + " (*)");
      projectAuthorLabel.setText(ResourcesManager.getLanguageDependentString("NewProjectAuthorLabel"));
      projectLibraryLabel.setText(ResourcesManager.getLanguageDependentString("NewProjectLibraryLabel") + " (*)");
      insetsPanel3.setLayout(gridLayout1);
      insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
      // Buttons
      okButton.setText("Ok");
      okButton.addActionListener(this);
      cancelButton.setText(ResourcesManager.getLanguageDependentString("CancelButton"));
      cancelButton.addActionListener(this);
      libLoader = new LibraryLoader();
      projectLibrary = new JComboBox(libLoader.getLibraryList());
      projectLibrary.setEditable(false);
      projectLibrary.setSelectedIndex(0);
      projectLibrary.addActionListener(this);
      // Image
      insetsPanel2.add(imageLabel, null);
      panel2.add(insetsPanel2, BorderLayout.WEST);
      this.getContentPane().add(panel1, null);
      insetsPanel3.add(projectNameLabel, null);
      insetsPanel3.add(projectNameTextField, null);
      insetsPanel3.add(projectAuthorLabel, null);
      insetsPanel3.add(projectAuthorTextField, null);
      insetsPanel3.add(projectLibraryLabel, null);
      insetsPanel3.add(projectLibrary, null);
      panel2.add(insetsPanel3, BorderLayout.CENTER);
      insetsPanel1.add(okButton, null);
      insetsPanel1.add(cancelButton, null);      
      panel1.add(insetsPanel1, BorderLayout.SOUTH);
      panel1.add(panel2, BorderLayout.NORTH);
 
    }

    /**
     * Overridden so we can exit when window is closed
     * @param e
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
       if(checkInfoFields()){
         ok = true;  
         this.setVisible(false);
         // TODO finish to implement the file chooser
         // Get the selected track lib and load it for display
       }
     }
     /*
     if (e.getSource() == libraryChooserButton) {
        JFileChooser chooser = new JFileChooser();
        // Note: source for ExampleFileFilter can be found in FileChooserDemo,
        // under the demo/jfc directory in the Java 2 SDK, Standard Edition.
        //LibraryFileFilter filter = new LibraryFileFilter();
        //filter.addExtension("jpg");
        //filter.addExtension("gif");
        //filter.setDescription("JPG & GIF Images");
        chooser.setFileFilter(new LibraryFileFilter());
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
           projectLibraryTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
      }
      */ 
   }
   
   /**
    * 
    * @return
    */
   private boolean checkInfoFields(){
       boolean checkResult = true;
       if(projectNameTextField.getText() == null || "".equals(projectNameTextField.getText())){
           checkResult = false;
       }
       return checkResult;
   }
   
   /**
    * 
    * @return 
    */
   public boolean isValidated(){
       return ok;
   }
   
   /**
    * Returns the project name
    * @return The project name
    */
   public String getProjectName(){
       return projectNameTextField.getText();
   }

   /**
    * Returns the project author
    * @return The project author
    */
   public String getAuthorName(){
       return projectAuthorTextField.getText();
   }
   
   /**
    * Returns the project library
    * @return The project library
    */
   public String getLibraryName(){
	   //return projectLibraryTextField.getText();
	   return projectLibrary.getSelectedItem().toString();
   }   
   
}
