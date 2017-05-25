package com.yd.dby.utils.security;

import com.yd.dby.a.sys.entity.YdSysUserJwt;
import com.yd.dby.utils.jwt.YdJwtType;

public abstract class YdZzzSecurityUtils extends YdZzzSecurityVerify {

	protected abstract void zzUtilGenerateSms(YdJwtType type, String mobile, String salt) throws Exception;

	protected abstract String zzUtilGenerateSalt() throws Exception;

	protected abstract String zzUtilGenerateToken(YdJwtType type, String salt) throws Exception;

	protected abstract String zzUtilGenerateToken(YdJwtType type, YdSysUserJwt user) throws Exception;

}