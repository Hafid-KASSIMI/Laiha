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

package lp.util;

public final class Misc {
    
    public static String getLevelAbrev(String groupName) {
        return groupName.substring(0, groupName.lastIndexOf("-"));
    }
    
    public static Boolean isArabic(String text) {
        return text.matches(".*[أبجدهـوزحطيكلمنسعفصقرشتثخذضظغ].*");
    }
    
    public static String cleanReversedAR(String text) {
        return text.replace("(", "%OP%").replace("[", "%OB%").replace("{", "%OC%").replace(")", "(").replace("]", "[").replace("}", "{").replace("%OP%", ")").replace("%OB%", "]").replace("%OC%", "}");
    }
    
    public static String removeShakl(String arText) {
        return arText.replaceAll("[ًٌٍَُِّْ]", "");
    }    
}
