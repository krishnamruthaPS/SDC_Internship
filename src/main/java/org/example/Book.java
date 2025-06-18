package org.example;

public class Book {
    protected String title;
    protected String author;
    protected String isbn;

    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public org.bson.Document toDocument() {
        return new org.bson.Document("title", title)
                .append("author", author)
                .append("isbn", isbn)
                .append("type", "Generic");
    }

    public void display() {
        System.out.println("Title : " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN  : " + isbn);
    }
}
