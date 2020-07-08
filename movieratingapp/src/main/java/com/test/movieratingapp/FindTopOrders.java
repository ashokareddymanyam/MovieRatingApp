package com.test.movieratingapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author 
 *
 */
public class FindTopOrders {

	public static void main(String[] args) {
		
		Map<String,Set<String>> itemOrders = new HashMap<>();
		
		Map<String,Integer> itemCounterMap = new HashMap<>();
		
		Map<String, List<String>> orderItemMap = prepareInputOrders();
		
		Set<String> items = prepareUniqueItemSet();
		
		items.forEach(item -> {
			System.out.println(item);
			Map<String, List<String>> itemOrderMap = orderItemMap.entrySet().stream().filter(val -> val.getValue().contains(item)).
					collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
			
			System.out.println(itemOrderMap);
			itemOrders.put(item, itemOrderMap.keySet());
			itemCounterMap.put(item, itemOrderMap.values().size());
		});
		System.out.println(itemOrders);
		itemCounterMap
		// take that map as a stream of entries
		.entrySet().stream()
		// sort them by count in reverse order
		.sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
		// limit the number to get top two
		.limit(2).map(Map.Entry::getKey).forEach(i ->{
			Set<String> orders = itemOrders.get(i);
			System.out.println("Top 2 Items : "+ i +" UsageCount:"+ orders.size() +" orders"+ orders);
		});
		

	}

	/**
	 * Prepare UniqueItemSet
	 * 
	 * @return Set<String> 
	 */
	private static Set<String> prepareUniqueItemSet() {
		Set<String> items = new HashSet<>();
		items.add("item1");
		items.add("item2");
		items.add("item3");
		return items;
	}

	/**
	 * Prepare input orders
	 * 
	 * @return Map<String, List<String>>
	 */
	private static Map<String, List<String>> prepareInputOrders() {
		Map<String,List<String>> orderItemMap = new HashMap<>();
		List<String> itemList = new ArrayList<>();
		itemList.add("item1");
		itemList.add("item2");
		orderItemMap.put("order1", itemList);
		
		List<String> itemList1 = new ArrayList<>();
		itemList1.add("item3");
		orderItemMap.put("order2", itemList1);
		
		List<String> itemList2 = new ArrayList<>();
		itemList2.add("item2");
		orderItemMap.put("order3", itemList2);
		
		List<String> itemList4 = new ArrayList<>();
		itemList4.add("item1");
		itemList4.add("item2");
		orderItemMap.put("order4", itemList4);
		return orderItemMap;
	}

}
