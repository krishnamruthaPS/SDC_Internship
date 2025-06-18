package org.example;

public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn);
        this.genre = genre;
    }

    @Override
    public org.bson.Document toDocument() {
        return super.toDocument()
                .append("genre", genre)
                .append("type", "Fiction");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Genre : " + genre);
        System.out.println("Type  : Fiction\n");
    }
}
