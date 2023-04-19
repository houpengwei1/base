package com.base.lx.cs;

import java.util.HashMap;
import java.util.Map;

public class TestControlle {

    private static Map<String, Object> strategyMap = new HashMap<>();

    public static void main(String[] args) {

        strategyMap.put("test1",new TestService());
        strategyMap.put("test2",new TestService2());

        TestAbstract testService = (TestAbstract) strategyMap.get("test2");
        testService.test(1,2);
    }

}
