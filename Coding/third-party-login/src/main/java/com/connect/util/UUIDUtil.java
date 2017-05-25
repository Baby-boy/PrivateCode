package com.connect.util;

import java.security.SecureRandom;

public class UUIDUtil {

	private final long mostSigBits;
	private final long leastSigBits;

	private UUIDUtil(byte[] arg0) {
		long arg1 = 0L;
		long arg3 = 0L;

		assert arg0.length == 16 : "data must be 16 bytes in length";

		int arg5;
		for (arg5 = 0; arg5 < 8; ++arg5) {
			arg1 = arg1 << 8 | (long) (arg0[arg5] & 255);
		}

		for (arg5 = 8; arg5 < 16; ++arg5) {
			arg3 = arg3 << 8 | (long) (arg0[arg5] & 255);
		}

		this.mostSigBits = arg1;
		this.leastSigBits = arg3;
	}

	public static void main(String[] args) {
		System.out.println(randomUUID().toStr());
		System.out.println(Holder.numberGenerator);
	}

	private static class Holder {
		static final SecureRandom numberGenerator = new SecureRandom();
	}

	public static UUIDUtil randomUUID() {
		SecureRandom arg = Holder.numberGenerator;
		byte[] arg0 = new byte[16];
		arg.nextBytes(arg0);
		arg0[6] = (byte) (arg0[6] & 15);
		arg0[6] = (byte) (arg0[6] | 64);
		arg0[8] = (byte) (arg0[8] & 63);
		arg0[8] = (byte) (arg0[8] | 128);
		return new UUIDUtil(arg0);
	}

	private static String digits(long arg, int arg1) {
		long arg2 = 1L << arg1 * 4;
		return Long.toHexString(arg2 | arg & arg2 - 1L).substring(1);
	}

	private String toStr() {
		return digits(this.mostSigBits >> 32, 8) + digits(this.mostSigBits >> 16, 4) + digits(this.mostSigBits, 4) + digits(this.leastSigBits >> 48, 4) + digits(this.leastSigBits, 12);
	}

	public static String generate() {
		return randomUUID().toStr();
	}

}
