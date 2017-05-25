package com.yd.dby.app.config.aop;
//package com.yd.dby.app.basic;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.request.ServletWebRequest;
//
//import com.yd.dby.app.common.HttpCode;
//import com.yd.dby.app.common.JsonResult;
//
///**
// * 说明: AOP切面
// * 
// * @Author: lgl(lgl@zwzyd.com)
// * @date:2017年2月13日 上午9:45:27
// * @Version:1.0
// */
//@Aspect
//@Configuration
//public class TestAop {
//
//	// 前置通知
//	@Before(value = "execution(* com.yd.dby.app.controller..*(..))")
//	public void before(JoinPoint joinPoint) throws Exception {
//
//		// System.out.println("获取连接点方法运行时的入参列表:" + joinPoint.getArgs());
//		// for (int i = 0; i < joinPoint.getArgs().length; i++) {
//		// System.out.println("" + joinPoint.getArgs()[i]);
//		// }
//		//
//		// System.out.println("获取连接点的方法签名对象:" + joinPoint.getSignature());
//		// System.out.println("获取连接点所在的目标对象:" + joinPoint.getTarget());
//		// System.out.println("获取代理对象本身:" + joinPoint.getThis());
//
//		System.out.println("@Before：模拟权限检查...");
//
//		// System.out.println("@Before：目标方法为：" +
//		// joinPoint.getSignature().getDeclaringTypeName() + "."
//		// + joinPoint.getSignature().getName());
//		//
//		// System.out.println("@Before：参数为：" +
//		// Arrays.toString(joinPoint.getArgs()));
//		//
//		// System.out.println("@Before：被织入的目标对象为：" + joinPoint.getTarget());
//
//		if (!joinPoint.getArgs()[0].equals("lgl")) {
//			JsonResult jsonResult = new JsonResult();
//			jsonResult.setStatusCode(HttpCode.NON_AUTHORITATIVE_INFORMATION);
//			jsonResult.setMsg("非授权信息");
//
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//					.getRequest();
//			HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes())
//					.getResponse();
//			System.out.println("url:" + request.getRequestURI());
//			HttpSession session = request.getSession();
//			response.sendRedirect(request.getContextPath());
//
//		}
//
//		System.out.println("------------------------------------------");
//
//	}
//
//}