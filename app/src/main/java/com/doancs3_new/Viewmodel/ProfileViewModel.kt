package com.doancs3_new.Viewmodel

import androidx.lifecycle.ViewModel
import com.doancs3_new.Data.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _userData = MutableStateFlow(User())
    val userData: MutableStateFlow<User> = _userData

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        val uid = firebaseAuth.currentUser?.uid ?: return
        firestore.collection("users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                document?.let {
                    _userData.value = User(
                        firstName = it.getString("firstName"),
                        lastName = it.getString("lastName"),
                        nickname = it.getString("nickname"),
                        currentWeight = it.getDouble("currentWeight")?.toInt(),
                        targetWeight = it.getDouble("targetWeight")?.toInt(),
                        currentHeight = it.getDouble("currentHeight")?.toInt(),
                        currentBMI = it.getDouble("currentBMI"),
                        targetBMI = it.getDouble("targetBMI"),
                        aim = it.getString("aim"),
                        email = it.getString("email")
                    )
                }
            }
    }
}
