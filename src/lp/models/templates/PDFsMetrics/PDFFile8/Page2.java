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

package lp.models.templates.PDFsMetrics.PDFFile8;

public class Page2 extends Page {
    
    private final float[] ys = { 121.559f, 271.078f, 421.078f };
    private final float[] ws = { 26.402f, 308.520f, 306.840f };
    private final float[] hs = { 148.562f, 150.719f, 149.043f };
    private final float[] xs = { 794.398f, 412.441f, 102.719f };
    
    public Page2() {
        super((short) 3);
        
        days[0].reset(xs[0], ys[0], ws[0], hs[0]);
        days[1].reset(xs[0], ys[1], ws[0], hs[1]);
        days[2].reset(xs[0], ys[2], ws[0], hs[2]);
        
        mornsBody[0].reset(xs[1], ys[0], ws[1], hs[0]);
        mornsBody[1].reset(xs[1], ys[1], ws[1], hs[1]);
        mornsBody[2].reset(xs[1], ys[2], ws[1], hs[2]);
        
        aftsBody[0].reset(xs[2], ys[0], ws[2], hs[0]);
        aftsBody[1].reset(xs[2], ys[1], ws[2], hs[1]);
        aftsBody[2].reset(xs[2], ys[2], ws[2], hs[2]);
    }
    
}
