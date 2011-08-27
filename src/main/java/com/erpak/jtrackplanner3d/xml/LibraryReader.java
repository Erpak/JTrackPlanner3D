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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.erpak.jtrackplanner3d.graphics.TrackSystem;
import com.erpak.jtrackplanner3d.graphics.symbols.CurvedTrackSymbol;
import com.erpak.jtrackplanner3d.graphics.symbols.StraightTrackSymbol;
import com.erpak.jtrackplanner3d.graphics.symbols.StraightTurnoutSymbol;
import com.erpak.jtrackplanner3d.graphics.symbols.SymetricThreeWayTurnoutSymbol;
import com.erpak.jtrackplanner3d.utils.software.ResourcesManager;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
/**
 * LibraryReader : Reads informations contained in a library file 
 */
public class LibraryReader {

    /** logger */
    static Logger logger = Logger.getLogger(LibraryReader.class);
    /** Tree */
    private JTree tree;
    
    /**
     * create a new LibraryReader object with the library file name
     * @param fileName Name of the library file
     */
    public LibraryReader(String fileName){

        TrackSystem trackSystem = null;
        TracksTreeCellRenderer renderer =  new TracksTreeCellRenderer();
        try {
            renderer.setStraigthTrackIcon(new ImageIcon(ResourcesManager.getResource("StraigthTrackIcon")));
            renderer.setCurvedTrackIcon(new ImageIcon(ResourcesManager.getResource("CurvedTrackIcon")));
            renderer.setStraightTurnoutTrackIcon(new ImageIcon(ResourcesManager.getResource("StraigthTurnoutTrackIcon")));
            renderer.setCrossingTrackIcon(new ImageIcon(ResourcesManager.getResource("CrossingTrackIcon")));
        }
        catch(Exception ex){
            logger.error("Unable to load tree node icons");
            logger.error(ex.toString());
        }
        
        try {
            Element trackSystemInfoNode;
            Element element;
            Element subElement;
            Attribute attribute;
            Iterator iter;
            DefaultMutableTreeNode treeTop;
            DefaultMutableTreeNode treeCategory;
            DefaultMutableTreeNode treeElement;  
            
            // Load the xml library file
            InputStream in = new FileInputStream(fileName); 
            EntityResolver resolver = new EntityResolver(){
                public InputSource resolveEntity(String publicId, String systemId) {
                    logger.debug("Public id : " + publicId);
                    logger.debug("System Id : " + systemId);
                    if ( publicId.equals( "-//TrackPlanner//DTD track_system V 1.0//EN" ) ) {
                        InputStream in = getClass().getResourceAsStream("ressources/track_system.dtd");
                        return new InputSource( in );
                    }
                    return null;
                }
            };
            SAXReader reader = new SAXReader();
            reader.setEntityResolver(resolver);
            // Read the xml library file
            Document document = reader.read(in);
            Element root = document.getRootElement();
            logger.debug("Root name : " + root.getName());
            
            // Get "track_system_info" node
            trackSystemInfoNode = root.element("track_system_infos");
            // Get track_system_info_node attributes 
            trackSystem = new TrackSystem(trackSystemInfoNode.attribute("name").getValue(), trackSystemInfoNode.attribute("version").getValue());
            trackSystem.setScale(trackSystemInfoNode.attribute("scale").getValue());
            trackSystem.setManufacturer(trackSystemInfoNode.attribute("manufacturer").getValue());
            // Get the "track_system_caracteristics" node
            element = trackSystemInfoNode.element ("track_system_caracteristics");
            trackSystem.setBallastWidth(Float.parseFloat(element.attribute("ballast_width").getValue()));
            trackSystem.setTrackWidth(Float.parseFloat(element.attribute("railway_width").getValue()));
            // Get the "track_system_colors" node
            element = trackSystemInfoNode.element ("track_system_colors");
            for ( iter = element.elementIterator(); iter.hasNext(); ) {
                subElement = (Element) iter.next();
                trackSystem.addColor(subElement.attribute("name").getValue(), subElement.attribute("r").getValue(), subElement.attribute("g").getValue(), subElement.attribute("b").getValue());
            }
            
            // Set the top node of tree with tracksystem
            treeTop = new DefaultMutableTreeNode(trackSystem.getName());            
            
            // iterate "straight_tracks" node
            element = root.element("straight_tracks");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());
            for ( iter = element.elementIterator("straight_track"); iter.hasNext(); ) {
                subElement = (Element) iter.next();
                logger.debug("Adding reference : " + subElement.attribute("reference").getValue());
                treeElement = new DefaultMutableTreeNode(
                    new StraightTrackSymbol(
                        trackSystem,
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(), 
                        Float.parseFloat(subElement.attribute("length").getValue()),
                        subElement.attribute("color").getValue()
                    )
                );
                treeCategory.add(treeElement);                
            }

            // iterate "curved_tracks" node
            element = root.element("curved_tracks");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());            
            for ( iter = element.elementIterator("curved_track"); iter.hasNext(); ) {
                subElement = (Element) iter.next();
                logger.debug("Adding reference : " + subElement.attribute("reference").getValue());
                treeElement = new DefaultMutableTreeNode(
                    new CurvedTrackSymbol(
                        trackSystem, 
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(), 
                        Float.parseFloat(subElement.attribute("radius").getValue()),
                        Float.parseFloat(subElement.attribute("angle").getValue()),
                        subElement.attribute("color").getValue()
                    )
                );
                treeCategory.add(treeElement);                   
            }

