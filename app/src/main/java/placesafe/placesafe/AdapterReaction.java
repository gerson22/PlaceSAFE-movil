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
    public void onBindViewHolder(final ReactionViewHolder holder, final int position) {
        holder.nameReaction.setText(reactionList.get(position).getName());

        holder.imageIconReaction.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                HashMap<String,String> data = new HashMap<String, String>();
                data.put("lat", reactionList.get(position).getBundle().getString("lat"));
                data.put("lng", reactionList.get(position).getBundle().getString("lng"));
                data.put("nameReaction",reactionList.get(position).getName());
                data.put("nickname", usuarioSqlLiteHelper.getInstance(holder.itemView.getContext(), "DBUsuarios", null, 1).getUserLog(holder.itemView.getContext()));
                holder.request.requestString("POST", "/userReaction", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("cambio")) {
                            Toast.makeText(holder.itemView.getContext(),reactionList.get(position).getName(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(holder.itemView.getContext(), response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, data);
            }
        });
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
        Intent intentRecived;
        public ReactionViewHolder(View itemView) {
            super(itemView);
            imageIconReaction = (NetworkImageView) itemView.findViewById(R.id.imageIconReaction);
            nameReaction = (TextView) itemView.findViewById(R.id.nameReaction);
            request = RequestVolley.getInstance(itemView.getContext());
        }
    }
}
