package com.example.skinlesionscreeningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChatbotActivity extends AppCompatActivity {

    EditText etMessage;
    Button btnSend;
    TextView tvChatLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        tvChatLog = findViewById(R.id.tvChatLog);

        // Initial Welcome Message
        tvChatLog.setText("Bot: Hello! Ask me anything about skincare, SPF, or skin lesions.\n\n");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMsg = etMessage.getText().toString().trim();
                if (!userMsg.isEmpty()) {
                    // Show user message in chat log
                    tvChatLog.append("You: " + userMsg + "\n");

                    // Get correct response based on user question
                    String botResponse = getBotResponse(userMsg);
                    tvChatLog.append("Bot: " + botResponse + "\n\n");

                    // Clear input box
                    etMessage.setText("");
                }
            }
        });
    }

    private String getBotResponse(String message) {
        String msgLower = message.toLowerCase();

        if (msgLower.contains("skincare") || msgLower.contains("skin care") || msgLower.contains("routine")) {
            return "A good skincare routine includes cleansing, moisturizing, and applying sunscreen daily to protect and maintain healthy skin.";
        }
        else if (msgLower.contains("spf") || msgLower.contains("sunscreen")) {
            return "SPF protects your skin from harmful UV rays and sunburn. It is recommended to use at least SPF 30 every day.";
        }
        else if (msgLower.contains("acne") || msgLower.contains("pimple")) {
            return "Acne occurs when hair follicles become clogged with oil and dead skin cells. Keeping your face clean and using gentle cleansers helps manage it.";
        }
        else if (msgLower.contains("dry skin")) {
            return "For dry skin, use hydrating moisturizers containing ingredients like hyaluronic acid to keep your skin soft and nourished.";
        }
        else if (msgLower.contains("lesion") || msgLower.contains("mole") || msgLower.contains("spot")) {
            return "Skin lesions can be safe or harmful. For an accurate check, please use our AI Skin Lesion Scanner feature!";
        }
        else if (msgLower.contains("hello") || msgLower.contains("hi")) {
            return "Hello there! How can I assist you with your skin health today?";
        }
        else {
            return "That is a great question! As your Skincare Assistant, I can help you with skincare routines, SPF, and skin tips. For lesion diagnosis, please use our scanner feature.";
        }
    }
}