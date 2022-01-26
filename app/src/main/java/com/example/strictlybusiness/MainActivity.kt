package com.example.strictlybusiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var editItem: EditText
    lateinit var editItemCost: EditText
    lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById<Button>(R.id.addButton);
        editItem = findViewById<EditText>(R.id.editItem);
        editItemCost = findViewById<EditText>(R.id.editItemCost);

         addButton.setOnClickListener {
            saveItem();

        }
    }

    private fun saveItem() {
        val item = editItem.text.toString().trim();
        val itemCost = editItemCost.text.toString().trim();

        val ref = FirebaseDatabase.getInstance().getReference("items")
        val itemId = ref.push().key

        val items = Item(itemId, item, itemCost)
        ref.child(itemId.toString()).setValue(items).addOnCanceledListener {
            Toast.makeText(applicationContext, "Item added successfully", Toast.LENGTH_LONG).show()
        }
    }
}