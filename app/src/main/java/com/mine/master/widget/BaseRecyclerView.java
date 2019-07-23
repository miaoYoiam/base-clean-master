package com.mine.master.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

/**
 * 实现 item 点击事件
 */
public class BaseRecyclerView extends RecyclerView {

    private static final String TAG = BaseRecyclerView.class.getSimpleName();

    public BaseRecyclerView(Context context) {
        super(context);
        initListener(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initListener(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initListener(context);
    }

    private void initListener(Context context) {
        addOnItemTouchListener(new RecyclerViewItemClickListener(context));
    }

    private OnItemClickListener onItemClickListener;

    private OnItemClickListenerExtended onItemClickListenerExtended;

    private OnItemLongClickListener onItemLongClickListener;

    private GestureDetector gestureDetector;

    private View currentChildView;

    private int currentChildPosition;

    private boolean interceptedByChild;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemClickListenerExtended {
        void onItemClick(View view, int position, float x, float y);
    }

    public interface OnItemLongClickListener {
        boolean onItemClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListenerExtended listener) {
        onItemClickListenerExtended = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }

    private class RecyclerViewItemClickListener implements OnItemTouchListener {

        public RecyclerViewItemClickListener(Context context) {

            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (currentChildView != null && (onItemClickListener != null || onItemClickListenerExtended != null)) {
                        if (currentChildPosition != -1) {
                            if (onItemClickListener != null) {
                                onItemClickListener.onItemClick(currentChildView, currentChildPosition);
                            } else if (onItemClickListenerExtended != null) {
                                float x = e.getX();
                                float y = e.getY();
                                onItemClickListenerExtended.onItemClick(currentChildView, currentChildPosition, x, y);
                            }
                        }

                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    if (currentChildView != null) {
                        View child = currentChildView;
                        if (onItemLongClickListener != null) {
                            if (currentChildPosition != -1 && onItemLongClickListener.onItemClick(currentChildView, currentChildPosition)) {
                                child.setHapticFeedbackEnabled(true);
                                child.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                            }
                        }
                    }
                }

            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            int action = e.getActionMasked();
            boolean isScrollIdle = BaseRecyclerView.this.getScrollState() == RecyclerView.SCROLL_STATE_IDLE;
            if ((action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN)
                    && currentChildView == null
                    && isScrollIdle) {
                float ex = e.getX();
                float ey = e.getY();
                currentChildView = rv.findChildViewUnder(ex, ey);
                currentChildPosition = -1;
                if (currentChildView != null) {
                    currentChildPosition = rv.getChildAdapterPosition(currentChildView);
                    MotionEvent childEvent = MotionEvent.obtain(0, 0, e.getActionMasked(), e.getX() - currentChildView.getLeft(), e.getY() - currentChildView.getTop(), 0);
                    if (currentChildView.onTouchEvent(childEvent)) {
                        interceptedByChild = true;
                    }
                    childEvent.recycle();
                }
            }

            if (currentChildView != null && !interceptedByChild) {
                try {
                    if (e != null) {
                        gestureDetector.onTouchEvent(e);
                    }
                } catch (Exception error) {
                    Log.e(TAG, "onInterceptTouchEvent: " + error.toString());
                }

            }

            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_CANCEL || !isScrollIdle) {
                if (currentChildView != null) {
                    currentChildView = null;
                    interceptedByChild = false;
                }
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
