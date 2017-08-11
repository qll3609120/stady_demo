package com.example.demo;

import com.example.demo.util.ListUtil;
import org.junit.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by evan.qi on 2017/8/3.
 */
@SpringBootTest
public class JavaTest {

	ListUtil listUtil = new ListUtil();

	@org.junit.Test
	public void test(){

		System.out.println("test begin");
		Long beginTime = System.currentTimeMillis();
		Integer data = listUtil.getLast(Arrays.asList(1,2,3,5));
		Long endTime = System.currentTimeMillis();
		System.out.println(String.valueOf(data)+(endTime - beginTime));
		beginTime = System.currentTimeMillis();
		data = listUtil.getLastByRecursion(Arrays.asList(1,2,3,5));
		endTime = System.currentTimeMillis();
		Assert.assertEquals(Integer.valueOf(5),data);
		System.out.println(String.valueOf(data)+(endTime - beginTime));
	}

	@Test(expected = EmptyStackException.class)
	public void test2(){
		listUtil.getLastByRecursion(Collections.emptyList());
	}

	@Test
	public void test3(){
		Integer data = listUtil.getDataBySteam(Arrays.asList(1,2,4,6),2);
		Assert.assertEquals(Integer.valueOf(4),data);
	}

	@Test
	public void test4(){
		Integer data = listUtil.getDataBySteam(Arrays.asList(1,2,2,4,6),2);
		Assert.assertEquals(Integer.valueOf(4),data);
	}

	@Test
	public void test5(){
		List rs = listUtil.reverseList(Arrays.asList(1,2,4,6));
		rs.stream().forEach(item->{
			System.out.println(item);
		});
	}


}