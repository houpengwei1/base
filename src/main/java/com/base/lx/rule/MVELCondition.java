package com.base.lx.rule;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import java.io.Serializable;
import java.util.Map;

/**
 * 改造过的MVELCondition
 */
public class MVELCondition implements Condition {

    private final Serializable compiledExpression;

    public MVELCondition(String expression) {
        this.compiledExpression = MVEL.compileExpression(expression);
    }

    public MVELCondition(String expression, ParserContext parserContext) {
        this.compiledExpression = MVEL.compileExpression(expression, parserContext);
    }

    /**
     * 与MVELCondition不同之处在于此方法
     * @param facts
     * @return
     */
    public boolean evaluate(Facts facts) {
        //为了减少表达式的层级,此处先将facts中的MAP对象取出来,再进行表达式匹配
        Map<String, Object> params = facts.get("PARAM");
        return (Boolean) MVEL.executeExpression(this.compiledExpression, params);
    }
}
