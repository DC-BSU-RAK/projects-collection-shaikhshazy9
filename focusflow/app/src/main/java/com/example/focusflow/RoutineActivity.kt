package com.example.focusflow

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * RoutineActivity — lets the user select their focus goal,
 * energy level, and available time to generate a personalised routine.
 */
class RoutineActivity : AppCompatActivity() {

    private lateinit var spinnerGoal: Spinner
    private lateinit var spinnerEnergy: Spinner
    private lateinit var spinnerTime: Spinner
    private lateinit var tvRoutineResult: TextView
    private lateinit var resultCard: View

    // Spinner options
    private val goals   = listOf("📖 Study", "💻 Coding", "🔁 Revision", "📚 Reading")
    private val energy  = listOf("⚡ High", "🔋 Medium", "😴 Low")
    private val times   = listOf("⏱ 15 Minutes", "⏱ 30 Minutes", "⏱ 60 Minutes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        // Bind views
        spinnerGoal     = findViewById(R.id.spinnerGoal)
        spinnerEnergy   = findViewById(R.id.spinnerEnergy)
        spinnerTime     = findViewById(R.id.spinnerTime)
        tvRoutineResult = findViewById(R.id.tvRoutineResult)
        resultCard      = findViewById(R.id.resultCard)

        // Set up spinners
        setupSpinner(spinnerGoal, goals)
        setupSpinner(spinnerEnergy, energy)
        setupSpinner(spinnerTime, times)

        // Generate routine on button tap
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnGenerate)
            .setOnClickListener { generateRoutine() }

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
     * Reads spinner selections and generates a matching routine message.
     */
    private fun generateRoutine() {
        val goalIndex   = spinnerGoal.selectedItemPosition
        val energyIndex = spinnerEnergy.selectedItemPosition
        val timeIndex   = spinnerTime.selectedItemPosition

        val routine = buildRoutine(goalIndex, energyIndex, timeIndex)
        tvRoutineResult.text = routine
        resultCard.visibility = View.VISIBLE
    }

