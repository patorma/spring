package com.patriciocontreras.springboot.backend.apirest.auth;

public class JwtConfig {
	//static se puede acceder desde la clase y no del objeto y 
	//final no se puede cambiar el valor
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEoQIBAAKCAQEAsHF+Fsn2cUP/yoWJsbmaCg7Kf0nl+4d2Fy4YkjTM2aGqH/OU\r\n"
			+ "QHOg18f4meoVWTf8GcATWSnHPxGLcMHBLQCA2P4XzG9y/oqcUSfJUWy8uEfP23mJ\r\n"
			+ "0GIk9H4HhmSdiJg5GUEoreWHQ9nIV4vR/aF6ePjY5jnC8Qe4r+b7UXjMUEy953zA\r\n"
			+ "X11z7G/peQ/7M0eMHpoCdBpwa/a/zM1hkV/wrbFONnJXBpP1ecwnGW1Wjpza67GE\r\n"
			+ "VMXB40FvU2GC8sgmBWGiuv0YNc5b6OA7s12AmUXlkwyPksoLlLW4XsQzcFPqRKOQ\r\n"
			+ "JctXe3VHgMDl25nKfGm4Wkk5l6mXUiFLmn1PXwIDAQABAoIBAFac78NqEtLZCXi2\r\n"
			+ "Ari+nnIvfpHribzmaz7HvwIObzyqEmQq4uyclik2nb+YXDB2vgc59YWAb7FzAFsy\r\n"
			+ "kYxwwc9DNBWD5BQ1oLRp7nZNBXsErXFUgSVM+E0YjMMZIwfJicXu6XQrxzG0d5ca\r\n"
			+ "KNPzb7ZwmbCyTBKbsrqOjXIZ9Vm48Uh/oRonrL3TZ7HHof6ahxLHJINHBup3gHm8\r\n"
			+ "n5W3SdZPj5+s6tD2NGjlmG64zlGf69oPatevW6Hftlpl7SfcrJsAzC7q/b5sZtDD\r\n"
			+ "Jw/sHTnqTNpQb04kF6T6SwuMotoPYUaA26dXRZ0Zqps+eZ2wzFssEyhiiqu2UMxi\r\n"
			+ "vaaisFkCgYEA4LFelhd1vp3e4s5VRWgNwsKkdp/MBCgSjaSuzd8vlqqrg2k+T49D\r\n"
			+ "0DNBpkwY8AL0Q7GRaBS/hp/LhGnZrZlYHeC/FrOrfeu/iWt+f71z2S5E55ekGYJg\r\n"
			+ "Ydwo0Ku1MkDPEzjwoDaX4l2LY+LD+4BDI/2hBQskFhRU7IchGRtwIzMCgYEAyQcZ\r\n"
			+ "CSfWl69oaC0mWEKBEHLqLjzzaWWGOf4w0iTFBEoCE5pHbvxgud1d9F2xPXk8LqZo\r\n"
			+ "nJhuKV2Ew7RxrOUuI+KsFiYWxI5dHStdQ3W9fys4cxpwnuGisie/qmd9LkBQt0Oz\r\n"
			+ "3/50wcStHsYTuwxbcsejo8vfeoCLtHi9cxum4yUCf0DzBGReY6q197eX7Q4jBddG\r\n"
			+ "bEPd7RtGehZ3hA/+c8MZknpp8ZkN0IxeRJvNY2f9cp4etelgFfzUNAASmNmVeeMp\r\n"
			+ "FBN73BaKDtOqPuat3M7qkQBg1Q6KoVGoUiP/fmEIWtMzolqtbToB0EkPQ5dve/jr\r\n"
			+ "V6AdmFDJj8LPTMH1LHUCgYEApGDmauMliPEIia7pnN/8k0u0C80RKMy4fjFJEHy6\r\n"
			+ "kTBHFGpiIPHD0heeHRSDuoWo3Wh7QBkwcyiiSDkDfuID4ZoXJyFMGrtijliIMYJU\r\n"
			+ "Smk4Bn7HOlO8arsv8ZIf/TLgDYsRyYZTSKKfAPvs5FVNbcSrHWO2wH/ePNtB5p7k\r\n"
			+ "zvUCgYBKaOHX+iE0hKkaCPUv0qBqmS1qKNkh0aGA6r0wYiLGjA1wMnTpUquXfcP1\r\n"
			+ "7qL4cQ4NOmheH2YbuHCEdXpxOmWsW/QzLOcIG42wzRj6YP9WqAisO3hSuM52l/2n\r\n"
			+ "FJ2IPyEHBwipONmhCToTJfhfNau6yO+kaSlv6SnRxNQ6zP8kCg==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsHF+Fsn2cUP/yoWJsbma\r\n"
			+ "Cg7Kf0nl+4d2Fy4YkjTM2aGqH/OUQHOg18f4meoVWTf8GcATWSnHPxGLcMHBLQCA\r\n"
			+ "2P4XzG9y/oqcUSfJUWy8uEfP23mJ0GIk9H4HhmSdiJg5GUEoreWHQ9nIV4vR/aF6\r\n"
			+ "ePjY5jnC8Qe4r+b7UXjMUEy953zAX11z7G/peQ/7M0eMHpoCdBpwa/a/zM1hkV/w\r\n"
			+ "rbFONnJXBpP1ecwnGW1Wjpza67GEVMXB40FvU2GC8sgmBWGiuv0YNc5b6OA7s12A\r\n"
			+ "mUXlkwyPksoLlLW4XsQzcFPqRKOQJctXe3VHgMDl25nKfGm4Wkk5l6mXUiFLmn1P\r\n"
			+ "XwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
