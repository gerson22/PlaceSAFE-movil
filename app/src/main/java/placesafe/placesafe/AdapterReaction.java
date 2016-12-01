package placesafe.placesafe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Gerson on 01/12/16.
 */
public class AdapterReaction extends RecyclerView.Adapter<AdapterReaction.ReactionViewHolder>{
    List<Reaction> reactionList;


    public AdapterReaction(List<Reaction> reactionList) {
        this.reactionList = reactionList;
    }

    @Override
    public ReactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_reaction,parent,false);
        ReactionViewHolder holder = new ReactionViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReactionViewHolder holder, int position) {
        holder.nameReaction.setText(reactionList.get(position).getName());
        holder.request.requestImage(reactionList.get(position).getUrl_image(), holder.imageIconReaction);
    }


    @Override
    public int getItemCount() {
        return reactionList.size();
    }

    public static class ReactionViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageIconReaction;
        TextView nameReaction;
        RequestVolley request;
        public ReactionViewHolder(View itemView) {
            super(itemView);
            imageIconReaction = (NetworkImageView) itemView.findViewById(R.id.imageIconReaction);
            nameReaction = (TextView) itemView.findViewById(R.id.nameReaction);
            request = RequestVolley.getInstance(itemView.getContext());
        }
    }
}
