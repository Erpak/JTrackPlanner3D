/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erpak.jtrackplanner3d.graphics;

/**
 * Object to store informations about the layout
 * @author jguillemotte
 */
public class Layout {
    
    // Maybe it is not necessary to define a layout form ...
    // The user starts to put tracks, then we can imagine a feature that draw a layout
    // following the externalm tracks with a little margin => eg : 50 mm
    
    // Define Layout form
    // Simple rectangle, L form, U form, modular layout, round of room ....
    /*public enum LayoutType {
        Rectangle, L_Form, U_Form
    }*/
    
    // For simple rectangle layout : 2 dimensions on ly => width, length
    private long width;
    private long length;
    
}
