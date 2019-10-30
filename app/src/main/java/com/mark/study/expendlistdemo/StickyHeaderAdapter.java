package com.mark.study.expendlistdemo;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public interface StickyHeaderAdapter<T extends RecyclerView.ViewHolder> {

    /**
     * Indicate this item_preview_viewpgaer has no corresponding header.
     */
    long NO_HEADER = -1L;

    /**
     * Returns the header id for the item_preview_viewpgaer at the given position.
     *
     * @param childAdapterPosition the item_preview_viewpgaer adapter position
     * @return the header id, or {@link #NO_HEADER} if this item_preview_viewpgaer has no header.
     */
    long getHeaderId(int childAdapterPosition);

    /**
     * Creates a new header ViewHolder.
     *
     * @param parent the header's view parent, typically the RecyclerView
     * @return a view holder for the created view
     */
    T onCreateHeaderViewHolder(ViewGroup parent);

    /**
     * Creates a new header ViewHolder.
     *
     * @param parent the header's view parent, typically the RecyclerView
     * @param adapterPosition 指定位置header
     * @return a view holder for the created view
     */
    T onCreateHeaderViewHolder(ViewGroup parent,int adapterPosition);

    /**
     * Display the data at the specified position.
     * <p>
     *     PLEASE NOTE THE PARAM IS CHILD POSITION!
     *     IF YOU WANT TO USE HEADER ID, PLEASE CALL YOUR {@link #getHeaderId(int)}.
     * </p>
     * @param holder the header view holder
     * @param childAdapterPosition the child item_preview_viewpgaer position, can be used to retrieve header id.
     */
    void onBindHeaderViewHolder(T holder, int childAdapterPosition);

}
