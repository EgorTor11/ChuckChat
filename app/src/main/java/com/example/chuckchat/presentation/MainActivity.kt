package com.example.chuckchat.presentation

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuckchat.R
import com.example.chuckchat.data.repository.UserRepositoryImpl
import com.example.chuckchat.databinding.ActivityMainBinding
import com.example.chuckchat.domaun.domain.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import okhttp3.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val mViewModel: MainViewModel by viewModel<MainViewModel>()
    val repositoryImpl = UserRepositoryImpl()

    //val getMessageUseCase = GetMessageUseCase(repositoryImpl)
    //val sendMessageUseCase = SendMessageUseCase(repositoryImpl)
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repositoryImpl.getMessage()
        val database = Firebase.database
        val myRef = database.getReference("message")
        auth = Firebase.auth

        binding.btnSend.setOnClickListener {
//            mViewModel.sendMessage(User(auth.currentUser?.displayName.toString(),
//               binding.edMessage.text.toString()))
//           binding.edMessage.setText("")
            repositoryImpl.sendMessage(User(auth.currentUser?.displayName.toString(),
                binding.edMessage.text.toString()))
            binding.edMessage.setText("")
        }
        binding.imageView.setOnClickListener {
            Thread {
                val request = Request.Builder()
                    .url("https://api.chucknorris.io/jokes/random")
                    .get().build()
                val client = OkHttpClient.Builder().build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.body().use { responseBody ->
                            var test = responseBody!!.string()
                            var txt = test.substringAfter("value")
                            var txt1 = txt.substringAfter(":\"")
                            var txt2 = txt1.substringBefore("\"}")
                            Log.d("MyLog",
                                test)
                            runOnUiThread(){
                                binding.edMessage.setText(txt2)


                            }
                        }
                    }
                })
            }.start()

        }

        //setUpActionBar()
        initRCView()
        nfdscAdapter()


    }

    private fun initRCView() = with(binding) {
        adapter = RVAdapter()
        val layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.layoutManager = layoutManager
        rcView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == com.google.firebase.database.R.id.sign_out) {
//            auth.signOut()
//            finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }


    private fun setUpActionBar() {
        val ab = supportActionBar
        Thread {
            val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
            val dIcon = BitmapDrawable(resources, bMap)
            runOnUiThread {
                ab?.setDisplayHomeAsUpEnabled(true)
                ab?.setHomeAsUpIndicator(dIcon)
                ab?.title = auth.currentUser?.displayName
            }

        }.start()

    }

    fun nfdscAdapter() {
        repositoryImpl.lvData.observe(this, {
            adapter.submitList(repositoryImpl.listUser)
            adapter.notifyDataSetChanged()
            binding.rcView.smoothScrollToPosition(adapter.itemCount - 1)
        })

    }

}