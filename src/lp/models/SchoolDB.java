/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/

package lp.models;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class SchoolDB {
    private final ConcurrentHashMap<String, Level> levels;
    private String school, direction, academy, year;
    
    public SchoolDB() {
        levels = new ConcurrentHashMap();
    }
    
    public void reset() {
        levels.clear();
        school = direction = academy = year = "";
    }
    
    public ArrayList<Level> getLevels() {
        return new ArrayList(levels.values());
    }
    
    public Level getLevel(String levelName) {
        return levels.get(levelName);
    }
    
    public Level addLevel(String levelName) {
        if ( levels.containsKey(levelName) )
            return levels.get(levelName);
        Level lev = new Level(levelName);
        levels.put(levelName, lev);
        return lev;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
