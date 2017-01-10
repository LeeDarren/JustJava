/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.duration;
import static android.R.attr.name;
import static android.R.attr.y;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price;

        CheckBox hasWhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = hasWhippedCreamCheckBox.isChecked();

        CheckBox hasChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = hasChocolateCheckBox.isChecked();

        price = calculatePrice(hasWhippedCream, hasChocolate);

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, name));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int displayNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + displayNumber);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method currently sets the value of quantity to 3 through the display method
     *
     * @param view
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast toast = Toast.makeText(this, "You can't have more than 100 Coffees", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method currently sets the value of quantity to 1 through the display method
     *
     * @param view
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(this, "You must have at least 1 Coffee", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     * <p>
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int pricePerCup = 5;
        if (hasWhippedCream)
            pricePerCup += 1;
        if (hasChocolate)
            pricePerCup += 2;
        return quantity * pricePerCup;
    }

    /**
     * Create summary of the order
     *
     * @param total           The total price
     * @param hasWhippedCream Yes if whipped cream is checked
     * @param hasChocolate    Yes if chocolate is checked
     * @return String with order summary
     */
    private String createOrderSummary(int total, boolean hasWhippedCream, boolean hasChocolate,
                                      String name) {
        String summary = "Name: " + name;
        summary += "\nHas whipped cream? " + hasWhippedCream;
        summary += "\nHas chocolate? " + hasChocolate;
        summary += "\nQuantity: " + quantity;
        summary += "\nTotal: $" + total;
        summary += "\nThank You!";
        return summary;
    }
}