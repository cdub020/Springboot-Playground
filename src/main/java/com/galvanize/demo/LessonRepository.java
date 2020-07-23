package com.galvanize.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface LessonRepository extends CrudRepository <Lesson,Long> {
    Lesson findByTitle(String title);
    Iterable<Lesson> findBydeliveredOnBetween(Date date1, Date date2);
}
