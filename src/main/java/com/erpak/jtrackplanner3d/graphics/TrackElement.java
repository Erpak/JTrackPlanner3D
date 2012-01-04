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

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.JComponent;
import com.erpak.jtrackplanner3d.graphics.symbols.TrackSymbol;
import com.erpak.jtrackplanner3d.utils.software.IdManager;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * TrackElement : a track element 
 */
public class TrackElement extends JComponent{

    /** Track symbol */
    private TrackSymbol symbol;
    /** X coordinate */
    private int xCoord;
    /** Y coordinate */
    private int yCoord;
    /** Selected */
    private boolean selected;
    /** Unique id */
    private int id;
    
    /**
     * 
     * @param symbol 
     * @param x 
     * @param y 
     */
    public TrackElement(TrackSymbol symbol, int x, int y) {
        this.symbol = symbol;
        this.xCoord = x;
        this.yCoord = y;
        this.selected = false;
        this.setBounds(x, y, x+symbol.getGeneralPath().getBounds().width, y+symbol.getGeneralPath().getBounds().width);
        this.setPreferredSize(symbol.getGeneralPath().getBounds().getSize());
        this.setMinimumSize(symbol.getGeneralPath().getBounds().getSize());
        this.setMaximumSize(symbol.getGeneralPath().getBounds().getSize());
        this.addMouseListener(new TrackElementMouseListener(this));
        this.id = IdManager.getInstance().getNextId();
    }
    
    /**
     * 
     * @param x 
     */
    public void setXCoord(int x){
        this.xCoord = x;
    }
    
    /**
     * 
     * @param y 
     */
    public void setYCoord(int y){
        this.yCoord = y;
    }
    
    /**
     * 
     * @param x 
     * @param y 
     */
    public void setXYCoord(int x, int y){
        setXCoord(x);
        setYCoord(y);
    }
    
    /**
     * 
     * @param selected 
     */
    public void setSelected(boolean selected){
        this.selected = selected;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isSelected(){
        return selected;
    }
    
    /**
     * 
     * @param x 
     * @param y 
     * @return <code>true</code> if element contains the point, <code>false</code> otherwise.
     */
    public boolean contains(int x, int y){
        return symbol.getGeneralPath().contains(x, y);
    }
        
    /**
     * returns a String representation of the element
     * @return <code>String</code>
     */
    public String toString(){
        StringBuffer buffer = new StringBuffer("Element [");
        buffer.append(this.symbol.getDesignation());
        buffer.append("], id=");
        buffer.append(this.id);
        buffer.append(", x=");
        buffer.append(this.xCoord);
        buffer.append(", y=");
        buffer.append(this.yCoord);
        return buffer.toString();
    }
    
    /**
     * Returns the element unique id
     * @return <code>int</code> value
     */
    public int getId(){
        return this.id;
    }
    
    /**
    *
    * @param g
    */
    public final void paintComponent(Graphics g){
        super.paintComponent(g); // Pas sur que ca soit utile ??
        GeneralPath gp = this.symbol.getGeneralPath();
        float dash1[] = {10.0f};
        BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);       
        BasicStroke stroke = new BasicStroke(2.0f);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Dessin du symbol
        if(selected){
            g2d.setComposite(makeComposite(0.5f));            
        }
        else{
            g2d.setComposite(makeComposite(1.0f));            
        }
        g2d.setColor(this.symbol.getColor());
        g2d.fill(gp);
        if(selected){
            g2d.setStroke(dashed);
        }
        else{
            g2d.setStroke(stroke);
        }
        g2d.setComposite(makeComposite(1.0f)); 
        g2d.setColor(Color.BLACK);
        g2d.draw(gp);
        // Dessin de la reference
        g2d.drawString(symbol.getReference(),symbol.getGeneralPath().getBounds().width/2, symbol.getGeneralPath().getBounds().height/2);
    }
    
    /**
     * 
     * @param alpha 
     * @return 
     */
    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }    

}
