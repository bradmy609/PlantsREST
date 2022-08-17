package com.codecademy.plants.controllers;

import com.codecademy.plants.entities.Adventure;
import com.codecademy.plants.entities.ConflictException;
import com.codecademy.plants.repositories.AdventureRepository;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RequestMapping("/traveladventures")
@RestController
public class TravelAdventuresController {

  private final AdventureRepository adventureRepository;

  public TravelAdventuresController(AdventureRepository adventureRepo) {
    this.adventureRepository = adventureRepo;
  }

  // Add controller methods below:

  @PostMapping(
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE}, 
    produces = MediaType.APPLICATION_JSON_VALUE, value=""
  )
  @ResponseStatus(HttpStatus.CREATED)
  public Adventure createAdventure(@RequestBody Adventure adventure) throws Exception {
    Integer id = adventure.getId();
    Optional<Adventure> conflictingAdventure = this.adventureRepository.findById(id);
    if (conflictingAdventure.isPresent()) {
      throw new ConflictException();
    }
    adventure.validateAdventure();
    adventure.printAdventure();
    Adventure returnAdventure = this.adventureRepository.save(adventure);
    return returnAdventure;
  }

  @GetMapping
  public Iterable<Adventure> adventures() {
    Iterable<Adventure> adventures = this.adventureRepository.findAll();
    return adventures;
  }

  @GetMapping("/bycountry/{country}")
  public Iterable<Adventure> countries(@PathVariable String country) {
    Iterable<Adventure> countries = this.adventureRepository.findByCountry(country);
    return countries;
  }

  @PutMapping("/{id}")
  public Adventure updateAdventure(@PathVariable Integer id, @RequestBody Adventure adventure) throws Exception {
    Optional<Adventure> optionalAdventure = this.adventureRepository.findById(id);
    if (!optionalAdventure.isPresent()) {
      throw new Exception("No adventure that matches the id: " + id);
    }
    Adventure oldAdventure = optionalAdventure.get();
    Adventure newAdventure = this.adventureRepository.save(adventure);
    System.out.println("Updating old adventure:");
    oldAdventure.printAdventure();
    System.out.println("Replacing with new adventure:");
    newAdventure.printAdventure();
    return newAdventure;
  }

  @DeleteMapping("/{id}")
  public Adventure deleteAdventure(@PathVariable Integer id) throws Exception {
    Optional<Adventure> optionalAdventure = this.adventureRepository.findById(id);
    if (!optionalAdventure.isPresent()) {
      throw new Exception("No adventure matches the id: " + id);
    }
    Adventure deletedAdventure = optionalAdventure.get();
    System.out.println("Deleted adventure: ");
    deletedAdventure.printAdventure();
    return deletedAdventure;
  }
  
}
