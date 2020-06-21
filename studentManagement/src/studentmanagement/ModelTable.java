/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import javafx.beans.property.StringProperty;

/**
 *
 * @author hussein
 */
public class ModelTable {
    public String  id,name,fn,gn,mn,birth,ad,not,pn,price;
    public StringProperty ss;

    public ModelTable(String id ,String name, String fn, String gn, String mn,String birth,  String ad, String not,String pn,  String price) {
        this.id = id;
        this.name = name;
        this.fn = fn;
        this.gn = gn;
        this.mn = mn;
        this.birth = birth;
        this.ad = ad;
        this.not = not;
        this.pn = pn;
        this.price = price;
    }
    public ModelTable(){
        
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFn() {
        return fn;
    }

    public String getGn() {
        return gn;
    }

    public String getMn() {
        return mn;
    }

    public String getBirth() {
        return birth;
    }

    public String getAd() {
        return ad;
    }

    public String getNot() {
        return not;
    }

    public String getPn() {
        return pn;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    

    
    
    
}
