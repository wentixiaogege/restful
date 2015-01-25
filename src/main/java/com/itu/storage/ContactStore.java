package com.itu.storage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.itu.bean.Address;
import com.itu.bean.Contact;

public class ContactStore {
	private static Map<String,Contact> store;
	private static ContactStore instance = null;
	
	private ContactStore() {
		store = new HashMap<String,Contact>();
		initOneContact();
	}
	
	public static Map<String,Contact> getStore() {
		if(instance==null) {
			instance = new ContactStore();
		}
		return store;
	}
	
	private static void initOneContact() {
		Address[] addrs = {
			new Address("Shanghai", "Long Hua Road"),
			new Address("Shanghai", "Dong Quan Street")
		};
		
		Address[] addrs2 = {
				new Address("Milpitus", "120 Dixon ST")
			};
		Contact cHuang = new Contact("huangyim", "Huang Yi Ming", Arrays.asList(addrs));
		store.put(cHuang.getId(), cHuang);
		// just a test
		Contact pGuan = new Contact("gqq", "Peter Guan", Arrays.asList(addrs2));
		store.put(pGuan.getId(), pGuan);
	}
}
