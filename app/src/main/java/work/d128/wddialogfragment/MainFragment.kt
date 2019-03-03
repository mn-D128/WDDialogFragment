package work.d128.wddialogfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import work.d128.fragment.app.dialogFragment.WDDialogFragment

class MainFragment: Fragment(), WDDialogFragment.OnResultListener {

    companion object {

        private const val TAG_DATEPICKERDIALOGFRAGMENT = "work.d128.dialogfragmentsample.MainActivity.TAG_DATEPICKERDIALOGFRAGMENT"

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.button).setOnClickListener(clickListener)
    }

    override fun onFragmentResult(requestTag: String, resultCode: Int, data: Intent?) {
        if (requestTag == MainFragment.TAG_DATEPICKERDIALOGFRAGMENT && resultCode == WDDialogFragment.RESULT_OK && data != null) {
            val entity = DatePickerDialogFragment.resultFromIntent(data)
            Log.d("", entity.year.toString())
        }
    }

    private val clickListener = { view: View ->
        val dialogFragment = DatePickerDialogFragment.newInstance()
        dialogFragment.show(childFragmentManager, MainFragment.TAG_DATEPICKERDIALOGFRAGMENT)
    }

}