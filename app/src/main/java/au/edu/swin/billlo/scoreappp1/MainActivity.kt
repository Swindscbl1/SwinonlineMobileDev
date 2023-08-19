package au.edu.swin.billlo.scoreappp1
// MainActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.media.MediaPlayer // Import the MediaPlayer class

class MainActivity : AppCompatActivity() {

    private lateinit var buttonRed: Button
    private lateinit var buttonReset: Button
    private lateinit var buttonBlue: Button
    private lateinit var textViewScoreR: TextView
    private lateinit var textViewScoreB: TextView
    private var score_r = 0
    private var score_b = 0
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonRed = findViewById(R.id.buttonRed)
        buttonBlue = findViewById(R.id.buttonBlue)
        buttonReset = findViewById(R.id.buttonReset)
        textViewScoreR = findViewById(R.id.textViewScoreR)
        textViewScoreB = findViewById(R.id.textViewScoreB)

        // Get the score from the saved instance
        if (savedInstanceState != null) {
            score_r = savedInstanceState.getInt("score_r")
            score_b = savedInstanceState.getInt("score_b")
        }

        textViewScoreR.text = getString(R.string.score_r, score_r)    // Display the score
        textViewScoreB.text = getString(R.string.score_b, score_b)    // Display the score

        // Create a media player object
        mediaPlayer = MediaPlayer.create(this, R.raw.beep) // and set to play

        // Set the listeners for the buttons
        buttonReset.setOnClickListener {
            score_r = 0
            score_b = 0
            textViewScoreR.text = getString(R.string.score_r, score_r)
            textViewScoreB.text = getString(R.string.score_b, score_b)
        }

        buttonRed.setOnClickListener {
            // Deduct the score by 1
            score_r++
            // Display the score on the text view
            textViewScoreR.text = getString(R.string.score_r, score_r)
            // Check if the score is 15
            if (score_r == 15) {
                // Play the sound
                mediaPlayer.start()
            }
        }

        buttonBlue.setOnClickListener {
            // Add the score by 1
            score_b++
            // Display the score on the text view
            textViewScoreB.text = getString(R.string.score_b, score_b)
            // Check if the score is 15
            if (score_b == 15) {
                // Play the sound
                mediaPlayer.start()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the score in the bundle with the key "score"
        outState.putInt("score_r", score_r)
        outState.putInt("score_b", score_b)
    }
}

