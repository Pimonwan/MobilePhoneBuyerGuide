package com.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.myapplication.DataString
import com.myapplication.R
import com.myapplication.view.viewInterface.SortButtonInterface
import kotlinx.android.synthetic.main.fragment_fragment_dialog.*

class FragmentDialog(listener: SortButtonInterface) : DialogFragment() {

    private val mRadioListener: SortButtonInterface = listener

    private fun setRadioButton() {
        sortChoicesButton.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = view!!.findViewById<RadioButton>(checkedId)
            when {
                radioButton.text.toString() == DataString.optionPriceLowToHigh -> {
                    mRadioListener.sortData(DataString.optionPriceLowToHigh)
                    this.dismiss()
                }
                radioButton.text.toString() == DataString.optionPriceHighToLow -> {
                    mRadioListener.sortData(DataString.optionPriceHighToLow)
                    this.dismiss()
                }
                else -> {
                    mRadioListener.sortData(DataString.optionRateHighToLow)
                    this.dismiss()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRadioButton()
    }
}