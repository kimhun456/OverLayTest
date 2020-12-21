package com.github.overlaytest.presentation.main

import android.content.res.Resources
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.overlaytest.R
import com.github.overlaytest.databinding.GroupItemBinding
import com.github.overlaytest.domain.enitity.Member

class GroupItemViewHolder(
    val binding: GroupItemBinding,
    private val layoutInflater: LayoutInflater
) : RecyclerView.ViewHolder(binding.root) {

    fun setMemberList(memberList: List<Member>) {
        val size = memberList.size
        binding.memberList.removeAllViews()
        if (size > 6) {
            memberList.subList(0, 5).forEachIndexed { index: Int, member: Member ->
                inflateMemberImageView(index, member, 6)
            }
            inflateMemberEtcTextView(size - 5)
        } else {
            memberList.forEachIndexed { index: Int, member: Member ->
                inflateMemberImageView(index, member, size)
            }
        }
    }

    private fun inflateMemberImageView(index: Int, member: Member, size: Int) {
        val child: ImageView =
            layoutInflater.inflate(
                R.layout.member_image_view,
                binding.memberList,
                false
            ) as ImageView
        val layoutParams: ConstraintLayout.LayoutParams =
            child.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.apply {
            rightMargin = (size - index - 1) * dpToPx(22)
        }
        Glide.with(child)
            .load(member.uri)
            .circleCrop()
            .into(child)
        binding.memberList.addView(child)
    }

    private fun inflateMemberEtcTextView(remainMemberNumber: Int) {
        val child: ConstraintLayout =
            layoutInflater.inflate(
                R.layout.member_etc_image_view,
                binding.memberList,
                false
            ) as ConstraintLayout
        child.findViewById<TextView>(R.id.etc_text).text = "+$remainMemberNumber"
        binding.memberList.addView(child)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

}