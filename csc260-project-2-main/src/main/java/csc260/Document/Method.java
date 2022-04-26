package csc260.Document;

import java.io.Serializable;
import java.util.ArrayList;

public class Method implements Serializable {
    private String name;
    private ArrayList<String> paras;
    private int numParas;

    public  Method(String name){
        this.name = name;
        this.paras = null;
        this.numParas = 0;
    }


    public  Method(String name, ArrayList<String> paras, int numParas){
        this.name = name;
        this.paras = paras;
        this.numParas = numParas;
    }

    //setters
    public void setName(String title){
        name = title;
    }
    public void addInstVar(String var){
        if(paras== null){
            paras = new ArrayList<String>();

        }
        paras.add(var);
        numParas++;
    }
    public void addInstVar(String newVar, int pos){
        if(paras== null){
            paras = new ArrayList<String>();

        }
        paras.add(pos, newVar);
        numParas++;
    }
    public void delete(String paraToDelete){
        if(paras != null && paras.contains(paraToDelete)) {
            paras.remove(paraToDelete);
            numParas--;}
        if(paras == null) {
            numParas = 0;
        }
    }
    public void delete(int paraIndexToDelete){
        if(paras != null && numParas > paraIndexToDelete) {
            paras.remove(paraIndexToDelete);
            numParas--;}
        if(paras == null) {
            numParas = 0;
        }
    }
    //getters
    public String getName(){
        return name;
    }
    public ArrayList<String> getParas(){
        return paras;
    }
    public int getNumParas(){return numParas;}


}
