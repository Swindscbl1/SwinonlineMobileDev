package au.edu.swin.billlo.scoreappp1
// MainActivity.kt Bill Lo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.media.MediaPlayer // Import the MediaPlayer class, Sound from Google's royalty free sound library 2022
import android.util.Log //logging

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonReset: Button
    private lateinit var textViewScore: TextView
    private var scoreVal = 0
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSub = findViewById(R.id.buttonSub)
        buttonReset = findViewById(R.id.buttonSave)
        textViewScore = findViewById(R.id.textViewScore)

        // Get the score from the saved instance
        if (savedInstanceState != null) {
            scoreVal = savedInstanceState.getInt("score")
            textViewScore.text = getString(R.string.score, scoreVal)// this was working before.
            Log.i("ScoreApp1", "Loaded Save")
        }
        else
        {
            Log.i("ScoreApp1", "Failed to load save")
        }

        textViewScore.text = getString(R.string.score, scoreVal)    // Display the score

        // Create a media player object and set to play
        mediaPlayer = MediaPlayer.create(this, R.raw.bugle_tune)

        // Set the listeners for the buttons
        buttonReset.setOnClickListener {
            scoreVal = 0
            textViewScore.text = getString(R.string.score, scoreVal)
            Log.i("ScoreApp1", "Reset")
        }

        buttonAdd.setOnClickListener {
            // Add to score by 1
            scoreVal++
            Log.i("ScoreApp1", "Red score increment")
            // Update the score on the text view
            textViewScore.text = getString(R.string.score, scoreVal)
            // Check if the score is 15
            if (scoreVal == 15) {
                // Play the sound
                mediaPlayer.start()
                Log.i("ScoreApp1", "Red Reached 15, played sound")
            }
            if(scoreVal == 16)
            {
                scoreVal = 0
                textViewScore.text = getString(R.string.score, scoreVal)
                Log.i("ScoreApp1", "Did reset all scores")
            }
        }

        buttonSub.setOnClickListener {
            // Check if the score is 0
            if (scoreVal > 0) {
                // Play the sound
                scoreVal--
                Log.i("ScoreApp1", "score dec")
                // Display the score on the text view
                textViewScore.text = getString(R.string.score, scoreVal)
                Log.i("ScoreApp1", "Blue Reached 15, played sound")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the score in the bundle with the key "score"
        outState.putInt("score", scoreVal)
        Log.i("ScoreApp1", "Save enacted")
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}