package com.example.kubik.picturetime.kotlin

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlin.collections.ArrayList

/**
 * Created by kubik on 9/14/17.
 */
abstract class StrangeAdapter<T>(@ColorInt color: Int) : RecyclerView.Adapter<StrangeAdapter.StrangeHolder<T>>() {


    protected val itemList: ArrayList<T?> = ArrayList()
    @ColorInt
    protected val pourColor: Int = color

    private val emptyPositions: ArrayList<Int> = ArrayList()

    override fun getItemCount(): Int = itemList.size

    private fun addEmptyList() {
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
    }

    private fun addEmptyItem() {
        itemList.add(null)
        emptyPositions.add(itemList.size - 1)
    }

    fun addData(data: List<T>) {
        for (i in emptyPositions.reversed())
            itemList.removeAt(i)
        emptyPositions.clear()
        itemList.addAll(data)
        addEmptyItem()
        notifyDataSetChanged()
    }

    fun clearData() {
        itemList.clear()
        emptyPositions.clear()
        addEmptyList()
        notifyDataSetChanged()
    }

    open class StrangeHolder<in T>(itemView: View, private @ColorInt val color: Int) : RecyclerView.ViewHolder(itemView) {

        private val VALUE_IMAGE_VIEW = "image_view"
        private val VALUE_TEXT_VIEW = "text_view"
        private val VALUE_VIEW = "view"

        private val EMPTY_TEXT = "\t\t\t\t\t\t\t\t\t"

        private val viewTypeIdMap: HashMap<Int, String> = HashMap()

        init {
            if (itemView is ViewGroup)
                createViewIdMap(itemView)
        }

        private fun createViewIdMap(viewGroup: ViewGroup) {
            (0 until viewGroup.childCount)
                    .map { viewGroup.getChildAt(it) }
                    .forEach {
                        when (it) {
                            is ViewGroup -> createViewIdMap(it)
                            is ImageView -> viewTypeIdMap.put(it.id, VALUE_IMAGE_VIEW)
                            is TextView -> viewTypeIdMap.put(it.id, VALUE_TEXT_VIEW)
                            else -> viewTypeIdMap.put(it.id, VALUE_VIEW)
                        }
                    }
        }

        private fun setBackgrounds(@ColorInt pourColor: Int) {
            for (id in viewTypeIdMap.keys) {
                when (viewTypeIdMap[id]) {
                    VALUE_IMAGE_VIEW -> {
                        val iv: ImageView = itemView.findViewById(id) as ImageView
                        iv.setImageResource(0)
                        iv.setBackgroundColor(pourColor)
                    }
                    VALUE_TEXT_VIEW -> {
                        val tv: TextView = itemView.findViewById(id) as TextView
                        tv.text = EMPTY_TEXT
                        tv.setBackgroundColor(pourColor)
                    }
                    VALUE_VIEW -> itemView.findViewById(id).setBackgroundColor(pourColor)
                }
            }
        }

        open fun setItem(item: T) {
            if (item == null) {
                setBackgrounds(color)
            } else {
                setBackgrounds(Color.TRANSPARENT)
            }
        }

    }

    init {
        addEmptyList()
    }
}