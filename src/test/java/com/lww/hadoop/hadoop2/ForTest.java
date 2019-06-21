package com.lww.hadoop.hadoop2;

import org.junit.Test;

public class ForTest {
	@Test
	public void TestA() {
		flag:for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println("j : "+j);
				if(j==5) {
					break flag;
				}
			}
			System.out.println("i : "+i);
		}
	}
}
