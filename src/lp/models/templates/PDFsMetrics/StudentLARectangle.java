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

package lp.models.templates.PDFsMetrics;

public class StudentLARectangle {
    private LARectangle row, numero, additional, code, firstName, secondName, gender, birthDate, birthPlace;

    public StudentLARectangle(LARectangle row, LARectangle numero, LARectangle code, LARectangle firstName, LARectangle secondName, LARectangle gender, LARectangle birthDate, LARectangle birthPlace) {
        this.row = row;
        this.numero = numero;
        this.code = code;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public StudentLARectangle(LARectangle row, LARectangle numero, LARectangle code, LARectangle firstName) {
        this.row = row;
        this.numero = numero;
        this.code = code;
        this.firstName = firstName;
    }

    public StudentLARectangle(LARectangle row, LARectangle numero, LARectangle code, LARectangle firstName, LARectangle secondName) {
        this.row = row;
        this.numero = numero;
        this.code = code;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public StudentLARectangle() {
    }

    public LARectangle getRow() {
        return row;
    }

    public void setRow(LARectangle row) {
        this.row = row;
    }

    public void setRow(float y, float h) {
        this.row = new LARectangle();
        row.setYH(y, h);
    }

    public LARectangle getNumero() {
        return numero;
    }

    public void setNumero(LARectangle numero) {
        this.numero = numero;
    }

    public LARectangle getAdditional() {
        return additional;
    }

    public void setAdditional(LARectangle additional) {
        this.additional = additional;
    }

    public void setNumero(float x, float w) {
        numero = new LARectangle();
        numero.setXW(x, w);
    }

    public void setNumero(float x, float w, int fontSize, String font) {
        numero = new LARectangle();
        numero.setXW(x, w);
        numero.setFormat(fontSize, font);
    }

    public LARectangle getCode() {
        return code;
    }

    public void setCode(LARectangle code) {
        this.code = code;
    }

    public void setCode(float x, float w) {
        code = new LARectangle();
        code.setXW(x, w);
    }

    public void setCode(float x, float w, int fontSize, String font) {
        code = new LARectangle();
        code.setXW(x, w);
        code.setFormat(fontSize, font);
    }

    public LARectangle getFirstName() {
        return firstName;
    }

    public void setFirstName(LARectangle firstName) {
        this.firstName = firstName;
    }

    public void setFirstName(float x, float w) {
        firstName = new LARectangle();
        firstName.setXW(x, w);
    }

    public void setFirstName(float x, float w, int fontSize, String font) {
        firstName = new LARectangle();
        firstName.setXW(x, w);
        firstName.setFormat(fontSize, font);
    }

    public LARectangle getSecondName() {
        return secondName;
    }

    public void setSecondName(LARectangle secondName) {
        this.secondName = secondName;
    }

    public void setSecondName(float x, float w) {
        secondName = new LARectangle();
        secondName.setXW(x, w);
    }

    public void setSecondName(float x, float w, int fontSize, String font) {
        secondName = new LARectangle();
        secondName.setXW(x, w);
        secondName.setFormat(fontSize, font);
    }

    public LARectangle getGender() {
        return gender;
    }

    public void setGender(LARectangle gender) {
        this.gender = gender;
    }

    public void setGender(float x, float w) {
        gender = new LARectangle();
        gender.setXW(x, w);
    }

    public void setGender(float x, float w, int fontSize, String font) {
        gender = new LARectangle();
        gender.setXW(x, w);
        gender.setFormat(fontSize, font);
    }

    public LARectangle getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LARectangle birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthDate(float x, float w) {
        birthDate = new LARectangle();
        birthDate.setXW(x, w);
    }

    public void setBirthDate(float x, float w, int fontSize, String font) {
        birthDate = new LARectangle();
        birthDate.setXW(x, w);
        birthDate.setFormat(fontSize, font);
    }

    public LARectangle getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(LARectangle birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setBirthPlace(float x, float w) {
        birthPlace = new LARectangle();
        birthPlace.setXW(x, w);
    }

    public void setBirthPlace(float x, float w, int fontSize, String font) {
        birthPlace = new LARectangle();
        birthPlace.setXW(x, w);
        birthPlace.setFormat(fontSize, font);
    }
    
}
