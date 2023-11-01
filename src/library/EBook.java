package library;

import java.time.LocalDate;

public class EBook extends Book{
    private String fileSize;

    public EBook(Long id, String name, String author, Long isbn, LocalDate publishedDate, String fileSize) {
        this.setId(id);
        this.setName(name);
        this.setAuthor(author);
        this.setIsbn(isbn);
        this.setPublishedDate(publishedDate);
        this.fileSize = fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }
}
