package com.example.vibedecoder

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity - The single activity for Vibe Decoder.
 *
 * This app acts as a creative "calculator" that takes emotional (vibe)
 * and situational (context) inputs, and generates a personalized insight
 * message for the user — no numbers involved!
 */
class MainActivity : AppCompatActivity() {

    // ── UI References ──
    private lateinit var spinnerVibe: Spinner
    private lateinit var spinnerContext: Spinner
    private lateinit var btnDecode: Button
    private lateinit var btnHowItWorks: Button
    private lateinit var tvResult: TextView
    private lateinit var resultCard: View

    // ── Data: Vibe & Context options ──
    private val vibeOptions = listOf(
        "🔥 Motivated",
        "😔 Low",
        "😤 Stressed",
        "🤔 Confused"
    )

    private val contextOptions = listOf(
        "🌅 Morning",
        "💼 Work",
        "🌙 Night",
        "📚 Study Time"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind all views
        bindViews()

        // Set up the two spinners with their options
        setupSpinners()

        // Handle "Decode My Vibe" button tap
        btnDecode.setOnClickListener {
            decodeVibe()
        }

        // Handle "How it works" button tap — shows the modal dialog
        btnHowItWorks.setOnClickListener {
            showHowItWorksModal()
        }
    }

    /**
     * Binds all UI components from the layout to their Kotlin variables.
     */
    private fun bindViews() {
        spinnerVibe      = findViewById(R.id.spinnerVibe)
        spinnerContext   = findViewById(R.id.spinnerContext)
        btnDecode        = findViewById(R.id.btnDecode)
        btnHowItWorks    = findViewById(R.id.btnHowItWorks)
        tvResult         = findViewById(R.id.tvResult)
        resultCard       = findViewById(R.id.resultCard)
    }

    /**
     * Populates both Spinners using ArrayAdapters.
     */
    private fun setupSpinners() {
        val vibeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            vibeOptions
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        val contextAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            contextOptions
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        spinnerVibe.adapter    = vibeAdapter
        spinnerContext.adapter = contextAdapter
    }

    /**
     * Reads the selected vibe and context, generates a message,
     * and displays it in the result card.
     */
    private fun decodeVibe() {
        // Get selected positions (0-indexed)
        val vibeIndex    = spinnerVibe.selectedItemPosition
        val contextIndex = spinnerContext.selectedItemPosition

        // Generate the message based on the combination
        val message = generateMessage(vibeIndex, contextIndex)

        // Show the result card and populate it
        tvResult.text  = message
        resultCard.visibility = View.VISIBLE
    }

    /**
     * Returns a personalized message based on vibe + context combination.
     *
     * Vibe indices:    0 = Motivated | 1 = Low | 2 = Stressed | 3 = Confused
     * Context indices: 0 = Morning   | 1 = Work | 2 = Night   | 3 = Study Time
     */
    private fun generateMessage(vibe: Int, context: Int): String {
        return when {

            // ── MOTIVATED ──
            vibe == 0 && context == 0 ->
                "You woke up and chose violence — the good kind. The morning is yours before anyone else even opens their eyes. Set one bold intention and own the day."

            vibe == 0 && context == 1 ->
                "You're walking into work like the main character of a documentary about success. Channel that energy into one meaningful task — not ten average ones."

            vibe == 0 && context == 2 ->
                "Motivated at night? That's a superpower and a trap. Use 30 minutes to plan tomorrow, then actually rest — your future self needs a sharp mind, not a tired one."

            vibe == 0 && context == 3 ->
                "This is it. The zone. Put your phone face-down, brew something warm, and let the material absorb into you. Motivated + studying = results that surprise even you."

            // ── LOW ──
            vibe == 1 && context == 0 ->
                "Mornings like this are heavy. You don't need to conquer anything today — just show up. Make your bed, drink water, and let the day unfold gently."

            vibe == 1 && context == 1 ->
                "Feeling low at work is more common than anyone admits. Give yourself permission to do just enough to get through. One task. That's enough for today."

            vibe == 1 && context == 2 ->
                "Night and low energy are old friends. Don't fight it. Make something warm, let yourself be still, and remember — rest is not laziness. It's maintenance."

            vibe == 1 && context == 3 ->
                "Studying when you're low feels like swimming in honey. Try the 5-minute rule: open the book for just 5 minutes. Often that's all it takes to ease in."

            // ── STRESSED ──
            vibe == 2 && context == 0 ->
                "The stress arrived before breakfast. Take three deep breaths before you check your phone again. The chaos can wait 60 seconds — your nervous system cannot."

            vibe == 2 && context == 1 ->
                "Work stress has a way of multiplying. Write down every task on paper — all of it. Looking at it outside your head makes it shrink. Then tackle one thing."

            vibe == 2 && context == 2 ->
                "Stressed at night means your brain won't switch off. Try a 'brain dump' — write everything worrying you, then close the notebook. Your thoughts are noted. Rest now."

            vibe == 2 && context == 3 ->
                "Study stress is real, but panic is not a study strategy. Step back for 10 minutes. Walk, stretch, breathe. You'll return clearer than you left."

            // ── CONFUSED ──
            vibe == 3 && context == 0 ->
                "Waking up confused is your mind asking for direction. Before you reach for your phone, ask yourself: what's one thing I actually want from today? Start there."

            vibe == 3 && context == 1 ->
                "Confusion at work often means you're at a crossroads, not a dead end. Write down what you know vs. what you don't. Clarity lives in the gap between those two lists."

            vibe == 3 && context == 2 ->
                "Night confusion is your mind asking bigger questions than it has answers for. Let it. Not everything resolves tonight. Sleep is also a form of processing."

            vibe == 3 && context == 3 ->
                "Confused during study? That's not a bad sign — it means you're at the edge of what you know. That's exactly where learning happens. Ask a question. Look it up. Sit with it."

            // Fallback
            else -> "The vibe is unclear, and that's okay too. Take a breath, make a choice, and keep moving."
        }
    }

    /**
     * Displays an AlertDialog modal explaining how the app works.
     * Triggered by the "How it works" button.
     */
    private fun showHowItWorksModal() {
        val message = """
            🌟 What is Vibe Decoder?
            
            Vibe Decoder is a creative insight tool that reads your current emotional state and situation, then delivers a personalized message to guide your mindset.
            
            📖 How to use it:
            1. Pick your current Vibe — how are you feeling right now?
            2. Pick your Context — what's your situation or time of day?
            3. Tap "Decode My Vibe" to receive your insight.
            
            💬 What kind of output can you expect?
            
            Each combination of vibe and context produces a unique, human-written message designed to feel real and relevant — not generic. Think of it as a thoughtful nudge from someone who gets it.
            
            There are 16 unique combinations to explore. Try them all!
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("✨ How Vibe Decoder Works")
            .setMessage(message)
            .setPositiveButton("Got it! ✓") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}