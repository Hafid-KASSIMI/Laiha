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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.SimpleIntegerProperty;

public class JobsLogger {
    private String current;
    private SimpleIntegerProperty processedJobs;
    private LocalDateTime startTime, endTime;
    private boolean working, hadWorked, done;
    private int total;

    public JobsLogger() {
        reset();
    }
    
    public final void reset() {
        processedJobs = new SimpleIntegerProperty(0);
        working = false;
        hadWorked = false;
        done = false;
        current = "";
    }

    public void setCurrentJob(String current) {
        this.current = current;
    }

    public String getCurrentJob() {
        return current;
    }
    
    public float getDuration() {
        return ( ( !working && done && hadWorked ) ? ChronoUnit.MILLIS.between(startTime, endTime) / 1000f : 0 );
    }
    
    public void start(){
        if ( working )  return;
        startTime = LocalDateTime.now();
        working = true;
        hadWorked = true;
        done = false;
    }

    public boolean isWorking() {
        return working;
    }

    public boolean isDone() {
        return done;
    }
    
    public void stop(){
        if ( !working )  return;
        endTime = LocalDateTime.now();
        working = false;
        done = true;
        processedJobs.set(0);
    }
    
    public int getProcessedJobsCount() {
        return processedJobs.get();
    }
    
    public SimpleIntegerProperty getProcessedJobsProperty() {
        return processedJobs;
    }
    
    public void increment() {
        processedJobs.set( processedJobs.get() + 1 );
    }
    
    public void setTotalJobs(int total) {
        this.total = total;
    }
    
    public Double getPercentage() {
        return ( (double) processedJobs.get() ) / total;
    }
}
