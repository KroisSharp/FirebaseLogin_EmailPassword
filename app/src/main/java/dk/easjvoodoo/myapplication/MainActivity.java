package dk.easjvoodoo.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonsignup;
    private EditText editusername, editpassword;
    private TextView TextviewSignin;

    private ProgressDialog ProgressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();

        ProgressDialog  = new ProgressDialog(this);

        buttonsignup = (Button) findViewById(R.id.botton_signup);

        editusername = (EditText) findViewById(R.id.edit_username);
        editpassword = (EditText) findViewById(R.id.edit_password);

        TextviewSignin = (TextView) findViewById(R.id.textview_signin);

        buttonsignup.setOnClickListener(this);
        TextviewSignin.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(this, ProfileAcitivty.class));
        }

    }


    @Override
    public void onClick(View v) {
        if (v == buttonsignup){
            signup();
        }
        if (v == TextviewSignin){
            startActivity(new Intent(this, LoginAcitivty.class));
        }
    }

    private void signup() {
        String email = editusername.getText().toString().trim();
        String password = editpassword.getText().toString();


        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"email empty", Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"password empty", Toast.LENGTH_SHORT).show();

        }
        //if ok then:
        ProgressDialog.setMessage("sign up user");
        ProgressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    ProgressDialog.dismiss();
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileAcitivty.class));

                }else{
                    Toast.makeText(MainActivity.this,"not success", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
