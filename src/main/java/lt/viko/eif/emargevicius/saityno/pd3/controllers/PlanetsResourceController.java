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
import java.util.stream.Collectors;

/**
 * Planets API Controller.
 *
 * @author Emil
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "PlanetsAPI", description = "PlanetsResourceController")
public class PlanetsResourceController {
    /**
     * Get All Planets request.
     * Returns all of the planets
     * @return ResponseEntity<CollectionModel<EntityModel<Planet>>> Planet list with links
     * @see Planet
     */
    @GetMapping
    @Operation(summary = "Get All Planets", description = "Returns all planets stored in the repository")
    public ResponseEntity<CollectionModel<EntityModel<Planet>>> allPlanets() {
        List<EntityModel<Planet>> planets = PlanetsRepository.getPlanets().stream()
                .map(planet -> EntityModel.of(planet,
                        linkTo(methodOn(PlanetsResourceController.class).getByName(planet.getName())).withSelfRel(),
                        linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets")
                        )
                ).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(planets, linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withSelfRel()));
    }

    /**
     * Get planet by name
     * Returns a specified by name planet
     * @param name of the planet to get
     * @return ResponseEntity<EntityModel<Planet>> planet object with links
     * @see Planet
     */
    @GetMapping(value = "/{name}")
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


    /**
     * Create a new planet
     * Adds the given planet into the repository if the name is not taken
     * @param newPlanet a {@link Planet} object to add     * @see Planet
     * @return ResponseEntity<EntityModel<Planet>> planet object with links
     * @see Planet
     */
    @PostMapping(consumes = { "application/json", "application/xml" })
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

    /**
     * Update/Create a planet by name
     * Updates a planet with the given name. If the planet is not in the repository, it is added
     *
     * @param name of the planet to change/create
     * @param newPlanet a {@link Planet} object
     * @return ResponseEntity<CollectionModel<EntityModel<Planet>>> Planet with links
     */
    @PutMapping(value = "/{name}", consumes = { "application/json", "application/xml" })
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

    /**
     * Remove a planet from the repository.
     * Removes the planet from the repository by it's name
     * @param name of the planet to remove
     * @return the full list after the removal
     */
    @DeleteMapping(value = "/{name}")
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
}
