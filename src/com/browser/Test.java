package com.browser;

public class Test {

	public static void main(String[] args){
/*		for(int i=0;i<100;i++){
			try{
			if(i==10){
				System.out.println("----------");
				throw new RuntimeException("exception!!");
			}
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
			System.out.println(i);
		}*/
		// TODO Auto-generated method stub
		int num = 99;
		int a;
		int b;
		int c;
		a = num&1;
		b = num>>2;
		System.out.println(Integer.toBinaryString(num));
		c = num>>3;
		System.out.println(Integer.toBinaryString(num));
		System.out.println(a + "   "+ b +"   " +c);
		
	}

}
