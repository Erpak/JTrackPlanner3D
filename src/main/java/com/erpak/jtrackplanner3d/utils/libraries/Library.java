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
package com.erpak.jtrackplanner3d.utils.libraries;

import java.net.URL;

/**
* <p>Title: JTrackPlanner3D</p>
* <p>Description: Track Planning Software</p>
* <p>Copyright: Copyright © 2011</p>
* @author Erpak
*/
public class Library {

	private String manufacturer;
	private String scale;
	private String designation;
	private String comment;
	private URL url;
	
	public Library(String manufacturer, String scale, String designation, String comment, URL url){
		this.manufacturer = manufacturer;
		this.scale = scale;
		this.designation = designation;
		this.comment = comment;
		this.url = url;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getScale() {
		return scale;
	}

	public String getDesignation() {
		return designation;
	}

	public String getComment() {
		return comment;
	}

	public URL getUrl() {
		return url;
	}
	
}

