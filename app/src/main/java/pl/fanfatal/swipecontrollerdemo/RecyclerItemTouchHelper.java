package pl.fanfatal.swipecontrollerdemo;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;

import android.graphics.Canvas;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import pl.fanfatal.swipecontrollerdemo.PlayersDataAdapter.PlayerViewHolder;

/**
 * Created by ravi on 29/09/17.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;
    private int offsetWidth;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground ;
            offsetWidth = -(viewHolder.itemView.findViewById(R.id.ib_delete).getMeasuredWidth() * 2);
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {

        if (actionState == ACTION_STATE_SWIPE) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
            /*Log.e("onChildDrawOver_1: ", "dX: "+ dX);
            dX = Math.min(dX, buttonWidth * 2);
            Log.e("onChildDrawOver_2: ", "dX: "+ dX);*/
            if (dX > offsetWidth)
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
            //else
             //   super.onChildDrawOver(c,recyclerView,viewHolder,(buttonWidth), dY,actionState, isCurrentlyActive);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        if (actionState == ACTION_STATE_SWIPE) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
            /*Log.e("onChildDraw_1: ", "dX: "+ dX);
            dX = Math.min(dX, buttonWidth * 2);
            Log.e("onChildDraw_2: ", "dX: "+ dX);*/

            if (dX > offsetWidth)
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
            //else
            //    super.onChildDraw(c,recyclerView,viewHolder,(buttonWidth), dY,actionState, isCurrentlyActive);
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull ViewHolder viewHolder,
        @NonNull ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
