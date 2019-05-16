import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.quintessentiel.BaseActivity;
import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.R;

public class AddOrder extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_order);
        super.onCreateDrawer(true);



    }
}