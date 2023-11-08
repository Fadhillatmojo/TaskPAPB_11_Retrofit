package com.example.taskpapb_11_retrofit.ItemLazdayAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskpapb_11_retrofit.databinding.ItemLazdayBinding
import com.example.taskpapb_11_retrofit.model.LazdayData
import com.example.taskpapb_11_retrofit.model.LazdayModel

typealias onClickLazday = (LazdayData) -> Unit

class LazdayItemAdapter (
    private val listLazday: ArrayList<LazdayData>,
    private val onClickLazday: onClickLazday
) : RecyclerView.Adapter<LazdayItemAdapter.ItemLazdayViewHolder>() {

    // ... (Bagian lain dari kelas adapter)

    inner class ItemLazdayViewHolder(private val binding: ItemLazdayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:LazdayData) {
            with(binding) {
                txtFilmLazday.text = data.title
                itemView.setOnClickListener {
                    onClickLazday(data) // Memanggil onClickLazday dengan LazdayModel, bukan LazdayData
                }
                Glide.with(itemView.context).load(data.image).into(binding.imageLazday)
            }
        }
    }

    // ... (Metode lain dari kelas adapter)
    // untuk menyusun yang akan ditampilkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLazdayViewHolder
    {
        val binding = ItemLazdayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemLazdayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemLazdayViewHolder, position: Int) {
        holder.bind(listLazday[position])
    }

    override fun getItemCount(): Int = listLazday.size

//    // setData tetap sama karena tipe data list telah diubah
//    fun setData(data: ArrayList<LazdayModel>) {
//        listLazday.clear()
//        listLazday.addAll(data.result)
//        notifyDataSetChanged()
//    }

}
