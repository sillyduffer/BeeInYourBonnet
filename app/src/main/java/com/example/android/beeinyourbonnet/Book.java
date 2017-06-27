package com.example.android.beeinyourbonnet;

public class Book {

    private String mTitle;
    private String mAuthor;
    private String mThumbnailUrl;
    private String mUrl;
    private String mDescription;

    public Book(String title, String author, String thumbnailUrl, String url, String description) {

        mTitle = title;
        String placeHolder = author.replaceAll("[\\[\\]]", "");
        String placeHolderTwo = placeHolder.replaceAll("\"", "");
        mAuthor = placeHolderTwo.replaceAll(",", ", ");
        mThumbnailUrl = thumbnailUrl;
        mUrl = url;
        mDescription = description;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDescription() {
        return mDescription;
    }
}
