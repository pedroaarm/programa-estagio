package com.aiko.apiolhovivo.controller.v1;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aiko.apiolhovivo.entities.Client;
import com.aiko.apiolhovivo.service.ClientService;

@RestController
//@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private ClientService customerService;
    
   

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public Client getUserDetail(@PathVariable Long id){
        return customerService.findById(id);
    }
    

}
