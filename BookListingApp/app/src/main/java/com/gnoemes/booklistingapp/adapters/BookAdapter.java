package com.gnoemes.booklistingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnoemes.booklistingapp.R;
import com.gnoemes.booklistingapp.Utils;
import com.gnoemes.booklistingapp.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private static final String NO_IMAGE = "https://www.justpro.co/img/no-image.png";
    private List<Book> books;
    private final OnItemClickListener listener;


    public BookAdapter(List<Book> books, final OnItemClickListener listener) {
        this.books = books;
        this.listener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.click(books.get(position), listener);
        holder.title.setText(books.get(position).getTitle());
        holder.author.setText(books.get(position).getAuthorsString());
        holder.description.setText(books.get(position).getDescription());
        if (books.get(position).getImageURL().equals(Utils.ERR_MESSAGE)) {
            books.get(position).setUrl(NO_IMAGE);
        }
        Picasso.with(holder.imageView.getContext())
                .load(books.get(position).getImageURL())
                .into(holder.imageView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;
        private TextView author;
        private TextView description;

        public BookViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_book);
            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        public void click(final Book book, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(book);
                }
            });
        }

    }
    public interface OnItemClickListener {
        void onClick(Book item);
    }
}
