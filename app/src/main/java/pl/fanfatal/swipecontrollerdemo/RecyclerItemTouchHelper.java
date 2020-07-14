package pl.fanfatal.swipecontrollerdemo;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import pl.fanfatal.swipecontrollerdemo.PlayersDataAdapter.PlayerViewHolder;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;

/**
 * Created by ravi on 29/09/17.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;
    private int offsetWidth;
    private boolean swipingBack;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.e("onSelectedChanged", actionState+"");
        if (viewHolder != null) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground ;
            offsetWidth = -(viewHolder.itemView.findViewById(R.id.ib_delete).getMeasuredWidth() * 2);
            getDefaultUIUtil().onSelected(foregroundView);
        }

        //super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        //Log.e("onChildDrawOver", "dX: " + dX + " offsetWidth: " + offsetWidth);
        //super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //if (actionState == ACTION_STATE_SWIPE) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
            //if (isCurrentlyActive) {
                //dX = Math.max(dX, offsetWidth);
                //getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                //        actionState, isCurrentlyActive);
            //}
        //}

        dX = Math.max(dX, offsetWidth);
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);

        if (dX > offsetWidth) {
            foregroundView.setClickable(true);
        }
        else
            foregroundView.setClickable(false);

        /*if (actionState == ACTION_STATE_SWIPE) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
            /*Log.e("onChildDrawOver_1: ", "dX: "+ dX);
            dX = Math.min(dX, buttonWidth * 2);
            Log.e("onChildDrawOver_2: ", "dX: "+ dX);
            if (dX > offsetWidth)
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
            //else*/
               // super.onChildDrawOver(c,recyclerView,viewHolder,(offsetWidth), dY,actionState, isCurrentlyActive);
        //}
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //Log.e("onChildDraw", "dX: " + dX + " offsetWidth: " + offsetWidth);
        //if (actionState == ACTION_STATE_SWIPE) {
            final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
            //if (isCurrentlyActive) {
                //dX = Math.max(dX, offsetWidth);
                //getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                //        actionState, isCurrentlyActive);
            //}
        //}

        dX = Math.max(dX, offsetWidth);
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
        if (dX > offsetWidth) {
            foregroundView.setClickable(true);
        }
        else
            foregroundView.setClickable(false);

        //if (actionState == ACTION_STATE_SWIPE) {
        //    final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
            /*Log.e("onChildDraw_1: ", "dX: "+ dX);
            dX = Math.min(dX, buttonWidth * 2);
            Log.e("onChildDraw_2: ", "dX: "+ dX);*/

            /*if (dX > offsetWidth)
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
            //else*/
                //super.onChildDraw(c,recyclerView,foregroundView,(offsetWidth), dY,actionState, isCurrentlyActive);
        //}
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.e("clearView", "clearView");
        final View foregroundView = ((PlayerViewHolder) viewHolder).foreground;
        getDefaultUIUtil().clearView(foregroundView);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull ViewHolder viewHolder,
        @NonNull ViewHolder target) {
        Log.e("onMove", "onMove");
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
