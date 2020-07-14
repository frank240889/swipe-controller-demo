package pl.fanfatal.swipecontrollerdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

class PlayersDataAdapter extends RecyclerView.Adapter<PlayersDataAdapter.PlayerViewHolder> {
    public List<Player> players;

    public static class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(v, getAdapterPosition());
        }

        interface OnItemClickListener {
            void onItemClick(View v, int position);
        }
        ConstraintLayout background;
        LinearLayout foreground;
        SwipeRevealLayout swipeRevealLayout;

        private TextView name, nationality, club, rating, age;
        private OnItemClickListener mOnItemClickListener;

        public PlayerViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            swipeRevealLayout = view.findViewById(R.id.srl_reveal_container);
            background = view.findViewById(R.id.background);
            foreground = view.findViewById(R.id.foreground);
            name = (TextView) view.findViewById(R.id.name);
            nationality = (TextView) view.findViewById(R.id.nationality);
            club = (TextView) view.findViewById(R.id.club);
            rating = (TextView) view.findViewById(R.id.rating);
            age = (TextView) view.findViewById(R.id.age);
            mOnItemClickListener = onItemClickListener;
            MaterialButton edit = view.findViewById(R.id.ib_edit);
            MaterialButton delete = view.findViewById(R.id.ib_delete);
            swipeRevealLayout.setLeftOffsetThreshold(edit.getMinWidth() + delete.getMinWidth() - 128);
            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
        }
    }

    private PlayerViewHolder.OnItemClickListener mOnItemClickListener;
    public PlayersDataAdapter(List<Player> players, PlayerViewHolder.OnItemClickListener onItemClickListener) {
        this.players = players;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_row, parent, false);

        return new PlayerViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.name.setText(player.getName());
        holder.nationality.setText(player.getNationality());
        holder.club.setText(player.getClub());
        holder.rating.setText(player.getRating().toString());
        holder.age.setText(player.getAge().toString());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
