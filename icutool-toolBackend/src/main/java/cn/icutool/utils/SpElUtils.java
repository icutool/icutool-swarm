package cn.icutool.utils;

import javax.validation.constraints.NotNull;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Optional;

public class SpElUtils {
    private static final ExpressionParser parser = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public static String parseSpEl(Method method, Object[] args, String spEl) {
        getMethodArgs result = getGetMethodArgs(method, args, spEl);
        return result.expression.getValue(result.context, String.class);
    }

    public static <T> T parseSpEl(Method method, Object[] args, String spEl, Class<T> clazz) {
        getMethodArgs result = getGetMethodArgs(method, args, spEl);
        return result.expression.getValue(result.context,clazz);
    }

    @NotNull
    private static getMethodArgs getGetMethodArgs(Method method, Object[] args, String spEl) {
        String[] params = Optional.ofNullable(parameterNameDiscoverer.getParameterNames(method)).orElse(new String[]{});//解析参数名
        EvaluationContext context = new StandardEvaluationContext();//el解析需要的上下文对象
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);//所有参数都作为原材料扔进去
        }
        Expression expression = parser.parseExpression(spEl);
        getMethodArgs result = new getMethodArgs(context, expression);
        return result;
    }

    private static class getMethodArgs {
        public final EvaluationContext context;
        public final Expression expression;

        public getMethodArgs(EvaluationContext context, Expression expression) {
            this.context = context;
            this.expression = expression;
        }
    }


    public static String getMethodKey(Method method) {
        return method.getDeclaringClass() + "#" + method.getName();
    }
}
