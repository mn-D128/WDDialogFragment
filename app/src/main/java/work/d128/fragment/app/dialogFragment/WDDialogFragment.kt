package work.d128.fragment.app.dialogFragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

@Suppress("unused")
open class WDDialogFragment: DialogFragment() {

    private var resultCode: Int = RESULT_CANCELED
    private var resultData: Intent? = null

    companion object {

        const val RESULT_CANCELED: Int = 0
        const val RESULT_OK = -1

        private const val SAVE_KEY_RESULT_CODE = "work.d128.fragment.app.DialogFragment.WDDialogFragment.SAVE_KEY_RESULT_CODE"
        private const val SAVE_KEY_RESULT_DATA = "work.d128.fragment.app.DialogFragment.WDDialogFragment.SAVE_KEY_RESULT_DATA"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            resultCode = it.getInt(SAVE_KEY_RESULT_CODE, RESULT_CANCELED)
            resultData = it.getParcelable(SAVE_KEY_RESULT_DATA)
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)

        tag?.let { tag ->
            resultListener?.onFragmentResult(tag, resultCode, resultData)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SAVE_KEY_RESULT_CODE, resultCode)
        outState.putParcelable(SAVE_KEY_RESULT_DATA, resultData)
    }

    protected fun setResult(code: Int, data: Intent? = null) {
        resultCode = code
        resultData = data
    }

    private val resultListener: OnResultListener?
        get() {
            (parentFragment as? OnResultListener)?.let {
                return it
            }

            (activity as? OnResultListener)?.let {
                return it
            }

            return null
        }

    interface OnResultListener {

        fun onFragmentResult(requestTag: String, resultCode: Int, data: Intent?)

    }

}
