package producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import producer.bean.User;

@Controller
@ResponseBody
public class UserController
{
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> userRedisTemplate;

    @RequestMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value)
    {
        redisTemplate.opsForValue()
                .set(key,value );
        return "OK";
    }

    @RequestMapping("/get/{key}")
    public String get(@PathVariable("key") String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/user/")
    public String addUser(User user)
    {
        userRedisTemplate.opsForValue().set(user.getId() + "", user);
        return "OK";
    }

    @GetMapping("/user/{id}")
    public Object getUser(@PathVariable("id") String id)
    {
        return userRedisTemplate.opsForValue().get(id);
    }
}
