package com.example.hardmad2024_1.presentation.welcome_screen.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.hardmad2024_1.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val context: Context,
) {
    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.create(context)

    suspend fun signIn() : Pair<String, String?>? {
        return try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.server_client_id))
                .setAutoSelectEnabled(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val response = credentialManager.getCredential(context, request)

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(response.credential.data)
            val idToken = googleIdTokenCredential.idToken

            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(firebaseCredential).await()
            val firebaseUser = authResult.user

            return firebaseUser?.let {
                it.uid to it.displayName
            }
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }

    fun getSignedInUser(): String? {
        val firebaseUser = auth.currentUser
        return firebaseUser?.uid
    }

    fun signOut(){
        auth.signOut()
    }
}