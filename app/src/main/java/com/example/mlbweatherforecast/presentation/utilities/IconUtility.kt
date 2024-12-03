package com.example.mlbweatherforecast.presentation.utilities

import android.content.Context
import com.example.mlbweatherforecast.R

class IconUtility {
    fun getIconDrawable(context: Context, icon: String): Int
    {
        val drawableName = "ic_${icon}"

        val resourceId = context.resources.getIdentifier(drawableName, "drawable", context.packageName)

        return if (resourceId != 0)
        {
            resourceId
        }
        else
        {
            R.drawable.ic_01d
        }
    }
}