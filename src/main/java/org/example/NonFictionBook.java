package org.example;

public class NonFictionBook extends Book {
    private String subject;

    public NonFictionBook(String title, String author, String isbn, String subject) {
        super(title, author, isbn);
        this.subject = subject;
    }

    @Override
    public org.bson.Document toDocument() {
        return super.toDocument()
                .append("subject", subject)
                .append("type", "NonFiction");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Subject: " + subject);
        System.out.println("Type   : Non-Fiction\n");
    }
}
