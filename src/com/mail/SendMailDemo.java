package com.mail;

public class SendMailDemo {
	 public static void main(String[] args) {
	  // 设置邮件服务器信息
	  MailSenderInfo mailInfo = new MailSenderInfo();
	  mailInfo.setMailServerHost("smtp.qq.com");
	  mailInfo.setMailServerPort("25");
	  mailInfo.setValidate(true);
	  
	  // 邮箱用户名
	  mailInfo.setUserName("415279211@qq.com");
	  // 邮箱密码
	  mailInfo.setPassword("Hai9010093431");
	  // 发件人邮箱
	  mailInfo.setFromAddress("415279211@qq.com");
	  // 收件人邮箱
	  mailInfo.setToAddress("415279211@qq.com");
	  // 邮件标题
	  mailInfo.setSubject("测试Java程序发送邮件");
	  // 邮件内容
	  StringBuffer buffer = new StringBuffer();
	  buffer.append("JavaMail 1.4.5 jar包下载地址：http://www.oracle.com/technetwork/java/index-138643.html\n");
	  buffer.append("JAF 1.1.1 jar包下载地址：http://www.oracle.com/technetwork/java/javase/downloads/index-135046.html");
	  mailInfo.setContent(buffer.toString());
	  
	  // 发送邮件
	  SimpleMailSender sms = new SimpleMailSender();
	  // 发送文体格式
	  sms.sendTextMail(mailInfo);
	  // 发送html格式
	  SimpleMailSender.sendHtmlMail(mailInfo);
	  System.out.println("邮件发送完毕");
	 }
	}
