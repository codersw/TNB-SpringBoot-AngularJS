package com.example.project;

/**
 * 常量枚举
 */
public enum Constants {

	//session用户
	SESSION_NAME_LOGIN_RESULT("user"),
	//session菜单
	SESSION_NAME_LOGIN_MENU("menuList"),
	//菜单根目录id
	SYSTEM_ROOT_MENU_ID("eaca2e4d86474829bcdd07c92f3f7f0f"),
	//用户默认密码
	USER_DEFAULT_PASS_WORD("123456"), ;

	private String ConstantsString;

	Constants(String ConstantsString){
		this.ConstantsString = ConstantsString;
	}

	@Override
	public String toString() {
		return this.ConstantsString;
	}

	public static Constants parse(String Constant) {
		for(Constants cs : Constants.values()) {
			if (cs.toString().equals(Constant)) {
				return cs;
			}
		}

		throw new IllegalArgumentException("Unable to parse the provided constant " + Constant);
	}
}
