package com.example.springRepo2;

import com.example.springRepo2.features.entities.ProgramLanguages;
import com.example.springRepo2.repositories.ProgramLanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/programLanguages")
public class PLController {
    @Autowired
    private ProgramLanguagesRepository re;

    @PostMapping("/create")
    public ProgramLanguages createPL(@RequestBody ProgramLanguages programLanguages) {
        programLanguages.setId(null);
        return re.saveAndFlush(programLanguages);
    }

    @GetMapping
    public Page<ProgramLanguages> getPagePLList
            (@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size) {
        if (page.isPresent() && size.isPresent()) {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "name"), new Sort.Order(Sort.Direction.DESC, "inventor"));
            Pageable pageable = PageRequest.of(page.get(), size.get(), sort);
            return re.findAll(pageable);
        } else {
            return Page.empty();
        }
    }

    @GetMapping("/{id}")
    public ProgramLanguages getSinglePL(@PathVariable Long id) {
        if (re.existsById(id)) {
            return re.findById(id).get();
        } else {
            return new ProgramLanguages();
        }
    }

    @PutMapping("/{id}")
    public ProgramLanguages updatePLInventor(@PathVariable Long id, @RequestParam(required = false) String inventor) {
        if (re.existsById(id)) {
            ProgramLanguages programmingLanguage = re.findById(id).get();
            programmingLanguage.setInventor(inventor);
            return re.saveAndFlush(programmingLanguage);
        } else {
            return new ProgramLanguages();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteSinglePL(@PathVariable long id) {
        if (re.existsById(id)) {
            re.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @DeleteMapping("")
    public void deleteAllPL() {
        re.deleteAll();
    }
}
