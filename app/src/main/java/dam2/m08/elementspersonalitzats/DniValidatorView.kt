package dam2.m08.elementspersonalitzats

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.regex.Pattern

/**
 * Created by aristidesguimeraorozco on 30/9/18.
 */
class DniValidatorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs),
    TextWatcher {


    var successColor: Int
    var errorColor: Int

    lateinit var tvErrorCode:TextView
    lateinit var etDni:EditText

    init {
        inflate(context, R.layout.dni_validator, this)


        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DniValidator)

        tvErrorCode = findViewById<TextView>(R.id.tvErrorCode)

        tvErrorCode.text = attributes.getString(R.styleable.DniValidator_textError)
        errorColor = attributes.getColor(R.styleable.DniValidator_underlineErrorColor, ContextCompat.getColor(context, R.color.red))
        successColor = attributes.getColor(R.styleable.DniValidator_underlineSuccessColor, ContextCompat.getColor(context, R.color.green))
        attributes.recycle()

        etDni = findViewById<EditText>(R.id.etDni)
        etDni.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val pattern = Pattern.compile("\\d{8}[A-z]$")
        val matcher = pattern.matcher(s.toString())
        val valid = matcher.matches()
        if (valid) {
            tvErrorCode.visibility = View.INVISIBLE
            etDni.background.setTint(ContextCompat.getColor(context, R.color.green))
        } else {
            tvErrorCode.visibility = View.VISIBLE
            etDni.background.setTint(ContextCompat.getColor(context, R.color.red))
        }
    }
}