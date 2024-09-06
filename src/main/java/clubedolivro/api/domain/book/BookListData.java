package clubedolivro.api.domain.book;

public record BookListData(Long id, String title, String author, String synopsis) {

    public BookListData(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getSynopsis());
    }
}
