package com.glanway.util;

import java.util.Arrays;

/**
 * RFC2045 Base64 and Url Safe Base64 encoder/decoder实现.
 * 
 * Url Safe Base64 替换"+/"为"_-".
 * 
 * Encoding 支持两种模式有行分隔符和没有行分隔符,当有行分隔符时,结果中每行最大76个字符.
 * 
 * Decoding 时如果输入包含多行,每行必须76个字符,行以 CRLF("\r\n")结尾.
 * 
 * modify from apache shiro.
 */
public abstract class Base64 {

	/**
	 * Chunk size per RFC 2045 section 6.8.
	 * 
	 * @see <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC2045 6.8</a>
	 */
	private static final int CHUNK_SIZE = 76;

	private static final char PADDING = '=';
	private static final String BASE64_TAB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static final char[] CHARS = BASE64_TAB.toCharArray();
	private static final char[] URL_SAFE_CHARS = BASE64_TAB.replace("+/", "_-").toCharArray();

	private static final int[] INV = new int[256];
	private static final int[] URL_SAFE_INV = new int[256];

	public static final byte[] EMPTY_BYTES = new byte[0];
	public static final char[] EMPTY_CHARS = new char[0];

	static {
		Arrays.fill(INV, -1);
		for (int i = 0, iS = CHARS.length; i < iS; i++) {
			INV[CHARS[i]] = i;
		}
		INV[PADDING] = 0;

		// url safe decode table
		Arrays.fill(URL_SAFE_INV, -1);
		for (int i = 0, iS = URL_SAFE_CHARS.length; i < iS; i++) {
			URL_SAFE_INV[URL_SAFE_CHARS[i]] = i;
		}
		URL_SAFE_INV[PADDING] = 0;
	}

	/** ------------------------------ encode ------------------------------ */

	public static String encodeToUrlSafeString(final byte[] bytes) {
		return encodeToUrlSafeString(bytes, true);
	}

	public static String encodeToUrlSafeString(final byte[] bytes, final boolean lineSeparator) {
		return String.valueOf(encodeToChars(bytes, lineSeparator, true));
	}

	public static String encodeToString(final byte[] bytes) {
		return encodeToString(bytes, false);
	}

	public static String encodeToString(final byte[] bytes, final boolean lineSeparator) {
		return new String(encodeToChars(bytes, lineSeparator));
	}

	public static char[] encodeToChars(final byte[] bytes, final boolean lineSeparator) {
		return encodeToChars(bytes, lineSeparator, false);
	}

	public static byte[] encode(final byte[] bytes) {
		return encode(bytes, false);
	}

	public static byte[] encode(final byte[] bytes, final boolean lineSeparator) {
		return encode(bytes, lineSeparator, false);
	}

	/**
	 * 编码为Char[].
	 *
	 * @param bytes
	 * @param lineSeparator
	 * @param urlSafe
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static char[] encodeToChars(final byte[] bytes, final boolean lineSeparator, final boolean urlSafe) {
		final char[] TAB = urlSafe ? URL_SAFE_CHARS : CHARS;
		int len = bytes != null ? bytes.length : 0;
		if (len == 0) {
			return EMPTY_CHARS;
		}

		int evenlen = (len / 3) * 3;
		int cnt = ((len - 1) / 3 + 1) << 2;
		int destLen = cnt + (lineSeparator ? (cnt - 1) / CHUNK_SIZE << 1 : 0);
		char[] dest = new char[destLen];

		for (int s = 0, d = 0, cc = 0; s < evenlen;) {
			int i = (bytes[s++] & 0xff) << 16 | (bytes[s++] & 0xff) << 8 | (bytes[s++] & 0xff);

			dest[d++] = TAB[(i >>> 18) & 0x3f];
			dest[d++] = TAB[(i >>> 12) & 0x3f];
			dest[d++] = TAB[(i >>> 6) & 0x3f];
			dest[d++] = TAB[i & 0x3f];

			if (lineSeparator && (++cc == 19) && (d < (destLen - 2))) {
				dest[d++] = '\r';
				dest[d++] = '\n';
				cc = 0;
			}
		}

		int left = len - evenlen; // 0 - 2.
		if (left > 0) {
			int i = ((bytes[evenlen] & 0xff) << 10) | (left == 2 ? ((bytes[len - 1] & 0xff) << 2) : 0);

			dest[destLen - 4] = TAB[i >> 12];
			dest[destLen - 3] = TAB[(i >>> 6) & 0x3f];
			dest[destLen - 2] = left == 2 ? TAB[i & 0x3f] : PADDING;
			dest[destLen - 1] = PADDING;
		}
		return dest;
	}

	/**
	 * 编码为byte[].
	 *
	 * @param bytes
	 * @param lineSep
	 * @param urlSafe
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static byte[] encode(final byte[] bytes, final boolean lineSep, final boolean urlSafe) {
		final char[] TAB = urlSafe ? URL_SAFE_CHARS : CHARS;
		int len = bytes != null ? bytes.length : 0;
		if (len == 0) {
			return EMPTY_BYTES;
		}

		int evenlen = (len / 3) * 3;
		int cnt = ((len - 1) / 3 + 1) << 2;
		int destlen = cnt + (lineSep ? (cnt - 1) / 76 << 1 : 0);
		byte[] dest = new byte[destlen];

		for (int s = 0, d = 0, cc = 0; s < evenlen;) {
			int i = (bytes[s++] & 0xff) << 16 | (bytes[s++] & 0xff) << 8 | (bytes[s++] & 0xff);

			dest[d++] = (byte) TAB[(i >>> 18) & 0x3f];
			dest[d++] = (byte) TAB[(i >>> 12) & 0x3f];
			dest[d++] = (byte) TAB[(i >>> 6) & 0x3f];
			dest[d++] = (byte) TAB[i & 0x3f];

			if (lineSep && ++cc == 19 && d < destlen - 2) {
				dest[d++] = '\r';
				dest[d++] = '\n';
				cc = 0;
			}
		}

		int left = len - evenlen;
		if (left > 0) {
			int i = ((bytes[evenlen] & 0xff) << 10) | (left == 2 ? ((bytes[len - 1] & 0xff) << 2) : 0);

			dest[destlen - 4] = (byte) TAB[i >> 12];
			dest[destlen - 3] = (byte) TAB[(i >>> 6) & 0x3f];
			dest[destlen - 2] = left == 2 ? (byte) TAB[i & 0x3f] : (byte) PADDING;
			dest[destlen - 1] = PADDING;
		}
		return dest;
	}

	/** ------------------------------ decode ------------------------------ */

