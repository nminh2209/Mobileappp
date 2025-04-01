package com.example.musicplayer.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.musicplayer.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
    }
}
