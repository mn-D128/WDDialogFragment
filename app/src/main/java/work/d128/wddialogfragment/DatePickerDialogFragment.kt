package work.d128.wddialogfragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import work.d128.fragment.app.dialogFragment.WDDialogFragment
import java.util.GregorianCalendar
import java.util.Calendar

class DatePickerDialogFragment: WDDialogFragment() {

    companion object {

        private const val RESULT_DATA = "work.d128.dialogfragmentsample.DatePickerDialogFragment.RESULT_DATA"

        private const val SAVE_KEY_YEAR = "work.d128.dialogfragmentsample.DatePickerDialogFragment.SAVE_KEY_YEAR"
        private const val SAVE_KEY_MONTH = "work.d128.dialogfragmentsample.DatePickerDialogFragment.SAVE_KEY_MONTH"
        private const val SAVE_KEY_DAY_OF_MONTH = "work.d128.dialogfragmentsample.DatePickerDialogFragment.SAVE_KEY_DAY_OF_MONTH"

        fun newInstance(): DatePickerDialogFragment {
            return DatePickerDialogFragment()
        }

        fun resultFromIntent(intent: Intent): YMDEntity {
            return intent.getParcelableExtra(RESULT_DATA)
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        context?.let { context ->
            val calendar = GregorianCalendar.getInstance()

            savedInstanceState?.let {
                val year = it.getInt(SAVE_KEY_YEAR, calendar[Calendar.YEAR])
                val month = it.getInt(SAVE_KEY_MONTH, calendar[Calendar.MONTH])
                val dayOfMonth = it.getInt(SAVE_KEY_DAY_OF_MONTH, calendar[Calendar.DAY_OF_MONTH])

                return DatePickerDialog(context, dateSetListener, year, month, dayOfMonth)
            }

            return DatePickerDialog(context, dateSetListener, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
        }

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        (dialog as? DatePickerDialog)?.datePicker?.let {
            outState.putInt(SAVE_KEY_YEAR, it.year)
            outState.putInt(SAVE_KEY_MONTH, it.month)
            outState.putInt(SAVE_KEY_DAY_OF_MONTH, it.dayOfMonth)
        }
    }

    private val dateSetListener = { view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        val intent = Intent()
        intent.putExtra(RESULT_DATA, YMDEntity(year, month, dayOfMonth))
        setResult(RESULT_OK, intent)
    }

}