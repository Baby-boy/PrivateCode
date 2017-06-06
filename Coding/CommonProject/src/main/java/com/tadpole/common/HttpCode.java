package com.tadpole.common;

/**
 * Http协议状态码.
 *
 * @author FUQIHAO
 * @version 1.0.0
 * @dateTime 2017年5月25日 下午4:03:52
 */
public enum HttpCode {

	/** 继续 */
	CONTINUE(100, "Continue"),

	/** 转换协议 */
	SWITCHING_PROTOCOLS(101, "Switching Protocols"),

	/** 处理 */
	PROCESSING(102, "Processing"),

	/** 检查站 */
	CHECKPOINT(103, "Checkpoint"),

	/** 成功 */
	OK(200, "OK"),

	/** 已创建 */
	CREATED(201, "Created"),

	/** 已接受 */
	ACCEPTED(202, "Accepted"),

	/** 非授权信息 */
	NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),

	/** 无内容 */
	NO_CONTENT(204, "No Content"),

	/** 重置内容 */
	RESET_CONTENT(205, "Reset Content"),

	/** 部分内容 */
	PARTIAL_CONTENT(206, "Partial Content"),

	/** 多状态 */
	MULTI_STATUS(207, "Multi-Status"),

	/** 已报道 */
	ALREADY_REPORTED(208, "Already Reported"),

	/** 自己用 */
	IM_USED(226, "IM Used"),

	/** 多种选择 */
	MULTIPLE_CHOICES(300, "Multiple Choices"),

	/** 永久移动 */
	MOVED_PERMANENTLY(301, "Moved Permanently"),

	/** 临时移动 */
	FOUND(302, "Found"),

	/** 查看其他位置 */
	SEE_OTHER(303, "See Other"),

	/** 未修改 */
	NOT_MODIFIED(304, "Not Modified"),

	/** 临时重定向 */
	TEMPORARY_REDIRECT(307, "Temporary Redirect"),

	/** 永久重定向 */
	PERMANENT_REDIRECT(308, "Permanent Redirect"),

	/** 错误请求 */
	BAD_REQUEST(400, "Bad Request"),

	/** 未授权 */
	UNAUTHORIZED(401, "Unauthorized"),

	/** 支付要求 */
	PAYMENT_REQUIRED(402, "Payment Required"),

	/** 禁止 */
	FORBIDDEN(403, "Forbidden"),

	/** 未找到 */
	NOT_FOUND(404, "Not Found"),

	/** 方法禁用） */
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

	/** 不接受 */
	NOT_ACCEPTABLE(406, "Not Acceptable"),

	/** 需要代理授权 */
	PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),

	/** 请求超时 */
	REQUEST_TIMEOUT(408, "Request Timeout"),

	/** 冲突 */
	CONFLICT(409, "Conflict"),

	/** 已删除 */
	GONE(410, "Gone"),

	/** 需要有效长度 */
	LENGTH_REQUIRED(411, "Length Required"),

	/** 未满足前提条件 */
	PRECONDITION_FAILED(412, "Precondition Failed"),

	/** 请求实体过大 */
	PAYLOAD_TOO_LARGE(413, "Payload Too Large"),

	/** 请求的 URI 过长 */
	URI_TOO_LONG(414, "URI Too Long"),

	/** 不支持的媒体类型 */
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

	/** 请求范围不符合要求 */
	REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),

	/** 未满足期望值 */
	EXPECTATION_FAILED(417, "Expectation Failed"),

	/** 我是茶壶 */
	I_AM_A_TEAPOT(418, "I'm a teapot"),

	/** 无法处理的实体 */
	UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

	/** 失败的依赖 */
	LOCKED(423, "Locked"), FAILED_DEPENDENCY(424, "Failed Dependency"),

	/** 升级所需 */
	UPGRADE_REQUIRED(426, "Upgrade Required"),

	/** 前提要求 */
	PRECONDITION_REQUIRED(428, "Precondition Required"),

	/** 太多的请求 */
	TOO_MANY_REQUESTS(429, "Too Many Requests"),

	/** 请求头字段太大 */
	REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),

	/** 服务器内部错误 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

	/** 尚未实施 */
	NOT_IMPLEMENTED(501, "Not Implemented"),

	/** 错误网关 */
	BAD_GATEWAY(502, "Bad Gateway"),

	/** 服务不可用 */
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),

	/** 网关超时 */
	GATEWAY_TIMEOUT(504, "Gateway Timeout"),

	/** HTTP 版本不受支持 */
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),

	/** 转换进行的谈判 */
	VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),

	/** 存储空间不足 */
	INSUFFICIENT_STORAGE(507, "Insufficient Storage"),

	/** 环路检测 */
	LOOP_DETECTED(508, "Loop Detected"),

	/** 没有扩展 */
	BANDWIDTH_LIMIT_EXCEEDED(509, "Not Extended"),

	/** 网络认证要求 */
	NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

	private final int value;

	private final String reasonPhrase;

	private HttpCode(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public boolean is1xxInformational() {
		return (Series.INFORMATIONAL.equals(series()));
	}

	public boolean is2xxSuccessful() {
		return (Series.SUCCESSFUL.equals(series()));
	}

	public boolean is3xxRedirection() {
		return (Series.REDIRECTION.equals(series()));
	}

	public boolean is4xxClientError() {
		return (Series.CLIENT_ERROR.equals(series()));
	}

	public boolean is5xxServerError() {
		return (Series.SERVER_ERROR.equals(series()));
	}

	public Series series() {
		return Series.valueOf(this);
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	public static HttpCode valueOf(int statusCode) {
		for (HttpCode status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}

	public static enum Series {

		INFORMATIONAL(1), SUCCESSFUL(2), REDIRECTION(3), CLIENT_ERROR(4), SERVER_ERROR(5);

		private final int value;

		private Series(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}

		public static Series valueOf(int status) {
			int seriesCode = status / 100;
			for (Series series : values()) {
				if (series.value == seriesCode) {
					return series;
				}
			}
			throw new IllegalArgumentException("No matching constant for [" + status + "]");
		}

		public static Series valueOf(HttpCode status) {
			return valueOf(status.value);
		}

	}
}
