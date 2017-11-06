package actualsolusi.com.samplesqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by erick on 06/11/2017.
 */

public class RecyclerWishListener implements RecyclerView.OnItemTouchListener {

    private RecyclerTouchListener listener;
    private GestureDetector gd;

    public interface RecyclerTouchListener {
        public void OnClickItem(View v,int position);
        public void OnLongClickItem(View v,int position);
    }

    public RecyclerWishListener(Context ctx, final RecyclerView rv,
                                final RecyclerTouchListener listener){
        this.listener = listener;
        gd = new GestureDetector(ctx,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                View v = rv.findChildViewUnder(e.getX(),e.getY());
                listener.OnLongClickItem(v,rv.getChildAdapterPosition(v));
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View v = rv.findChildViewUnder(e.getX(),e.getY());
                listener.OnClickItem(v,rv.getChildAdapterPosition(v));
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(),e.getY());
        return (child != null && gd.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}