package com.galvanize.demo;

public class MathService {
    private String operation;
    private String x;
    private String y;
    private String finalstr = "";

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String returnCalc () {
        if (this.operation == null){
            this.operation = "add";
        }
        switch(this.operation) {
            case "add": this.finalstr = ((this.x + " + " + this.y + " = " + (Integer.parseInt(this.x) + Integer.parseInt(this.y))));
                break;
            case "multiply": this.finalstr = ((this.x + " * " + this.y + " = " + (Integer.parseInt(this.x) * Integer.parseInt(this.y))));
                break;
            case "subtract": this.finalstr = ((this.x + " - " + this.y + " = " + (Integer.parseInt(this.x) - Integer.parseInt(this.y))));
                break;
            case "divide": this.finalstr = ((this.x + " / " + this.y + " = " + (Integer.parseInt(this.x) / Integer.parseInt(this.y))));
                break;
            default :   this.finalstr = "Hello World";
                        break;
        }
        return this.finalstr;
    }
}
