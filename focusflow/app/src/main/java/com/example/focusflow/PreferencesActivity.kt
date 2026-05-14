package com.example.focusflow

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * PreferencesActivity — allows the user to save their name,
 * preferred study style, and theme mood using SharedPreferences.
 * Saved values are loaded automatically when the screen opens.
 */
class PreferencesActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var spinnerStudyStyle: Spinner
    private lateinit var spinnerTheme: Spinner

    // SharedPreferences key constants
    companion object {
        const val PREFS_NAME   = "FocusFlowPrefs"
        const val KEY_NAME     = "user_name"
        const val KEY_STYLE    = "study_style"
        const val KEY_THEME    = "theme_mood"
    }

    private val studyStyles = listOf("🔇 Silent", "🎵 Music", "🍅 Pomodoro", "🧠 Deep Work")
    private val themeMoods  = listOf("🌿 Calm", "☀️ Bright", "🌙 Dark")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        // Bind views
        etName           = findViewById(R.id.etName)
        spinnerStudyStyle = findViewById(R.id.spinnerStudyStyle)
        spinnerTheme     = findViewById(R.id.spinnerTheme)

        // Set up spinners
        setupSpinner(spinnerStudyStyle, studyStyles)
        setupSpinner(spinnerTheme, themeMoods)

        // Load previously saved preferences
        loadPreferences()

        // Save preferences on button tap
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnSave)
            .setOnClickListener { savePreferences() }

        // Back to Home
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnBack)
            .setOnClickListener { finish() }
    }

    /**
     * Helper to attach an ArrayAdapter to a Spinner.
     */
    private fun setupSpinner(spinner: Spinner, items: List<String>) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        spinner.adapter = adapter
    }

    /**
     * Loads saved SharedPreferences values into the UI fields.
     */
    private fun loadPreferences() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Restore saved name
        etName.setText(prefs.getString(KEY_NAME, ""))

        // Restore saved spinner positions
        spinnerStudyStyle.setSelection(prefs.getInt(KEY_STYLE, 0))
        spinnerTheme.setSelection(prefs.getInt(KEY_THEME, 0))
    }

    /**
     * Saves current UI values to SharedPreferences and confirms to the user.
     */
    private fun savePreferences() {
        val name  = etName.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name first!", Toast.LENGTH_SHORT).show()
            return
        }

        // Save to SharedPreferences
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_NAME,  name)
            .putInt(KEY_STYLE,    spinnerStudyStyle.selectedItemPosition)
            .putInt(KEY_THEME,    spinnerTheme.selectedItemPosition)
            .apply()

        // Confirm to user
        val styleName = studyStyles[spinnerStudyStyle.selectedItemPosition]
        Toast.makeText(
            this,
            "✅ Saved! Welcome, $name. Style: $styleName",
            Toast.LENGTH_LONG
        ).show()
    }
}