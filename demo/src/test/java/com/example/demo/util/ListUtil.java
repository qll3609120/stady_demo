package com.example.demo.util;

import org.springframework.util.CollectionUtils;
import rx.internal.util.LinkedArrayList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by evan.qi on 2017/8/3.
 * 获取最后一个元素
 */
public class ListUtil {

	public <T> T getLast(List<T> rs){
		if(CollectionUtils.isEmpty(rs)){
			throw new NoSuchElementException();
		}
		return rs.get(rs.size()-1);
	}

	public <T> T getLastByRecursion(List<T> rs){
		if(CollectionUtils.isEmpty(rs)){
			throw new EmptyStackException();
		}

		if(rs.size()==1){
			return rs.get(0);
		}
		return getLastByRecursion(rs.subList(1,rs.size()));
	}


	/**
	 * 获取指定位置的数据
	 */
	public <T> T getDataByPos(List<T> list,int pos){
		if(pos==0){
			return list.get(pos);
		}

		return getDataByPos(list.subList(1,list.size()),--pos);
	}

	/**
	 *
	 * @param list
	 * @param pos
	 * @param <T>
	 * @return
	 */
	public <T> T getDataBySteam(List<T> list,int pos){
		return list.stream().limit(pos+1).collect(Collectors.toCollection(LinkedList::new)).getLast();
	}

	public <T> List<T> getDataByP(List<T> list,T data){
		List rs =
				list.stream().filter(item->item.equals(data)).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(rs)){
			throw new NoSuchElementException();
		}
		return rs;
	}

	/**
	 * 获取长度
	 */
	public <T> long  getSize(List<T> rs){
		return rs.stream().count();
	}
	/**
	 * reverse list
	 *
	 */
	public <T> List<T> reverseList(List<T> rs){
		Collections.reverse(rs);
		rs = IntStream.iterate(rs.size()-1,el->el-1).limit(rs.size()).mapToObj(rs::get).collect(Collectors.toList());
		Collections.reverse(rs);
		return rs;
	}
}