            // iterate "straight_turnouts" node
            element = root.element("straight_turnouts");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());             
            for ( iter = element.elementIterator("straight_turnout"); iter.hasNext(); ) {
                subElement = (Element) iter.next();
                logger.debug("Adding reference : " + subElement.attribute("reference").getValue());
                treeElement = new DefaultMutableTreeNode(
                    new StraightTurnoutSymbol(
                        trackSystem,
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(),
                        Float.parseFloat(subElement.attribute("length").getValue()),
                        Float.parseFloat(subElement.attribute("radius").getValue()),
                        Float.parseFloat(subElement.attribute("angle").getValue()),                       
                        subElement.attribute("direction").getValue(),
                        subElement.attribute("color").getValue()
                    )
                );
                treeCategory.add(treeElement);
            }
            
            // iterate through "curved_turnouts" node
            element = root.element("curved_turnouts");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());            
            for ( Iterator i = root.elementIterator( "curved_turnouts" ); i.hasNext(); ) {
                element = (Element) i.next();
                logger.debug("Node : " + element.getName());   
            }
            
            // iterate through "three_way_turnouts" node
            element = root.element("three_way_turnouts");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());            
            for ( iter = element.elementIterator("symetric_three_way_turnout"); iter.hasNext(); ) {
                subElement = (Element) iter.next();
                logger.debug("Adding reference : " + subElement.attribute("reference").getValue());
                treeElement = new DefaultMutableTreeNode(
                    new SymetricThreeWayTurnoutSymbol(
                        trackSystem,
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(), 
                        subElement.attribute("reference").getValue(),
                        Float.parseFloat(subElement.attribute("length").getValue()),
                        Float.parseFloat(subElement.attribute("radius").getValue()),
                        Float.parseFloat(subElement.attribute("angle").getValue()),                       
                        subElement.attribute("color").getValue()
                    )
                );
                treeCategory.add(treeElement);
            }            
            
            // iterate through "crossings" node
            element = root.element("crossings");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());              
            for ( Iterator i = root.elementIterator( "crossings" ); i.hasNext(); ) {
                element = (Element) i.next();
                logger.debug("Node : " + element.getName());   
            }
            
            // iterate through "double_slip_switchs" node
            element = root.element("double_slip_switchs");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());             
            for ( Iterator i = root.elementIterator( "double_slip_switchs" ); i.hasNext(); ) {
                element = (Element) i.next();
                logger.debug("Node : " + element.getName());   
            }            

            // iterate through "special_tracks" node
            element = root.element("special_tracks");
            treeCategory = new DefaultMutableTreeNode(element.getName());
            treeTop.add(treeCategory);
            logger.debug("Node : " + element.getName());            
            for ( Iterator i = root.elementIterator( "special_tracks" ); i.hasNext(); ) {
                element = (Element) i.next();
                logger.debug("Node : " + element.getName());   
            }            
            
            // fill the tree
            tree = new JTree(treeTop); 
        }
        catch(Exception ex){
            logger.error("Unable to load track trackSystem library");
            logger.error(ex.toString());
            tree = new JTree();
        }
     
        logger.debug("Parsing done for trackSystem : " + trackSystem);
        tree.setName("Track systems");
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setCellRenderer(renderer);        
    }

    /**
     * Returns a JTree filled with the informations contained in the library file
     * @return A JTree object
     */
    public JTree getTree(){
        return tree;
    }
    
}
