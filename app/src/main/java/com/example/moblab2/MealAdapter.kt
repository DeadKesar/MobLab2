package com.example.moblab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealAdapter(
    private val contracts: List<MealItem>,
    private val onItemClicked: (item: MealItem) -> Unit,
): RecyclerView.Adapter<MealViewHolder>() {

    private fun onViewHolderClicked(position: Int) {
        onItemClicked(contracts[position])
    }
    /**
     * Метод по созданию ViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item, parent, false)
        return MealViewHolder(view, this::onViewHolderClicked)
    }
    /**
     * Кол-во элементов в адаптере (списке)
     */
    override fun getItemCount(): Int {
        return contracts.size
    }
    /**
     * Добавление данных на соответсвующий ViewHolder.
     */
    override fun onBindViewHolder(
        holder: MealViewHolder, position: Int
    ) {
        holder.bind(contracts[position])
    }
}
/**
 * ViewHolder элемента списка.
 * Наследуемся от RecyclerView.ViewHolder
 */
class MealViewHolder(
    itemView: View,
    private val onItemClicked: (position: Int) -> Unit
): RecyclerView.ViewHolder(itemView) {
    private val nameView: TextView =
        itemView.findViewById(R.id.meal_name)
    private val statusView: TextView =
        itemView.findViewById(R.id.meal_status)
    private val categoryView: TextView =
        itemView.findViewById(R.id.meal_category)

    init {
        itemView.setOnClickListener { onItemClicked(getAbsoluteAdapterPosition()) }
    }
    /**
     * Метод для связывания данных с ViewHolder
     */
    fun bind(item: MealItem) {
        nameView.text = item.strMeal
        statusView.text = itemView.context.getString(R.string.area2, item.strArea ?: "ХЗ")
        categoryView.text =
            itemView.context.getString(R.string.category_in_caontactsAdapter,
                item.strCategory ?: "ХЗ")
    }
}