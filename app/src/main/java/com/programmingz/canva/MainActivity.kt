package com.programmingz.canva

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.programmingz.canva.databinding.ActivityMainBinding
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var originalText: String = ""
    private var undoState: Boolean = false
    private var defaultColor: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        defaultColor = ContextCompat.getColor(this, R.color.white)

        binding.btnColor.setOnClickListener {
            openColorPicker();
        }

        changeFontFamily()
        changeFontSize()

        binding.btnOk.setOnClickListener {
            btnSetText()
        }

        binding.btnUndo.setOnClickListener {
            binding.txtChangeFont.text = originalText
        }

        binding.txtChangeFont.setOnClickListener {
            val i: Int = 1
            if (i == KeyEvent.KEYCODE_DEL) {
                if (undoState == false) {
                    originalText = binding.txtChangeFont.text.toString().trim()
                    undoState = true
                }
            }
        }


    }

    private fun btnSetText() {
        val userText = binding.etText.text.toString()
        binding.txtChangeFont.setText(userText)

    }

    private fun openColorPicker() {
        val colorPicker = AmbilWarnaDialog(this, defaultColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {

                // User canceled the dialog
            }
            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {

                defaultColor = color
                // Change the background color of the FrameLayout
                binding.txtChangeFont.setTextColor(defaultColor)
            }
        })
        colorPicker.show()

    }

    private fun changeFontSize() {
        val fontSize: Array<String> = arrayOf("18", "10", "12", "15", "20", "22", "25", "30", "50")
        val arrayAdapterSize: ArrayAdapter<String> =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, fontSize)
        binding.spinnerSize.adapter = arrayAdapterSize


        binding.spinnerSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    binding.txtChangeFont.setTextSize(18F)
                } else if (p2 == 1) {
                    binding.txtChangeFont.setTextSize(10F)
                } else if (p2 == 2) {
                    binding.txtChangeFont.setTextSize(12F)
                } else if (p2 == 3) {
                    binding.txtChangeFont.setTextSize(15F)
                } else if (p2 == 4) {
                    binding.txtChangeFont.setTextSize(20F)
                } else if (p2 == 5) {
                    binding.txtChangeFont.setTextSize(22F)
                } else if (p2 == 6) {
                    binding.txtChangeFont.setTextSize(25F)
                } else if (p2 == 7) {
                    binding.txtChangeFont.setTextSize(30F)
                } else {
                    binding.txtChangeFont.setTextSize(50F)
                }

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }


    private fun changeFontFamily() {
        val fontList: Array<String> = arrayOf(
            "Select Font Family",
            "aguafina script",
            "alex brush",
            "alfa slab",
            "amita",
            "archivo black",
            "bangers"
        )

        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, fontList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    binding.txtChangeFont.setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.artifika
                        )
                    )
                } else if (position == 2) {
                    binding.txtChangeFont.setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.alex_brush
                        )
                    )
                } else if (position == 3) {
                    binding.txtChangeFont.setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.alfa_slab_one
                        )
                    )
                } else if (position == 4) {
                    binding.txtChangeFont.setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.amiri
                        )
                    )
                } else if (position == 5) {
                    binding.txtChangeFont.setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.archivo_black
                        )
                    )
                } else {
                    binding.txtChangeFont.setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.bangers
                        )
                    )
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}