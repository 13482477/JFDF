package com.jhonelee.jfdf.treemap;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {
	
	@Test
	public void treeMapTest() {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		
		treeMap.put("zzz", 1);
		treeMap.put("aaa", 2);
		treeMap.put("lizhiqiang", 3);
		treeMap.put("aaz", 4);
		
		System.out.println(treeMap.toString());
		
		SortedMap<String, Object> sortedMap = treeMap.headMap("llll");
		System.out.println(sortedMap);
		
		SortedMap<String, Object> subMap = treeMap.subMap("aaa", "b");
		System.out.println(subMap);
	}
                                                      
}
