package com.base.lx;

import com.base.lx.rule.MVELRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.HashMap;
import java.util.Map;

public class RuleController {

    public static void main(String[] args) {

        //创建规则
        Rules rules = new Rules();
        //如果规则满足条件 返回cs = success
        Rule ageRule = new MVELRule()
                // 规则表达式 如果年龄等于18 那就在map里面塞cs = success
                .when("name == '侯朋威' && age % 10 == 0 ")
                .then(res -> {
                    Map<String, Object> params = res.get("PARAM");
                    params.put("cs", "success");
                });
        rules.register(ageRule);

        // 条件
        HashMap<String,String> all = new HashMap<>();
        all.put("name","侯朋威");
        all.put("age","20");

        // 参数比对
        Facts facts = new Facts();
        facts.put("PARAM", all);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

        System.out.println(facts.asMap());

    }

}
