
package book_management.domain;

public class Book {

    private String isbn;
    private String name;
    private String author;
    private String publish_name;
    private int price;
    private int inventory;
    private String category_name;

    public Book() {
    }

    public Book(String isbn, String name, String author,
                String publish_name, int price, int inventory,
                String category_name) {

        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publish_name = publish_name;
        this.price = price;
        this.inventory = inventory;
        this.category_name = category_name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish_name() {
        return publish_name;
    }

    public int getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public String getCategory_name() {
        return category_name;
    }
}
