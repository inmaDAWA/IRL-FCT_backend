package com.IRL.appformacioncontinua.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRERTA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEArw+gLhtRTIaV0uH6rhF2A3d+MF45VdZ8W+pIMQMTOo/ZRxZP\r\n" + 
			"QIllcq6uWIGVYqBdWYsRztBloxt6QY9L3ZQkUyJwnl7JM+N5z7joOdPT7g0Fkf2L\r\n" + 
			"0oj39wfLc2RydVPhhy+Mt7UjyWBpNyau6lJWVNxXJayRKc4WtkI3AeKDZK+fCUcj\r\n" + 
			"CuFsz2z0gcIx59UV6ydJ987+0gSK8h4pqNtv6SLSdIqg5X6/a9JoO1fAIn02EVae\r\n" + 
			"+aCZDKptVX7gLcBUj6V1GN6mnSt5ED6y6TZtAi+vSWJJEvwkCRw4cqiInKLmFToy\r\n" + 
			"FX06l0XHHzxMhtoywM886FrLIE6LG7zw0wYeuQIDAQABAoIBAHrxlGMNGFmQI2SJ\r\n" + 
			"BA5/gMwKfXrmkYmxKoE61hxltnloUQXVqy79z1ttRlAuIYW0bhrWyljejqKwR3oH\r\n" + 
			"cxZYVfJ0u3bdxpxEY2X4zE7gb3Q9fO38jNf9DXhV5xJRNvkjQb45LLBP7KQeq4Xq\r\n" + 
			"CunRjYPnguSYfxJHBDhX5F3UgMaSTgs+US+1iAw2jKSv/CwQ4uJ88oMjIh9LUxZo\r\n" + 
			"gL7hzmdP0bjlLIkDGTgM6T5Y0kP0ddjf0HgaMk+xQgja4jVm0fKipo2QVXWiylKn\r\n" + 
			"E38BztHvcjurp1V0nKFyNU2FhCqLx96NQZSnH9pIu6j3GCfguFFoeGGeNHUDPB0E\r\n" + 
			"YKiZvokCgYEA4+2n4Bd0oqMv4ax+ZWd/RSkdFGOLiv/R9zo83yHaJuHbQR/C96Gq\r\n" + 
			"YzDmkq49Iyy25ZPOiNV+U9nldolOhvMfeed6M3qlJa0t7+lDcVgvdw4a7AwXltj1\r\n" + 
			"lYue0c2qvCjzEhp8KTQCyiWxnMJFDAqckb/2Bty8x9tyi4fjYBnBKI8CgYEAxJ8e\r\n" + 
			"X/C06LARSL6zmlTEZTO6xv5YZz+YcyjNtMnkNfG98fOYKgxVTInpvyxsczfK3YPO\r\n" + 
			"t6RY2qyR8GlmvV3z8aooKD4hGkuNRWXvtVt34ggR2OF8HX2a6GJUxXq3D9pOsI7u\r\n" + 
			"lGlVZJxhmG1Y9c2yr+5jnnHsOepG82Urkwg3GDcCgYBVxyLemvUyH+ftLlWDJCgp\r\n" + 
			"hweCnNPvQ8dpG2m9L6hB6c3x/FoLh7PwEH81xI0Np85NKAsaQ/5+4AoTSilbcDPw\r\n" + 
			"VMUQip+MRlh7aXYQOffi/lPzcbmO86e42Aq1Ool+toqDzaPcfc5u9TCapiIGHhg+\r\n" + 
			"YdfdTUVekZ/SExnnGMow0QKBgQCc5kCt3H3whLd2Lj9R8zy5aVstej9/PJ9hnfjw\r\n" + 
			"wH6skVi6n2EYhLj4l8EAPenY03e95nVp0+rXrQfUKbfDexq0CM18t39g5VM5QsvT\r\n" + 
			"E8eJhlj/kYeiXN3lfLoaz/8O1m0etSArLBbpb2Tt5qwrkdZ6T6gNAum3mXy+s0ff\r\n" + 
			"T5nvZwKBgQCxDOLmgfvs+dzv9QptohbAnACx0xBZPUKFGSM59W2xl/UHCVsq3F88\r\n" + 
			"tmq3+IP8EP65u/y4Q9RMJrs9sqbZJ5Jfwhn9xSNLCOW334rnUOU5xfvhUQkDE8nY\r\n" + 
			"MP5XNQEiz+IQf3qAK6X6EBnHCPN6XkQD53aox0V1O/XVdfp8mmMJHA==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArw+gLhtRTIaV0uH6rhF2\r\n" + 
			"A3d+MF45VdZ8W+pIMQMTOo/ZRxZPQIllcq6uWIGVYqBdWYsRztBloxt6QY9L3ZQk\r\n" + 
			"UyJwnl7JM+N5z7joOdPT7g0Fkf2L0oj39wfLc2RydVPhhy+Mt7UjyWBpNyau6lJW\r\n" + 
			"VNxXJayRKc4WtkI3AeKDZK+fCUcjCuFsz2z0gcIx59UV6ydJ987+0gSK8h4pqNtv\r\n" + 
			"6SLSdIqg5X6/a9JoO1fAIn02EVae+aCZDKptVX7gLcBUj6V1GN6mnSt5ED6y6TZt\r\n" + 
			"Ai+vSWJJEvwkCRw4cqiInKLmFToyFX06l0XHHzxMhtoywM886FrLIE6LG7zw0wYe\r\n" + 
			"uQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	

}
