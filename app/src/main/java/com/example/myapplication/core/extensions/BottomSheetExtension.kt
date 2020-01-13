package com.example.myapplication.core.extensions

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun BottomSheetBehavior<View>.isExpanded() = state == BottomSheetBehavior.STATE_EXPANDED

fun BottomSheetBehavior<View>.expand() {
    state = BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<View>.collapse() {
    state = BottomSheetBehavior.STATE_COLLAPSED
}

fun BottomSheetBehavior<View>.hide() {
    state = BottomSheetBehavior.STATE_HIDDEN
}