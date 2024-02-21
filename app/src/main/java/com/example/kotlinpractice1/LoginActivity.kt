package com.example.kotlinpractice1

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
//import kotlinx.android.synthetic.main.activity_login.drawer
//import kotlinx.android.synthetic.main.activity_login.fab
//import kotlinx.android.synthetic.main.activity_login.navView

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
/*        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }*/
        // 设置默认选中
/*        navView.setCheckedItem(R.id.navCall)
        // 菜单项选中事件监听
        navView.setNavigationItemSelectedListener {
            drawer.closeDrawers()
            true
        }*/
/*        fab.setOnClickListener {
            Snackbar.make(it, "点击啦", Snackbar.LENGTH_SHORT)
                .setAction("Null"){
                    Toast.makeText(this, "弹出toast啦", Toast.LENGTH_SHORT).show()
                }
                .show()
        }*/
    }
}