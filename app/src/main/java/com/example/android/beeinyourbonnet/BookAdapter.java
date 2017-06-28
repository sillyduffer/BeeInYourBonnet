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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, ArrayList<Book> theseBooks) {
        super(context, 0, theseBooks);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        ViewHolder holder;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
            holder = new ViewHolder();
            holder.authorView = (TextView) listItemView.findViewById(R.id.book_author);
            holder.titleView = (TextView) listItemView.findViewById(R.id.book_title);
            holder.descriptionView = (TextView) listItemView.findViewById(R.id.book_description);
            holder.thumbnailView = (ImageView) listItemView.findViewById(R.id.small_thumbnail);

            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        Book currentBook = getItem(position);

        if (currentBook.getmThumbnailUrl() == null) {
            holder.thumbnailView.setImageResource(R.drawable.no_image_found_placeholder);
        } else {
            Picasso.with(getContext()).load(currentBook.getmThumbnailUrl()).into(holder.thumbnailView);
        }

        holder.titleView.setText(currentBook.getmTitle());

        holder.descriptionView.setText(currentBook.getmDescription());

        holder.authorView.setText(currentBook.getmAuthor());

        return listItemView;

    }

    private static class ViewHolder {
        TextView authorView;
        TextView titleView;
        TextView descriptionView;
        ImageView thumbnailView;
    }

}
