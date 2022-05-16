package com.example.chuckchat.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.chuckchat.domain.repository.UserRepositoryInterface
import com.example.chuckchat.domaun.domain.models.User
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepositoryImpl : UserRepositoryInterface {
    val lvData = MutableLiveData<User>()
    val listUser = ArrayList<User>()
    val database = Firebase.database
    val myRef = database.getReference("message")
    override fun sendMessage(user: User) {
        myRef.push().setValue(user)
    }

    override fun getMessage() {

        myRef.addChildEventListener(object : ChildEventListener {


            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                snapshot.getValue(User::class.java)?.let { listUser.add(it) }

                lvData.value=snapshot.getValue(User::class.java)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


    }

}