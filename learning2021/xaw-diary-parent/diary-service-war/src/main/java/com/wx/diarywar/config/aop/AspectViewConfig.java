package com.wx.diarywar.config.aop;

import com.alibaba.fastjson.JSON;
import com.wx.common.log.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class AspectViewConfig {
    private Logger commonLogger ;
    @Pointcut("execution(public * com.wx.diarywar.controller..*.*(..))")
    public void controllerScope(){}
    @Before("controllerScope()")
    public void beforeActionScope(JoinPoint joinPoint){
        commonLogger = LogUtils.getCommonLogger(joinPoint.getSignature().getDeclaringTypeName() + "_" + joinPoint.getSignature().getName()+"():   ");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        commonLogger.info("Request URL = " + request.getRequestURL().toString());
        commonLogger.info("Request Information : "+Arrays.toString(joinPoint.getArgs()));
    }

    @After("controllerScope()")
    public void logAfter(JoinPoint joinPoint) {
        commonLogger.info("EXECUTE FINISHED... ");
    }
    @AfterReturning(pointcut = "controllerScope()" ,returning = "res")
    public void afterReturning(Object res){
        commonLogger.info("Response Information : "+ JSON.toJSONString(res));
    }

    @AfterThrowing(pointcut = "controllerScope()" ,throwing = "ex")
    public void afterThrowing(Throwable ex){
        commonLogger.info("Requeste Throwing : "+ JSON.toJSONString(ex));
    }

}
