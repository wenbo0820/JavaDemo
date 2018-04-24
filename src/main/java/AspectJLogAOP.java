import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dzt.framework.enums.LogLevel;
import com.dzt.framework.exception.business.BusinessLogicException;
import com.dzt.framework.util.DateTimeUtil;
import com.google.common.base.Optional;
import com.hazelcast.nio.serialization.Data;
import com.meidusa.fastjson.JSONObject;

@Component
@Aspect
public class AspectJLogAOP extends BasicAOP{

	
	private static Logger logger = LoggerFactory.getLogger(AspectJLogAOP.class);
	
	private static final String SEPERATOR = "||";

	private static final int BUFF_SIZE = 1024;

	/**
	 * 方法上的logMonitor代理
	 */
	@Around("@annotation(com.dzt.biz.aop.LogMonitor)")
	public Object aroundMethodLogMonitor(ProceedingJoinPoint point)
			throws Throwable, BusinessLogicException {
		
		LogMonitor tm = verifyPoint(point, LogMonitor.class);
		if (!Optional.fromNullable(tm).isPresent()) {
			return point.proceed(point.getArgs());
		}
		Object target = point.getTarget();
		boolean isClassAnnotation = target.getClass().isAnnotationPresent(LogMonitor.class);
		if (isClassAnnotation) { 
			// 如果类上面已经有了注解，则方法上不再重复代理
			return point.proceed(point.getArgs());
		}
		
		Method method = ((MethodSignature) point.getSignature())
				.getMethod();
		String className = target.getClass().getName();//类名
		Class<?> cl = Class.forName(className);
		
		String methodName = method.getName();//方法名
//		Class<?>[] parameterTypes = method.getParameterTypes();//参数类型
		Object[] args = point.getArgs();//参数值
		LogLevel logLevel = LogLevel.INFO;//自定义参数类型
		
		logLevel = method.getAnnotation(LogMonitor.class).logLevel();
		// 调用目标对象的方法并获取返回值
		Object targetMethodReturnValue = null;
		
		
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append(System.currentTimeMillis());
		logBuffer.append(className);
		logBuffer.append(".");
		logBuffer.append(methodName);
		
		int argNum = args==null?0:args.length;
		if(argNum>0){
			int num = 1;
			StringBuffer logArgBuffer = new StringBuffer();
			logArgBuffer.append(logBuffer);
			for(Object obj : args){
				if(num==1){
					logArgBuffer.append("(");
				}
				logArgBuffer.append(num);
				logArgBuffer.append(":");
				logArgBuffer.append(JSONObject.toJSONString(obj));
				if(num==argNum){
					logArgBuffer.append(")");
				}else{
					logArgBuffer.append(",");
				}
				num ++;
			}
			if(logger.isDebugEnabled() && logLevel.compareTo(LogLevel.DEBUG)==0){
				logger.debug(logArgBuffer.toString());
			}else if(logger.isInfoEnabled() && logLevel.compareTo(LogLevel.INFO)==0){
				logger.info(logArgBuffer.toString());
			}
		}
		
		try {
			targetMethodReturnValue = point.proceed(args);
		} catch(BusinessLogicException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			
		}
		
		Class<?> returnType = method.getReturnType();
		if(returnType!=null && !returnType.toString().equals("void")){
			logBuffer.append(".ReturnValue:");
			logBuffer.append(JSONObject.toJSONString(targetMethodReturnValue));
			if(logger.isDebugEnabled() && logLevel.compareTo(LogLevel.DEBUG)==0){
				logger.debug(logBuffer.toString());
			}else if(logger.isInfoEnabled() && logLevel.compareTo(LogLevel.INFO)==0){
				logger.info(logBuffer.toString());
			}
		}
		return targetMethodReturnValue;
	}

}
