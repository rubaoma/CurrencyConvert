package com.rubdev.currencyconverter

import ViewHolder
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.PointerIcon
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.rubdev.currencyconverter.R.id.button_calculate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.textView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mViewHolder: ViewHolder = ViewHolder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewHolder.editValue = findViewById(R.id.edit_value)
        mViewHolder.textDolar = findViewById(R.id.text_valorDolar)
        mViewHolder.textEuro = findViewById(R.id.text_valorEuro)
        //mViewHolder.buttonCalculate = findViewById(R.id.button_calculate)

        var btnCalcular = findViewById<Button>(button_calculate)
        btnCalcular.setOnClickListener(this)


        clearValues()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_calculate) {
            val value = mViewHolder.editValue!!.text.toString()
            if ("" == value) {
                Toast.makeText(this, R.string.informe_valor, Toast.LENGTH_SHORT).show()
            } else {
                val real = java.lang.Double.valueOf(value)
                mViewHolder.textDolar!!.text = String.format("%.2f", real / 5)
                mViewHolder.textEuro!!.text = String.format("%.2f", real / 5.7)
            }
        }
    }

    fun getData() {
        val retrofitClient = NetworkUtils
                                .getRetrofitInstance("https://economia.awesomeapi.com.br/json")

        val endpoint = retrofitClient.create(EndPoint::class.java)
        val callback = endpoint.getDolar()

        callback.enqueue(object : Callback<List<Cotacoes>>, retrofit2.Callback<List<Cotacoes>> {
            override fun onFailure(call: Call<List<Cotacoes>>, t: Throwable){
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Cotacoes>>, response: Response<List<Cotacoes>>){
                response.body()?.forEach {
                  //  textView.text

                }
                }
            }
        }

    })


    private fun clearValues() {
        mViewHolder.textDolar!!.text = ""
        mViewHolder.textEuro!!.text = ""
    }


    private class ViewHolder {
        var editValue: EditText? = null
        var textDolar: TextView? = null
        var textEuro: TextView? = null
        var buttonCalculate: Button? = null
    }

}





