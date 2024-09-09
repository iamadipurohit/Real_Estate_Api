package com.Project.Api.Controller;

import com.Project.Api.Entity.User;
import com.Project.Api.Repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CrudController {


    ItemRepository itemrepo;

    @Autowired
    public CrudController(ItemRepository itemrepo) {
        this.itemrepo = itemrepo;
    }

    @RequestMapping("/")
    private List<User> bhejde()
    {
        User temp=new User("adi@8649","iamadi","aditya");
        itemrepo.insert(temp);
        List<User> list=itemrepo.findAll();
        for(User u:list)
        {
            System.out.println(u);
        }
        return list;
    }
}
