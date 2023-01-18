package com.henra.dokterpokemon.utils

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

class PokemonCard(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        if (measuredWidth < measuredHeight) {
            setMeasuredDimension(measuredWidth, measuredWidth)
//        } else {
//            setMeasuredDimension(measuredHeight, measuredHeight)
//        }
    }
}