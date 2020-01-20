package consumer.controller;

import consumer.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@ResponseBody
public class MovieController
{

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/user/{id}")
    public User findById(@PathVariable String id)
    {
        User forObject = restTemplate.getForObject("http://localhost:9000/user/" + id, User.class);
        return forObject;
    }
}
