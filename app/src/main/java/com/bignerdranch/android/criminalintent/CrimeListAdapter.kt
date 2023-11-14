package com.bignerdranch.android.criminalintent

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID
private const val DATE_FORMAT = "EEE, MMM dd, yyyy HH:mm:ss z"


class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        binding.crimeTitle.text = crime.title

        // In here we can change the following to Locale.getDefault() to get the default format
        val dateformat = DateFormat.getBestDateTimePattern(Locale.getDefault(), DATE_FORMAT)
        val df = SimpleDateFormat(dateformat, Locale.getDefault())
        val formattedDate =  df.format(crime.date).toString()
        binding.crimeDate.text = formattedDate

        binding.root.setOnClickListener {
            onCrimeClicked(crime.id)
        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val onCrimeClicked: (crimeId: UUID) -> Unit
) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        val df = DateFormat.getBestDateTimePattern(Locale.getDefault(), DATE_FORMAT)
        val formattedDate = df.format(crime.date)
        holder.bind(crime, onCrimeClicked)
    }

    override fun getItemCount() = crimes.size
}