	public static byte[] decode(final String base64) {
		return decode(base64, false);
	}

	public static byte[] decode(final char[] base64Chars) {
		return decode(base64Chars, false);
	}

	public static byte[] decode(final byte[] base64Bytes) {
		return decode(base64Bytes, false);
	}

	public static byte[] decode(final String base64, final boolean urlSafe) {
		return decode(base64.toCharArray(), urlSafe);
	}

	/**
	 * 解码char[].
	 *
	 * @param base64Chars
	 * @param urlSafe
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static byte[] decode(final char[] base64Chars, final boolean urlSafe) {
		int[] DECODE_TAB = urlSafe ? URL_SAFE_INV : INV;
		int length = base64Chars.length;
		if (length == 0) {
			return EMPTY_BYTES;
		}

		int sndx = 0, endx = length - 1;
		int pad = base64Chars[endx] == PADDING ? (base64Chars[endx - 1] == PADDING ? 2 : 1) : 0;
		int cnt = endx - sndx + 1;
		int sepCnt = length > CHUNK_SIZE ? (base64Chars[CHUNK_SIZE] == '\r' ? cnt / CHUNK_SIZE + 2 : 0) << 1 : 0;
		int len = ((cnt - sepCnt) * 6 >> 3) - pad;
		byte[] dest = new byte[len];

		int d = 0;
		for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
			int i = DECODE_TAB[base64Chars[sndx++]] << 18 | DECODE_TAB[base64Chars[sndx++]] << 12
					| DECODE_TAB[base64Chars[sndx++]] << 6 | DECODE_TAB[base64Chars[sndx++]];

			dest[d++] = (byte) (i >> 16);
			dest[d++] = (byte) (i >> 8);
			dest[d++] = (byte) i;

			if (sepCnt > 0 && ++cc == 19) {
				sndx += 2;
				cc = 0;
			}
		}

		if (d < len) {
			int i = 0;
			for (int j = 0; sndx <= endx - pad; j++) {
				i |= DECODE_TAB[base64Chars[sndx++]] << (18 - j * 6);
			}
			for (int r = 16; d < len; r -= 8) {
				dest[d++] = (byte) (i >> r);
			}
		}

		return dest;
	}

	/**
	 * 解码byte[].
	 *
	 * @param base64Bytes
	 * @param urlSafe
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static byte[] decode(final byte[] base64Bytes, final boolean urlSafe) {
		final int[] DECODE_TAB = urlSafe ? URL_SAFE_INV : INV;
		int length = base64Bytes.length;
		if (length == 0) {
			return EMPTY_BYTES;
		}

		int sndx = 0, endx = length - 1;
		int pad = base64Bytes[endx] == PADDING ? (base64Bytes[endx - 1] == PADDING ? 2 : 1) : 0;
		int cnt = endx - sndx + 1;
		int sepCnt = length > 76 ? (base64Bytes[76] == '\r' ? cnt / 78 : 0) << 1 : 0;
		int len = ((cnt - sepCnt) * 6 >> 3) - pad;
		byte[] dest = new byte[len];

		int d = 0;
		for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
			int i = DECODE_TAB[base64Bytes[sndx++]] << 18 | DECODE_TAB[base64Bytes[sndx++]] << 12
					| DECODE_TAB[base64Bytes[sndx++]] << 6 | DECODE_TAB[base64Bytes[sndx++]];

			dest[d++] = (byte) (i >> 16);
			dest[d++] = (byte) (i >> 8);
			dest[d++] = (byte) i;

			if (sepCnt > 0 && ++cc == 19) {
				sndx += 2;
				cc = 0;
			}
		}
		if (d < len) {
			int i = 0;
			for (int j = 0; sndx <= endx - pad; j++) {
				i |= DECODE_TAB[base64Bytes[sndx++]] << (18 - j * 6);
			}
			for (int r = 16; d < len; r -= 8) {
				dest[d++] = (byte) (i >> r);
			}
		}
		return dest;
	}
}