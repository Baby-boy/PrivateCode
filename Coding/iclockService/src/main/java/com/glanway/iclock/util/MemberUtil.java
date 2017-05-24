package com.glanway.iclock.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;



/**
 * 会员工具类
 * @author SongZhe
 *
 */
public class MemberUtil {

	public static final String mainName="YJBB";
	
	/**
	 * 生成用户推荐码
	 * @param id
	 * @return
	 */
	public static String generateRecommendedCode(Long id) {

		String _simpleCode = Long.toHexString((id * 3) + 9).toString();
//		System.out.println("***"+_simpleCode);
		String simpleCode = StringUtil.addZeroForNum(_simpleCode, 6);
		return mainName + simpleCode;
	}
	
	/**
	 * 将推荐码转成会员ID
	 * @param recommendedCode
	 * @return
	 */
	public static Long decodeRecommendedCode(String recommendedCode) {

		String _code = StringUtil.removeZeroForNum(recommendedCode.split(mainName)[1], 0);
		Long id = (Long.parseLong(_code,16)-9L)/3L;
		return id;
	}
	
	public static void main(String[] args) {
		
		System.out.println(generateRecommendedCode(12223L));
		System.out.println(decodeRecommendedCode("YJBB008f46"));
		long a=0023;
		long b=23;
		//System.out.println(Long.toHexString(36));
	}
	
	/*public static Long getCurrentMemberId() {
		Member member = (Member) RequestContextHolder.getRequestAttributes().getAttribute(
                Constants.MEMBER,
                RequestAttributes.SCOPE_SESSION
        );
        Long id = null != member ? member.getId() : null;
        return id;
    }*/
    
    /**
     * <p>名称：getCurrentMember</p> 
     * <p>描述：获取当前登录用户</p>
     * @author：ChenGuang
     * @return
     */
   /* public static Member getCurrentMember(HttpSession session) {
    	Member member = (Member) RequestContextHolder.getRequestAttributes().getAttribute(
                Constants.MEMBER,
                RequestAttributes.SCOPE_SESSION
        );
        return member;
    }*/
}
