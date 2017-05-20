package com.xinyuewulian.test;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * jerry
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2017-05-19 23:34
 */
public class TestSpel {

    public static void main(String[] args) {
        //创建解析器
        ExpressionParser parser=new SpelExpressionParser();
        //解析表达式
        Expression expression=
                parser.parseExpression("('Hello'+'World').concat('!')");
        //构造上下文
        EvaluationContext context=new StandardEvaluationContext();
        //为end参数值来赋值
        context.setVariable("end","!");
        //打印expression表达式的值
        System.out.println(expression.getValue(context));
    }
}
