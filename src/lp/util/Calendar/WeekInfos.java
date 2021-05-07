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

package lp.util.Calendar;

import java.util.Calendar;

public class WeekInfos {
    private int firstDay;
    
    public WeekInfos(int firstDay) {
        this.firstDay = firstDay + 1;
    }

    public WeekInfos() {
    }

    public int getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(int firstDay) {
        this.firstDay = firstDay;
    }
    
    public DateRange getWeek(int factor) {
        DateRange w = new DateRange();
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_WEEK);
        int diff = today - firstDay;
        if ( diff < 0 )
            diff += 7;
        c.add(Calendar.DAY_OF_MONTH, ( factor * 7 ) - diff);
        w.setBeginning(String.format("%02d/%02d/%d", c.get(Calendar.DAY_OF_MONTH), (c.get(Calendar.MONTH) + 1), c.get(Calendar.YEAR)));
        c.add(Calendar.DAY_OF_MONTH, 5);
        w.setEnd(String.format("%02d/%02d/%d", c.get(Calendar.DAY_OF_MONTH), (c.get(Calendar.MONTH) + 1), c.get(Calendar.YEAR)));
        return w;
    }
    
    public DateRange getWeekHalf(short wh, int factor) {
        DateRange w = new DateRange();
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_WEEK);
        int diff = today - firstDay;
        if ( diff < 0 )
            diff += 7;
        c.add(Calendar.DAY_OF_MONTH, ( factor * 7 ) - diff + ( (wh == WEEK.FIRST_HALF) ? 0 : 3 ));
        w.setBeginning(String.format("%02d/%02d/%d", c.get(Calendar.DAY_OF_MONTH), (c.get(Calendar.MONTH) + 1), c.get(Calendar.YEAR)));
        c.add(Calendar.DAY_OF_MONTH, 2);
        w.setEnd(String.format("%02d/%02d/%d", c.get(Calendar.DAY_OF_MONTH), (c.get(Calendar.MONTH) + 1), c.get(Calendar.YEAR)));
        return w;
    }
    
    public DateRange getCurrentWeek() {
        return getWeek(0);
    }
    
    public DateRange getNextWeek() {
        return getWeek(1);
    }
}