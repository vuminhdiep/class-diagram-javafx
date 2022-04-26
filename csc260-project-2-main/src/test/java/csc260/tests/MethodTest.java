package csc260.tests;

import csc260.Document.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class MethodTest {
    private Method m;
    private Method mTwo;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {

        ArrayList<String> paras = new ArrayList<>();
        paras.add("firstParas");
        m = new Method("TestClass");

        mTwo = new Method("TestClassTwo", paras,1);
    }

    @After
    public void tearDown() {
        m = null;
        mTwo = null;
    }

    @Test
    public void testConstruct(){
        assertEquals("The name of the method m is TestClass","TestClass",m.getName());
        assertEquals("The name of method mTwo is TestClassTwo","TestClassTwo",mTwo.getName());

        assertEquals("There is no parameters in method m",0,m.getNumParas());
        assertEquals("There is 1 parameter in method mTwo",1,mTwo.getNumParas());

        ArrayList<String> parasList = new ArrayList<>();
        parasList.add("firstParas");
        assertEquals("The list of parameters in method m is null",null,m.getParas());
        assertEquals("The list of parameters in method mTwo has one parameter",parasList,mTwo.getParas());




    }

    @Test
    public void testSetGetName(){
        assertEquals("The default name is TestClass","TestClass",m.getName());
        assertEquals("The default name is TestClassTwo","TestClassTwo",mTwo.getName());

        m.setName("Method");
        mTwo.setName("MethodTwo");

        assertEquals("New name is Method","Method",m.getName());
        assertEquals("New name is MethodTwo","MethodTwo",mTwo.getName());
    }

    @Test
    public void testAddInstVars(){
        assertEquals("The list of parameters in method m is null",null,m.getParas());

        m.addInstVar("newParas");
        ArrayList<String> parasList = new ArrayList<>();
        parasList.add("newParas");

        assertEquals("There is one parameter in method m",1,m.getNumParas());
        assertEquals("The list of parameters in method m has newParas",parasList,m.getParas());

        ArrayList<String> parasListTwo = new ArrayList<>();
        parasListTwo.add("firstParas");
        assertEquals("The list of parameters in method mTwo has one parameter",parasListTwo,mTwo.getParas());
        assertEquals("There is one parameter in method mTwo",1,mTwo.getNumParas());

        mTwo.addInstVar("parasTwo",0);
        parasListTwo.add(0,"parasTwo");

        assertEquals("The list of parameters in method mTwo has two parameters",parasListTwo,mTwo.getParas());
        assertEquals("There is two parameters in method mTwo",2,mTwo.getNumParas());
    }

    @Test
    public void testDelete(){
        m.delete("parasDel");
        assertEquals("Can't delete a parameter in method m because there is none to begin with",0,m.getNumParas());

        ArrayList<String> parasListTwo = new ArrayList<>();
        parasListTwo.add("firstParas");
        mTwo.delete("paras");
        assertEquals("Delete a non-existent parameter doesn't change the list parameters in method mTwo",parasListTwo,mTwo.getParas());
        assertEquals("Delete a non-existent parameter doesn't change the number of parameters in method mTwo",1,mTwo.getNumParas());

        mTwo.delete("firstParas");
        assertEquals("Now method mTwo has no parameters",0,mTwo.getNumParas());
        assertEquals("Now the list parameters in mTwo is empty",null,m.getParas());

        m.addInstVar("newParas");
        m.addInstVar("anotherParas",0);
        m.addInstVar("parasToDelete");

        ArrayList<String> parasList = new ArrayList<>();
        parasList.add("newParas");
        parasList.add(0,"anotherParas");
        parasList.add("parasToDelete");

        m.delete(5);
        assertEquals("The parasIndexToDelete is invalid so there is no delete in method m",3,m.getNumParas());
        assertEquals("The parasIndexToDelete is invalid so method m still has same parameters",parasList,m.getParas());

        m.delete("newParas");
        parasList.remove("newParas");
        assertEquals("There are 2 parameters in method m",2,m.getNumParas());
        assertEquals("Method m still has same parameters as parasList",parasList,m.getParas());

        m.delete(1);
        parasList.remove(1);
        assertEquals("There are 1 parameters in method m",1,m.getNumParas());
        assertEquals("Method m still has same parameters as parasList",parasList,m.getParas());



    }

}