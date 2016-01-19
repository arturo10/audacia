package com.audacia.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.micros.iteso.audacia.R;
import com.audacia.beans.Book;
import java.util.List;


/**
 * Created by root on 12/01/16.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    List<Book> booksList;

    public static class BookViewHolder extends RecyclerView.ViewHolder{
        CardView cvBook;
        ImageView coverPage;

        BookViewHolder(View itemView){
            super(itemView);
            cvBook=(CardView)itemView.findViewById(R.id.cv);
            coverPage=(ImageView)itemView.findViewById(R.id.cover_page);
        }
    }
    public BookAdapter(List<Book> books){
        this.booksList=books;
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_cardview, viewGroup, false);
        BookViewHolder pvh = new BookViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int i) {
        bookViewHolder.coverPage.setBackground(booksList.get(i).getImage());
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
