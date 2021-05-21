package lt.viko.eif.emargevicius.saityno.pd3.controllers;

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

@RestController
@RequestMapping(value = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetsResourceController {
    private final PlanetsRepository planetsRepo = new PlanetsRepository();

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Planet>>> allPlanets() {
        List<EntityModel<Planet>> students = planetsRepo.getPlanets().stream()
                .map(student -> EntityModel.of(student,
                        linkTo(methodOn(PlanetsResourceController.class).getByName(student.getName())).withSelfRel(),
                        linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets")
                        )
                ).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(students, linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withSelfRel()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<EntityModel<Planet>> getByName(@PathVariable String name) {
        Planet planet;
        try {
            planet = planetsRepo.getPlanets().stream().filter(n -> n.getName().equals(name)).findAny().get();
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Name not found");
        }
        EntityModel<Planet> model = EntityModel.of(planet);

        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self"));
        model.add(linkTo(methodOn(PlanetsResourceController.class).allPlanets()).withRel("get-all-planets"));
        return ResponseEntity.ok(model);
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
