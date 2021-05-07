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

public class Group {
    private ArrayList<Student> students;
    private String name, levelName;
    private int index;
    
    public Group(String name, String levelName) {
        students = new ArrayList();
        this.name = name;
        this.levelName = levelName;
        index = 0;
    }
    
    public void addStudent(Student stu){
        students.add(stu);
    }
    
    public void sort() {
        students.sort((s1, s2) -> {
            double n1, n2;
            try {
                n1 = s1.getNum();
                n2 = s2.getNum();
            }
            catch( NumberFormatException ex) {
                return 0;
            }
            return (n1 > n2) ? 1 : -1;
        });
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getInitials() {
        return this.name.substring(0, 2);
    }
    
    public String getLevelName() {
        return this.levelName;
    }
    
    public int getStudentsCount() {
        return students.size();
    }
    
    public Student getNextStudent() {
        if ( index >= students.size() ) {
            index = 0;
            return null;
        }
        return students.get(index++);
    }
    
    public Student getStudent(int index) {
        if ( index >= students.size() ) return null;
        return students.get(index);
    }
    
    public Integer getIndex() {
        try {
            return Integer.parseInt(name.split("-")[1]);
        }
        catch( NumberFormatException ex ) {
            return -1;
        }
    }
}
