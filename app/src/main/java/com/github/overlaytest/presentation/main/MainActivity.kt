package com.github.overlaytest.presentation.main

import android.content.res.Resources
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.overlaytest.R
import com.github.overlaytest.databinding.ActivityMainBinding
import com.github.overlaytest.domain.enitity.Member
import com.github.overlaytest.domain.usecase.GetMembersUseCase
import timber.log.Timber


class MainActivity : AppCompatActivity() {


    private lateinit var rootView: ConstraintLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = GroupListAdapter(this, this, viewModel)
        binding.groupList.layoutManager = LinearLayoutManager(this)
        binding.groupList.adapter = adapter
        viewModel.groupList.observe(this, { list -> adapter.submitList(list) })
        viewModel.refresh()
    }

    private fun initMemberList() {
        Timber.i("initMemberList()")
        val memberList: List<Member> = GetMembersUseCase().execute()
        val size = memberList.size
        rootView.removeAllViews()
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
            layoutInflater.inflate(R.layout.member_image_view, rootView, false) as ImageView
        val layoutParams: ConstraintLayout.LayoutParams =
            child.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.apply {
            rightMargin = (size - index - 1) * dpToPx(22)
        }
        Glide.with(child)
            .load(member.uri)
            .circleCrop()
            .into(child)
        rootView.addView(child)
    }

    private fun inflateMemberEtcTextView(remainMemberNumber: Int) {
        val child: ConstraintLayout =
            layoutInflater.inflate(
                R.layout.member_etc_image_view,
                rootView,
                false
            ) as ConstraintLayout
        child.findViewById<TextView>(R.id.etc_text).text = "+$remainMemberNumber"
        rootView.addView(child)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}