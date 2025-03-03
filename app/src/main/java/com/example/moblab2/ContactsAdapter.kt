package com.example.moblab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactsAdapter(
    private val contracts: List<ContactItem>,
    private val onItemClicked: (item: ContactItem) -> Unit,
): RecyclerView.Adapter<ContactViewHolder>() {

    private fun onViewHolderClicked(position: Int) {
        onItemClicked(contracts[position])
    }
    /**
     * Метод по созданию ViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view, this::onViewHolderClicked)
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
        holder: ContactViewHolder, position: Int
    ) {
        holder.bind(contracts[position])
    }
}
/**
 * ViewHolder элемента списка.
 * Наследуемся от RecyclerView.ViewHolder
 */
class ContactViewHolder(
    itemView: View,
    private val onItemClicked: (position: Int) -> Unit
): RecyclerView.ViewHolder(itemView) {
    private val nameView: TextView =
        itemView.findViewById(R.id.contact_name)
    private val statusView: TextView =
        itemView.findViewById(R.id.contact_status)
    private val categoryView: TextView =
        itemView.findViewById(R.id.contact_category)

    init {
        itemView.setOnClickListener { onItemClicked(getAbsoluteAdapterPosition()) }
    }
    /**
     * Метод для связывания данных с ViewHolder
     */
    fun bind(item: ContactItem) {
        nameView.text = item.strMeal
        statusView.text = "Зона: ${item.strArea ?: "ХЗ"}"
        categoryView.text = "Категоря: ${item.strCategory ?: "ХЗ"}"
    }
}