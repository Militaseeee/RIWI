package com.cpfire.Crud_ResponseEntity.controller;

import com.cpfire.Crud_ResponseEntity.database.Database;
import com.cpfire.Crud_ResponseEntity.inputs.LoginInputDto;
import com.cpfire.Crud_ResponseEntity.inputs.UserInputDto;
import com.cpfire.Crud_ResponseEntity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public ResponseEntity<ArrayList<User>> getAll(){
        return new ResponseEntity<>(Database.users,HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginInputDto loginDto){
        for (User userSelected : Database.users){
            if (userSelected.getEmail().equals(loginDto.getEmail())){
                System.out.println("Email Encontrado");

                if (userSelected.getPassword().equals(loginDto.getPassword())){
                    return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody UserInputDto userInputDto){
        User newUser = new User();

        newUser.setName(userInputDto.getName());
        newUser.setEmail(userInputDto.getEmail());
        newUser.setPassword(userInputDto.getPassword());

        Database.users.add(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
