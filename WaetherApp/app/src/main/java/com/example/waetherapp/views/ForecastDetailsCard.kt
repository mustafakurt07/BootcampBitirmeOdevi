package com.example.waetherapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.getTextOrThrow
import com.example.waetherapp.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.detailed_temp_card_view.view.*

class ForecastDetailsCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.detailed_temp_card_view, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ForecastDetailsCard)
        val description =
            typedArray.getTextOrThrow(R.styleable.ForecastDetailsCard_detDescription)
        val unit = typedArray.getTextOrThrow(R.styleable.ForecastDetailsCard_detUnit)
        val value = typedArray.getTextOrThrow(R.styleable.ForecastDetailsCard_detValue)

        this.description.text = description
        this.unit.text = unit
        this.value.text = value

        typedArray.recycle()
    }
}