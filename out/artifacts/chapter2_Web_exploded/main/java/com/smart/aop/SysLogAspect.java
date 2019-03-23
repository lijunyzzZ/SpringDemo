package com.smart.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
@Aspect
public class SysLogAspect {
    private static final Logger logger = Logger.getLogger(SysLogAspect.class);

    /**
     * 定义Pointcut，Pointcut的名称，此方法不能有返回值，该方法只是一个标示
     */
    @Pointcut("@annotation(com.smart.aop.OperationLogger)")
    public void controllerAspect() {
        System.out.println("我是一个切入点");
    }

    /**
     * 前置通知（Before advice） ：在某连接点（JoinPoint）之前执行的通知，但这个通知不能阻止连接点前的执行。
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("=====SysLogAspect前置通知开始=====");
        //handleLog(joinPoint, null);
    }

    /**
     * 后通知（After advice） ：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("=====SysLogAspect后置通知开始=====");
        //handleLog(joinPoint, null);
    }

    /**
     * 抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        System.out.println("=====SysLogAspect异常通知开始=====");
        //handleLog(joinPoint, e);
    }

    /**
     * 环绕通知（Around advice） ：包围一个连接点的通知，类似Web中Servlet规范中的Filter的doFilter方法。可以在方法的调用前后完成自定义的行为，也可以选择不执行。
     *
     * @param joinPoint
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====SysLogAspect 环绕通知开始=====");
        //handleLog(joinPoint, null);
        Object obj = joinPoint.proceed();
        System.out.println("=====SysLogAspect 环绕通知结束=====");
        return obj;
    }

    /**
     * 日志处理
     *
     * @param joinPoint
     * @param e
     */
    private void handleLog(JoinPoint joinPoint, Exception e) {
        try {
            //获得注解
            OperationLogger logger = giveController(joinPoint);
            if (logger == null) {
                return;
            }

            String signature = joinPoint.getSignature().toString(); // 获取目标方法签名
            String methodName = signature.substring(signature.lastIndexOf(".") + 1,
                    signature.indexOf("("));

            String longTemp = joinPoint.getStaticPart().toLongString();
            String classType = joinPoint.getTarget().getClass().getName();

            Class<?> clazz = Class.forName(classType);

            Method[] methods = clazz.getDeclaredMethods();
            System.out.println("methodName: " + methodName);

            for (Method method : methods) {
                if (method.isAnnotationPresent(OperationLogger.class)
                        && method.getName().equals(methodName)) {
                    //OpLogger logger = method.getAnnotation(OpLogger.class);
                    String clazzName = clazz.getName();
                    System.out.println("clazzName: " + clazzName + ", methodName: "
                            + methodName);
                }
            }

        } catch (Exception exp) {
            logger.error("异常信息:{}", exp);
            exp.printStackTrace();
        }
    }

    /**
     * 获得注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static OperationLogger giveController(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(OperationLogger.class);
        }
        return null;
    }

}
