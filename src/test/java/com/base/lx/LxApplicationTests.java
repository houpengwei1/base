package com.base.lx;

import com.base.lx.demo.IoCContainer;
import com.base.lx.demo.car.Audi;
import com.base.lx.demo.car.Beki;
import com.base.lx.demo.person.Fuyuhcuang;
import com.base.lx.demo.person.Houpengwei;
import com.base.lx.demo.person.Person;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class LxApplicationTests {

    private IoCContainer ioCContainer = new IoCContainer();

    @Test
    void contextLoads() {
        ioCContainer.setBean(Audi.class, "audi");
        ioCContainer.setBean(Beki.class, "beki");
        ioCContainer.setBean(Houpengwei.class, "houpengwei", "audi");
        ioCContainer.setBean(Fuyuhcuang.class, "fuyuchuang", "beki");
        Person houpengwei = (Person) ioCContainer.getBean("houpengwei");
        houpengwei.home();
        Person fuyuchuang = (Person) ioCContainer.getBean("fuyuchuang");
        fuyuchuang.home();
    }

}
