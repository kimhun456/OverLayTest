package com.github.overlaytest.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.overlaytest.databinding.GroupItemBinding
import com.github.overlaytest.domain.enitity.Group
import timber.log.Timber

class GroupListAdapter(
    private val context: Context,
    private val liefCycleOwner: LifecycleOwner,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<GroupItemViewHolder>() {

    private val groupList: MutableList<Group> = mutableListOf()

    fun submitList(newList: List<Group>) {
        Timber.i("list -> $newList")
        groupList.clear()
        groupList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupItemViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val binding: GroupItemBinding = GroupItemBinding.inflate(layoutInflater, parent, false)
        binding.lifecycleOwner = liefCycleOwner
        binding.viewModel = viewModel
        return GroupItemViewHolder(binding, layoutInflater)
    }

    override fun onBindViewHolder(holder: GroupItemViewHolder, position: Int) {
        holder.binding.group = groupList[position]
        holder.binding.memberList.removeAllViewsInLayout()
        holder.setMemberList(groupList[position].memberList)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = groupList.size

}
