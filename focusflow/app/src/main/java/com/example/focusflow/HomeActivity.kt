package com.example.focusflow

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Navigate to Routine screen
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnGoToRoutine)
            .setOnClickListener {
                startActivity(Intent(this, RoutineActivity::class.java))
            }

        // Navigate to Preferences screen
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnGoToPreferences)
            .setOnClickListener {
                startActivity(Intent(this, PreferencesActivity::class.java))
            }

        // Show How It Works modal
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnHowItWorks)
            .setOnClickListener {
                showHowItWorksModal()
            }
    }

    private fun showHowItWorksModal() {
        val message = """
            🎯 What is Focus Flow?
            
            Focus Flow is your personal study companion. It helps you build a focus routine that matches your energy, goals, and available time — so every study session feels intentional.
            
            📖 How to use it:
            1. Tap "Plan My Routine" to generate a personalised study plan.
            2. Choose your focus goal, energy level, and available time.
            3. Tap "Generate My Routine" to get your plan instantly.
            
            ⚙️ How preferences help:
            
            Visit "My Preferences" to save your name, preferred study style (e.g. Pomodoro, Deep Work), and mood theme. The more you personalise, the better your experience becomes.
            
            💡 Tip: Save your preferences first for the best experience!
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("✨ How Focus Flow Works")
            .setMessage(message)
            .setPositiveButton("Got it! ✓") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}