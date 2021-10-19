package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.secure.Type;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/petStore")
@Produces("application/json")
public class PetResource {

	List<Pet> pets = new ArrayList<Pet>();
	List<Type> types = new ArrayList<Type>();

	public PetResource(){
		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Boola");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		Pet pet4 = new Pet();
		pet4.setPetId(4);
		pet4.setPetAge(6);
		pet4.setPetName("Chichi");
		pet4.setPetType("Cat");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);


		Type type1 = new Type();
		type1.setTypeId(1);
		type1.setTypeName("Dog");

		Type type2 = new Type();
		type2.setTypeId(2);
		type2.setTypeName("Cat");

		Type type3 = new Type();
		type3.setTypeId(3);
		type3.setTypeName("Bird");

		Type type4 = new Type();
		type4.setTypeId(4);
		type4.setTypeName("Fish");

		types.add(type1);
		types.add(type2);
		types.add(type3);
		types.add(type4);
	}



	//getting all pets
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/allPets")
	public Response getPets() {
		return Response.ok(pets).build();
	}



	//get the pet with ID
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(3);
		pet.setPetName("Buula");
		pet.setPetType("Dog");

		return Response.ok(pet).build();
		
	}




	//adding pet 
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "Adding pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "Couldn't add the pet") })
	@POST
	@Path("add/{petId}/{age}/{name}/{type}")
	public Response addPet(@PathParam("petId") int petId,@PathParam("age") int age,@PathParam("name") String name,@PathParam("type") String type) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(age);
		pet.setPetName(name);
		pet.setPetType(type);

		pets.add(pet);

		return Response.ok(pets).build();
		
	}



	//deleting a pet
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "delete pet by id success", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the id.")
	})

	@DELETE
	@Path("delete/{petId}")
	public Response deletePet(@PathParam("petId") int petId) {
	if (petId <= 0) {
		return Response.status(Status.NOT_FOUND).build();
	}

	pets.removeIf(availablePets -> availablePets.getPetId().equals(petId));
	
	return Response.ok(pets).build();
	}





	//updating name
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "updateing name successful", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the id.")
	})

	@PUT
	@Path("updateName/{petId}/{name}")
	public Response updatePetName(@PathParam("petId") int petId,@PathParam("name") String name) {
	if (petId <= 0) {
		return Response.status(Status.NOT_FOUND).build();
	}

	pets.forEach(pet -> {
		if (pet.getPetId() == petId) {
			pet.setPetName(name);
		}
	});
	return Response.ok(pets).build();
	}

	//updating age
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "updateing age successfuk", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the id.")
	})


	@PUT
	@Path("updateAge/{petId}/{age}")
	public Response updatePetAge(@PathParam("petId") int petId,@PathParam("age") int age) {
	if (petId <= 0) {
		return Response.status(Status.NOT_FOUND).build();
	}

	pets.forEach(pet -> {
		if (pet.getPetId() == petId) {
			pet.setPetAge(age);
		}
	});
	return Response.ok(pets).build();
	}


	//updating Type
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "updateing type successfuk", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the id.")
	})
	
	@PUT
	@Path("updateType/{petId}/{type}")
	public Response updatePetType(@PathParam("petId") int petId,@PathParam("type") String type) {
	if (petId <= 0) {
		return Response.status(Status.NOT_FOUND).build();
	}

	pets.forEach(pet -> {
		if (pet.getPetId() == petId) {
			pet.setPetType(type);
		}
	});
	return Response.ok(pets).build();
	}




	//search by name
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "search successful", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the name.")})
	@GET
	@Path("searchPetName/{input}")
	public Response searchPetName( @PathParam("input") String input ) {
	
	List<Pet> foundPets = new ArrayList<Pet>();
	
	pets.forEach(pet -> {
		if (pet.getPetName().equals(input)) {
			Pet foundPet = new Pet();
			foundPet.setPetId(pet.getPetId());
			foundPet.setPetName(pet.getPetName());
			foundPet.setPetAge(pet.getPetAge());
			foundPet.setPetType(pet.getPetType());
			foundPets.add(foundPet);
		}
	});
	
	return Response.ok(foundPets).build();
	}

	//search by id
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "search successful", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the name.")})
	@GET
	@Path("searchPetId/{input}")
	public Response searchPetId( @PathParam("input") int input ) {
	
	List<Pet> foundPets = new ArrayList<Pet>();
	
	pets.forEach(pet -> {
		if (pet.getPetId().equals(input)) {
			Pet foundPet = new Pet();
			foundPet.setPetId(pet.getPetId());
			foundPet.setPetName(pet.getPetName());
			foundPet.setPetAge(pet.getPetAge());
			foundPet.setPetType(pet.getPetType());
			foundPets.add(foundPet);
		}
	});
	
	return Response.ok(foundPets).build();
	}

	//search by age
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "search successful", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the name.")})
	@GET
	@Path("searchPetAge/{input}")
	public Response searchPetAge( @PathParam("input") int input ) {
	
	List<Pet> foundPets = new ArrayList<Pet>();
	
	pets.forEach(pet -> {
		if (pet.getPetAge().equals(input)) {
			Pet foundPet = new Pet();
			foundPet.setPetId(pet.getPetId());
			foundPet.setPetName(pet.getPetName());
			foundPet.setPetAge(pet.getPetAge());
			foundPet.setPetType(pet.getPetType());
			foundPets.add(foundPet);
		}
	});
	
	return Response.ok(foundPets).build();
	}

	//search by type
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "search successful", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
		@APIResponse(responseCode = "404", description = "No Pet found for the name.")})
	@GET
	@Path("searchPetType/{input}")
	public Response searchPetType( @PathParam("input") String input ) {
	
	//		Pet foundPet = new Pet();
	List<Pet> foundPets = new ArrayList<Pet>();
	
	pets.forEach(pet -> {
		if (pet.getPetType().equals(input)) {
			Pet foundPet = new Pet();
			foundPet.setPetId(pet.getPetId());
			foundPet.setPetName(pet.getPetName());
			foundPet.setPetAge(pet.getPetAge());
			foundPet.setPetType(pet.getPetType());
			foundPets.add(foundPet);
		}
	});
	
	return Response.ok(foundPets).build();
	}



	//Types//


	//getting all types
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Type"))) })
	@GET
	@Path("/allTypes")
	public Response getTypes() {
		return Response.ok(types).build();
	}

	//adding types
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "Add Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Type"))) })
	@POST
	@Path("/addType/{id}/{type}")
	public Response addType(@PathParam("id") int id,@PathParam("type") String type) {

		Type newType = new Type();
			newType.setTypeId(id);
			newType.setTypeName(type);

			types.add(newType);

		return Response.ok(types).build();
	}

	//updating types
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "Update Type Name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Type"))) })
	@PUT
	@Path("/updateTypeName/{id}/{name}")
	public Response updateType(@PathParam("id") int id,@PathParam("name") String name) {

		types.forEach(type -> {
			if (type.getTypeId() == id) {
				type.setTypeName(name);
			}
		});

		return Response.ok(types).build();
	}

	//deleting types
	@APIResponses(value = {
		@APIResponse(responseCode = "200", description = "Delete Type Name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Type"))) })
	@DELETE
	@Path("/deleteType/{id}")
	public Response deleteType(@PathParam("id") int id) {

		types.removeIf(types -> types.getTypeId().equals(id));

		return Response.ok(types).build();
	}


}





