package com.innominds.controller;

import java.util.List;
import java.lang.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.IndexField.SortOrder;
import com.cloudant.client.api.model.Response;
import com.innominds.db.CloudantDB;
import com.innominds.dto.PersonDTO;

@RestController
public class PersonController {

	
	@Autowired
	private CloudantDB cloudantDB;

	@RequestMapping(value = "/details", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String saveData(@RequestBody PersonDTO personDetails) {

		// cloudantDB.getDB().save(personDetails);

		// PersonDTO create=(PersonDTO) cloudantDB.getDB().find(PersonDTO.class,
		// "35073b9d83a94d059b8b57dc1bbdb3fc");

		// return create.toString();

		// System.out.println("save data " + person);

		Response r = null;

		if (personDetails != null) {
			r = cloudantDB.getDB().post(personDetails);
		}
		System.out.println("save data " + personDetails);

		return r.getId();

	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List getAll(@RequestParam(required = false) String id) {

		List allDocs = null;
		try {
			if (id == null) {
				allDocs = cloudantDB.getDB().getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
						.getDocsAs(PersonDTO.class);

			} else {
				cloudantDB.getDB().createIndex("personview", "persondoc", "javascript",
						new IndexField[] { new IndexField("id", SortOrder.asc) });

				System.out.println("Successfully created index");
				allDocs = cloudantDB.getDB().findByIndex("{\"id\" : " + id + "}", PersonDTO.class);

			}
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		return allDocs;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response updatePerson(@RequestParam(required = false) String id, @RequestBody PersonDTO personDTO) {

		PersonDTO update = cloudantDB.getDB().find(PersonDTO.class, "person123");

		//update.setId(personDTO.getId());
		update.setName(personDTO.getName());
		update.setAge(personDTO.getAge());
        update.setRev(personDTO.getRev());
		return cloudantDB.getDB().update(update);

	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Response deletePerson(@RequestParam(required = false) String id, @RequestBody PersonDTO personDTO){
		
		PersonDTO delete = cloudantDB.getDB().find(PersonDTO.class, "person123");
		return cloudantDB.getDB().remove(delete);
		
		
	}

}
