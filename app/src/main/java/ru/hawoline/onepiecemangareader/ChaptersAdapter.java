package ru.hawoline.onepiecemangareader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.ChapterViewHolder>{
    private ArrayList<Chapter> chapters;
    public static MainView mainView;

    public ChaptersAdapter(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate( R.layout.chapter_item, parent, false);
        return new ChapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        final Chapter currentChapter = chapters.get(position);
        holder.chapter_name_text_view.setText(currentChapter.getTitle());
        holder.chapter_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.showChapter(currentChapter);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    class ChapterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout chapter_parent;
        TextView chapter_name_text_view;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            chapter_parent = itemView.findViewById(R.id.chapter_item_row);
            chapter_name_text_view = itemView.findViewById(R.id.chapter_name_text_view);
        }
    }
}
