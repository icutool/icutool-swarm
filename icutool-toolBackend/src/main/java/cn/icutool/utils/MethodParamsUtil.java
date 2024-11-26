package cn.icutool.utils;// package cn.icutool.utils;
//
// import org.springframework.core.DefaultParameterNameDiscoverer;
// import org.springframework.expression.EvaluationContext;
// import org.springframework.expression.Expression;
// import org.springframework.expression.spel.support.StandardEvaluationContext;
//
// import java.lang.reflect.Method;
// import java.util.Optional;
//
//
// public class MethodParamsUtil {
//
//     private static final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
//     public static <T> T getValueByClass(Method method, Object[] args, Class<T> clazz) {
//         String[] params = Optional.ofNullable(parameterNameDiscoverer.getParameterNames(method)).orElse(new String[]{});//解析参数名
//         EvaluationContext context = new StandardEvaluationContext();//el解析需要的上下文对象
//         for (int i = 0; i < params.length; i++) {
//             context.setVariable(params[i], args[i]);//所有参数都作为原材料扔进去
//         };
//         return params.getClass();
//     }
// }
