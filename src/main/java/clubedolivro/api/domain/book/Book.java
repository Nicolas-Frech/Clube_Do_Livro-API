package clubedolivro.api.domain.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Book")
@Table(name = "books")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String synopsis;

    private String releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String price;

    private Boolean active;

    public Book(BookData data) {
        this.active = true;
        this.title = data.title();
        this.author = data.author();
        this.synopsis = data.synopsis();
        this.releaseDate = data.releaseDate();
        this.genre = data.genre();
        this.price = data.price();
    }

    public void update(BookUpdateData data) {
        if(data.price() != null) {
            this.price = data.price();
        }
    }

    public void delete() {
        this.active = false;
    }
}
