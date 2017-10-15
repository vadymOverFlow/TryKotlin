package com.nikit.android.geeghub.meetupkotlin.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

import com.nikit.android.geeghub.meetupkotlin.R

class LoginActivity : AppCompatActivity() {


    private var mCallBackManager: CallbackManager? = null
    private var mAuth: FirebaseAuth? = null

    private var TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin)

        var tvCreateAccount = findViewById(R.id.login_create_account) as TextView

        tvCreateAccount.setOnClickListener {
            startActivity(
                    Intent(this, CreateAccountActivity::class.java))
        }

        val btFacebook = findViewById(R.id.login_bt_facebook)
        btFacebook.setOnClickListener { view ->
            Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show()
            LoginManager.getInstance().logInWithReadPermissions(this,
                    listOf("public_profile", "email"))
        }

        mAuth = FirebaseAuth.getInstance()

        mCallBackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(
                mCallBackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        Toast.makeText(this@LoginActivity, "onSuccess", Toast.LENGTH_SHORT).show()

                        handleFacebookAccessToken(result!!.accessToken)
                    }

                    override fun onCancel() {
                    }

                    override fun onError(error: FacebookException?) {
                    }
                }
        )

    }

    override fun onStart() {
        super.onStart()
        var currentUser = mAuth?.currentUser;
        //TODO: update ui
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        mCallBackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        var credential = FacebookAuthProvider.getCredential(token.token)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        Toast.makeText(this@LoginActivity, "onComplete", Toast.LENGTH_SHORT).show()

                        if (task.isSuccessful) {
                            Log.d(TAG, "facebook success")
                            var user = mAuth?.currentUser
                            //TODO: update ui
                            Toast.makeText(this@LoginActivity, user?.displayName, Toast.LENGTH_SHORT).show()

                        } else {
                            Log.d(TAG, "facebook failed")
                            Toast.makeText(this@LoginActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                })


    }

}
