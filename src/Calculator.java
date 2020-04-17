import TestClass.AfterSuite;
import TestClass.BeforeSuite;
import TestClass.Test;

import java.sql.SQLOutput;

public class Calculator {

    public Calculator() {
    }

    @Test(priority = 1)
    public int sum (int a, int b){
        System.out.println(a + " + " + b + " = " + (a+b));
        return a + b;
    }

    @Test(priority = 2)
    public int multiply (int a, int b) {
        System.out.println(a + " * " + b + " = " + (a*b));
        return a * b;
    }

    @BeforeSuite
    public void aboutCalculator (){
        System.out.println("Calculactor v.1.0. Start Testing !!!");
    }

    @AfterSuite
    public void showTestResult (String result){
        System.out.println(result);
    }
}
