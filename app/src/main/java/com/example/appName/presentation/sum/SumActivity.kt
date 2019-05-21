package com.example.appName.presentation.sum

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.appName.R
import com.example.appName.presentation.base.BaseActivity
import com.example.appName.presentation.sum.calculation.CalculationFragment
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_sum.*
import kotlinx.android.synthetic.main.view_balance_card.view.*


class SumActivity : BaseActivity<SumViewState, SumPresenter>(
        R.layout.activity_sum
), SumView {

    override val changeFirstNumberIntent: Observable<Long> by lazy {
        firstNumber.textChanges().map { it.toString().toLongOrNull() ?: 0 }.skip(1)
    }

    override val changeSecondNumberIntent: Observable<Long> by lazy {
        secondNumber.textChanges().map { it.toString().toLongOrNull() ?: 0 }.skip(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(fragmentFrame.id, CalculationFragment.newInstance())
                commit()
            }
        }

        val adapter = MyAdapter(arrayOf("1", "2", "3", "4", "5", "6", "7", "8"))
        recyclerView.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    override fun render(viewState: SumViewState) {
        sum.text = viewState.sum.toString()
    }

    class MyAdapter(private val myDataset: Array<String>) :
            RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_balance_card, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.view.textView.text = myDataset[position]

            val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
            animator.addUpdateListener {
                val value = it.animatedValue as Float
                holder.view.alpha = value
                holder.view.scaleX = value
                holder.view.scaleY = value
            }
            animator.duration = 600
            animator.startDelay = 100
            animator.start()
        }

        override fun getItemCount() = myDataset.size
    }
}