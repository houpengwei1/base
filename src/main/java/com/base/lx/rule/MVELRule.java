package com.base.lx.rule;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.core.BasicRule;
import org.jeasy.rules.mvel.MVELAction;
import org.mvel2.ParserContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 改造过的MVELRule
 * when方法和then方法支持lambda表达式
 */
public class MVELRule extends BasicRule {

    private Condition condition;
    private final List<Action> actions;
    private final ParserContext parserContext;

    public MVELRule() {
        this(new ParserContext());
    }

    public MVELRule(ParserContext parserContext) {
        super();
        this.condition = Condition.FALSE;
        this.actions = new ArrayList<>();
        this.parserContext = parserContext;
    }

    public MVELRule name(String name) {
        this.name = name;
        return this;
    }

    public MVELRule description(String description) {
        this.description = description;
        return this;
    }

    public MVELRule priority(int priority) {
        this.priority = priority;
        return this;
    }

    public MVELRule when(String condition) {
        this.condition = new MVELCondition(condition, this.parserContext);
        return this;
    }

    /**
     * 条件(支持lambda表达式)
     * @param condition
     * @return
     */
    public MVELRule when(Condition condition) {
        this.condition = condition;
        return this;
    }

    public MVELRule then(String action) {
        this.actions.add(new MVELAction(action, this.parserContext));
        return this;
    }

    /**
     * 执行结果(支持lambda表达式)
     * @param action
     * @return
     */
    public MVELRule then(Action action) {
        this.actions.add(action);
        return this;
    }

    public boolean evaluate(Facts facts) {
        return this.condition.evaluate(facts);
    }

    public void execute(Facts facts) throws Exception {
        for (Action action : this.actions) {
            action.execute(facts);
        }
    }
}
