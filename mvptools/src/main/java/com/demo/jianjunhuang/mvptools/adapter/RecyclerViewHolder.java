package com.demo.jianjunhuang.mvptools.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/6.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener onItemClickListener;

    //缓存子views
    private SparseArray<View> mViews;

    private Context context;

    private View itemView;

    private int position;

    private RecyclerViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        mViews = new SparseArray<>();
    }

    public static RecyclerViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        //TODO 每次都新建！？
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(context, itemView, parent);
        return recyclerViewHolder;
    }

    public <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
        }
        return (T) view;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public View getConvertView() {
        return itemView;
    }

    /**
     * set text
     *
     * @param id   view id
     * @param text string
     * @return ViewHolder
     */
    public RecyclerViewHolder setText(int id, String text) {
        TextView view = getView(id);
        view.setText(text);
        return this;
    }

    /**
     * set text
     *
     * @param id   view id
     * @param text string
     * @return ViewHolder
     */
    public RecyclerViewHolder setText(int id, int text) {
        TextView view = getView(id);
        view.setText(text);
        return this;
    }

    /**
     * set text's type
     *
     * @param id       view id
     * @param typeface Typeface object
     * @return ViewHolder
     */
    public RecyclerViewHolder setTextType(int id, Typeface typeface) {
        TextView view = getView(id);
        view.setTypeface(typeface);
        return this;
    }

    /**
     * set img
     *
     * @param id         img id
     * @param drawableId drawable id
     * @return ViewHolder
     */
    public RecyclerViewHolder setImgeResoure(int id, int drawableId) {
        ImageView img = getView(id);
        img.setImageResource(drawableId);
        return this;
    }

    /**
     * set img
     *
     * @param id     imageView id
     * @param bitmap bitmap
     * @return viewHolder
     */
    public RecyclerViewHolder setImgeBitmap(int id, Bitmap bitmap) {
        ImageView img = getView(id);
        img.setImageBitmap(bitmap);
        return this;
    }


    /**
     * set img
     *
     * @param id img id
     * @param bm bitmap
     * @return ViewHolder
     */
    public RecyclerViewHolder setImageBitmap(int id, Bitmap bm) {
        ImageView img = getView(id);
        img.setImageBitmap(bm);
        return this;
    }

    /**
     * set checkBox isChecked
     *
     * @param id        checkBox id
     * @param isChecked true or false
     * @return ViewHolder
     */
    public RecyclerViewHolder setChecked(int id, boolean isChecked) {
        CheckBox cb = getView(id);
        cb.setChecked(isChecked);
        return this;
    }

    /**
     * set button's background
     *
     * @param id       button's id
     * @param drawable drawable
     * @return ViewHolder
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public RecyclerViewHolder setBtnBackground(int id, Drawable drawable) {
        Button btn = getView(id);
        btn.setBackground(drawable);
        return this;
    }

    /**
     * set button's background color
     *
     * @param id    button's id
     * @param color color
     * @return ViewHolder
     */
    public RecyclerViewHolder setBtnBackgroundColor(int id, int color) {
        Button btn = getView(id);
        btn.setBackgroundColor(color);
        return this;
    }

    /**
     * set button's background resource
     *
     * @param id       button's id
     * @param drawable drawable id
     * @return ViewHolder
     */
    public RecyclerViewHolder setBtnBackgroundResource(int id, int drawable) {
        Button btn = getView(id);
        btn.setBackgroundResource(drawable);
        return this;
    }

    /**
     * set view's onClickListener
     *
     * @param id       view's id
     * @param listener OnClickListener
     * @return ViewHolder
     */
    public RecyclerViewHolder setOnClickListener(int id, View.OnClickListener listener) {
        View view = getView(id);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * set view's onLongClickListener
     *
     * @param id       view's id
     * @param listener OnLongClickListener
     * @return ViewHolder
     */
    public RecyclerViewHolder setOnLongClickListener(int id, View.OnLongClickListener listener) {
        View view = getView(id);
        view.setOnLongClickListener(listener);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(getConvertView(), position);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        getConvertView().setOnClickListener(this);
    }

    public interface OnItemClickListener {
        void onItemClick(View convertView, int position);
    }


}
