package com.example.library;

public class Books {
//    private int id;
    private String Title = null;
    private String Author = null;
    private String Genre = null;
    private int Height;
    private String Publisher= null;


    public Books(){

    }


    public Books(String tit, String at,String ger, int hei,String pub){
//        this.id= iddd;
        this.Title = tit;
        this.Author=at;
        this.Genre=ger;
        this.Height = hei;
        this.Publisher = pub;

    }

    @Override
    public String toString() {
        return
//                "Books{" +
                "Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Genre='" + Genre + '\'' +
                ", height=" + Height +
                ", Publisher='" + Publisher + '\'' +
                        "\n";
//                '}';
    }

    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getAuthor() {
        return Author;
    }


    public String getPublisher() {
        return Publisher;
    }

    public String getTitle() {
        return Title;
    }

    public int getHeight() {
        return Height;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setHeight(int height) {
        this.Height = height;
    }

}
