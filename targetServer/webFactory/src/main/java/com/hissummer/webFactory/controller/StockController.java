package com.hissummer.webFactory.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hissummer.webFactory.model.Stock;

@RestController
@RequestMapping("/stock")
public class StockController {
	
    @RequestMapping(method=RequestMethod.GET )  
    public String defaultRequest()
    {  
        return"Hello!";  
    }  
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET )  
    public String get(@PathVariable("id") String id){  
        return"Hello!"+id;  
    }  
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE )  
    public String delete(@PathVariable("id") String id){  
        return"delete!"+id;  
    }  	
    
    @RequestMapping(value="/{id}",method=RequestMethod.POST )  
    public String post(@PathVariable("id") String id, @RequestBody  Stock stock){  
    	
        return"Hello! "+id + " " + stock.stockId;  
    }  		

    @RequestMapping(value="/{id}",method=RequestMethod.PUT )  
    public String put(@PathVariable("id") String id, @RequestBody  Stock stock){  
        return"Hello!";  
    } 
    
}
