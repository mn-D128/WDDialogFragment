package work.d128.wddialogfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.fragments.isEmpty()) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main_root_layout, MainFragment.newInstance())
            transaction.commit()
        }
    }

}
