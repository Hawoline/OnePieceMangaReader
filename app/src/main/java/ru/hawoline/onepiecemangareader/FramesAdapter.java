package ru.hawoline.onepiecemangareader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class FramesAdapter extends RecyclerView.Adapter<FramesAdapter.FramesViewHolder> {
    private Chapter chapter;

    public FramesAdapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @NonNull
    @Override
    public FramesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item, parent, false);
        return new FramesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FramesViewHolder holder, int position) {
        Picasso.with(holder.frame_parent.getContext())
                .load(chapter.getFrames().get(position))
                .into(holder.frame_image_view);
    }

    @Override
    public int getItemCount() {
        return chapter.getFrames().size();
    }

    class FramesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout frame_parent;
        ImageView frame_image_view;

        public FramesViewHolder(@NonNull View itemView) {
            super(itemView);

            frame_parent = itemView.findViewById(R.id.frame_item);
            frame_image_view = itemView.findViewById(R.id.frame_image_view);
        }
    }
}
