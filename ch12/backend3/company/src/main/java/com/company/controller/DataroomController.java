package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.Dataroom;
import com.company.service.DataroomService;

@RestController
@RequestMapping("/dataroom")
public class DataroomController {

    private final DataroomService dataroomService;

    @Autowired
    public DataroomController(DataroomService dataroomService) {
        this.dataroomService = dataroomService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Dataroom>> getDataroomList() {
        List<Dataroom> datarooms = dataroomService.getDataroomList();
        return new ResponseEntity<>(datarooms, HttpStatus.OK);
    }

    @GetMapping("/detail/{dno}")
    public ResponseEntity<Dataroom> getDataroom(@PathVariable("dno") int dno) {
        Dataroom dataroom = dataroomService.getDataroom(dno);
        if (dataroom != null) {
            return new ResponseEntity<>(dataroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertDataroom(@RequestParam("title") String title,
                                                 @RequestParam("content") String content,
                                                 @RequestParam("author") String author,
                                                 @RequestParam("file") MultipartFile file) {
        Dataroom dataroom = new Dataroom();
        dataroom.setTitle(title);
        dataroom.setContent(content);
        dataroom.setAuthor(author);
        dataroomService.insert(dataroom, file);
        return new ResponseEntity<>("Dataroom entry inserted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{dno}")
    public ResponseEntity<String> updateDataroom(@PathVariable("dno") int dno,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("content") String content,
                                                 @RequestParam("author") String author,
                                                 @RequestParam("file") MultipartFile file) {
        Dataroom dataroom = dataroomService.getDataroom(dno);
        if (dataroom == null) {
            return new ResponseEntity<>("Dataroom entry not found", HttpStatus.NOT_FOUND);
        }
        dataroom.setTitle(title);
        dataroom.setContent(content);
        dataroom.setAuthor(author);
        dataroomService.update(dataroom, file);
        return new ResponseEntity<>("Dataroom entry updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{dno}")
    public ResponseEntity<String> deleteDataroom(@PathVariable("dno") int dno) {
        dataroomService.delete(dno);
        return new ResponseEntity<>("Dataroom entry deleted successfully", HttpStatus.OK);
    }
}
