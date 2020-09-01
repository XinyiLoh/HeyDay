/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

/**
 *
 * @author Xin Yi
 */
public class Category {
    
    private char code;
    private String name;
    
    public Category(){
        this.name = "";
    }
    
    public Category(char code,String name){
        code = Character.toUpperCase(code);
        this.code = code;
        
        name = name.substring(0,1).toUpperCase()+
                name.substring(1).toLowerCase();
        this.name = name;
    }
    
    public char getCode() {
        return code;
    }

    public void setCode(char code) {
        code = Character.toUpperCase(code);
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.substring(0,1).toUpperCase()+
                name.substring(1).toLowerCase();
        this.name = name;
    }
    
    public boolean equals(Object obj) {
        Category temp = (Category) obj;
        return (this.getName().equalsIgnoreCase(temp.getName()));
    }
      
    public String toString(){
        return String.format("%-8c %-10s",code,name);
    }
}
