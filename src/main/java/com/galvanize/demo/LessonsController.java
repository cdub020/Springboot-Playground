package com.galvanize.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/{id}")
    public Optional<Lesson> byid(@PathVariable long id){
        return this.repository.findById(id);
    }

    @GetMapping("/find/{title}")
    public Lesson getbytitle(@PathVariable String title){
        return this.repository.findByTitle(title);
    }

    @GetMapping("/find")
    public Iterable<Lesson> getbydate(@RequestParam(value="date1") @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                            @RequestParam(value="date2") @DateTimeFormat(pattern="yyyy-MM-dd") Date date2){
            return this.repository.findBydeliveredOnBetween(date1, date2);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
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
