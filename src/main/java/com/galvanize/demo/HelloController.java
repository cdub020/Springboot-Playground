package com.galvanize.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/math")
public class HelloController {

    @GetMapping("/pi")
    public double helloWorld() {
        return Math.PI;
    }

    @GetMapping("/calculate")
    public String calculate(MathService mathService) {
        return mathService.returnCalc();
    }

    @PostMapping("/sum")
    public String sumparams(@RequestParam MultiValueMap<String,String> stringmap) {
        List<String> newstr = (List<String>) stringmap.get("n");
        int sum = 0;
        String returnstr = "";
        for (int x=0; x<newstr.size(); x++){
            sum += Integer.parseInt(newstr.get(x));
            returnstr += newstr.get(x);
            if (newstr.size() - 1 != x){
                returnstr += " + ";
            }
        }
        return (returnstr + " = " + sum);
    }

    @GetMapping("/volume/{length}/{width}/{height}")
    public String getIndividualParams(@PathVariable Map<String, String> allparams) {
        int length = Integer.parseInt(allparams.get("length"));
        int width = Integer.parseInt(allparams.get("width"));
        int height = Integer.parseInt(allparams.get("height"));
        int quotient = length * width * height;
        return "The volume of a " + allparams.get("length") + "x" + allparams.get("width") +
                "x" + allparams.get("height") + " rectangle is " + quotient;
    }

    @PostMapping("/area")
    public String getCircleArea(@RequestBody Map<String, String> circleparams) throws Exception{
        String returnstr;
        DecimalFormat df = new DecimalFormat("#.00000");
        try{
            switch(circleparams.get("type")) {
                case "circle" : if (circleparams.containsKey("radius")){
                                    returnstr = ("Area of a circle with a radius of " + circleparams.get("radius") +
                                            " is " + df.format(Math.PI * Math.pow(Double.parseDouble(circleparams.get("radius")), 2)));
                                }
                                else {
                                    returnstr = "Invalid";
                                }
                                break;
                case "rectangle" : if (circleparams.containsKey("width") && circleparams.containsKey("height")) {
                    returnstr = ("Area of a " + circleparams.get("width") + "x" + circleparams.get("height") +
                            " rectangle is " + Integer.parseInt(circleparams.get("width")) * Integer.parseInt(circleparams.get("height")));
                                }
                                else {
                                    returnstr = "Invalid";
                                }
                                break;
                default :   returnstr = "Invalid";
                }
            }
            catch (Exception e){
                throw new Exception("No type found!");
            }
            return returnstr;
        }
}


