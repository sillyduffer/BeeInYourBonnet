package com.example.android.beeinyourbonnet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, ArrayList<Book> theseBooks) {
        super(context, 0, theseBooks);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);

        ImageView thumbnailView = (ImageView) listItemView.findViewById(R.id.small_thumbnail);
//        set thumbnailView via url in background thread

        TextView titleView = (TextView) listItemView.findViewById(R.id.book_title);
        titleView.setText(currentBook.getmTitle());

        TextView descriptionView = (TextView) listItemView.findViewById(R.id.book_description);
        descriptionView.setText(currentBook.getmDescription());

        TextView authorView = (TextView) listItemView.findViewById(R.id.book_author);
        authorView.setText(currentBook.getmAuthor());


        return listItemView;

    }

}
