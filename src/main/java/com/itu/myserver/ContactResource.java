package com.itu.myserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.itu.bean.Address;
import com.itu.bean.Contact;
import com.itu.storage.ContactStore;
import com.itu.util.ParamUtil;
import com.sun.jersey.api.NotFoundException;

public class ContactResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String contact;

	public ContactResource(UriInfo uriInfo, Request request, String contact) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.contact = contact;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Contact getContact() {
		Contact cont = ContactStore.getStore().get(contact);
		if (cont == null) {
			Address a = new Address("beijing", "haidian");
			List<Address> adds = new ArrayList<Address>();
			adds.add(a);
			Contact t = new Contact("gqq", "Peter Guanq", adds);
			return t;
		}
		return cont;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putContact(JAXBElement<Contact> jaxbContact) {
		Contact c = jaxbContact.getValue();
		return putAndGetResponse(c);
	}

	@PUT
	public Response putContact(@Context HttpHeaders herders, byte[] in) {
		Map<String, String> params = ParamUtil.parse(new String(in));
		Contact c = new Contact(params.get("id"), params.get("name"),
				new ArrayList<Address>());
		return putAndGetResponse(c);
	}

	private Response putAndGetResponse(Contact c) {
		Response res;
		if (ContactStore.getStore().containsKey(c.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		ContactStore.getStore().put(c.getId(), c);
		return res;
	}

	@DELETE
	public void deleteContact() {
		Contact c = ContactStore.getStore().remove(contact);
		if (c == null)
			throw new NotFoundException("No such Contact.");
	}
}
