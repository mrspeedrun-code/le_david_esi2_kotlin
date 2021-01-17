package com.example.mds_dle_kotlin_todo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(val title: String, val text: String): Parcelable {
}

