package com.felix.springbootdemo.designPatterns.proxy;

public class ChromeBrowser implements Browser{



	public void visitInternet() {
		encrypt();
		System.out.println("visit YouTube");
		decrypt();
	}

	// 加密
	private void encrypt(){
		System.out.println("encrypt ...");
	}

	// 解密
	private void decrypt(){
		System.out.println("decrypt ...");
	}

	public void listenToMusic(){
		System.out.println("listen to Cranberries");
	}

}
