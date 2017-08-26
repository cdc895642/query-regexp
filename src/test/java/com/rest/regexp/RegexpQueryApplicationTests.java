package com.rest.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RegexpQueryApplicationTests {

	@Test
	public void contextLoads() {
		Pattern p= Pattern.compile("a*b");
		Matcher m=p.matcher("123456789aaaa[aaa]aaaaaa");
		String i="^12345678^9aa[aa]aa$aaaa!!!aaa$";
		String pattern=".*\\[\\p{IsAlphabetic}+\\].*";
		System.out.println(i.matches(pattern));
		StringBuilder builder=new StringBuilder(i);
		builder.replace(0,1,"");
		System.out.println(builder.toString());
		builder.replace(builder.length()-1,builder.length(),"");
		System.out.println(builder.toString());
		System.out.println("\\p");
		System.out.println(i.replaceFirst("\\[\\p{IsAlphabetic}+\\]",""));//
		i="Vasiliev Vasiliy";
		pattern="[ASV].*.*y";
		System.out.println(!i.matches(pattern));
	}

}
