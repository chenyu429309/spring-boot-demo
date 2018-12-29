package com.felix.springbootdemo.designPatterns.proxy;

public class ChromeBrowserProxy implements Browser{
 
	private ChromeBrowser browser;
	
	public ChromeBrowserProxy(ChromeBrowser chromeBrowser) {
		this.browser = chromeBrowser;
	}
 
	public void visitInternet() {
		encrypt();
		browser.visitInternet();
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
 
}
