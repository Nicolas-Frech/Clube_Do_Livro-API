package clubedolivro.api.domain.book;

public record BookDetailsData(Long id, String title, String author, String synopsis, String releaseDate, Genre genre, String price) {
    public BookDetailsData(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getSynopsis(), book.getReleaseDate(), book.getGenre(), book.getPrice());
    }
}
