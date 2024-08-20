package com.example.pddiary.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pddiary.R
import com.example.pddiary.models.DairyButtonModel
import com.example.pddiary.models.DairyListItem
import com.example.pddiary.models.DairyModel
import com.google.android.material.slider.Slider


class DairyAdapter(
    private val list: MutableList<DairyListItem>, private var onItemClicked: (() -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ROW = 1
        const val BUTTON = 2
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.time)
        val on: CheckBox = itemView.findViewById(R.id.on)
        val asleep: CheckBox = itemView.findViewById(R.id.asleep)
        val onWithTroublesome: CheckBox = itemView.findViewById(R.id.on_with_troublesome)
        val onWithoutTroublesome: CheckBox = itemView.findViewById(R.id.on_without_troublesome)
        val off: CheckBox = itemView.findViewById(R.id.off)
        val med1Status: CheckBox = itemView.findViewById(R.id.med_1_status)
        val med2Status: CheckBox = itemView.findViewById(R.id.med_2_status)
        val measurementSlider: Slider = itemView.findViewById(R.id.measurement_slider)
    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.dairy_save_button)
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is DairyModel -> ROW
            is DairyButtonModel -> BUTTON
            else -> throw IllegalArgumentException("unsupported type is sent to dairyAdapter")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ROW -> ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_dairy_row, parent, false)
            )

            BUTTON -> ButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_dairy_save_button, parent, false)
            )

            else -> throw IllegalArgumentException("unsupported type is sent to dairyAdapter")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = list[position]) {
            is DairyModel -> {
                val mHolder = holder as ItemViewHolder

                val backgroundColor = if (position % 2 == 0) Color.LTGRAY else Color.WHITE
                mHolder.itemView.setBackgroundColor(backgroundColor)
                mHolder.asleep.setOnCheckedChangeListener(null)
                mHolder.on.setOnCheckedChangeListener(null)
                mHolder.onWithTroublesome.setOnCheckedChangeListener(null)
                mHolder.onWithoutTroublesome.setOnCheckedChangeListener(null)
                mHolder.off.setOnCheckedChangeListener(null)
                mHolder.med1Status.setOnCheckedChangeListener(null)
                mHolder.med2Status.setOnCheckedChangeListener(null)
                mHolder.measurementSlider.clearOnChangeListeners()

                mHolder.time.text = item.time
                mHolder.asleep.isChecked = item.asleep
                if (item.asleep) {
                    mHolder.measurementSlider.isEnabled = false
                }
                mHolder.on.isChecked = item.on
                mHolder.onWithTroublesome.isChecked = item.onWithTroublesome
                mHolder.onWithoutTroublesome.isChecked = item.onWithoutTroublesome
                mHolder.off.isChecked = item.off
                mHolder.med1Status.isChecked = item.med1Status
                mHolder.med2Status.isChecked = item.med2Status
                mHolder.measurementSlider.value = item.measurement?.toFloat() ?: 1f

                mHolder.measurementSlider.addOnChangeListener { _, value, _ ->
                    item.measurement = value.toInt()
                    list[position] = item
                }

                mHolder.measurementSlider.isEnabled = !item.asleep

                val checkboxListener = View.OnClickListener {
                    item.asleep = mHolder.asleep.isChecked
                    item.on = mHolder.on.isChecked
                    item.onWithTroublesome = mHolder.onWithTroublesome.isChecked
                    item.onWithoutTroublesome = mHolder.onWithoutTroublesome.isChecked
                    item.off = mHolder.off.isChecked
                    item.med1Status = mHolder.med1Status.isChecked
                    item.med2Status = mHolder.med2Status.isChecked

                    if (it == mHolder.asleep) {
                        item.on = false
                        item.onWithTroublesome = false
                        item.onWithoutTroublesome = false
                        item.off = false
                        if (item.asleep) {
                            item.measurement = null
                            mHolder.measurementSlider.isEnabled = false
                        }
                    } else {
                        mHolder.measurementSlider.isEnabled = true
                        when (it) {
                            mHolder.on -> {
                                item.asleep = false
                                item.onWithTroublesome = false
                                item.onWithoutTroublesome = false
                                item.off = false
                            }

                            mHolder.onWithTroublesome -> {
                                item.asleep = false
                                item.on = false
                                item.onWithoutTroublesome = false
                                item.off = false
                            }

                            mHolder.onWithoutTroublesome -> {
                                item.asleep = false
                                item.on = false
                                item.onWithTroublesome = false
                                item.off = false
                            }

                            mHolder.off -> {
                                item.asleep = false
                                item.on = false
                                item.onWithTroublesome = false
                                item.onWithoutTroublesome = false
                            }
                            mHolder.med1Status -> {
                                item.med1Status = mHolder.med1Status.isChecked
                            }
                            mHolder.med2Status -> {
                                item.med2Status = mHolder.med2Status.isChecked
                            }
                        }
                    }

                    notifyItemChanged(position)
                }

                mHolder.asleep.setOnClickListener(checkboxListener)
                mHolder.on.setOnClickListener(checkboxListener)
                mHolder.onWithTroublesome.setOnClickListener(checkboxListener)
                mHolder.onWithoutTroublesome.setOnClickListener(checkboxListener)
                mHolder.off.setOnClickListener(checkboxListener)
                mHolder.med1Status.setOnClickListener(checkboxListener)
                mHolder.med2Status.setOnClickListener(checkboxListener)
            }

            is DairyButtonModel -> {
                val bHolder = holder as ButtonViewHolder
                bHolder.button.setOnClickListener {
                    onItemClicked()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}