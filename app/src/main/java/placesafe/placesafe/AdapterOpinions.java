package placesafe.placesafe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gerson on 29/11/16.
 */
public class AdapterOpinions extends RecyclerView.Adapter<AdapterOpinions.OpinionsViewHolder> {

    List<Opinion> opinions;

    public AdapterOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    @Override
    public OpinionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment,parent,false);
        OpinionsViewHolder holder = new OpinionsViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(OpinionsViewHolder holder, int position) {
        holder.iconcomment.setImageResource(R.drawable.iconcoment);
        holder.user_text.setText(opinions.get(position).getUser());
        holder.opinions_text.setText(opinions.get(position).getOpinion_text());
    }

    @Override
    public int getItemCount() {
        return opinions.size();
    }

    public static class OpinionsViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconcomment;
        TextView opinions_text,user_text;
        public OpinionsViewHolder(View itemView) {

            super(itemView);
            iconcomment = (ImageView) itemView.findViewById(R.id.iconcomment);
            opinions_text = (TextView) itemView.findViewById(R.id.opinionText);
            user_text = (TextView) itemView.findViewById(R.id.user);

        }
    }
}

