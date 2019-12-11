package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialEditText email,pass;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findingView
        email=findViewById(R.id.email_id);
        pass=findViewById(R.id.password_id);
        login=findViewById(R.id.login_id);

        //onClick
        login.setOnClickListener(this);

        //firebase
        mAuth=FirebaseAuth.getInstance();


        //progressDialogue
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging In...");




    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(MainActivity.this,Dashboard.class));
            finish();

        }
    }
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.login_id)
        {
            adminLogin();


        }

    }

    private void adminLogin() {
        final String Email=email.getText().toString().trim();
        String Pass=pass.getText().toString();

        //checking

        if(Email.isEmpty())
        {
            email.setError("Email Required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            email.setError("Invalid Email");
            email.requestFocus();
            return;
        }
        if(Pass.isEmpty())
        {
            pass.setError("Incorrect Password");
            pass.requestFocus();
            return;
        }

        progressDialog.show();



        //firebaselogin

        mAuth.signInWithEmailAndPassword(Email,Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.cancel();

                        if(task.isSuccessful())
                        {

                            db.collection("Admins").get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            for(QueryDocumentSnapshot document : task.getResult())
                                            {
                                                String email=document.getString("email");

                                                if(email.equals(Email))
                                                {
                                                    Toast.makeText(getApplicationContext(),"Login succesfull",Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(MainActivity.this,Dashboard.class));
                                                    finish();
                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(),"You are not an admin!",Toast.LENGTH_LONG).show();

                                                }
                                            }

                                        }
                                    });


                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}
