package placesafe.placesafe;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.NetworkImageView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Gerson on 02/12/16.
 */
public class AdapterReactionsPlaces extends RecyclerView.Adapter<AdapterReactionsPlaces.ReactionPlacesViewHolder>{
    List<Reaction> reactionList;


    public AdapterReactionsPlaces(List<Reaction> reactionList) {
        this.reactionList = reactionList;
    }

    @Override
    public ReactionPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reaction_places,parent,false);
        ReactionPlacesViewHolder holder = new ReactionPlacesViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ReactionPlacesViewHolder holder, final int position) {
        holder.nameReaction.setText(reactionList.get(position).getName());
        holder.countReaction.setText(reactionList.get(position).getBundle().getString("countReaction"));
        holder.request.requestImage(reactionList.get(position).getUrl_image(), holder.imageIconReaction);
    }


    @Override
    public int getItemCount() {
        return reactionList.size();
    }

    public static class ReactionPlacesViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageIconReaction;
        TextView nameReaction;
        TextView countReaction;
        RequestVolley request;
        public ReactionPlacesViewHolder(View itemView) {
            super(itemView);
            imageIconReaction = (NetworkImageView) itemView.findViewById(R.id.imageIconReactionPlace);
            nameReaction = (TextView) itemView.findViewById(R.id.nameReactionPlace);
            countReaction = (TextView) itemView.findViewById(R.id.countReaction);
            request = RequestVolley.getInstance(itemView.getContext());
        }
    }
}
