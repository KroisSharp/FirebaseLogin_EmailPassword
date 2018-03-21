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

public class LoginAcitivty extends AppCompatActivity implements View.OnClickListener {


    private Button buttonsignin;
    private EditText edit_username_login, edit_password_login;
    private TextView TextviewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivty);


        edit_password_login = findViewById(R.id.edit_password_login);
        edit_username_login = findViewById(R.id.edit_username_login);
        buttonsignin = findViewById(R.id.botton_signin);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = firebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileAcitivty.class));
        }

        buttonsignin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == buttonsignin) {
            userlogin();
        }

    }

    private void userlogin() {
        String useremail = edit_username_login.getText().toString().trim();
        String userpassword = edit_password_login.getText().toString().trim();

        if (TextUtils.isEmpty(useremail)) {
            //email is empty
            Toast.makeText(this, "email empty", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(userpassword)) {
            //password is empty
            Toast.makeText(this, "password empty", Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("please wait");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileAcitivty.class));
                }
            }
        });

    }
}
