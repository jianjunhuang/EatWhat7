package com.demo.jianjunhuang.mvptools.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
 * @since 2017/2/10.
 */

public class CommonViewHolder {

    private int position;
    protected View convertView;
    protected SparseArray<View> views;
    private ViewGroup parent;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.position = position;
        this.views = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        CommonViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new CommonViewHolder(context, parent, layoutId, position);

        } else {
            viewHolder = (CommonViewHolder) convertView.getTag();
            //refresh position
            viewHolder.position = position;
        }
        viewHolder.parent = parent;
        return viewHolder;
    }

    public int getPosition() {
        return position;
    }

    public ViewGroup getParent() {
        return parent;
    }

    /**
     * get view's instance by id
     *
     * @param id  view's id
     * @param <T> view
     * @return view
     */
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = convertView.findViewById(id);
        }
        return (T) view;
    }

    /**
     * get convert view
     *
     * @return convert view
     */
    public View getConvertView() {
        return convertView;
    }

    /**
     * set text
     *
     * @param id   view id
     * @param text string
     * @return ViewHolder
     */
    public CommonViewHolder setText(int id, String text) {
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
    public CommonViewHolder setText(int id, int text) {
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
    public CommonViewHolder setTextType(int id, Typeface typeface) {
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
    public CommonViewHolder setImgeResoure(int id, int drawableId) {
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
    public CommonViewHolder setImgeBitmap(int id, Bitmap bitmap) {
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
    public CommonViewHolder setImageBitmap(int id, Bitmap bm) {
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
    public CommonViewHolder setChecked(int id, boolean isChecked) {
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
    public CommonViewHolder setBtnBackground(int id, Drawable drawable) {
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
    public CommonViewHolder setBtnBackgroundColor(int id, int color) {
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
    public CommonViewHolder setBtnBackgroundResource(int id, int drawable) {
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
    public CommonViewHolder setOnClickListener(int id, View.OnClickListener listener) {
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
    public CommonViewHolder setOnLongClickListener(int id, View.OnLongClickListener listener) {
        View view = getView(id);
        view.setOnLongClickListener(listener);
        return this;
    }

}
