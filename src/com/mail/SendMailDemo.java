package com.mail;

public class SendMailDemo {
	 public static void main(String[] args) {
	  // �����ʼ���������Ϣ
	  MailSenderInfo mailInfo = new MailSenderInfo();
	  mailInfo.setMailServerHost("smtp.qq.com");
	  mailInfo.setMailServerPort("25");
	  mailInfo.setValidate(true);
	  
	  // �����û���
	  mailInfo.setUserName("415279211@qq.com");
	  // ��������
	  mailInfo.setPassword("Hai9010093431");
	  // ����������
	  mailInfo.setFromAddress("415279211@qq.com");
	  // �ռ�������
	  mailInfo.setToAddress("415279211@qq.com");
	  // �ʼ�����
	  mailInfo.setSubject("����Java�������ʼ�");
	  // �ʼ�����
	  StringBuffer buffer = new StringBuffer();
	  buffer.append("JavaMail 1.4.5 jar�����ص�ַ��http://www.oracle.com/technetwork/java/index-138643.html\n");
	  buffer.append("JAF 1.1.1 jar�����ص�ַ��http://www.oracle.com/technetwork/java/javase/downloads/index-135046.html");
	  mailInfo.setContent(buffer.toString());
	  
	  // �����ʼ�
	  SimpleMailSender sms = new SimpleMailSender();
	  // ���������ʽ
	  sms.sendTextMail(mailInfo);
	  // ����html��ʽ
	  SimpleMailSender.sendHtmlMail(mailInfo);
	  System.out.println("�ʼ��������");
	 }
	}
