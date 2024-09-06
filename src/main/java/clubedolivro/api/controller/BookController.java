package clubedolivro.api.controller;

import clubedolivro.api.domain.book.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerBook(@RequestBody @Valid BookData data, UriComponentsBuilder uriBuilder) {
        var book = new Book(data);
        repository.save(book);

        var uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).body(new BookDetailsData(book));
    }

    @GetMapping
    public ResponseEntity<Page<BookListData>> getBookList(@PageableDefault(size = 10) Pageable pageable) {
        var listBooks = repository.findAllByActiveTrue(pageable).map(BookListData::new);

        return ResponseEntity.ok(listBooks);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateBookPrice(@RequestBody @Valid BookUpdateData data) {
        var book = repository.getReferenceById(data.id());
        book.update(data);

        return ResponseEntity.ok(new BookDetailsData(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity getBook(@PathVariable Long id) {
        var book = repository.getReferenceById(id);

        return ResponseEntity.ok(new BookDetailsData(book));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteBook(@PathVariable Long id) {
        var book = repository.getReferenceById(id);
        book.delete();

        return ResponseEntity.noContent().build();
    }
}
