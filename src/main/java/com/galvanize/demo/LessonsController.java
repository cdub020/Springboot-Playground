package com.galvanize.demo;

import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController{
    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> byid(@PathVariable long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletebyid(@PathVariable long id){
        this.repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Lesson putrequest(@PathVariable long id, @RequestBody Lesson thebody){
        thebody.setId(id);
        return this.repository.save(thebody);
    }

    @PatchMapping("/{id}")
    public Lesson patchrequest(@PathVariable long id, @RequestBody Lesson thebody){
        Optional <Lesson> thelesson = this.repository.findById(id);
        thelesson.get().setTitle(thebody.getTitle());
        thelesson.get().setDeliveredOn(thebody.getDeliveredOn());
        return this.repository.save(thelesson.get());
    }
}
