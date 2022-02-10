package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dto.StudentDto;
import ru.itis.services.SearchService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SearchController {

    private final SearchService searchService;

    @RequestMapping("/search/students")
    public String getStudentsSearchPage() {
        return "search";
    }

    @RequestMapping(value = "/search/students/byName", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentDto> getStudentByFirstOrLastName(@RequestParam("name") String name) {
        return searchService.getStudentByFirstOrLastName(name);
    }
}