    /**
     * Returns a personalised routine string based on the three inputs.
     *
     * Goal:   0=Study | 1=Coding | 2=Revision | 3=Reading
     * Energy: 0=High  | 1=Medium | 2=Low
     * Time:   0=15min | 1=30min  | 2=60min
     */
    private fun buildRoutine(goal: Int, energy: Int, time: Int): String {
        return when {

            // ── STUDY ──
            goal == 0 && energy == 0 && time == 0 ->
                "🔥 Power Sprint\n\nYou're energised and pressed for time. Pick ONE topic. Spend 12 minutes reading actively — highlight, annotate, question. 3-minute review. Done."

            goal == 0 && energy == 0 && time == 1 ->
                "🚀 Deep Dive (30)\n\nHigh energy + 30 minutes = serious progress. Split into 2 x 12-min blocks with a 6-min break. Use the first block to understand, the second to summarise in your own words."

            goal == 0 && energy == 0 && time == 2 ->
                "🏆 Full Study Session\n\nYou're in the zone. 3 x 20-min Pomodoro blocks. Each block: read → note → self-test. 5-min breaks between blocks. End with a 5-min mind map of everything covered."

            goal == 0 && energy == 1 && time == 0 ->
                "📌 Quick Focus\n\nMedium energy, short time. Pick one concept. Read for 10 minutes, write 3 bullet-point takeaways. That's a win."

            goal == 0 && energy == 1 && time == 1 ->
                "📘 Steady Study Block\n\n25 minutes of focused reading followed by a 5-minute review. Write what you remember without looking — this is the most effective technique for retention."

            goal == 0 && energy == 1 && time == 2 ->
                "📗 Extended Study Flow\n\nTwo 25-min Pomodoro blocks + 10-min break in the middle. Focus on understanding before memorising. Use the last 10 minutes to review everything."

            goal == 0 && energy == 2 && time == 0 ->
                "🌙 Gentle Review\n\nLow energy is okay. Spend 15 minutes re-reading your existing notes — no pressure to absorb new material. Familiarity is also progress."

            goal == 0 && energy == 2 && time == 1 ->
                "☁️ Light Study Session\n\nRe-read a familiar topic. 20 minutes of passive reading, 10 minutes of doodling a concept map. Keep it relaxed — your brain is still absorbing."

            goal == 0 && energy == 2 && time == 2 ->
                "🛋️ Recovery Study\n\nLow energy + 60 minutes = take it slow. 3 gentle 15-min blocks, 5-min breaks. Watch a summary video, read notes, listen to a related podcast. No pressure."

            // ── CODING ──
            goal == 1 && energy == 0 && time == 0 ->
                "⚡ Code Sprint\n\nHigh energy, tight time. Pick one small feature or bug. Code it fully. No distractions, no context switching. Ship something — even small wins matter."

            goal == 1 && energy == 0 && time == 1 ->
                "💻 Build Block\n\n30 minutes of focused coding. Spend 5 minutes planning what you'll build, 20 minutes building it, and 5 minutes reviewing and commenting your code."

            goal == 1 && energy == 0 && time == 2 ->
                "🛠️ Deep Coding Session\n\nYou have time and energy — use it. Set a clear goal for the hour. Code in 25-min blocks. Break to review logic, refactor, and test. Document as you go."

            goal == 1 && energy == 1 && time == 0 ->
                "🔧 Quick Fix Mode\n\nPick a bug or a small improvement. One focused task. Write it, test it, done. Small wins build momentum."

            goal == 1 && energy == 1 && time == 1 ->
                "📐 Structured Build\n\nPlan (5 min) → Code (20 min) → Review (5 min). Stick to one problem. Avoid rabbit holes. Commit your work at the end."

            goal == 1 && energy == 1 && time == 2 ->
                "🧩 Full Feature Session\n\nTwo 25-min coding blocks with a 10-min break. First block: build. Second block: test, refactor, document. End with a mini retrospective — what worked?"

            goal == 1 && energy == 2 && time == 0 ->
                "😌 Low-Effort Code\n\nRead through existing code. Add comments. Clean up formatting. Low energy is perfect for maintenance tasks — no heavy thinking required."

            goal == 1 && energy == 2 && time == 1 ->
                "📖 Code Reading Session\n\nDon't write new code. Instead, read documentation or review a tutorial. Understanding beats producing when you're tired."

            goal == 1 && energy == 2 && time == 2 ->
                "🎧 Passive Coding\n\nWatch a coding tutorial. Follow along loosely. No pressure to build — just absorb patterns and techniques. Low-energy learning still counts."

            // ── REVISION ──
            goal == 2 && energy == 0 && time == 0 ->
                "🔁 Flash Revision\n\nHigh energy, 15 minutes. Use flashcards or write 5 key facts from memory. Test yourself — no peeking. Rapid recall is the best revision technique."

            goal == 2 && energy == 0 && time == 1 ->
                "📝 Active Recall Block\n\nClose your notes. Spend 25 minutes writing everything you remember about the topic. Then check your notes for gaps. This is the most powerful revision method."

            goal == 2 && energy == 0 && time == 2 ->
                "🏆 Full Revision Session\n\nPast papers + active recall. 20 minutes of recall, 20 minutes of past questions, 20 minutes of marking and gap analysis. This is exam-winning revision."

            goal == 2 && energy == 1 && time == 0 ->
                "📋 Key Points Review\n\nSkip to your summary notes. Read the key points for 12 minutes. Write 3 things you weren't sure about to revisit later."

            goal == 2 && energy == 1 && time == 1 ->
                "🗂️ Mind Map Revision\n\nPick a topic and build a mind map from memory. Fill in gaps from your notes. Visual revision strengthens connections your brain already made."

            goal == 2 && energy == 1 && time == 2 ->
                "📊 Structured Revision\n\nTwo topics, 25 minutes each, 10-minute break. Use Cornell notes: recall column on the left, detail on the right. Review both at the end."

            goal == 2 && energy == 2 && time == 0 ->
                "🌙 Passive Review\n\nRead through your existing summary notes slowly. No writing required. Sometimes gentle re-exposure is all your tired brain needs."

            goal == 2 && energy == 2 && time == 1 ->
                "☁️ Light Revision\n\nWatch a short summary video or read a one-page overview. Low energy revision is still revision — don't skip it."

            goal == 2 && energy == 2 && time == 2 ->
                "🛋️ Easy Revision Mode\n\nThree 15-minute blocks: read notes, watch a video, re-read notes. Gentle and effective. Rest between blocks. You're still moving forward."

            // ── READING ──
            goal == 3 && energy == 0 && time == 0 ->
                "📖 Focused Reading Sprint\n\nHigh energy and 15 minutes — a perfect combination. Read actively: annotate as you go, underline key ideas, write one question per page."

            goal == 3 && energy == 0 && time == 1 ->
                "📚 Deep Reading Block\n\n30 minutes of uninterrupted reading. No phone. Use the SQ3R method: Survey → Question → Read → Recite → Review."

            goal == 3 && energy == 0 && time == 2 ->
                "🌊 Reading Flow State\n\nYou have the energy and the time. Set a reading goal (e.g. 30 pages or 2 chapters). Take brief notes every 20 minutes. End with a written summary."

            goal == 3 && energy == 1 && time == 0 ->
                "📄 Quick Read\n\nRead one section or 5-10 pages at a comfortable pace. No pressure to finish. Just move forward."

            goal == 3 && energy == 1 && time == 1 ->
                "📘 Steady Reading\n\nSet a clear reading goal and read for 25 minutes. Jot down 3 things that stood out. Keep it relaxed but intentional."

            goal == 3 && energy == 1 && time == 2 ->
                "📗 Extended Reading Session\n\nTwo 25-minute blocks with a 10-minute break. Read in the first block, review and take notes in the second."

            goal == 3 && energy == 2 && time == 0 ->
                "🌙 Gentle Read\n\nRead something light or familiar. 15 minutes of comfortable reading — no pressure, no notes. Just the words."

            goal == 3 && energy == 2 && time == 1 ->
                "☁️ Relaxed Reading\n\nPick up where you left off. Read slowly and comfortably for 30 minutes. Low energy reading is still reading — it all counts."

            goal == 3 && energy == 2 && time == 2 ->
                "🛋️ Long Slow Read\n\nTake your time. Three 15-minute reading blocks with breaks. Read what interests you most and let your mind wander a little. Recovery counts too."

            else ->
                "✅ Focus Session\n\nSet a clear intention for your session, eliminate distractions, and work steadily. Any progress is good progress — start now."
        }
    }
}