package lt.viko.eif.emargevicius.saityno.pd3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planet;
import lt.viko.eif.emargevicius.saityno.pd3.repo.PlanetsRepository;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "PlanetsAPI", description = "PlanetsResourceController")
public class PlanetsResourceController {

    @GetMapping(produces = { "application/json", "application/xml" })
    @Operation(summary = "Get All Planets", description = "Returns all planets stored in the repository")
    public ResponseEntity<CollectionModel<EntityModel<Planet>>> allPlanets() {
        List<EntityModel<Planet>> students = PlanetsRepository.getPlanets().stream()
                .map(planet -> EntityModel.of(planet,
                        linkTo(methodOn(PlanetsResourceController.class).getByName(planet.getName())).withSelfRel(),
                        linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets")
                        )
                ).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(students, linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withSelfRel()));
    }

    @GetMapping(value = "/{name}", produces = { "application/json", "application/xml" })
    @Operation(summary = "Get Planet", description = "Returns a planet by name stored in the repository")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success!"),
                    @ApiResponse(responseCode = "404", description = "Planet not Found!")
            }
    )
    public ResponseEntity<EntityModel<Planet>> getByName(@PathVariable String name) {
        Planet planet;
        try {
            planet = PlanetsRepository.getPlanets().stream().filter(n -> n.getName().equals(name)).findAny().get();
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        EntityModel<Planet> model = EntityModel.of(planet);

        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self"));
        model.add(linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets"));
        return ResponseEntity.ok(model);
    }

    @PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @Operation(summary = "Post Planet", description = "Posts a planet into the repository")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success!"),
                    @ApiResponse(responseCode = "409", description = "A planet with that name already exists!"),
            }
    )
    public ResponseEntity<EntityModel<Planet>> postByName(@RequestBody Planet newPlanet) {
        final Planet planet = newPlanet;
        if (PlanetsRepository.getPlanets().stream().anyMatch(n -> n.getName().equals(planet.getName()))){
            throw  new ResponseStatusException(HttpStatus.CONFLICT);
        }

        PlanetsRepository.addPlanet(planet);

        EntityModel<Planet> model = EntityModel.of(planet);

        model.add(linkTo(methodOn(PlanetsResourceController.class).getByName(newPlanet.getName())).withSelfRel());
        model.add(linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets"));
        return ResponseEntity.ok(model);
    }

    @PutMapping(value = "/{name}", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @Operation(summary = "Put Planet", description = "Puts a planet into the repository. If the planet already exists it updates it.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success!")
            }
    )
    public ResponseEntity<EntityModel<Planet>> putByName(@PathVariable String name, @RequestBody Planet newPlanet) {
        List<Planet> planetList = PlanetsRepository.getPlanets();

        boolean updateFlag = false;
        for (int i = 0; i < planetList.size(); ++i) {
            if (planetList.get(i).getName().equals(newPlanet.getName())) {
                planetList.set(i, newPlanet);
                updateFlag = true;
                break;
            }
        }
        if (updateFlag) {
            PlanetsRepository.setPlanets(planetList);
        } else {
            PlanetsRepository.addPlanet(newPlanet);
        }

        EntityModel<Planet> model = EntityModel.of(newPlanet);

        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();

        model.add(linkTo(methodOn(PlanetsResourceController.class).getByName(name)).withSelfRel());
        model.add(linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets"));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping(value = "/{name}", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @Operation(summary = "Delete Planet", description = "Deletes a planet from the repository")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success!"),
                    @ApiResponse(responseCode = "404")
            }
    )
    public ResponseEntity<CollectionModel<EntityModel<Planet>>> deleteByName(@PathVariable String name) {
        int startingCount = PlanetsRepository.getPlanets().size();

        List<Planet> planetList;
        try {
            planetList = PlanetsRepository.getPlanets().stream().filter(n -> !n.getName().equals(name)).collect(Collectors.toList());
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No planets found. Empty repository");
        }
        if (startingCount == planetList.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Couldn't find %s for deletion", name));
        }
        PlanetsRepository.setPlanets(planetList);

        return allPlanets();
    }

/*
    @GetMapping("/")
    public ResponseEntity<EntityModel<Planet>> getById(@RequestParam Long id) {
        Planet planet;
        try {
            planet = planetsRepo.getPlanets().stream().filter(n -> n.getId() == id).findAny().get();
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
        return getByName(planet.getName());
    }
*/
}
