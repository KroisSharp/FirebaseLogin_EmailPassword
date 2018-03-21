package dk.easjvoodoo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileAcitivty extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private TextView TextViewUserEmail;
    private Button button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acitivty);
        button_logout = findViewById(R.id.button_logout);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginAcitivty.class));
        }



        button_logout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == button_logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginAcitivty.class));
        }

    }
}
