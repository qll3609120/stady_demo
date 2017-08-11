package com.example.demo;

import com.example.demo.resource.JerseyController;
import com.example.demo.spi.Search;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Cache;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by evan.qi on 6/7/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ MockServletContext.class,DemoApplication.class})
public class D2mo extends Test{

    @Autowired
    private RedisTemplate redisTemplate;

    private MockMvc mock;


    @org.junit.Test
    public  void main() {

        ServiceLoader<Search> searches = ServiceLoader.load(Search.class);
        Iterator iterable = searches.iterator();
        for(Search s: searches){
            s.search();
        }
        if(iterable.hasNext()){
            System.out.println("true");
            Search search = (Search)iterable.next();
            search.search();
        }
        System.out.println("end");

        cache();

        /*String key = (String)redisTemplate.randomKey();
		redisTemplate.opsForValue().set("jedis_key","jedis_value");
        System.out.println(key);
        redisTemplate.opsForList().leftPush("list","2323");
        String value = (String)redisTemplate.opsForValue().get("aaa");
        System.out.println(value);*/
    }

    public void sub(){

    }
    @Autowired
    public CacheManager cacheManager;

    @org.junit.Test
    public void cache(){
        Cache cache = cacheManager.getCache("demo");
        for(int i=0;i<11;i++){
            if(i==1||i==2){
                Element el = cache.get("data"+0);
                System.out.println(el.getHitCount());
            }
            cache.put(new Element("data"+i,i));
        }
        cache.getKeys().stream().forEach(el-> System.out.println(el));
        cache.getKeys().stream().forEach(el-> System.out.println(cache.get(el)));

    }

    @Before
    public void before(){
        mock = MockMvcBuilders.standaloneSetup(new JerseyController()).build();
    }

    @org.junit.Test
    public void mockTest() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/reset/prj/list"))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("aa")));
    }
}
